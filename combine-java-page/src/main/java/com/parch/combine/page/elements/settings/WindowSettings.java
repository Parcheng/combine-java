package com.parch.combine.page.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldObject;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class WindowSettings extends BaseSettings{

    @ComponentField(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    private String title;

    @ComponentField(key = "content", name = "内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @ComponentFieldObject(type = SubElementSettings.class)
    private SubElementSettings body;

    @ComponentField(key = "closeTriggers", name = "窗口关闭的触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldRef(key = WebSettingCanstant.TRIGGER_KEY)
    private List<TriggerEntity> closeTriggers;

    public List<TriggerEntity> getCloseTriggers() {
        return closeTriggers;
    }

    public void setCloseTriggers(List<TriggerEntity> closeTriggers) {
        this.closeTriggers = closeTriggers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SubElementSettings getBody() {
        return body;
    }

    public void setBody(SubElementSettings body) {
        this.body = body;
    }
}
