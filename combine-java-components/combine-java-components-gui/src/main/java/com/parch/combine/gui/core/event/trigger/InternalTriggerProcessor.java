package com.parch.combine.gui.core.event.trigger;

import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.util.function.Consumer;

public class InternalTriggerProcessor extends AbstractTriggerProcessor<InternalTriggerProcessor.Config>{

    public InternalTriggerProcessor(JFrame frame, InternalTriggerProcessor.Config config) {
        super(frame, config);
    }

    @Override
    public void trigger(ComponentEvent event) {
        if (this.config.getFunc() != null) {
            this.config.getFunc().accept(event);
        }
    }

    public static class Config {

        private Consumer<ComponentEvent> func;

        public Consumer<ComponentEvent> getFunc() {
            return func;
        }

        public void setFunc(Consumer<ComponentEvent> func) {
            this.func = func;
        }
    }
}
