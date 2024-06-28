package com.parch.combine.gui.base.build.control.img;

import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Image;
import java.util.Map;

public class GUIImgElement extends AbstractGUIComponentElement<GUIImgElementTemplate, GUIImgElement.Config, String> {

    private JPanel panel = null;

    public GUIImgElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIImgElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "img", template, config, GUIImgElementTemplate.class);
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
        ImageIcon icon = new ImageIcon(ResourceFileUtil.getURL(this.value));
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
        super.registerEvents(label, this.config.events);

        return label;
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        this.value = data.toString();
        panel.add(buildImg());
        return true;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIImgElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public Integer width;
        public Integer height;
        public EventConfig[] events;
    }
}
