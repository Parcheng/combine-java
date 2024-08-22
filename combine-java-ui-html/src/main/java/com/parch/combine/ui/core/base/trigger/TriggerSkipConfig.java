package com.parch.combine.ui.core.base.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.annotations.CommonObject;

import java.util.List;

/**
 * 配置类
 */
@CommonObject(order = 3, name = "跳转其他页面触发配置", desc = "当 TYPE = SKIP 时的参数列表")
public class TriggerSkipConfig extends TriggerConfig {

    @Field(key = "url", name = "要跳转的URL", type = FieldTypeEnum.TEXT, isRequired = true)
    private String url;

    @Override
    public void init() {}

    @Override
    public List<String> check() {
        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
