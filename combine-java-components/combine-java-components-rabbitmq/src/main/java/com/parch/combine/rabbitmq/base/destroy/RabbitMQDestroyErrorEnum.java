package com.parch.combine.rabbitmq.base.destroy;

import com.parch.combine.core.component.error.IComponentError;

public enum RabbitMQDestroyErrorEnum implements IComponentError {
    ;

    private String msg;

    private String showMsg;

    RabbitMQDestroyErrorEnum(String msg, String showMsg) {
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
