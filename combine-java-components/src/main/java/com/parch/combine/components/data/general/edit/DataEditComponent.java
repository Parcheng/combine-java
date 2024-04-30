package com.parch.combine.components.data.general.edit;

import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.variable.DataTypeEnum;
import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.DataParseUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 运算组件
 */
@Component(key = "edit", name = "数据修改组件", logicConfigClass = DataEditLogicConfig.class, initConfigClass = DataEditInitConfig.class)
@ComponentResult(name = "所有被创建的数据集合")
public class DataEditComponent extends AbsComponent<DataEditInitConfig, DataEditLogicConfig> {

    /**
     * 构造器
     */
    public DataEditComponent() {
        super(DataEditInitConfig.class, DataEditLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataEditLogicConfig logicConfig = getLogicConfig();
        List<DataEditLogicConfig.DataEditItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataEditLogicConfig.DataEditItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getSource())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "数据来源为空"));
                }
                DataEditTypeEnum type = item.getType();
                if (type == DataEditTypeEnum.NONE) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "类型不合规"));
                }
                if (item.getParams().size() < type.getMinParamCount()) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "参数数量不合规"));
                }

                if (type == DataEditTypeEnum.SET) {
                    String[] paramPath = item.getParams().get(1).split(SymbolConstant.COLON);
                    if (paramPath.length < 2) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "对象类型的初始值必须为“索引:字段值”的结构"));
                        break;
                    }
                }
                if (type == DataEditTypeEnum.PUT) {
                    String[] paramPath = item.getParams().get(0).split(SymbolConstant.COLON);
                    if (paramPath.length < 3) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "对象类型的初始值必须为“数据类型:字段名:字段值”的结构"));
                        break;
                    }
                }
            }
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataResult execute() {
        // 数据过滤
        DataEditLogicConfig logicConfig = getLogicConfig();
        List<DataEditLogicConfig.DataEditItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataEditLogicConfig.DataEditItem item : items) {
                DataEditErrorEnum error;
                Object sourceData = DataVariableHelper.parseValue(item.getSource(), true);
                if (sourceData instanceof Map) {
                    error = editMap((Map<Object, Object>) sourceData, item);
                } else if (sourceData instanceof Collection) {
                    error = editCollection((Collection<Object>) sourceData, item);
                } else {
                    error = editOther(sourceData, item);
                }

                if (error != null) {
                    return DataResult.fail(error);
                }
            }
        }

        return DataResult.success(true);
    }

    /**
     * 编辑MAP数据
     *
     * @param sourceData 源数据
     * @param item 参数配置
     * @return 错误信息
     */
    @SuppressWarnings("unchecked")
    private DataEditErrorEnum editMap(Map<Object, Object> sourceData, DataEditLogicConfig.DataEditItem item) {
        String sourcePath = item.getSource();
        DataEditTypeEnum type = item.getType();
        List<String> params = item.getParams();
        if (sourceData == null) {
            sourceData = new HashMap<>();
        }

        Object key, value;
        switch (type) {
            case PUT:
                String[] setPath = params.get(0).split(SymbolConstant.COLON);
                key = DataVariableHelper.parseValue(setPath[1], false);
                if (key != null) {
                    value = DataVariableHelper.parseValue(setPath[2], false);
                    value = getValue(sourceData.get(key), setPath[0], value);
                    sourceData.put(key, value);
                }
                break;
            case PUT_ALL:
                for (String s : params) {
                    if (s.contains(SymbolConstant.COLON)) {
                        String[] currSetPath = params.get(0).split(SymbolConstant.COLON);
                        key = DataVariableHelper.parseValue(currSetPath[1], false);
                        if (key != null) {
                            value = DataVariableHelper.parseValue(currSetPath[2], false);
                            value = getValue(sourceData.get(key), currSetPath[0], value);
                            sourceData.put(key, value);
                        }
                    } else {
                        Object currMapObj = DataVariableHelper.parseValue(s, false);
                        if (currMapObj == null) {
                            continue;
                        }
                        if (!(currMapObj instanceof Map)) {
                            return DataEditErrorEnum.PARAM_DATA_NOT_OBJECT;
                        }

                        Map<Object, Object> currMapData = (Map<Object, Object>) currMapObj;
                        for (Object newMapKey : currMapData.keySet()) {
                            sourceData.put(newMapKey, getValue(sourceData.get(newMapKey), params.get(0), currMapData.get(newMapKey)));
                        }
                    }
                }
                break;
            case REMOVE:
            case REMOVE_INDEX:
                key = DataVariableHelper.parseValue(params.get(0), false);
                if (key != null) {
                    sourceData.remove(key);
                }
                break;
            case REMOVE_ALL:
                for (String param : params) {
                    key = DataVariableHelper.parseValue(param, false);
                    if (key != null) {
                        if (key instanceof Map) {
                            for (Object currMapKey : ((Map<Object, Object>) key).keySet()) {
                                sourceData.remove(currMapKey);
                            }
                        } else if (key instanceof Collection) {
                            for (Object currMapKey : (Collection<Object>) key) {
                                sourceData.remove(currMapKey);
                            }
                        } else {
                            sourceData.remove(key);
                        }
                    }
                }
                break;
            case SET:
            case SET_ALL:
            case ADD:
            case ADD_ALL:
                return DataEditErrorEnum.TYPE_ERROR;
            default:
                return DataEditErrorEnum.UNKNOWN_TYPE;
        }

        DataVariableHelper.replaceValue(sourcePath, sourceData, false);
        return null;
    }

    /**
     * 编辑集合数据
     *
     * @param sourceData 源数据
     * @param item 参数配置
     * @return 错误信息
     */
    @SuppressWarnings("unchecked")
    private DataEditErrorEnum editCollection(Collection<Object> sourceData, DataEditLogicConfig.DataEditItem item) {
        String sourcePath = item.getSource();
        DataEditTypeEnum type = item.getType();
        List<String> params = item.getParams();
        if (sourceData == null) {
            sourceData = new ArrayList<>();
        }

        Object index, value;
        switch (type) {
            case SET:
                String[] setPath = params.get(1).split(SymbolConstant.COLON);
                index = DataVariableHelper.parseValue(setPath[0], false);
                if (index != null) {
                    value = DataVariableHelper.parseValue(setPath[1], false);
                    if (sourceData instanceof List) {
                        List<Object> listData = (List<Object>) sourceData;
                        if (DataTypeIsUtil.isInteger(index.toString())) {
                            int indexNum = Integer.parseInt(index.toString());
                            // 下标越界时补充数据
                            if (sourceData.size() <= indexNum) {
                                for (int i = 0; i < indexNum + 1 - sourceData.size(); i++) {
                                    sourceData.add(null);
                                }
                            }
                            value = getValue(listData.get(indexNum), params.get(0), value);
                            listData.set(indexNum, value);
                        }
                    }  else {
                        value = getValue(null, params.get(0), value);
                        sourceData.add(value);
                    }
                }
                break;
            case SET_ALL:
                for (int i = 0; i < params.size(); i++) {
                    if (i == 0) {
                        continue;
                    }

                    String[] currSetPath = params.get(i).split(SymbolConstant.COLON);
                    index = DataVariableHelper.parseValue(currSetPath[0], false);
                    if (index != null) {
                        value = DataVariableHelper.parseValue(currSetPath[1], false);
                        if (sourceData instanceof List) {
                            List<Object> listData = (List<Object>) sourceData;
                            if (DataTypeIsUtil.isInteger(index.toString())) {
                                int indexNum = Integer.parseInt(index.toString());
                                // 下标越界时补充数据
                                if (sourceData.size() <= indexNum) {
                                    for (int j = 0; j < indexNum + 1 - sourceData.size(); j++) {
                                        sourceData.add(null);
                                    }
                                }
                                value = getValue(listData.get(indexNum), params.get(0), value);
                                listData.set(indexNum, value);
                            }
                        } else {
                            value = getValue(null, params.get(0), value);
                            sourceData.add(value);
                        }
                    }
                }
                break;
            case ADD:
                value = DataVariableHelper.parseValue(params.get(1), false);
                sourceData.add(getValue(null, params.get(0), value));
                break;
            case ADD_ALL:
                for (int i = 0; i < params.size(); i++) {
                    if (i == 0) {
                        continue;
                    }

                    value = DataVariableHelper.parseValue(params.get(i), false);
                    if (value instanceof Collection) {
                        for (Object obj : (Collection<Object>) value) {
                            sourceData.add(getValue(null, params.get(0), obj));
                        }
                    } else {
                        sourceData.add(getValue(null, params.get(0), value));
                    }
                }
                break;
            case REMOVE:
                index = DataVariableHelper.parseValue(params.get(0), false);
                if (index != null) {
                    Object finalIndex = index;
                    sourceData.removeIf(i -> i != null && i.toString().equals(finalIndex.toString()));
                }
                break;
            case REMOVE_INDEX:
                for (String param : params) {
                    index = DataVariableHelper.parseValue(param, false);
                    if (index != null && sourceData instanceof List) {
                        List<Object> indexList = new ArrayList<>();
                        if (index instanceof Collection) {
                            indexList.addAll((Collection<Object>) index);
                        } else {
                            indexList.add(index);
                        }

                        for (Object indexItem : indexList) {
                            if (DataTypeIsUtil.isInteger(indexItem.toString())) {
                                int indexNum = Integer.parseInt(indexItem.toString());
                                if (sourceData.size() > indexNum) {
                                    ((List<Object>) sourceData).remove(indexNum);
                                }
                            }
                        }
                    }
                }
                break;
            case REMOVE_ALL:
                for (String param : params) {
                    index = DataVariableHelper.parseValue(param, false);
                    if (index == null) {
                        continue;
                    }

                    if (index instanceof Collection) {
                        for (Object indexItem : (Collection<Object>) index) {
                            if (indexItem != null) {
                                sourceData.removeIf(i -> i != null && i.toString().equals(indexItem.toString()));
                            }
                        }
                    } else {
                        Object finalIndex = index;
                        sourceData.removeIf(i -> i != null && i.toString().equals(finalIndex.toString()));
                    }
                }
                break;
            case PUT:
            case PUT_ALL:
                return DataEditErrorEnum.TYPE_ERROR;
            default:
                return DataEditErrorEnum.UNKNOWN_TYPE;
        }

        DataVariableHelper.replaceValue(sourcePath, sourceData, false);
        return null;
    }

    /**
     * 编辑其他数据
     *
     * @param sourceData 源数据
     * @param item 参数配置
     * @return 错误信息
     */
    private DataEditErrorEnum editOther(Object sourceData, DataEditLogicConfig.DataEditItem item) {
        String sourcePath = item.getSource();
        DataEditTypeEnum type = item.getType();
        List<String> params = item.getParams();

        Object value, newValue;
        switch (type) {
            case SET:
                String[] setPath = params.get(1).split(SymbolConstant.COLON);
                value = DataVariableHelper.parseValue(setPath[1], false);
                newValue = getValue(sourceData, params.get(0), value);
                break;
            case REMOVE:
            case REMOVE_INDEX:
            case REMOVE_ALL:
            case SET_ALL:
            case ADD:
            case ADD_ALL:
            case PUT:
            case PUT_ALL:
                return DataEditErrorEnum.TYPE_ERROR;
            default:
                return DataEditErrorEnum.UNKNOWN_TYPE;
        }

        DataVariableHelper.replaceValue(sourcePath, newValue, false);
        return null;
    }

    /**
     * 获取值
     *
     * @param sourceData 源数据
     * @param type 类型
     * @param value 值
     * @return 值
     */
    private Object getValue(Object sourceData, String type, Object value) {
        DataTypeEnum dataType = DataTypeEnum.get(type);
        Object newValue = value;
        if (newValue == null) {
            return null;
        }

        // 当类型为NONE时，自动匹配类型
        if (sourceData != null && dataType == DataTypeEnum.NONE) {
            if (sourceData instanceof Boolean) {
                dataType = DataTypeEnum.BOOLEAN;
            } else if (DataTypeIsUtil.isNumber(sourceData.toString())) {
                dataType = DataTypeEnum.NUMBER;
            } else if (sourceData instanceof Date) {
                dataType = DataTypeEnum.DATE;
            }
        }

        // 获取数据
        try {
            switch (dataType) {
                case STRING:
                    return newValue.toString();
                case NUMBER:
                    if (newValue.toString().contains(SymbolConstant.DOT)) {
                        return Double.parseDouble(newValue.toString());
                    } else {
                        return Long.parseLong(newValue.toString());
                    }
                case BOOLEAN:
                    return Boolean.parseBoolean(newValue.toString());
                case DATE:
                    return DataParseUtil.parseDate(newValue.toString());
                default:
                    return newValue;
            }
        } catch (Exception e) {
            return newValue;
        }
    }
}
