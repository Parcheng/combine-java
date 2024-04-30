package com.parch.combine.component.tool.lock;

import com.parch.combine.core.base.LogicConfig;

/**
 * 逻辑配置类
 */
public class ToolLockLogicConfig extends LogicConfig {

    private String key;

    private Integer count;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
