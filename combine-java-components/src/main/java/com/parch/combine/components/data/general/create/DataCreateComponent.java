package com.parch.combine.components.data.general.create;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataTypeEnum;
import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

/**
 * 运算组件
 */
@Component(key = "create", name = "数据创建组件", logicConfigClass = DataCreateLogicConfig.class, initConfigClass = DataCreateInitConfig.class)
@ComponentResult(name = "所有被创建的数据集合")
public class DataCreateComponent extends AbsComponent<DataCreateInitConfig, DataCreateLogicConfig> {

    /**
     * 构造器
     */
    public DataCreateComponent() {
        super(DataCreateInitConfig.class, DataCreateLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataCreateLogicConfig logicConfig = getLogicConfig();
        List<DataCreateLogicConfig.DataCreateItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataCreateLogicConfig.DataCreateItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getTarget())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "目标变量为空"));
                }
                DataTypeEnum type = item.getType();
                if (type == DataTypeEnum.NONE) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "创建类型不合规"));
                }

                // 参数值校验
                List<String> params = item.getParams();
                if (CheckEmptyUtil.isNotEmpty(params)) {
                    switch (type) {
                        case OBJECT:
                            for (String param : params) {
                                String[] paramPath = param.split(SymbolConstant.COLON);
                                if (paramPath.length < 2) {
                                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "对象类型的初始值必须为“字段名:字段值”的结构"));
                                    break;
                                }
                            }
                            break;
                        case DATE:
                            if (DataTypeIsUtil.isLong(params.get(0))){
                                result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "日期类型格式错误"));
                                break;
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();

        // 数据过滤
        DataCreateLogicConfig logicConfig = getLogicConfig();
        List<DataCreateLogicConfig.DataCreateItem> items = logicConfig.getItems();
        if (items == null) {
            return DataResult.success(result);
        }

        // 创建数据
        for (DataCreateLogicConfig.DataCreateItem item : items) {
            List<String> params = item.getParams();
            int size = CheckEmptyUtil.isEmpty(params) ? 0 : params.size();
            Object createData;
            switch (item.getType()) {
                case STRING:
                    createData = CheckEmptyUtil.EMPTY;
                    if (size > 0) {
                        createData = DataVariableHelper.parseValue(params.get(0), false);
                    }
                    break;
                case BOOLEAN:
                    createData = false;
                    if (size > 0) {
                        createData = Boolean.parseBoolean(DataVariableHelper.parseValue(params.get(0), false).toString());
                    }
                    break;
                case NUMBER:
                    createData = 0;
                    if (size > 0) {
                        Object numObj = DataVariableHelper.parseValue(params.get(0), false);
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
                        createData = DataParseUtil.parseDate(params.get(0));
                        if (createData == null) {
                            return DataResult.fail(DataCreateErrorEnum.DATE_ERROR);
                        }
                    } else {
                        createData = new Date();
                    }
                    break;
                case OBJECT:
                    Map<String, Object> objectData = new HashMap<>(16);
                    if (CheckEmptyUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            String[] paramPath = param.split(SymbolConstant.COLON);
                            Object keyObj = DataVariableHelper.parseValue(paramPath[0], false);
                            if (keyObj != null) {
                                objectData.put(keyObj.toString(), DataVariableHelper.parseValue(paramPath[1], false));
                            }
                        }
                    }
                    createData = objectData;
                    break;
                case LIST:
                    List<Object> listData = new ArrayList<>();
                    if (CheckEmptyUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            listData.add(DataVariableHelper.parseValue(param, false));
                        }
                    }
                    createData = listData;
                    break;
                default:
                    return DataResult.fail(DataCreateErrorEnum.FAIL);
            }

            result.add(createData);
            DataVariableHelper.replaceValue(item.getTarget(), createData, false);
        }

        return DataResult.success(result);
    }
}
