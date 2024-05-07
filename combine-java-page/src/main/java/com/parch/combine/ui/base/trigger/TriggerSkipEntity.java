package com.parch.combine.ui.base.trigger;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.core.component.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

/**
 * 配置类
 */
@ComponentCommonObject(order = 3, key = WebSettingCanstant.TRIGGER_KEY, name = "跳转其他页面触发配置", desc = "当 TYPE = SKIP 时的参数列表")
public class TriggerSkipEntity extends TriggerEntity {

    @Field(key = "url", name = "要跳转的URL", type = FieldTypeEnum.TEXT, isRequired = true)
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
