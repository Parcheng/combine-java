package com.parch.combine.components.data.general.filter;

import com.parch.combine.core.error.IComponentError;

/**
 * 数据过滤异常枚举
 */
public enum DataFilterErrorEnum implements IComponentError {

    NONE_RULE("过滤规则不存在", "数据处理异常"),
    CLEAR_ERROR("清理失败", "数据处理失败"),
    REPLACE_ERROR("替换失败", "数据处理失败"),
    RESULT_ERROR("获取执行结果失败", "数据异常"),
    ;

    private String msg;

    private String showMsg;

    DataFilterErrorEnum(String msg, String showMsg) {
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
