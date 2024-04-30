package com.parch.combine.components.access.redis.command;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.tools.ConfigGroupHelper;

import java.util.List;

public class RedisCommandLogicConfig extends LogicConfig {

    @ComponentField(key = "failStop", name = "失败是否停止", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    @ComponentFieldEg(eg = "true", desc = "commands 配置的命令如果某条执行失败，则不继续向下执行，直接返回错误")
    private Boolean failStop;

    @ComponentField(key = "commands", name = "命令配置集合", type = FieldTypeEnum.GROUP, isArray = true, isRequired = true)
    @ComponentFieldDesc("命令和命令参数配置集合，可使用#{}变量取值符")
    @ComponentFieldGroup(index = 0, name = "命令类型", type = FieldTypeEnum.SELECT)
    @ComponentFieldGroupSelect(index = 0, enumClass = RedisCommandTypeEnum.class)
    @ComponentFieldGroup(index = 1, name = "命令参数, 多个用空格分隔", type = FieldTypeEnum.TEXT, isRequired = false)
    @ComponentFieldEg(eg = "[\"SET username NX 10000 #{$r.data001.name}\"]", desc = "执行设值命令，只有 username 的 KEY存在才生效，值为 ID 为 data001 组件执行结果的 name 字段，有效期为 10000 毫秒")
    private List<RedisCommand> commands;

    public static class RedisCommand {

        private RedisCommandTypeEnum type;

        private String[] params;

        public RedisCommandTypeEnum getType() {
            return type;
        }

        public void setType(RedisCommandTypeEnum type) {
            this.type = type;
        }

        public String[] getParams() {
            return params;
        }

        public void setParams(String[] params) {
            this.params = params;
        }
    }

    @Override
    public void init() {
        super.init();
        if (this.failStop == null) {
            this.failStop =false;
        }
    }

    public List<RedisCommand> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = ConfigGroupHelper.buildItemList(commands, itemStr -> {
            RedisCommand item = new RedisCommand();
            item.setType(RedisCommandTypeEnum.get(ConfigGroupHelper.getConfigByIndex(itemStr,0)));
            item.setParams(ConfigGroupHelper.getConfigsByIndex(itemStr, 1, itemStr.length -1));
            return item;
        });
    }

    public Boolean getFailStop() {
        return failStop;
    }

    public void setFailStop(Boolean failStop) {
        this.failStop = failStop;
    }
}
