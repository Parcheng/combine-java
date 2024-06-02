package com.parch.combine.core.component.tools.variable;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据变量处理帮助类
 */
public class DataVariableHelper {

    /**
     * 解析变量
     *
     * @param data 数据
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static Object parse(Object data) {
        if (data == null) {
            return null;
        } else if (data instanceof List) {
            List<Object> listData = (List<Object>) data;
            for (int i = 0; i < listData.size(); i++) {
                listData.set(i, parse(listData.get(i)));
            }
            return listData;
        } else if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            mapData.replaceAll((k, v) -> parse(mapData.get(k)));
            return mapData;
        } else {
            return parseValue(data, false);
        }
    }

    /**
     * 解析变量（数据拷贝）
     *
     * @param data 数据
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static Object parseAndCopy(Object data) {
        if (data == null) {
            return null;
        } else if (data instanceof List) {
            List<Object> listData = (List<Object>) data;
            List<Object> newListData = new ArrayList<>();
            for (int i = 0; i < listData.size(); i++) {
                newListData.add(parse(i));
            }
            return newListData;
        } else if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            Map<String, Object> newMapData = new HashMap<>();
            mapData.forEach((k, v) -> newMapData.put(k, parse(mapData.get(k))));
            return newMapData;
        } else {
            return parseValue(data.toString(), false);
        }
    }

    /**
     * 解析值
     *
     * @param data 字段路径/数据
     * @param isForce 是否强制查找
     * @return 值
     */
    public static Object parseValue(Object data, boolean isForce) {
        if (data instanceof String) {
            boolean isFind = false;
            String path = data.toString();
            if (DataVariableFlagHelper.hasParseFlag(path)) {
                // 过滤掉#{...}
                path = path.substring(2, path.length() -1);
                isFind = true;
            }

            // 是否查找
            if (isFind || isForce) {
                return DataFindHandler.find(path);
            }
        }

        return data;
    }

    /**
     * 解析值
     *
     * @param path 字段路径
     * @return 值
     */
    public static boolean replaceValue(String path, List<String> newValues, String separator) {
        StringBuilder newValueStr = new StringBuilder();
        if (CheckEmptyUtil.isNotEmpty(newValues)) {
            for (int i = 0; i < newValues.size(); i++) {
                Object currNewValue = DataVariableHelper.parseValue(newValues.get(i), false);
                if (currNewValue != null) {
                    if (i != 0) {
                        newValueStr.append(separator);
                    }
                    newValueStr.append(currNewValue.toString());
                }
            }
        }

        path = DataVariableFlagHelper.filterParseFlag(path);
        return DataFindHandler.replace(path, newValueStr.toString());
    }

    /**
     * 解析值
     *
     * @param path 字段路径
     * @return 值
     */
    public static boolean replaceValue(String path, List<Object> newValues) {
        List<Object> newValueList = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(newValues)) {
            for (Object newValue : newValues) {
                newValueList.add(DataVariableHelper.parseValue(newValue.toString(), false));
            }
        }

        path = DataVariableFlagHelper.filterParseFlag(path);
        return DataFindHandler.replace(path, newValueList);
    }

    /**
     * 解析值
     *
     * @param path 字段路径
     * @param isForce 是否强制解析 newValue 值
     * @return 值
     */
    public static boolean replaceValue(String path, Object newValue, boolean isForce) {
        path = DataVariableFlagHelper.filterParseFlag(path);
        if (newValue != null) {
            newValue = DataVariableHelper.parseValue(newValue, isForce);
        }
        return DataFindHandler.replace(path, newValue);
    }

    /**
     * 解析值
     *
     * @param path 字段路径
     * @param func 函数
     * @return 值
     */
    public static boolean replaceValue(String path, DataFindHandler.GetDataFunction<?> func) {
        path = DataVariableFlagHelper.filterParseFlag(path);
        return DataFindHandler.replaceAsFunc(path, func);
    }

    /**
     * 解析值
     *
     * @param path 字段路径
     * @return 值
     */
    public static boolean clearValue(String path) {
        path = DataVariableFlagHelper.filterParseFlag(path);
        return DataFindHandler.clear(path);
    }
}
