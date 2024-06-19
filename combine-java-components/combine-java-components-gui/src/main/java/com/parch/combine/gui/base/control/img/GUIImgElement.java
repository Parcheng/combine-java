package com.parch.combine.gui.base.control.img;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public class GUIImgElement extends AbsGUIElement<GUIImgElementTemplate, GUIImgElement.Config> {

    private JPanel panel = null;

    public GUIImgElement(GUIImgElementTemplate template, Config config) {
        super("button", template, config, GUIImgElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);
                ImageIcon icon = new ImageIcon(config.path);
                graphics.drawImage(icon.getImage(), config.x, config.y, this);
            }
        };

        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());
        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        config.path = data.toString();
        panel.repaint();
        return true;
    }

    @Override
    public Object getData() {
        return config.path;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIImgElement(this.template, this.config);
    }

    public static class Config {
        public String path;
        public Integer x;
        public Integer y;
    }
}
