package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.settings.config.IOptionSetting;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.common.GUIAlignTypeEnum;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ComponentEvent;

public class DialogBoxTriggerProcessor extends AbstractTriggerProcessor<DialogBoxTriggerProcessor.Config> {

    public DialogBoxTriggerProcessor(JFrame frame, DialogBoxTriggerProcessor.Config config) {
        super(frame, config);
    }

    @Override
    public void trigger(ComponentEvent event) {
        JLabel label = new JLabel(config.getText());
        label.setHorizontalAlignment(GUIAlignTypeEnum.get(config.getAlign()).getValue());
        JOptionPane.showMessageDialog(frame, label, config.getTitle(), JOptionPane.PLAIN_MESSAGE, null);
    }

    public static class Config {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        private String title;

        @Field(key = "text", name = "内容", type = FieldTypeEnum.TEXT, isRequired = true)
        private String text;

        @Field(key = "align", name = "对齐方式", type = FieldTypeEnum.SELECT)
        @FieldSelect(enumClass = GUIAlignTypeEnum.class)
        private String align;

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

        public String getAlign() {
            return align;
        }

        public void setAlign(String align) {
            this.align = align;
        }
    }
}
