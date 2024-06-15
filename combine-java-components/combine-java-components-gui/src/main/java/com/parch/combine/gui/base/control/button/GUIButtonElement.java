package com.parch.combine.gui.base.control.button;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.*;

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
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        button = new JButton();
        button.setText(config.text);
        //button.setBackground(ElementStyleConstant.BG_COLOR);
        // button.setBorder(new EmptyBorder(10, 10, 10, 10));
        // button.setBorder(new LineBorder(ElementStyleConstant.BORDER_COLOR, 1));
        //button.setMargin(new Insets(2, 10, 2, 10));

        ElementHelper.set(button, template.getButton());

//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(frame, "按钮被点击了！");
//            }
//        });

        panel.add(button);
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (button == null || data == null) {
            return false;
        }

        button.setText(data.toString());
        return true;
    }

    @Override
    public Object getData() {
        return button.getText();
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    public static class Config {
        public String text;
    }
}
