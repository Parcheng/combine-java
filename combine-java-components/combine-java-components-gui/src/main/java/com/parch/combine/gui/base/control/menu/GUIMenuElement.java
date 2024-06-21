package com.parch.combine.gui.base.control.menu;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.List;

public class GUIMenuElement extends AbsGUIElement<GUIMenuElementTemplate, GUIMenuElement.Config> {

    private static Integer MAX_LAYER = 99;
    private JMenuBar menuBar = null;

    public GUIMenuElement(GUIMenuElementTemplate template, Config config) {
        super("menu", template, config, GUIMenuElementTemplate.class);
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
                menus[i] =  layer == 0 ? new JMenu(item.text) : new JMenuItem(item.text);
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
        if (key == null || config.checkPath == null || config.checkPath.length <= layer) {
            return false;
        }

        return key.equals(config.checkPath[layer]);
    }

    @Override
    public boolean setData(Object data) {
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

        config.checkPath = path.toArray(new String[0]);
        buildMenu();
        return true;
    }

    @Override
    public Object getData() {
        if (this.menuBar == null) {
            return config.checkPath;
        }

        return null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIMenuElement(this.template, this.config);
    }

    public static class Config {
        public String[] checkPath;
        public ConfigDataItem[] items;
    }

    public static class ConfigDataItem {
        public String key;
        public String text;
        public ConfigDataItem[] items;
    }
}
