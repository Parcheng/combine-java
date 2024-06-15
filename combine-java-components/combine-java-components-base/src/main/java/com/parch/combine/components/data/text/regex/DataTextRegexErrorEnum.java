package com.parch.combine.components.data.text.regex;

import com.parch.combine.core.component.error.IComponentError;

public enum DataTextRegexErrorEnum implements IComponentError {

    REGEX_IS_NULL("分隔符表达式为空", "分隔符配置为空"),
    FAIL("失败", "失败"),
    ;

    private String msg;

    private String showMsg;

    DataTextRegexErrorEnum(String msg, String showMsg) {
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
