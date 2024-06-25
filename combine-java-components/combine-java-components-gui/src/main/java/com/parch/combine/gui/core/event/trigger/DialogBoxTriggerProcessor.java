package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.event.ComponentEvent;

public class DialogBoxTriggerProcessor extends AbsTriggerProcessor<DialogBoxTriggerProcessor.Config>{

    public DialogBoxTriggerProcessor(JFrame frame, DialogBoxTriggerProcessor.Config config) {
        super(frame, config);
    }

    @Override
    public void trigger(ComponentEvent event) {
        JOptionPane.showMessageDialog(frame, config.getText(), config.getTitle(), JOptionPane.PLAIN_MESSAGE);
    }

    public static class Config {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        private String title;

        @Field(key = "text", name = "内容", type = FieldTypeEnum.TEXT, isRequired = true)
        private String text;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
