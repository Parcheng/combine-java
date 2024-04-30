package com.parch.combine.components.data.general.reset;

import com.parch.combine.core.tools.variable.DataTypeEnum;
import com.parch.combine.common.util.DataParseUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.tools.variable.DataVariableHelper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 数据过滤处理器
 */
public class DataResetHandler {

    /***
     * 检测配置项
     *
     * @param type 类型
     * @param value 值
     * @return 结果
     */
    public static String checkDataType(String type, String value) {
        DataTypeEnum dataType = DataTypeEnum.get(type);
        switch (dataType) {
            case NUMBER:
                if (!DataTypeIsUtil.isNumber(value)) {
                    return "值非整数类型";
                }
                break;
            case DATE:
                if (!DataTypeIsUtil.isDate(value)) {
                    return "日期格式错误";
                }
                break;
            case STRING:
            case BOOLEAN:
            case OBJECT:
            case LIST:
                break;
            default:
                return "数据类型不合规";
        }

        return null;
    }

    /**
     * 重设值
     *
     * @param config 配置
     * @return 结果
     */
    public static DataResetErrorEnum reset(DataResetLogicConfig.DataResetConfig config, boolean nullValue) {
        Object value = DataVariableHelper.parseValue(config.getValue(), false);

        Object newValue = null;
        if (value == null) {
            if (!nullValue) {
                return DataResetErrorEnum.NEW_VALUE_IS_NULL;
            }
        } else {
            DataTypeEnum dataType = DataTypeEnum.get(config.getType());
            switch (dataType) {
                case NUMBER:
                    newValue = DataParseUtil.parseNumber(value.toString());
                    break;
                case BOOLEAN:
                    newValue = Boolean.parseBoolean(value.toString());
                    break;
                case DATE:
                    newValue = DataParseUtil.parseDate(value.toString());
                    break;
                case STRING:
                    newValue = value.toString();
                    break;
                case LIST:
                    if (value instanceof Collection) {
                        newValue = value;
                        break;
                    } else {
                        return DataResetErrorEnum.NEW_VALUE_TYPE_ERROR;
                    }
                case OBJECT:
                    if (value instanceof Map) {
                        newValue = value;
                        break;
                    } else {
                        return DataResetErrorEnum.NEW_VALUE_TYPE_ERROR;
                    }
                default:
                    return DataResetErrorEnum.TYPE_ERROR;
            }
        }

        // 设置新值
        boolean success = DataVariableHelper.replaceValue(config.getFieldName(), newValue, false);
        if (!success) {
            return DataResetErrorEnum.FAIL;
        }

        return null;
    }
}
