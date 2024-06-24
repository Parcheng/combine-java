package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
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

        if (CheckEmptyUtil.isNotEmpty(this.config.elements)) {
            for (IGUIElement element : this.config.elements) {
                dialog.add(element.build(frame));
            }
        }

        this.dialog.setVisible(this.config.visible);
        return null;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

//        if (this.button != null) {
//            this.button.setText(data.toString());
//        }
//        this.config.text = data.toString();
        return true;
    }

    @Override
    public Object getValue() {
        return null;
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
        public String title;
        public Integer width;
        public Integer height;
        public Boolean visible;
        public IGUIElement[] elements;
    }
}
