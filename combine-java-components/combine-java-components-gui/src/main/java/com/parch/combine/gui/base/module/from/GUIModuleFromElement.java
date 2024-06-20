package com.parch.combine.gui.base.module.from;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.*;
import java.awt.*;

public class GUIModuleFromElement extends AbsGUIElement<GUIModuleFromTemplate, GUIModuleFromElement.Config> {

    private JButton button = null;

    public GUIModuleFromElement(GUIModuleFromTemplate template, Config config) {
        super("button", template, config, GUIModuleFromTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.button = new JButton();
        super.loadTemplates(this.button, this.sysTemplate.getButton(), this.template.getButton());
        this.button.setText(this.config.text);

//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(frame, "按钮被点击了！");
//            }
//        });

        panel.add(this.button);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        if (this.button != null) {
            this.button.setText(data.toString());
        }
        this.config.text = data.toString();
        return true;
    }

    @Override
    public Object getData() {
        return this.button == null ? config.text : this.button.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIModuleFromElement(this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
