package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIMenuElement extends AbstractGUIComponentElement<GUIMenuElementTemplate, GUIMenuElement.Config, String> {

    private static Integer MAX_LAYER = 99;
    private JPanel panel = null;

    private Map<String, JPanel> itemMap;
    private Map<String, String> keyUpLevelMap;

    public GUIMenuElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIMenuElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "menu", template, config, GUIMenuElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());
        this.buildMenu();
        return panel;
    }

    private void buildMenu() {
        this.itemMap = new HashMap<>();
        this.keyUpLevelMap = new HashMap<>();

        JPanel mainMenu = new JPanel();
        super.addSubComponent(this.panel, mainMenu, this.template.getBar(), this.buildGridConfig(1, 1));

        int colIndex = 1;
        if (CheckEmptyUtil.isNotEmpty(this.config.title)) {
            JButton title = new JButton(this.config.title);
            super.addSubComponent(mainMenu, title, this.template.getTitle(), this.buildGridConfig(colIndex, 1));
        }

        for (ConfigDataItem item : this.config.items) {
            if (CheckEmptyUtil.isEmpty(item.key)) {
                continue;
            }

            JButton itemMenu = new JButton(item.text);
            super.addSubComponent(mainMenu, itemMenu, this.template.getItem(), this.buildGridConfig(colIndex, 1));
            this.buildSubMenu(item.items, 2, item.key);

            this.keyUpLevelMap.put(item.key, null);
            // this.itemMap.put(item.key, itemMenu);
        }
    }

    private void buildSubMenu(ConfigDataItem[] items, int lineIndex, String parentKey) {
        if (lineIndex > MAX_LAYER) {
            return;
        }

        JPanel subMenu = new JPanel();
        super.addSubComponent(this.panel, subMenu, getElementConfigByLayer(lineIndex - 2), this.buildGridConfig(1, lineIndex));
        subMenu.setVisible(false);

        int colIndex = 1;
        for (ConfigDataItem item : items) {
            JButton itemMenu = new JButton(item.text);
            super.addSubComponent(subMenu, itemMenu, this.template.getMainItem(), this.buildGridConfig(colIndex, 1));
            this.buildSubMenu(item.items, lineIndex + 1, item.key);
            keyUpLevelMap.put(item.key, parentKey);
        }
    }

    private ElementGridConfig buildGridConfig(int col, int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.NONE.getKey());
        itemGridConfig.setPositionX(col);
        itemGridConfig.setPositionY(line);
        return itemGridConfig;
    }

    private ElementConfig getElementConfigByLayer(int layer) {
        ElementConfig[] templateItems = this.template.getSubBars();
        if (CheckEmptyUtil.isEmpty(templateItems)) {
            return new ElementConfig();
        }

        return templateItems[(layer)%templateItems.length];
    }

    private boolean hasChecked(String key, int layer) {
//        if (key == null || this.value == null || this.value.length <= layer) {
//            return false;
//        }

        return false; // key.equals(this.value[layer]);
    }

    @Override
    public boolean setValue(Object data) {
        this.value = data == null ? null : data.toString();
        // TODO Checked func
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
        return new GUIMenuElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String> {
        public String title;
        public ConfigDataItem[] items;
        public EventConfig[] events;
    }

    public static class ConfigDataItem {
        public String key;
        public String text;
        public ConfigDataItem[] items;
    }
}
