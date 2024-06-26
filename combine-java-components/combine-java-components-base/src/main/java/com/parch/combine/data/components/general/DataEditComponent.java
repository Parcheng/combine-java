package com.parch.combine.data.components.general;

import com.parch.combine.data.base.general.DataStructureHelper;
import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.data.base.general.edit.DataEditErrorEnum;
import com.parch.combine.data.base.general.edit.DataEditInitConfig;
import com.parch.combine.data.base.general.edit.DataEditLogicConfig;
import com.parch.combine.data.base.general.edit.DataEditTypeEnum;

import java.util.*;

@Component(key = "edit", name = "数据修改组件", logicConfigClass = DataEditLogicConfig.class, initConfigClass = DataEditInitConfig.class)
@ComponentResult(name = "所有被创建的数据集合")
public class DataEditComponent extends AbstractComponent<DataEditInitConfig, DataEditLogicConfig> {

    public DataEditComponent() {
        super(DataEditInitConfig.class, DataEditLogicConfig.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataResult execute() {
        // 数据过滤
        DataEditLogicConfig logicConfig = getLogicConfig();
        DataEditLogicConfig.DataEditItem[] items = logicConfig.items();
        if (items != null) {
            for (DataEditLogicConfig.DataEditItem item : items) {
                DataEditErrorEnum error;
                String source = item.source();
                Object sourceData = DataVariableHelper.parseValue(source, true);

                DataEditTypeEnum type = DataEditTypeEnum.get(item.type());
                if (DataEditTypeEnum.NONE == type) {
                    return DataResult.fail(DataEditErrorEnum.TYPE_ERROR);
                }

                DataTypeEnum dataType = DataTypeEnum.get(item.dataType());
                Object[] params = item.params();

                if (sourceData instanceof Map) {
                    error = editMap(source, (Map<Object, Object>) sourceData, type, dataType, params);
                } else if (sourceData instanceof Collection) {
                    error = editCollection(source, (Collection<Object>) sourceData, type, dataType, params);
                } else {
                    error = editOther(source, sourceData, type, dataType, params);
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
     * @return 错误信息
     */
    @SuppressWarnings("unchecked")
    private DataEditErrorEnum editMap(String sourcePath, Map<Object, Object> sourceData, DataEditTypeEnum type, DataTypeEnum dataType, Object[] params) {
        if (sourceData == null) {
            sourceData = new HashMap<>();
        }

        Object key, value;
        switch (type) {
            case PUT:
                for (Object param : params) {
                    if (param == null) {
                        continue;
                    }

                    if (param instanceof Map) {
                        sourceData.putAll((Map<String, Object>) param);
                    } else if (DataTypeIsUtil.isString(param) && DataStructureHelper.isStructure(param.toString())) {
                        String[] currSetPath = DataStructureHelper.parseStructure(param.toString());
                        key = DataVariableHelper.parseValue(currSetPath[0], false);
                        if (key != null) {
                            value = DataVariableHelper.parseValue(currSetPath[1], false);
                            value = getValue(sourceData.get(key), dataType, value);
                            sourceData.put(key, value);
                        }
                    } else {
                        return DataEditErrorEnum.PARAM_DATA_ERROR;
                    }
                }
                break;
            case REMOVE:
            case REMOVE_INDEX:
                if (params[0] != null) {
                    sourceData.remove(params[0]);
                }
                break;
            case REMOVE_ALL:
                for (Object param : params) {
                    if (param != null) {
                        if (param instanceof Map) {
                            for (Object currMapKey : ((Map<Object, Object>) param).keySet()) {
                                sourceData.remove(currMapKey);
                            }
                        } else if (param instanceof Collection) {
                            for (Object currMapKey : (Collection<Object>) param) {
                                sourceData.remove(currMapKey);
                            }
                        } else {
                            sourceData.remove(param);
                        }
                    }
                }
                break;
            case SET:
            case ADD:
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
     * @return 错误信息
     */
    @SuppressWarnings("unchecked")
    private DataEditErrorEnum editCollection(String sourcePath, Collection<Object> sourceData, DataEditTypeEnum type, DataTypeEnum dataType, Object[] params) {
        if (sourceData == null) {
            sourceData = new ArrayList<>();
        }

        switch (type) {
            case SET:
                for (Object param : params) {
                    if (param == null || !DataStructureHelper.isStructure(param.toString())) {
                        continue;
                    }

                    String[] paramPath = DataStructureHelper.parseStructure(param.toString());
                    Object key = DataVariableHelper.parseValue(paramPath[0], false);
                    if (key == null) {
                        continue;
                    }

                    Object value = DataVariableHelper.parseValue(paramPath[1], false);
                    if (sourceData instanceof List) {
                        List<Object> listData = (List<Object>) sourceData;
                        if (DataTypeIsUtil.isInteger(key)) {
                            int indexNum = Integer.parseInt(key.toString());
                            // 下标越界时补充数据
                            if (sourceData.size() <= indexNum) {
                                for (int j = 0; j <= indexNum + 1 - sourceData.size(); j++) {
                                    sourceData.add(null);
                                }
                            }
                            value = getValue(listData.get(indexNum), dataType, value);
                            listData.set(indexNum, value);
                        }
                    } else {
                        value = getValue(null, dataType, value);
                        sourceData.add(value);
                    }
                }
                break;
            case ADD:
                for (Object param : params) {
                    if (param == null) {
                        continue;
                    }

                    if (param instanceof Collection) {
                        for (Object obj : (Collection<Object>) param) {
                            sourceData.add(getValue(null, dataType, obj));
                        }
                    } else {
                        sourceData.add(getValue(null, dataType, param));
                    }
                }
                break;
            case REMOVE:
                if (params[0] != null) {
                    sourceData.removeIf(i -> i != null && i.toString().equals(params[0].toString()));
                }
                break;
            case REMOVE_INDEX:
                for (Object param : params) {
                    if (param != null && sourceData instanceof List) {
                        List<Object> indexList = new ArrayList<>();
                        if (param instanceof Collection) {
                            indexList.addAll((Collection<Object>) param);
                        } else {
                            indexList.add(param);
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
                for (Object param : params) {
                    if (param == null) {
                        continue;
                    }

                    if (param instanceof Collection) {
                        for (Object indexItem : (Collection<Object>) param) {
                            if (indexItem != null) {
                                sourceData.removeIf(i -> i != null && i.toString().equals(indexItem.toString()));
                            }
                        }
                    } else {
                        sourceData.removeIf(i -> i != null && i.toString().equals(param.toString()));
                    }
                }
                break;
            case PUT:
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
     * @return 错误信息
     */
    private DataEditErrorEnum editOther(String sourcePath, Object sourceData, DataEditTypeEnum type, DataTypeEnum dataType, Object[] params) {
        Object newValue;
        switch (type) {
            case SET:
                newValue = getValue(sourceData, dataType, params[0]);
                break;
            case REMOVE:
            case REMOVE_INDEX:
            case REMOVE_ALL:
            case ADD:
            case PUT:
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
     * @param dataType 类型
     * @param value 值
     * @return 值
     */
    private Object getValue(Object sourceData, DataTypeEnum dataType, Object value) {
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

        if (dataType == DataTypeEnum.NONE) {
            return value;
        }

        if (value == null) {
            return null;
        }

        // 获取数据
        try {
            switch (dataType) {
                case STRING:
                    return value.toString();
                case NUMBER:
                    if (value.toString().contains(SymbolConstant.DOT)) {
                        return Double.parseDouble(value.toString());
                    } else {
                        return Long.parseLong(value.toString());
                    }
                case BOOLEAN:
                    return Boolean.parseBoolean(value.toString());
                case DATE:
                    return DataParseUtil.parseDate(value.toString());
                default:
                    return value;
            }
        } catch (Exception e) {
            return value;
        }
    }
}
