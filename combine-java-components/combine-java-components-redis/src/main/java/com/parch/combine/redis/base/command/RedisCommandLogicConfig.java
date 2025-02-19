package com.parch.combine.redis.base.command;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldSelect;

public interface RedisCommandLogicConfig extends ILogicConfig {

    @Field(key = "failStop", name = "失败是否停止", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    @FieldEg(eg = "true", desc = "commands 配置的命令如果某条执行失败，则不继续向下执行，直接返回错误")
    Boolean failStop();

    @Field(key = "commands", name = "命令配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(RedisCommand.class)
    @FieldEg(eg = "{\"type\":\"SET\", \"params\":[\"username\", \"NX\", \"10000\", \"#{$r.data001.name}\"]}", desc = "执行设值命令，只有 username 的 KEY存在才生效，值为 ID 为 data001 组件执行结果的 name 字段，有效期为 10000 毫秒")
    RedisCommand[] commands();

    interface RedisCommand {

        @Field(key = "type", name = "命令类型", type = FieldTypeEnum.TEXT, isRequired = true)
        @FieldSelect(enumClass = RedisCommandTypeEnum.class)
        String type();

        @Field(key = "params", name = "命令参数", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        String[] params();
    }
}
