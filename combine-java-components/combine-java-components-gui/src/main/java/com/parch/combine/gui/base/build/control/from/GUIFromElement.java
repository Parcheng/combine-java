package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.*;
import java.util.Map;

public class GUIFromElement extends AbstractGUIComponentElement<GUIFromElementTemplate, GUIFromElement.Config, Map<String, Object>> {

    public GUIFromElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIFromElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "from", template, config, GUIFromElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.buildItems(panel);


//
//        super.registerEvents(this.text, this.config.events);
//        super.addSubComponent(panel, this.text, this.template.getText());

        return panel;
    }

    private void buildItems(JPanel parent) {
        if (this.config.items == null) {
            return;
        }

        int currLine = 0;
        double currLineWeight = 0D;
        float configRate = this.config.rate == null ? 1 : this.config.rate;
        for (ItemConfig item : this.config.items) {
            if (item == null) {
                continue;
            }

            JPanel itemPanel = new JPanel();
            this.buildItem(itemPanel, item);

            float currRate = item.rate == null ? configRate : item.rate;
            if (currLineWeight + currRate > 1) {
                currLineWeight = currRate;
                currLine++;
            }

            super.addSubComponent(parent, itemPanel, this.template.getItem(), this.buildGridConfig(currRate, currLine));
        }
    }

    private void buildItem(JPanel parent, ItemConfig item) {
        int currLine = 0;
        double currLineWeight = 0D;
        float configLabelRate = this.config.labelRate == null ? 1 : this.config.labelRate;
        float configElementRate = this.config.elementRate == null ? 1 : this.config.elementRate;

        // label
        JLabel label = new JLabel();
        if (item.label != null) {
            label.setText(item.label);
        }

        float currLabelRate = item.labelRate == null ? configLabelRate : item.labelRate;
        if (currLineWeight + currLabelRate > 1) {
            currLineWeight = currLabelRate;
            currLine++;
        }

        super.addSubComponent(parent, label, this.template.getLabel(), this.buildGridConfig(currLabelRate, currLine));

        // element
        JPanel elementPanel = new JPanel();
        super.loadTemplates(elementPanel, this.template.getControl());

        Object itemData = null;
        if (item.key != null && this.value != null) {
            itemData = this.value.get(item.key);
        }
        if (itemData == null) {
            itemData = item.defaultValue;
        }
        GUISubElementHelper.build(elementPanel, itemData, new GUISubElementConfig[]{item.element}, this);

        float currElementRate = item.elementRate == null ? configElementRate : item.elementRate;
        if (currLineWeight + currElementRate > 1) {
            currLine++;
        }

        super.addSubComponent(parent, label, this.template.getLabel(), this.buildGridConfig(currElementRate, currLine));
    }

    private ElementGridConfig buildGridConfig(double rate, int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.ALL.getKey());
        itemGridConfig.setWeightX(rate);
        itemGridConfig.setPositionY(line);
        return itemGridConfig;
    }

    @Override
    public boolean setValue(Object data) {
        // TODO
//        if (data == null) {
//            return false;
//        }
//
//        if (this.text != null) {
//            this.text.setText(data.toString());
//        }
//        this.value = data.toString();

        return true;
    }

    @Override
    public Object getValue() {
        // TODO
        return null; //this.text == null ? this.value : this.text.getText();
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIFromElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<Map<String, Object>> {
        public LayoutEnum layout;
        public Float rate;
        public Float labelRate;
        public Float elementRate;
        public ItemConfig[] items;
    }

    public static class ItemConfig {
        public String key;
        public String label;
        public GUISubElementConfig element;
        public Object defaultValue;
        public Float rate;
        public Float labelRate;
        public Float elementRate;
    }

}
