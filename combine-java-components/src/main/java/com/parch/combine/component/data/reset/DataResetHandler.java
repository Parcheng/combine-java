package com.parch.combine.component.data.reset;

import com.parch.combine.core.tools.variable.DataTypeEnum;
import com.parch.combine.common.util.DataParseUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.tools.variable.DataVariableHelper;

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
            case OBJECT:
            case LIST:
                return "暂不支持对象和集合类型";
            case NONE:
                return "数据类型不合规";
            default:
                return null;
        }

        return null;
    }

    /**
     * 重设值
     *
     * @param config 配置
     * @return 结果
     */
    public static DataResetErrorEnum reset(DataResetLogicConfig.DataResetItem config) {
        Object newValue;

        boolean isForce = false;
        DataTypeEnum dataType = DataTypeEnum.get(config.getType());
        switch (dataType) {
            case NUMBER:
                newValue = DataParseUtil.parseNumber(config.getValue());
                break;
            case BOOLEAN:
                newValue = Boolean.parseBoolean(config.getValue());
                break;
            case DATE:
                newValue = DataParseUtil.parseDate(config.getValue());
                break;
            default:
                newValue = config.getValue();
                break;
        }

        // 新值为空
        if (newValue == null) {
            return DataResetErrorEnum.NEW_VALUE_IS_NULL;
        }

        // 设置新值
        boolean success = DataVariableHelper.replaceValue(config.getFieldName(), newValue, isForce);
        if (!success) {
            return DataResetErrorEnum.FAIL;
        }

        return null;
    }
}
