package com.parch.combine.gui.base.control.panel;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class GUIPanelElement implements IGUIElement {

    private JPanel panel = null;
    private GUIPanelElementTemplate template;
    private Config config;

    public GUIPanelElement(GUIPanelElementTemplate template, Config config) {
        this.template = template == null ? new GUIPanelElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());



        return panel;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

        return true;
    }

    @Override
    public Object getData() {
        List<String> data = new ArrayList<>();

        return data.size() > 0 ? data : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return null;
    }

    public static class Config {
        public Object data;
        public IGUIElement[] elements;
    }
}
