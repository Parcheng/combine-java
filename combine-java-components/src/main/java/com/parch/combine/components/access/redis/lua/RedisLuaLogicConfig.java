package com.parch.combine.components.access.redis.lua;

import com.parch.combine.core.base.LogicConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldEg;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

public class RedisLuaLogicConfig extends LogicConfig {

    @ComponentField(key = "keys", name = "脚本KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    @ComponentFieldDesc("用于脚本中 KEYS[i] 取值")
    @ComponentFieldEg(eg = "[\"test_key\"]", desc = "集合中只有一个为 test_key 的脚本KEY")
    private List<String> keys;

    @ComponentField(key = "args", name = "脚本参数集合", type = FieldTypeEnum.TEXT, isArray = true)
    @ComponentFieldDesc("用于脚本中 ARGV[i] 取值")
    @ComponentFieldEg(eg = "[\"1\"]", desc = "集合中只有一个为 test_key 的脚本参数")
    private List<String> args;

    @ComponentField(key = "scriptLines", name = "要执行的脚本", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    @ComponentFieldDesc("其中集合每一项表示每一行脚本")
    @ComponentFieldEg(eg = "[\"local num = redis.call('incr', KEYS[1])\",\"return num\", \"end\"]", desc = "执行该语句")
    private List<String> scriptLines;

    public List<String> getKeys() {
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public List<String> getScriptLines() {
        return scriptLines;
    }

    public void setScriptLines(List<String> scriptLines) {
        this.scriptLines = scriptLines;
    }
}
