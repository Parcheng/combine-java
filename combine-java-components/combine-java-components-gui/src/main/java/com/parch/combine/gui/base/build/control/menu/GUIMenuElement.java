package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;
import com.parch.combine.gui.core.event.GUIEventTypeEnum;
import com.parch.combine.gui.core.event.InternalEventConfig;
import com.parch.combine.gui.core.event.trigger.GUITriggerTypeEnum;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIMenuElement extends AbstractGUIComponentElement<GUIMenuElementTemplate, GUIMenuElement.Config, String> {

    private final static int MAX_LAYER = 99;

    private JPanel panel = null;
    private Map<String, MenuItemCache> itemMap;
    private Map<String, JPanel> subBarMap;

    private JLabel checkedMainItem = null;
    private final List<JLabel> checkedItems = new ArrayList<>();

    private final List<JPanel> showBar = new ArrayList<>();

    public GUIMenuElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIMenuElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "menu", template, config, GUIMenuElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(this.panel, this.template.getExternal());
        this.buildMenu();
        return this.panel;
    }

    private void buildMenu() {
        this.itemMap = new HashMap<>(16);
        this.subBarMap = new HashMap<>(16);

        JPanel mainMenu = new JPanel();
        super.addSubComponent(this.panel, mainMenu, this.template.getBar(), this.buildGridConfig(1, 1));

        int colIndex = 1;
        if (CheckEmptyUtil.isNotEmpty(this.config.title)) {
            JButton title = new JButton(this.config.title);
            super.addSubComponent(mainMenu, title, this.template.getTitle(), this.buildGridConfig(colIndex, 1));
            colIndex++;
        }

        for (ConfigDataItem item : this.config.items) {
            if (CheckEmptyUtil.isEmpty(item.key)) {
                continue;
            }

            JLabel itemMenu = this.buildItemMenu(item.key, item.text, 1, this.config.events);
            super.addSubComponent(mainMenu, itemMenu, this.template.getMainItem(), this.buildGridConfig(colIndex, 1));
            this.buildSubMenu(item.items, 2, item.key);
            this.itemMap.put(item.key, new MenuItemCache(item.key, itemMenu, mainMenu, null));
            colIndex++;
        }

        this.checked();
    }

    private void buildSubMenu(ConfigDataItem[] items, int lineIndex, String parentKey) {
        if (items == null || items.length == 0 || lineIndex > MAX_LAYER) {
            return;
        }

        JPanel subMenu = new JPanel();
        super.addSubComponent(this.panel, subMenu, this.getElementConfigByLayer(lineIndex - 2), this.buildGridConfig(1, lineIndex));
        subMenu.setVisible(false);

        int colIndex = 1;
        for (ConfigDataItem item : items) {
            JLabel itemMenu = this.buildItemMenu(item.key, item.text, lineIndex, this.config.events);
            super.addSubComponent(subMenu, itemMenu, this.template.getItem(), this.buildGridConfig(colIndex, 1));
            this.buildSubMenu(item.items, lineIndex + 1, item.key);
            if (item.items == null || item.items.length == 0) {
                super.registerEvents(itemMenu, new InternalEventConfig(null, GUIEventTypeEnum.CLICK,
                        GUITriggerTypeEnum.INTERNAL, event -> this.hideSubBar(1)));
            }
            this.itemMap.put(item.key, new MenuItemCache(item.key, itemMenu, subMenu, parentKey));
            this.subBarMap.put(parentKey, subMenu);
            colIndex++;
        }
    }

    private JLabel buildItemMenu(String key, String text, int lineIndex, EventConfig[] events) {
        JLabel itemMenu = new JLabel(text);
        itemMenu.setHorizontalAlignment(SwingConstants.CENTER);
        super.registerEvents(itemMenu, new InternalEventConfig(null, GUIEventTypeEnum.CLICK,
                GUITriggerTypeEnum.INTERNAL, event -> this.setValue(key)));
        super.registerEvents(itemMenu, new InternalEventConfig(null, GUIEventTypeEnum.MOVE_IN,
                GUITriggerTypeEnum.INTERNAL, event -> this.showSubBar(key, lineIndex - 1)));
        super.registerEvents(itemMenu, events);
        return itemMenu;
    }

    private ElementGridConfig buildGridConfig(int col, int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.HORIZONTAL.getKey());
        itemGridConfig.setWeightX(1D);
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

    private synchronized void checked() {
        if (this.panel == null) {
            return;
        }

        this.unchecked();
        if (CheckEmptyUtil.isEmpty(this.config.value) || this.itemMap == null) {
            return;
        }

        MenuItemCache currItem = itemMap.get(this.value);
        while (currItem != null) {
            if (currItem.title != null) {
                super.loadTemplates(currItem.title, this.template.getItemActive());
                if (currItem.isMain) {
                    this.checkedMainItem = currItem.title;
                } else {
                    this.checkedItems.add(currItem.title);
                }
            }

            currItem = currItem.isMain ? null : itemMap.get(currItem.parentKey);
        }
    }

    private synchronized void showSubBar(String key, int line) {
        this.hideSubBar(line + 1);
        JPanel subBar = this.subBarMap.get(key);
        if (subBar != null) {
            subBar.setVisible(true);
            this.showBar.add(subBar);
        }
    }

    private synchronized void hideSubBar(int line) {
        if (line <= 1) {
            this.showBar.forEach(item -> item.setVisible(false));
            this.showBar.clear();
            return;
        }

        int showBarSize = this.showBar.size();
        if (showBarSize < line) {
            return;
        }

        for (int i = showBarSize - 1; i >= line; i--) {
            this.showBar.get(i).setVisible(false);
            this.showBar.remove(i);
        }
    }

    private synchronized void unchecked() {
        if (this.checkedMainItem != null) {
            super.loadTemplates(this.checkedMainItem, this.template.getMainItem());
        }
        this.checkedItems.forEach(item -> super.loadTemplates(item, this.template.getItem()));
        this.checkedItems.clear();
    }

    @Override
    public synchronized boolean setValue(Object data) {
        this.value = data == null ? null : data.toString();
        this.checked();
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

    private static class MenuItemCache {
        public String key;
        public JLabel title;
        public JPanel bar;
        public boolean isMain;
        public String parentKey;
        public MenuItemCache(String key, JLabel title, JPanel bar, String parentKey) {
            this.key = key;
            this.title = title;
            this.bar = bar;
            this.parentKey = parentKey;
            this.isMain = parentKey == null;
        }
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
