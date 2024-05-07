package com.parch.combine.ui.base.trigger;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.components.web.elements.enums.TriggerTypeEnum;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

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

    @Field(key = "event", name = "触发要监听的事件", type = FieldTypeEnum.TEXT, isRequired = true)
    private String event;

    @Field(key = "type", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = TriggerTypeEnum.class)
    private TriggerTypeEnum type;

    @Field(key = "success", name = "触发执行成功后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementEntity<?> success;

    @Field(key = "fail", name = "触发执行失败后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
    private ElementEntity<?> fail;

    @Field(key = "error", name = "触发执行异常后要渲染的元素配置", type = FieldTypeEnum.OBJECT)
    @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
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
