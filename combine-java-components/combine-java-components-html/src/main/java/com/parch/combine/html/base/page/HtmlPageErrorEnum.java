package com.parch.combine.html.base.page;

import com.parch.combine.core.component.error.IComponentError;

public enum HtmlPageErrorEnum implements IComponentError {

    FAIL("构建失败", "构建失败"),
    ;

    private String msg;

    private String showMsg;

    HtmlPageErrorEnum(String msg, String showMsg) {
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
