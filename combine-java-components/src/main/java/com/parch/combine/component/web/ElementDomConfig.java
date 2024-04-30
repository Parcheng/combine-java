package com.parch.combine.component.web;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;
import com.parch.combine.component.web.helper.DomConfig;

/**
 * HTML元素配置
 */
public class ElementDomConfig extends DomConfig {

    /**
     * 触发配置
     */
    private TriggerEntity trigger;

    @Override
    public TriggerEntity getTrigger() {
        return trigger;
    }

    @Override
    public void setTrigger(TriggerEntity trigger) {
        this.trigger = trigger;
    }
}

