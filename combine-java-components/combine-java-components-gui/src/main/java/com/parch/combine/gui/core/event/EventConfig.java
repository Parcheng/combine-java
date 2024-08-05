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

public interface EventConfig {

    @Field(key = "eventKey", name = "事件ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("用于引用")
    String eventKey();

    @Field(key = "eventType", name = "事件类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = GUIEventTypeEnum.class)
    String eventType();

    @Field(key = "triggerType", name = "触发类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @FieldSelect(enumClass = GUITriggerTypeEnum.class)
    String triggerType();

    @Field(key = "componentTrigger", name = "组件执行触发配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(ComponentTriggerProcessor.Config.class)
    ComponentTriggerProcessor.Config componentTrigger();

    @Field(key = "dialogBoxTrigger", name = "弹窗执行触发配置", type = FieldTypeEnum.CONFIG)
    @FieldObject(DialogBoxTriggerProcessor.Config.class)
    DialogBoxTriggerProcessor.Config dialogBoxTrigger();

    InternalTriggerProcessor.Config internalTrigger();
}
