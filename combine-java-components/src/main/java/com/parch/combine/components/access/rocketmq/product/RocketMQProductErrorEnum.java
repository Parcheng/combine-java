package com.parch.combine.components.access.rocketmq.product;

import com.parch.combine.core.component.error.IComponentError;

public enum RocketMQProductErrorEnum implements IComponentError {
    FAIL("消息发送失败", "消息发送失败"),
    ;

    private String msg;

    private String showMsg;

    RocketMQProductErrorEnum(String msg, String showMsg) {
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
