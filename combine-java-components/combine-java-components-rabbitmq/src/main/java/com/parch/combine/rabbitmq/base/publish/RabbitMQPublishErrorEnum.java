package com.parch.combine.rabbitmq.base.publish;

import com.parch.combine.core.component.error.IComponentError;

public enum RabbitMQPublishErrorEnum implements IComponentError {
    FAIL("消息发送失败", "消息发送失败"),
    ;

    private String msg;

    private String showMsg;

    RabbitMQPublishErrorEnum(String msg, String showMsg) {
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
