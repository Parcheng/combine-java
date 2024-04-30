package com.parch.combine.component.access.redis.lua;

import com.parch.combine.core.base.LogicConfig;

import java.util.List;

public class RedisLuaLogicConfig extends LogicConfig {

    private List<String> keys;

    private List<String> args;

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
