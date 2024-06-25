package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JDialog;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.util.Map;

public class GUIDialogBoxElement extends AbsGUIElement<GUIDialogBoxElementTemplate, GUIDialogBoxElement.Config> {

    private JDialog dialog;

    public GUIDialogBoxElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIDialogBoxElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "dialogbox", template, config, GUIDialogBoxElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.dialog = new JDialog();

        this.dialog.setTitle(this.config.title == null ? CheckEmptyUtil.EMPTY : this.config.title);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setModal(true);
        this.dialog.setLayout(new BorderLayout());

        int left = frame.getX() + frame.getWidth()/2;
        int top = frame.getY() + frame.getHeight()/2;
        this.dialog.setBounds(left, top, this.config.width, this.config.height);

        JComponent[] body = GUISubElementHelper.build(data, this.config.elementConfigs, this);
        for (JComponent item : body) {
            this.dialog.add(item);
        }

        this.dialog.setVisible(this.config.visible);
        return null;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        return GUISubElementHelper.setValue(this.config.data, this.config.elementConfigs);
    }

    @Override
    public Object getValue() {
        if (this.config.elementConfigs == null) {
            return this.config.data;
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

    @Override
    public void setVisible(Boolean isVisible) {
        if (this.dialog == null || isVisible == null) {
            return;
        }

        this.dialog.setVisible(isVisible);
    }

    public static class Config {
        public Object data;
        public String title;
        public Integer width;
        public Integer height;
        public Boolean visible;
        public GUISubElementConfig[] elementConfigs;
    }
}
