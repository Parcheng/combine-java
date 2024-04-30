package com.parch.combine.component.web.page.management;

import com.parch.combine.core.error.IComponentError;

/**
 * 逻辑判断异常枚举
 */
public enum PageManagementErrorEnum implements IComponentError {

    FAIL("构建页面失败", "构建页面失败：%s");

    private String msg;

    private String showMsg;

    PageManagementErrorEnum(String msg, String showMsg) {
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
