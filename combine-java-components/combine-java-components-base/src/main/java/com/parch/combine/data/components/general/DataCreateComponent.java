package com.parch.combine.data.components.general;

import com.parch.combine.data.base.general.DataStructureHelper;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.data.base.general.create.DataCreateErrorEnum;
import com.parch.combine.data.base.general.create.DataCreateInitConfig;
import com.parch.combine.data.base.general.create.DataCreateLogicConfig;

import java.util.*;

@Component(key = "create", name = "数据创建组件", logicConfigClass = DataCreateLogicConfig.class, initConfigClass = DataCreateInitConfig.class)
@ComponentResult(name = "所有被创建的数据集合")
public class DataCreateComponent extends AbstractComponent<DataCreateInitConfig, DataCreateLogicConfig> {

    public DataCreateComponent() {
        super(DataCreateInitConfig.class, DataCreateLogicConfig.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataResult execute() {
        List<Object> result = new ArrayList<>();

        // 数据过滤
        DataCreateLogicConfig logicConfig = getLogicConfig();
        DataCreateLogicConfig.DataCreateItem[] items = logicConfig.items();
        if (items == null) {
            return DataResult.success(result);
        }

        // 创建数据
        for (DataCreateLogicConfig.DataCreateItem item : items) {
            Object[] params = item.params();
            int size = params == null ? 0 : params.length;
            Object createData;

            DataTypeEnum type = DataTypeEnum.get(item.type());
            switch (type) {
                case STRING:
                    createData = CheckEmptyUtil.EMPTY;
                    if (size > 0) {
                        createData = params[0];
                    }
                    break;
                case BOOLEAN:
                    createData = false;
                    if (size > 0) {
                        createData = Boolean.parseBoolean(params[0].toString());
                    }
                    break;
                case NUMBER:
                    createData = 0;
                    if (size > 0) {
                        Object numObj = params[0];
                        if (numObj != null) {
                            if (DataTypeIsUtil.isLong(numObj.toString())) {
                                createData = Long.parseLong(numObj.toString());
                            } else if (DataTypeIsUtil.isDouble(numObj.toString())) {
                                createData = Double.parseDouble(numObj.toString());
                            } else {
                                return DataResult.fail(DataCreateErrorEnum.DATE_ERROR);
                            }
                        }
                    }
                    break;
                case DATE:
                    if (size > 0) {
                        createData = DataParseUtil.parseDate(params[0]);
                        if (createData == null) {
                            return DataResult.fail(DataCreateErrorEnum.DATE_ERROR);
                        }
                    } else {
                        createData = new Date();
                    }
                    break;
                case OBJECT:
                    Map<String, Object> objectData = new HashMap<>(16);
                    if (size > 0) {
                        for (Object param : params) {
                            if (param instanceof Map) {
                                objectData.putAll((Map<String, Object>) param);
                            } else if (DataTypeIsUtil.isString(param) && DataStructureHelper.isStructure(param.toString())) {
                                String[] paramPath = DataStructureHelper.parseStructure(param.toString());
                                Object key = DataVariableHelper.parseValue(paramPath[0], false);
                                if (key != null) {
                                    objectData.put(key.toString(), DataVariableHelper.parseValue(paramPath[1], false));
                                }
                            } else {
                                return DataResult.fail(DataCreateErrorEnum.OBJECT_ERROR);
                            }
                        }
                    }
                    createData = objectData;
                    break;
                case LIST:
                    List<Object> listData = new ArrayList<>();
                    if (size > 0) {
                        listData.addAll(Arrays.asList(params));
                    }
                    createData = listData;
                    break;
                default:
                    return DataResult.fail(DataCreateErrorEnum.FAIL);
            }

            result.add(createData);

            String target = item.target();
            if (CheckEmptyUtil.isNotEmpty(target)) {
                DataVariableHelper.replaceValue(item.target(), createData, false);
            }
        }

        return DataResult.success(result);
    }
}
