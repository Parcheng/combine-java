package com.parch.combine.components.tool.cache.cleanup;

import com.parch.combine.components.tool.cache.CacheKeyMatchRuleEnum;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 逻辑配置类
 */
public class CacheCleanupLogicConfig extends LogicConfig {

    @Field(key = "domain", name = "要清理的缓存域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    private String domain;

    @Field(key = "key", name = "要清理的缓存KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("不设置则表示全部")
    private String key;

    @Field(key = "keyMatchRule", name = "KEY匹配规则", type = FieldTypeEnum.SELECT, defaultValue = "EXACT")
    @FieldSelect(enumClass = CacheKeyMatchRuleEnum.class)
    private String keyMatchRule;

    @Field(key = "mode", name = "清理模式", type = FieldTypeEnum.SELECT, defaultValue = "ALL")
    @FieldSelect(enumClass = CacheCleanupModeEnum.class)
    private String mode;

    @Field(key = "maxCount", name = "最大清理数量", type = FieldTypeEnum.NUMBER, defaultValue = "-1（无限制）")
    @FieldDesc("如果实际要清理的KEY数量大于该值，则实际清理数量以该值为准")
    private Integer maxCount;

    @Override
    public void init() {
        if (this.domain == null) {
            this.domain = "$common";
        }
        if (this.maxCount == null) {
            this.maxCount = -1;
        }
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getKeyMatchRule() {
        return keyMatchRule;
    }

    public void setKeyMatchRule(String keyMatchRule) {
        this.keyMatchRule = keyMatchRule;
    }
}
