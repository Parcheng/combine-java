package com.parch.combine.html.base.trigger;

import com.parch.combine.core.common.base.ICheck;
import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.ui.core.base.element.SubElement;

/**
 * 事件触发配置
 */
public abstract class TriggerConfig implements IInit, ICheck {

    @Field(key = "id", name = "触发配置ID", type = FieldTypeEnum.ID)
    private String id;

    @Field(key = "event", name = "触发要监听的事件", type = FieldTypeEnum.TEXT, isRequired = true)
    private String event;

    @Field(key = "type", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = TriggerTypeEnum.class)
    private TriggerTypeEnum type;

    @Field(key = "success", name = "触发执行成功后要渲染的元素配置", type = FieldTypeEnum.ELEMENT)
    // @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    @SubElement
    private Object success;

    @Field(key = "fail", name = "触发执行失败后要渲染的元素配置", type = FieldTypeEnum.ELEMENT)
    // @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    @SubElement
    private Object fail;

    @Field(key = "error", name = "触发执行异常后要渲染的元素配置", type = FieldTypeEnum.ELEMENT)
    // @FieldRef(key = PageSettingCanstant.ELEMENT_ENTITY_KEY)
    @SubElement
    private Object error;

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

    public void setType(TriggerTypeEnum type) {
        this.type = type;
    }

    public Object getSuccess() {
        return success;
    }

    public void setSuccess(Object success) {
        this.success = success;
    }

    public Object getFail() {
        return fail;
    }

    public void setFail(Object fail) {
        this.fail = fail;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
