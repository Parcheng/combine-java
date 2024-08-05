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

public class DialogBoxTriggerProcessor extends AbstractTriggerProcessor {

    private final String title;
    private final String text;
    private final String align;

    public DialogBoxTriggerProcessor(JFrame frame, DialogBoxTriggerProcessor.Config config) {
        super(frame);
        this.title = config.title();
        this.text = config.text();
        this.align = config.align();
    }

    @Override
    public void trigger(ComponentEvent event) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(GUIAlignTypeEnum.get(align).getValue());
        JOptionPane.showMessageDialog(frame, label, title, JOptionPane.PLAIN_MESSAGE, null);
    }

    public interface Config {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        String title();

        @Field(key = "text", name = "内容", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "align", name = "对齐方式", type = FieldTypeEnum.SELECT)
        @FieldSelect(enumClass = GUIAlignTypeEnum.class)
        String align();
    }
}
