package com.parch.combine.core.error;

/**
 * 系统异常枚举
 */
public enum SystemErrorEnum implements IComponentError {

    SYSTEM_ERROR("系统异常", "系统异常"),
    COMPONENT_BUILD_ERROR("组件构建异常", "组件构建异常"),
    ;

    private String msg;

    private String showMsg;

    SystemErrorEnum(String msg, String showMsg) {
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
