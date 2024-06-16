package com.parch.combine.gui.base.control.button;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ConstantHelper;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.FlowLayout;

public class GUIButtonElement implements IGUIElement {

    private JButton button = null;
    private GUIButtonElementTemplate template;
    private Config config;

    public GUIButtonElement(GUIButtonElementTemplate template, Config config) {
        this.template = template == null ? new GUIButtonElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ConstantHelper.layout(FlowLayout.LEFT));
        ElementHelper.set(panel, this.template.getExternal());

        this.button = new JButton();
        this.button.setText(this.config.text);

        ElementHelper.set(this.button, this.template.getButton());

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
        return new GUIButtonElement(this.template, this.config);
    }

    public static class Config {
        public String text;
    }
}
