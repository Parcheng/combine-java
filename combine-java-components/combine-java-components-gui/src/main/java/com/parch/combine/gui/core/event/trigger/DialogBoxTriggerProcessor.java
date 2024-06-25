package com.parch.combine.gui.core.event.trigger;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.GUIElementManager;

import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.event.ComponentEvent;
import java.util.List;

public class DialogBoxTriggerProcessor extends AbsTriggerProcessor<DialogBoxTriggerProcessor.Config>{

    private GUIElementManager guiElementManager;

    public DialogBoxTriggerProcessor(GUIElementManager guiElementManager, JFrame frame, DialogBoxTriggerProcessor.Config config) {
        super(frame, config);
        this.guiElementManager = guiElementManager;
    }

    @Override
    public void trigger(ComponentEvent event) {
        JDialog dialog = new JDialog();
        dialog.setTitle(config.getTitle() == null ? CheckEmptyUtil.EMPTY : config.getTitle());
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        int left = frame.getX() + frame.getWidth()/2;
        int top = frame.getY() + frame.getHeight()/2;
        dialog.setBounds(left, top, 200, 100);

        if (CheckEmptyUtil.isNotEmpty(config.elementIds)) {
            for (String elementId : config.elementIds) {
                dialog.add(guiElementManager.get(elementId).build(frame));
            }
        }

        dialog.setVisible(true);
    }

    public static class Config {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        private String title;

        @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER, isRequired = true)
        private Integer width;

        @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER, isRequired = true)
        private Integer height;

        @Field(key = "elementIds", name = "GUI元素ID集合", type = FieldTypeEnum.TEXT, isArray = true)
        private List<String> elementIds;

        public List<String> getElementIds() {
            return elementIds;
        }

        public void setElementIds(List<String> elementIds) {
            this.elementIds = elementIds;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getWidth() {
            return width;
        }

        public void setWidth(Integer width) {
            this.width = width;
        }

        public Integer getHeight() {
            return height;
        }

        public void setHeight(Integer height) {
            this.height = height;
        }
    }
}
