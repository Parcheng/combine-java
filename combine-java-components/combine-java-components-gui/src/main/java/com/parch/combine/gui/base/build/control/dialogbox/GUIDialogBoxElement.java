package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbstractGUIWindowElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JDialog;
import javax.swing.JComponent;
import java.awt.*;
import java.util.Map;

public class GUIDialogBoxElement extends AbstractGUIWindowElement<GUIDialogBoxElementTemplate, GUIDialogBoxElement.Config, Object> {

    public GUIDialogBoxElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIDialogBoxElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "dialogbox", template, config, GUIDialogBoxElementTemplate.class);
    }

    @Override
    public Window build() {
        JDialog dialog = new JDialog();

        dialog.setTitle(this.config.title == null ? CheckEmptyUtil.EMPTY : this.config.title);
        dialog.setLocationRelativeTo(null);
        dialog.setModal(true);
        dialog.setLayout(new BorderLayout());

        int left = frame.getX() + frame.getWidth()/2;
        int top = frame.getY() + frame.getHeight()/2;
        dialog.setBounds(left, top, this.config.width, this.config.height);

        JComponent[] body = GUISubElementHelper.build(data, this.config.elementConfigs, this);
        for (JComponent item : body) {
            dialog.add(item);
        }

        return dialog;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = data;
        return GUISubElementHelper.setValue(this.value, this.config.elementConfigs);
    }

    @Override
    public Object getValue() {
        if (this.config.elementConfigs == null) {
            return this.value;
        }

        return GUISubElementHelper.getValue(this.config.elementConfigs);
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIDialogBoxElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<Object> {
        public String title;
        public Integer width;
        public Integer height;
        public GUISubElementConfig[] elementConfigs;
    }
}
