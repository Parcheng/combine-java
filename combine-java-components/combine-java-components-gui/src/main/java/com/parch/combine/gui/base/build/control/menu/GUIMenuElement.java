package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.event.EventConfig;

import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIMenuElement extends AbstractGUIComponentElement<GUIMenuElementTemplate, GUIMenuElement.Config, String[]> {

    private static Integer MAX_LAYER = 99;
    private JMenuBar menuBar = null;

    public GUIMenuElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIMenuElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "menu", template, config, GUIMenuElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.menuBar = new JMenuBar();
        super.loadTemplates(this.menuBar, this.sysTemplate.getBar(), this.template.getBar());
        buildMenu();

        panel.add(this.menuBar);
        return panel;
    }

    private void buildMenu() {
        JMenuItem[] items = buildMenu(this.config.items, 0);
        for (JMenuItem item : items) {
            super.loadTemplates(item, this.sysTemplate.getMainItem(), this.template.getMainItem());
            if (item.isSelected()) {
                super.loadTemplates(item, this.sysTemplate.getItemActive(), this.template.getItemActive());
            }
            this.menuBar.add(item);
        }
    }

    private JMenuItem[] buildMenu(ConfigDataItem[] items, int layer) {
        if (layer > MAX_LAYER) {
            return new JMenuItem[0];
        }

        JMenuItem[] menus = new JMenuItem[items.length];
        for (int i = 0; i < items.length; i++) {
            ConfigDataItem item = items[i];
            if (item.items == null || item.items.length ==0) {
                menus[i] = layer == 0 ? new JMenu(item.text) : new JMenuItem(item.text);
                super.registerEvents(menus[i], item.events);
            } else {
                menus[i] = new JMenu(item.text);
                JMenuItem[] subMenus = buildMenu(item.items, layer+1);
                for (JMenuItem subItem : subMenus) {
                    super.loadTemplates(subItem, this.sysTemplate.getItem(), this.template.getItem());
                    if (subItem.isSelected()) {
                        super.loadTemplates(subItem, this.sysTemplate.getItemActive(), this.template.getItemActive());
                    }
                    menus[i].add(subItem);
                }
            }

            menus[i].setSelected(hasChecked(item.key, layer));
        }

        return menus;
    }

    private boolean hasChecked(String key, int layer) {
        if (key == null || this.value == null || this.value.length <= layer) {
            return false;
        }

        return key.equals(this.value[layer]);
    }

    @Override
    public boolean setValue(Object data) {
        if (data == null) {
            return false;
        }

        if (!(data instanceof Iterable)) {
            List<String> dataList = new ArrayList<>();
            dataList.add(data.toString());
            data = dataList;
        }

        List<String> path = new ArrayList<>();
        for (Object item : (Iterable<?>) data) {
            path.add(item.toString());
        }

        this.value = path.toArray(new String[0]);
        buildMenu();
        return true;
    }

    @Override
    public Object getValue() {
        if (this.menuBar == null) {
            return this.value;
        }

        return null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIMenuElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<String[]> {
        public ConfigDataItem[] items;
    }

    public static class ConfigDataItem {
        public String key;
        public String text;
        public ConfigDataItem[] items;
        public EventConfig[] events;
    }
}
