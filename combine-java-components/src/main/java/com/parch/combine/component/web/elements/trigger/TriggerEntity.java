package com.parch.combine.component.web.elements.trigger;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.component.web.elements.enums.TriggerTypeEnum;
import com.parch.combine.component.web.elements.entity.ElementEntity;

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

    private String event;

    private TriggerTypeEnum type;

    private ElementEntity<?> success;

    private ElementEntity<?> fail;

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
