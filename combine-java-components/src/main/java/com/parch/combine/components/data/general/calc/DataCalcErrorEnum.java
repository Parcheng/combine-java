package com.parch.combine.components.data.general.calc;

import com.parch.combine.core.error.IComponentError;

public enum DataCalcErrorEnum implements IComponentError {

    PARAMS_NOT_NUMBER("参数非数字类型", "运算配置错误"),
    CALC_ERROR("执行运算时发生异常", "运算异常"),
    RANDOM_RANG_ERROR("随机数区间配置参数不合法", "运算配置错误"),
    FAIL("运算失败", "运算失败"),
    ;

    private String msg;

    private String showMsg;

    DataCalcErrorEnum(String msg, String showMsg) {
        this.msg = msg;
        this.showMsg = showMsg;

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
