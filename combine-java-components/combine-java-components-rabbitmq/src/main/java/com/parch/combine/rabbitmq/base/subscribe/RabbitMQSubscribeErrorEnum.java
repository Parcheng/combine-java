package com.parch.combine.rabbitmq.base.subscribe;

import com.parch.combine.core.component.error.IComponentError;

public enum RabbitMQSubscribeErrorEnum implements IComponentError {
    FAIL("监听消息失败", "监听消息失败"),
    ;

    private String msg;

    private String showMsg;

    RabbitMQSubscribeErrorEnum(String msg, String showMsg) {
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
