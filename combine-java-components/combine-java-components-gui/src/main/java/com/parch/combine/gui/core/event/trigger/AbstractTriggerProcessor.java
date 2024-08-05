package com.parch.combine.gui.core.event.trigger;

import javax.swing.JFrame;

public abstract class AbstractTriggerProcessor implements ITriggerProcessor{

    protected String id;

    protected JFrame frame;

    public AbstractTriggerProcessor(JFrame frame) {
        this.frame = frame;
    }
}
