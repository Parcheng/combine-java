package com.parch.combine.gui.core.event;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.core.event.trigger.ComponentTriggerProcessor;
import com.parch.combine.gui.core.event.trigger.DialogBoxTriggerProcessor;
import com.parch.combine.gui.core.event.trigger.GUITriggerTypeEnum;
import com.parch.combine.gui.core.event.trigger.InternalTriggerProcessor;

public class EventConfig {

    @Field(key = "eventKey", name = "事件ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("用于引用")
    private String eventKey;

    @Field(key = "eventType", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = GUIEventTypeEnum.class)
    private String eventType;

    @Field(key = "triggerType", name = "触发类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = GUITriggerTypeEnum.class)
    private String triggerType;

    @Field(key = "componentTrigger", name = "组件执行触发配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ComponentTriggerProcessor.Config.class)
    private ComponentTriggerProcessor.Config componentTrigger;

    @Field(key = "dialogBoxTrigger", name = "弹窗执行触发配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(DialogBoxTriggerProcessor.Config.class)
    private DialogBoxTriggerProcessor.Config dialogBoxTrigger;

    private InternalTriggerProcessor.Config internalTrigger;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public ComponentTriggerProcessor.Config getComponentTrigger() {
        return componentTrigger;
    }

    public void setComponentTrigger(ComponentTriggerProcessor.Config componentTrigger) {
        this.componentTrigger = componentTrigger;
    }

    public DialogBoxTriggerProcessor.Config getDialogBoxTrigger() {
        return dialogBoxTrigger;
    }

    public void setDialogBoxTrigger(DialogBoxTriggerProcessor.Config dialogBoxTrigger) {
        this.dialogBoxTrigger = dialogBoxTrigger;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public InternalTriggerProcessor.Config getInternalTrigger() {
        return internalTrigger;
    }

    public void setInternalTrigger(InternalTriggerProcessor.Config internalTrigger) {
        this.internalTrigger = internalTrigger;
    }
}
