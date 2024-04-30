package com.parch.combine.component.access.redis;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;


/**
 * Redis设置信息
 */
public class RedisSettingsHandler {

    public static void build(ComponentSettingBuilder builder) {
        builder.addProperty(PropertyTypeEnum.INIT, "nodes", FieldTypeEnum.TEXT, "节点集合",  true, true);
        builder.addPropertyDesc(PropertyTypeEnum.INIT, "nodes", "格式要求：127.0.0.1:8888");
        builder.addProperty(PropertyTypeEnum.INIT, "password", FieldTypeEnum.TEXT, "密码",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "timeout", FieldTypeEnum.NUMBER, "超时时间",   false, false, "2000");
        builder.addProperty(PropertyTypeEnum.INIT, "maxAttempts", FieldTypeEnum.NUMBER, "最大重试次数",  false, false, "10");
        builder.addProperty(PropertyTypeEnum.INIT, "printCommand", FieldTypeEnum.BOOLEAN, "是否打印命令信息", false, false, "true");
        builder.addProperty(PropertyTypeEnum.INIT, "pool", FieldTypeEnum.TEXT, "连接池配置", false, false);
        builder.addProperty(PropertyTypeEnum.INIT, "pool.max", FieldTypeEnum.TEXT, "连接最大数量",  false, false, "10");
        builder.addProperty(PropertyTypeEnum.INIT, "pool.min", FieldTypeEnum.TEXT, "连接最小数量",  false, false, "5");
        builder.addProperty(PropertyTypeEnum.INIT, "pool.timeout", FieldTypeEnum.TEXT, "连接超时时间", false, false, "10000");

        builder.addPropertyEg(PropertyTypeEnum.INIT, "nodes", "[\"127.0.0.1:8888\", \"127.0.0.1:8889\"]", "两个节点，分别为本机的 8888 和 8889 端口");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "password", "123456", "密码为123456");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "timeout", "6000", "超时时间为 6 秒");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "maxAttempts", "10", "最大重试次数 10 次");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.max", "10", "连接池最大连接数10");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.min", "5", "连接池最小连接数5");
        builder.addPropertyEg(PropertyTypeEnum.INIT, "pool.timeout", "10000", "连接超时时间为10000毫秒");
    }
}
