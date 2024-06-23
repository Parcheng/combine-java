package com.parch.combine.gui.core.event.trigger;

import javax.swing.JFrame;

public abstract class AbsTriggerProcessor<C> implements ITriggerProcessor{

    protected String id;

    protected JFrame frame;

    protected C config;

    public AbsTriggerProcessor(JFrame frame, C config) {
        this.frame = frame;
        this.config = config;
    }
}
