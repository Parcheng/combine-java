package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.component.error.IComponentError;

public enum RabbitMQErrorEnum implements IComponentError {
    CREATE_CONN_ERROR("创建链接异常", "创建链接失败"),
    DESTROY_CONN_ERROR("销毁链接异常", "销毁链接失败"),

    CREATE_CHANNEL_ERROR("通道创建失败, 连接或服务可能异常", "创建链接失败"),
    DESTROY_CHANNEL_ERROR("销毁通道异常", "销毁通道失败"),
    EXCHANGE_ERROR("绑定交换机异常", "绑定交换机失败"),
    PUBLISH_ERROR("消息发布异常", "消息发布失败"),
    SUBSCRIBE_ERROR("消息订阅异常", "消息订阅失败"),
    CANCEL_ERROR("取消订阅异常", "取消订阅失败"),
    ;

    private String msg;

    private String showMsg;

    RabbitMQErrorEnum(String msg, String showMsg) {
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
