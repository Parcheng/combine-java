package com.parch.combine.rabbitmq.base.helper;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.tools.thread.ThreadPoolConfig;

public interface RabbitMQConfig {

    @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String key();

    @Field(key = "host", name = "主机地址", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(HostConfig.class)
    HostConfig[] host();

    @Field(key = "virtualHost", name = "虚拟主机地址", type = FieldTypeEnum.TEXT, isRequired = true)
    String virtualHost();

    @Field(key = "username", name = "用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    String username();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    String password();

    @Field(key = "autoMaticRecoveryEnabled", name = "断线重连", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean autoMaticRecoveryEnabled();

    @Field(key = "networkRecoveryInterval", name = "重连时间", type = FieldTypeEnum.NUMBER, defaultValue = "30")
    Integer networkRecoveryInterval();

    @Field(key = "applicationName", name = "应用服务名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String applicationName();

    @Field(key = "clusterName", name = "集群名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String clusterName();

    @Field(key = "pool", name = "线程池配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ThreadPoolConfig.class)
    ThreadPoolConfig pool();

    interface HostConfig {

        @Field(key = "host", name = "主机地址", type = FieldTypeEnum.TEXT, isRequired = true)
        String host();

        @Field(key = "port", name = "端口号", type = FieldTypeEnum.NUMBER, isRequired = true)
        Integer port();
    }
}
