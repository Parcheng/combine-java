package com.parch.combine.components.web.elements.settings;

import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.trigger.TriggerEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 页面元素设置
 */
public class WindowSettings extends BaseSettings{

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    private String title;

    @Field(key = "content", name = "内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(type = SubElementSettings.class)
    private SubElementSettings body;

    @Field(key = "closeTriggers", name = "窗口关闭的触发配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldRef(key = WebSettingCanstant.TRIGGER_KEY)
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
