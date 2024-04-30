package com.parch.combine.component.access.redis.command;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.tools.ConfigGroupHelper;
import java.util.List;

public class RedisCommandLogicConfig extends LogicConfig {

    private Boolean failStop;

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
