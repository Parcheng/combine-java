package com.parch.combine.components.tool.lock;

import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class ToolLockLogicConfig extends LogicConfig {

    @Field(key = "key", name = "锁的KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("不设置该值时，默认所有流程共用一把锁")
    @FieldEg(eg = "user_change", desc = "添加或解除 KEY 为 user_change 的锁")
    private String key;

    @Field(key = "count", name = "加锁/解锁", type = FieldTypeEnum.NUMBER, isRequired = true)
    @FieldDesc("大于0表示加锁，小于0表示解锁")
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
