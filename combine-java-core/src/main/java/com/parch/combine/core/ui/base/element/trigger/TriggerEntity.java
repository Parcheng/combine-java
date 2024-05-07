package com.parch.combine.core.ui.base.element.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.PageSettingCanstant;

/**
 * 事件触发配置
 */
public abstract class TriggerEntity {

    @Field(key = "event", name = "触发要监听的事件", type = FieldTypeEnum.TEXT, isRequired = true)
    private String event;

    @Field(key = "type", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = TriggerTypeEnum.class)
    private TriggerTypeEnum type;

    @Field(key = "success", name = "触发执行成功后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementConfig<?, ?> success;

    @Field(key = "fail", name = "触发执行失败后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementConfig<?, ?> fail;

    @Field(key = "error", name = "触发执行异常后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementConfig<?, ?> error;

    public TriggerTypeEnum getType() {
        return type;
    }

    public void setType(String type) {
        this.type = TriggerTypeEnum.get(type);
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public ElementConfig<?, ?> getSuccess() {
        return success;
    }

    public void setSuccess(ElementConfig<?, ?> success) {
        this.success = success;
    }

    public ElementConfig<?, ?> getFail() {
        return fail;
    }

    public void setFail(ElementConfig<?, ?> fail) {
        this.fail = fail;
    }

    public ElementConfig<?, ?> getError() {
        return error;
    }

    public void setError(ElementConfig<?, ?> error) {
        this.error = error;
    }
}
