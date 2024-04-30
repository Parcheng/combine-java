package com.parch.combine.component.access.redis.command;

import com.parch.combine.component.access.redis.RedisSettingsHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class RedisCommandSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("redis.command", "Redis命令组件", true, RedisCommandComponent.class);
//        builder.addDesc("");

        RedisSettingsHandler.build(builder);


        builder.addProperty(PropertyTypeEnum.LOGIC, "failStop", FieldTypeEnum.BOOLEAN, "失败是否停止", true, false, "false");
        builder.addProperty(PropertyTypeEnum.LOGIC, "commands", FieldTypeEnum.GROUP, "命令配置集合",true, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "commands", "命令和命令参数配置集合，可使用#{}变量取值符");
        for (RedisCommandTypeEnum item : RedisCommandTypeEnum.values()) {
            if (item.isValid()) {
                builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "commands", item.getKey() + " - " + item.getName() + " | " + item.getDesc());
            }
        }
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "commands", "命令类型", FieldTypeEnum.SELECT, true,  Arrays.asList(RedisCommandTypeEnum.values()));
        builder.addPropertyGroup(PropertyTypeEnum.LOGIC, "commands", "命令参数, 多个用空格分隔", FieldTypeEnum.TEXT, true,  null);



        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "failStop", "true", "commands 配置的命令如果某条执行失败，则不继续向下执行，直接返回错误");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "commands", "[...]", "要直接执行命令集合");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "commands", "[\"SET username NX 10000 #{$r.data001.name}\"]",
                "执行设值命令，只有 username 的 KEY存在才生效，值为 ID 为 data001 组件执行结果的 name 字段，有效期为 10000 毫秒");



        builder.setResult("命令的执行结果集合", false);
        return builder.get();
    }
}
