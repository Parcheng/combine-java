package com.parch.combine.components.web.elements.trigger;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.enums.TriggerTypeEnum;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldDesc;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.annotations.ComponentFieldSelect;
import com.parch.combine.core.settings.config.FieldTypeEnum;

/**
 * 事件触发配置
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = TriggerCallEntity.class, name = "CALL"),
        @JsonSubTypes.Type(value = TriggerCallUrlEntity.class, name = "CALL_URL"),
        @JsonSubTypes.Type(value = TriggerCallFuncEntity.class, name = "CALL_FUNC"),
        @JsonSubTypes.Type(value = TriggerLoadEntity.class, name = "LOAD"),
        @JsonSubTypes.Type(value = TriggerLoadDataEntity.class, name = "LOAD_DATA"),
        @JsonSubTypes.Type(value = TriggerSkipEntity.class, name = "SKIP"),
        @JsonSubTypes.Type(value = TriggerCustomEntity.class, name = "CUSTOM")
})
public class TriggerEntity {

    @ComponentField(key = "event", name = "触发要监听的事件", type = FieldTypeEnum.TEXT, isRequired = true)
    private String event;

    @ComponentField(key = "type", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @ComponentFieldSelect(enumClass = TriggerTypeEnum.class)
    private TriggerTypeEnum type;

    @ComponentField(key = "success", name = "触发执行成功后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementEntity<?> success;

    @ComponentField(key = "fail", name = "触发执行失败后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementEntity<?> fail;

    @ComponentField(key = "error", name = "触发执行异常后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementEntity<?> error;

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

    public ElementEntity<?> getSuccess() {
        return success;
    }

    public void setSuccess(ElementEntity<?> success) {
        this.success = success;
    }

    public ElementEntity<?> getFail() {
        return fail;
    }

    public void setFail(ElementEntity<?> fail) {
        this.fail = fail;
    }

    public void setType(TriggerTypeEnum type) {
        this.type = type;
    }

    public ElementEntity<?> getError() {
        return error;
    }

    public void setError(ElementEntity<?> error) {
        this.error = error;
    }
}
