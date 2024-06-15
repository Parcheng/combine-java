package com.parch.combine.redis.base;

import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;


public interface RedisInitConfig extends IInitConfig {

    @Field(key = "nodes", name = "节点集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    @FieldDesc("格式要求：127.0.0.1:8888")
    @FieldEg(eg = "[\"127.0.0.1:8888\", \"127.0.0.1:8889\"]", desc = "两个节点，分别为本机的 8888 和 8889 端口")
    String[] nodes();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "123456", desc = "密码为123456")
    String password();

    @Field(key = "pool", name = "连接池配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(Pool.class)
    Pool pool();

    @Field(key = "timeout", name = "超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "2000")
    @FieldEg(eg = "6000", desc = "超时时间为 6 秒")
    Integer timeout();

    @Field(key = "maxAttempts", name = "最大重试次数", type = FieldTypeEnum.NUMBER, defaultValue = "10")
    @FieldEg(eg = "10", desc = "最大重试次数 10 次")
    Integer maxAttempts();

    @Field(key = "printCommand", name = "是否打印命令信息", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean printCommand();

    interface Pool {

        @Field(key = "max", name = "连接最大数量", type = FieldTypeEnum.NUMBER, defaultValue = "10")
        @FieldEg(eg = "10", desc = "连接池最大连接数10")
        Integer max();

        @Field(key = "min", name = "连接最小数量", type = FieldTypeEnum.NUMBER, defaultValue = "5")
        @FieldEg(eg = "5", desc = "连接池最小连接数5")
        Integer min();

        @Field(key = "timeout", name = "连接超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "10000")
        @FieldEg(eg = "10000", desc = "连接超时时间为10000毫秒")
        Long timeout();
    }
}
