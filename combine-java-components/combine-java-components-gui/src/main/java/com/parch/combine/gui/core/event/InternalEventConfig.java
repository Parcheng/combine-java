package com.parch.combine.gui.core.event;

import com.parch.combine.gui.core.event.trigger.ComponentTriggerProcessor;
import com.parch.combine.gui.core.event.trigger.DialogBoxTriggerProcessor;
import com.parch.combine.gui.core.event.trigger.GUITriggerTypeEnum;
import com.parch.combine.gui.core.event.trigger.InternalTriggerProcessor;

import java.awt.event.ComponentEvent;
import java.util.function.Consumer;

public class InternalEventConfig implements EventConfig {

    private final String key;
    private final GUIEventTypeEnum eventType;
    private final GUITriggerTypeEnum triggerType;
    private final InternalTriggerProcessor.Config config;

    public InternalEventConfig(String key, GUIEventTypeEnum eventType, GUITriggerTypeEnum triggerType, Consumer<ComponentEvent> func) {
        this.key = key;
        this.eventType = eventType;
        this.triggerType = triggerType;

        InternalTriggerProcessor.Config config = new InternalTriggerProcessor.Config();
        config.setFunc(func);
        this.config = config;
    }

    @Override
    public String eventKey() {
        return this.key;
    }

    @Override
    public String eventType() {
        return eventType.getKey();
    }

    @Override
    public String triggerType() {
        return triggerType.getKey();
    }

    @Override
    public ComponentTriggerProcessor.Config componentTrigger() {
        return null;
    }

    @Override
    public DialogBoxTriggerProcessor.Config dialogBoxTrigger() {
        return null;
    }

    @Override
    public InternalTriggerProcessor.Config internalTrigger() {
        return config;
    }
}
