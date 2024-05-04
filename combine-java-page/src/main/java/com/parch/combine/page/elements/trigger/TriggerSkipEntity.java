package com.parch.combine.page.elements.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "跳转其他页面触发配置", desc = "当 TYPE = SKIP 时的参数列表")
public class TriggerSkipEntity extends TriggerEntity {

    @ComponentField(key = "url", name = "要跳转的URL", type = FieldTypeEnum.TEXT, isRequired = true)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
