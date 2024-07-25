package com.parch.combine.gui.base.build.control.paging;

import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GUIPagingElement extends AbstractGUIComponentElement<GUIPagingElementTemplate, GUIPagingElement.Config, GUIPagingElement.ConfigValue> {

    private JLabel text = null;

    public GUIPagingElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIPagingElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "paging", template, config, GUIPagingElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

//        this.text = new JLabel();
//        this.text.setText(this.value);
//
//        super.registerEvents(this.text, this.config.events);
//        super.addSubComponent(panel, this.text, this.template.getText());

        return panel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setValue(Object data) {
        boolean success = false;
        if (data instanceof Map) {
            Map<String, Object> valueMap = (Map<String, Object>) data;
            Object page = valueMap.get("page");
            if (DataTypeIsUtil.isInteger(page)) {
                this.value.page = (int) page;
                success = true;
            }
            Object pageSize = valueMap.get("pageSize");
            if (DataTypeIsUtil.isInteger(pageSize)) {
                this.value.pageSize = (int) pageSize;
                success = true;
            }
            Object dataCount = valueMap.get("dataCount");
            if (DataTypeIsUtil.isInteger(dataCount)) {
                this.value.dataCount = (int) dataCount;
                success = true;
            }
        } else if (DataTypeIsUtil.isInteger(data)) {
            this.value.page = (int) data;
            success = true;
        }

        if (success) {
            // TODO
        }

        return success;
    }

    @Override
    public Object getValue() {
        Map<String, Object> value = new HashMap<>();
        value.put("page", this.value.page);
        value.put("pageSize", this.value.pageSize);
        value.put("dataCount", this.value.dataCount);
        return value;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIPagingElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<ConfigValue> {
    }

    public static class ConfigValue {
        public int page;
        public int pageSize;
        public long dataCount;
    }
}
