package com.parch.combine.gui.base.build.control.img;

import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;
import java.util.Map;

public class GUIImgElement extends AbsGUIElement<GUIImgElementTemplate, GUIImgElement.Config> {

    private JPanel panel = null;

    public GUIImgElement(String scopeKey, String elementId, Map<String, Object> data, GUIImgElementTemplate template, Config config) {
        super(scopeKey, elementId, data, "img", template, config, GUIImgElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        loadTemplates(this.panel, sysTemplate.getExternal(), template.getExternal());
        panel.add(buildImg());
        return panel;
    }

    private JLabel buildImg() {
        JLabel label = new JLabel();
        ImageIcon icon = new ImageIcon(ResourceFileUtil.getURL(config.path));
        if (config.width != null || config.height != null) {
            if (config.width == null) {
                config.width = icon.getIconWidth();
            }
            if (config.height == null) {
                config.height = icon.getIconHeight();
            }
            Image newImg = icon.getImage().getScaledInstance(config.width, config.height, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        label.setIcon(icon);

        return label;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        config.path = data.toString();
        panel.add(buildImg());
        return true;
    }

    @Override
    public Object getValue() {
        return config.path;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIImgElement(this.scopeKey, this.id, this.data, this.template, this.config);
    }

    public static class Config {
        public String path;
        public Integer x;
        public Integer y;
        public Integer width;
        public Integer height;
    }
}
