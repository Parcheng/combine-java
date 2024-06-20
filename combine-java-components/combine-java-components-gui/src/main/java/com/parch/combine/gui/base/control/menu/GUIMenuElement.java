package com.parch.combine.gui.base.control.menu;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JMenuBar;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class GUIMenuElement extends AbsGUIElement<GUIMenuElementTemplate, GUIMenuElement.Config> {

    private JMenuBar menuBar = null;

    public GUIMenuElement(GUIMenuElementTemplate template, Config config) {
        super("menu", template, config, GUIMenuElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.menuBar = new JMenuBar();
        JMenuItem[] items = buildMenu(this.config.items);
        for (JMenuItem item : items) {
            this.menuBar.add(item);
        }

        // fileMenu.setSelected(true);

        panel.add(this.menuBar);
        return panel;
    }

    private JMenuItem[] buildMenu(ConfigDataItem[] items) {
        JMenuItem[] menus = new JMenuItem[items.length];
        for (int i = 0; i < items.length; i++) {
            ConfigDataItem item = items[i];
            if (item.items == null || item.items.length ==0) {
                menus[i] = new JMenuItem(item.text);
            } else {
                menus[i] = new JMenu(item.text);
                JMenuItem[] subMenus = buildMenu(item.items);
                for (JMenuItem subItem : subMenus) {
                    menus[i].add(subItem);
                }
            }
        }

        return menus;
    }

    @Override
    public boolean setData(Object data) {
        if (data == null) {
            return false;
        }

//        if (this.button != null) {
//            this.button.setText(data.toString());
//        }
//        this.config.text = data.toString();
        return true;
    }

    @Override
    public Object getData() {
        return null;
        // this.button == null ? config.text : this.button.getText();
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
