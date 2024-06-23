package com.parch.combine.gui.base.build.control.dialogbox;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.*;
import java.util.Map;


public class GUIDialogBoxElement extends AbsGUIElement<GUIDialogBoxElementTemplate, GUIDialogBoxElement.Config> {

    private JPanel panel = null;

    public GUIDialogBoxElement(String scopeKey, String elementId, Map<String, Object> data, GUIDialogBoxElementTemplate template, Config config) {
        super(scopeKey, elementId, data, "dialogbox", template, config, GUIDialogBoxElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(this.panel, this.sysTemplate.getExternal(), this.template.getExternal());

//        JButton button = new JButton("Open Sub Popup");
//        button.setBounds(50, 50, 150, 30);
//        button.addActionListener(e -> {
//            // 创建一个子弹窗
//            JDialog subPopup = new JDialog(frame, "Sub Popup", true);
//            subPopup.setSize(200, 100);
//            subPopup.setLocationRelativeTo(null); // 将子弹窗设置为居中显示
//
//            JLabel label = new JLabel("弹窗功能暂未实现");
//             label.setHorizontalAlignment(SwingConstants.CENTER);
//
//            subPopup.add(label);
//            subPopup.setVisible(true);
//        });

//        this.panel.add(label);
        return this.panel;
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
        // this.button == null ? config.text : this.button.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIDialogBoxElement(this.scopeKey, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public EventConfig[] events;
    }
}
