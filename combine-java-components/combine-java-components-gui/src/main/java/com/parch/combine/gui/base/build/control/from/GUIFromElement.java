package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GUIFromElement extends AbstractGUIComponentElement<GUIFromElementTemplate, GUIFromElement.Config, Map<String, Object>> {

    private GUISubElementConfig[] subConfigs;

    public GUIFromElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIFromElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "from", template, config, GUIFromElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());
        this.buildItems(panel);
        return panel;
    }

    private void buildItems(JPanel parent) {
        if (this.config.items == null) {
            return;
        }

        int currCol = 0;
        int currLine = 1;
        double currLineWeight = 0D;
        float configRate = this.config.rate == null ? 1 : this.config.rate;
        this.subConfigs = new GUISubElementConfig[this.config.items.length];
        for (int i = 0; i < this.config.items.length; i++) {
            ItemConfig item = this.config.items[i];
            if (item == null) {
                continue;
            }

            float currRate = item.rate == null ? configRate : item.rate;
            if (currLineWeight + currRate > 1) {
                currLineWeight = 0D;
                currCol = 0;
                currLine++;
            }
            currLineWeight += currRate;

            JPanel itemPanel = new JPanel();
            super.addSubComponent(parent, itemPanel, this.template.getItem(), this.buildGridConfig(currRate, currCol++, currLine));
            // 必须放 addSubComponent 后面，否则不生效
            this.buildItem(itemPanel, item, i);
        }
    }

    private void buildItem(JPanel parent, ItemConfig item, int index) {
        int currLine = 1;
        float configLabelRate = this.config.labelRate == null ? 1 : this.config.labelRate;
        float configElementRate = this.config.elementRate == null ? 1 : this.config.elementRate;
        double currLineWeight = configLabelRate;

        // label
        JLabel label = new JLabel();
        if (item.label != null) {
            label.setText(item.label);
        }

        float currLabelRate = item.labelRate == null ? configLabelRate : item.labelRate;
        super.addSubComponent(parent, label, this.template.getLabel(), this.buildGridConfig(currLabelRate, 0, currLine));

        // element
        JPanel elementPanel = new JPanel();
        super.loadTemplates(elementPanel, this.template.getControl());

        GUISubElementConfig subConfig = GUISubElementHelper.copyAndBuild(this.value, item, this);
        this.subConfigs[index] = subConfig;

        int currElementCol = 1;
        float currElementRate = item.elementRate == null ? configElementRate : item.elementRate;
        if (currLineWeight + currElementRate > 1) {
            currElementCol = 0;
            currLine++;
        }

        super.addSubComponent(parent, subConfig.buildResult, this.template.getControl(), this.buildGridConfig(currElementRate, currElementCol, currLine));
    }

    private ElementGridConfig buildGridConfig(double rate, int col, int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.NONE.getKey());
        itemGridConfig.setWeightX(rate);
        itemGridConfig.setPositionX(col);
        itemGridConfig.setPositionY(line);
        return itemGridConfig;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setValue(Object data) {
        if (data == null) {
            data = Collections.emptyMap();;
        }
        if (!(data instanceof Map)) {
            return false;
        }

        this.value = (Map<String, Object>) data;
        return GUISubElementHelper.setValue(this.value, this.subConfigs);
    }

    @Override
    public Object getValue() {
        if (this.subConfigs == null) {
            return new HashMap<String, Object>();
        }

        return GUISubElementHelper.getValue(this.subConfigs);
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

    public static class ItemConfig extends GUISubElementConfig{
        public String label;
        public Float rate;
        public Float labelRate;
        public Float elementRate;
    }
}
