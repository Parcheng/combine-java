package com.parch.combine.gui.core.event.trigger;

import javax.swing.JFrame;
import java.awt.event.ComponentEvent;
import java.util.function.Consumer;

public class InternalTriggerProcessor extends AbstractTriggerProcessor{

    private Consumer<ComponentEvent> func;

    public InternalTriggerProcessor(JFrame frame, InternalTriggerProcessor.Config config) {
        super(frame);
        this.func = config.getFunc();
    }

    @Override
    public void trigger(ComponentEvent event) {
        if (this.func != null) {
            this.func.accept(event);
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
