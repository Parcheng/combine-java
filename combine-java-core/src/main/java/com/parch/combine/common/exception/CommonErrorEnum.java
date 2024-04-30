package com.parch.combine.common.exception;

public enum CommonErrorEnum implements IBaseError{
    SYSTEM_ERROR("系统错误", "系统繁忙，请稍后再试"),
    FLOW_COMPONENT_IS_NULL("流程不存在", "流程不存在"),
    FLOW_IS_PROTECTED("无法访问受保护流程", "无法访问受保护流程"),
    FLOW_UN_OPEN_REGISTER("流程未开放注册", "流程未开放注册"),
    CONFIG_ERROR("[%s]配置错误,取值范围 %s-%s", "系统配置错误"),
    JSON_SERIALIZE_ERROR("JSON序列化异常,%s", "JSON序列化异常"),
    JSON_DESERIALIZE_ERROR("JSON反序列化异常,%s", "JSON反序列化异常");

    private String errorMsg;
    private String showMsg;

    CommonErrorEnum(String errorMsg, String showMsg) {
        this.errorMsg = errorMsg;
        this.showMsg = showMsg;
    }


    @Override
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
