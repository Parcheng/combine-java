package com.parch.combine.core.tools.variable;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.context.GlobalContext;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.core.handler.ConstantHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数据处理帮助类
 */
public class DataFindHandler {

    /**
     * 查找数据
     *
     * @param path 字段路径
     * @return 结果
     */
    public static Object find(String path) {
        return findData(path);
    }

    /**
     * 清理数据
     *
     * @param path 字段路径
     * @return 是否成功
     */
    public static boolean clear(String path) {
        return replaceData(path, sourceData -> null);
    }

    /**
     * 替换数据
     *
     * @param path 字段路径
     * @param newValue 新值
     * @return 是否成功
     */
    public static boolean replace(String path, Object newValue) {
        return replaceData(path, sourceData -> newValue);
    }

    /**
     * 替换数据
     *
     * @param path 字段路径
     * @param func 新值
     * @return 是否成功
     */
    public static boolean replaceAsFunc(String path, GetDataFunction<?> func) {
        return replaceData(path, func);
    }

    /**
     * 数据处理
     *
     * @param path 字段路径
     * @return 结果
     */
    private static Object findData(String path) {
        if (CheckEmptyUtil.isEmpty(path)) {
            ComponentErrorHandler.print("查找失败：参数路径为空");
            return null;
        }

        // 解析 path 路径（将 path 中的 #{...} 解析成值）
        path = parsePath(path);

        // 将参数路径解析为数组
        String[] valuePathArr = path.split("\\.");

        // 全局配置
        GlobalContext.FlagConfigs flagConfigs = GlobalContextHandler.get().getFlagConfigs();

        // 解析多级数据（数据来源可能是入参，可能是其他组件结果，也可能是常量池）
        int startIndex;
        Object currData;
        String firstKey = valuePathArr[0].toLowerCase();
        if (firstKey.equals(flagConfigs.getComponentResult())) {
            // 当前数据为其他组件的执行结果数据
            currData = ComponentContextHandler.getResultData();
            startIndex = 1;
        } else if (firstKey.equals(flagConfigs.getFlowConstant())) {
            // 当前数据为常量池数据
            currData = ConstantHandler.get();
            startIndex = 1;
        } else if (firstKey.equals(flagConfigs.getFlowVariable())) {
            // 当前数据为流程中变量
            currData = ComponentContextHandler.getVariables();
            startIndex = 1;
        } else if (firstKey.equals(flagConfigs.getFlowHeader())) {
            // 当前数据为请求头中的数据
            currData = ComponentContextHandler.getHeader();
            startIndex = 1;
        } else {
            // 当前数据为入参数据
            currData = ComponentContextHandler.getParams();
            startIndex = 0;
        }

        // 查找数据
        return findData(currData, valuePathArr, startIndex);
    }

    /**
     * 查找数据
     *
     * @param currData 当前数据
     * @param path 字段路径
     * @param index 下标
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    private static Object findData(Object currData, String[] path, int index) {
        String param = path[index];

        // 结果中的数据取值
        if (currData instanceof DataResult) {
            DataResult resultData = (DataResult) currData;
            // 解析特殊符号
            Object parseResultData = parseResultDataFlag(resultData, param);
            if (parseResultData == null) {
                currData = resultData.getData();
            } else {
                return parseResultData;
            }
        }

        // 解析特殊符号
        Object parseData = parseFlag(currData, param);
        if (parseData != null) {
            return parseData;
        }

        // 集合类型取值（包含左右方括号表示集合类型，要按照下标取值）
        if (param.contains(SymbolConstant.LEFT_SQUARE_BRACKETS) && param.contains(SymbolConstant.RIGHT_SQUARE_BRACKETS)) {
            // 去掉左右括号，并判断是否是数字
            String listIndexStr = param.replace(SymbolConstant.LEFT_SQUARE_BRACKETS, CheckEmptyUtil.EMPTY).replace(SymbolConstant.RIGHT_SQUARE_BRACKETS, CheckEmptyUtil.EMPTY);

            // 验证当前数据是否为集合
            if(!(currData instanceof List)) {
                ComponentErrorHandler.print("根据路径查找数据-非集合类型无法根据下标取值 path=" + Arrays.toString(path) + ", index=" + index);
                return null;
            }
            List<Object> currListData = (List<Object>) currData;

            // 根据下标查找数据
            if (CheckEmptyUtil.isEmpty(listIndexStr)) {
                List<Object> newCurrData = new ArrayList<>();
                if (index == path.length - 1) {
                    newCurrData.addAll(currListData);
                } else {
                    for (Object item : currListData) {
                        newCurrData.add(findData(item, path, index + 1));
                    }
                }
                return newCurrData;
            } else {
                boolean isInteger = DataTypeIsUtil.isInteger(listIndexStr);
                if (!isInteger) {
                    ComponentErrorHandler.print("根据路径查找数据-集合类型下标不是数字 path=" + Arrays.toString(path) + ", index=" + index);
                    return null;
                }
                int currIndex = Integer.parseInt(listIndexStr);
                if (currIndex >= currListData.size()) {
                    ComponentErrorHandler.print("根据路径查找数据-集合类型下标越界 path=" + Arrays.toString(path) + ", index=" + index);
                    return null;
                }
                currData = currListData.get(currIndex);

                // 当前位置为倒数第二级，可以直接返回值
                if (index == path.length - 1) {
                    return currData;
                } else {
                    return findData(currData, path, index + 1);
                }
            }
        }

        // 对象类型取值
        else {
            if(!(currData instanceof Map)) {
                ComponentErrorHandler.print("根据路径查找数据-非对象类型无法获取下级字段 path=" + Arrays.toString(path) + ", index=" + index);
                return null;
            }
            Map<String, Object> currMapData = (Map<String, Object>) currData;
            currData = currMapData.get(param);

            // 当前位置为倒数第二级，可以直接返回值
            if (index == path.length - 1) {
                return currData instanceof DataResult ? ((DataResult) currData).getData() : currData;
            } else {
                return findData(currData, path, index + 1);
            }
        }
    }

    /**
     * 解析特殊标识
     *
     * @param data 当前数据
     * @param param 参数
     * @return
     */
    private static Object parseFlag(Object data, String param) {
        GlobalContext.FlagConfigs flagConfigs = GlobalContextHandler.get().getFlagConfigs();
        if (param.equals(flagConfigs.getSize())) {
            if (data == null) {
                return 0;
            } else if (data instanceof Collection) {
                return ((Collection<?>) data).size();
            } else if (data instanceof Map) {
                return ((Map<?,?>) data).size();
            } else if (data instanceof Object[]) {
                return ((Object[]) data).length;
            } else {
                return data.toString().length();
            }
        }

        return null;
    }

    /**
     * 解析结果中的特殊标识
     *
     * @param data 当前数据
     * @param param 参数
     * @return
     */
    private static Object parseResultDataFlag(DataResult data, String param) {
        GlobalContext.FlagConfigs flagConfigs = GlobalContextHandler.get().getFlagConfigs();
        if (param.equals(flagConfigs.getComponentResultShowMsg())) {
            return data.getShowMsg();
        } else if (param.equals(flagConfigs.getComponentResultErrorMsg())){
            return data.getErrMsg();
        } else if (param.equals(flagConfigs.getComponentResultSuccess())){
            return data.getSuccess();
        } else if (param.equals(flagConfigs.getComponentResultDownload())){
            return data.isDownload();
        }

        return null;
    }

    /**
     * 数据替换
     *
     * @param path 字段路径
     * @param func 新值
     * @return 结果
     */
    private static boolean replaceData(String path, GetDataFunction<?> func) {
        if (CheckEmptyUtil.isEmpty(path)) {
            ComponentErrorHandler.print("替换失败：参数路径为空");
            return false;
        }

        // 解析 path 路径（将 path 中的 #{...} 解析成值）
        path = parsePath(path);

        // 全局配置
        GlobalContext.FlagConfigs flagConfigs = GlobalContextHandler.get().getFlagConfigs();

        // 将参数路径解析为数组
        String[] valuePathArr = path.split("\\.");

        // 解析多级数据（数据来源可能是入参，可能是其他组件结果）
        int startIndex;
        Object currData;
        String firstKey = valuePathArr[0].toLowerCase();
        if (firstKey.equals(flagConfigs.getComponentResult())) {
            // 当前数据为其他组件的执行结果数据
            currData = ComponentContextHandler.getResultData();
            startIndex = 1;
        } else if (firstKey.equals(flagConfigs.getFlowVariable())) {
            // 当前数据为流程中变量
            currData = ComponentContextHandler.getVariables();
            startIndex = 1;
        } else {
            // 当前数据为入参数据
            currData = ComponentContextHandler.getParams();
            startIndex = 0;
        }

        // 替换数据
        return replaceData(currData, valuePathArr, startIndex, func);
    }

    /**
     * 数据替换
     *
     * @param data 当前数据
     * @param path 字段路径
     * @param index 当前字段路径索引
     * @param func 新值获取函数
     */
    @SuppressWarnings("unchecked")
    private static boolean replaceData(Object data, String[] path, int index, GetDataFunction<?> func) {
        String param = path[index];
        if (data == null) {
            ComponentErrorHandler.print("根据路径替换数据-数据无下级字段 path=" + Arrays.toString(path) + ", index=" + index);
            return false;
        }

        // 结果中的数据取值
        if (data instanceof DataResult) {
            data = ((DataResult) data).getData();
        }

        // 集合类型取值（包含左右方括号表示集合类型，要按照下标取值）
        if (param.contains(SymbolConstant.LEFT_SQUARE_BRACKETS) && param.contains(SymbolConstant.RIGHT_SQUARE_BRACKETS)) {
            // 判断集合数据是否合法
            if(!(data instanceof List)) {
                ComponentErrorHandler.print("根据路径替换数据-非集合类型无法根据下标取值 path=" + Arrays.toString(path) + ", index=" + index);
                return false;
            }
            List<Object> listData = (List<Object>) data;

            // 去掉左右括号，并判断是否是数字
            String listIndexStr = param.replace(SymbolConstant.LEFT_SQUARE_BRACKETS, "").replace(SymbolConstant.RIGHT_SQUARE_BRACKETS, "");

            // 如果是 [] 结果，标识对所有项做处理
            if (CheckEmptyUtil.EMPTY.equals(listIndexStr)) {
                // 当前位置为倒数第二级，可以直接返回值
                if (index == path.length - 1) {
                    Object newValue;
                    try {
                        newValue = func.get(listData);
                    } catch (Exception e) {
                        ComponentErrorHandler.print("值替换异常：" + e.getMessage(), e);
                        return false;
                    }

                    if (newValue == null) {
                        listData.clear();
                    } else {
                        for (int i = 0; i < listData.size(); i++) {
                            listData.set(i, newValue);
                        }
                    }
                } else {
                    for (Object item : listData) {
                        replaceData(item, path, index + 1, func);
                    }
                }
            } else {
                boolean isInteger = DataTypeIsUtil.isInteger(listIndexStr);
                if (!isInteger) {
                    ComponentErrorHandler.print("根据路径替换数据-集合类型下标不是数字 path=" + Arrays.toString(path) + ", index=" + index);
                    return false;
                }
                int currIndex = Integer.parseInt(listIndexStr);

                // 当前位置为倒数第二级，可以直接返回值
                if (index == path.length - 1) {
                    Object newValue;
                    try {
                        newValue = func.get(listData);
                    } catch (Exception e) {
                        ComponentErrorHandler.print("值替换异常：" + e.getMessage(), e);
                        return false;
                    }

                    if (newValue == null) {
                        listData.remove(currIndex);
                    } else {
                        listData.set(currIndex, newValue);
                    }

                } else {
                    Object currData = listData.get(currIndex);
                    replaceData(currData, path, index+1, func);
                }
            }
        }

        // 对象类型取值
        else {
            if(!(data instanceof Map)) {
                ComponentErrorHandler.print("根据路径替换数据-非对象类型无法获取下级字段 path=" + Arrays.toString(path) + ", index=" + index);
                return false;
            }
            Map<String, Object> currMapData = (Map<String, Object>) data;
            Object currData = currMapData.get(param);

            // 当前位置为倒数第二级，可以直接返回值
            if (index == path.length - 1) {
                Object newValue;
                try {
                    newValue = func.get(currData);
                } catch (Exception e) {
                    ComponentErrorHandler.print("值替换异常：" + e.getMessage(), e);
                    return false;
                }

                if (newValue == null) {
                    currMapData.remove(param);
                } else {
                    currMapData.put(param, newValue);
                }
            } else {
                replaceData(currData, path, index+1, func);
            }
        }

        return true;
    }

    /**
     * 获取数据函数
     *
     * @param <R> 新值
     */
    public interface GetDataFunction<R> {
        R get(Object obj) throws Exception;
    }

    /**
     * 判断是否有解析标识
     *
     * @param path 路径
     * @return 是否有解析标识
     */
    public static boolean hasParseFlag(String path) {
        return path.length() > 3 && '#' == path.charAt(0) && '{' == path.charAt(1) && '}' == path.charAt(path.length() - 1);
    }

    /**
     * 过滤掉标识符
     *
     * @param path 字段路径
     * @return 值
     */
    public static String filterParseFlag(String path) {
        if (hasParseFlag(path)) {
            // 过滤掉#{...}
            return path.substring(2, path.length() -1);
        }

        return path;
    }

    /**
     * 解析路径（将路径中#{...}解析成值）
     *
     * @param path 路径
     * @return 解析后的路径
     */
    public static String parsePath(String path) {
        String regex2 = "#\\{(.*?)}";
        Pattern pattern2 = Pattern.compile(regex2);
        Matcher matcher2 = pattern2.matcher(path);
        while (matcher2.find()) {
            String paramStr = matcher2.group();
            if (paramStr.length() <= 3) {
                continue;
            }

            String paramFieldStr = paramStr.substring(2, paramStr.length() -1);
            Object value = DataFindHandler.find(paramFieldStr);
            if (value != null) {
                path = path.replace(paramStr, value.toString());
            }
        }

        return path;
    }
}
