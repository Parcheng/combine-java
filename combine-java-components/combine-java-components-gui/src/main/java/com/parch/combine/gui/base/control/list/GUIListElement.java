package com.parch.combine.gui.base.control.list;

import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.style.ElementHelper;
import com.parch.combine.gui.core.style.ElementStyleConstant;

import javax.swing.JViewport;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import java.awt.BorderLayout;
import java.awt.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GUIListElement implements IGUIElement {

    JScrollPane listPanel = null;
    private IGUIElement[] elements = null;
    private GUIListElementTemplate template;
    private Config config;

    public GUIListElement(GUIListElementTemplate template, Config config) {
        this.template = template == null ? new GUIListElementTemplate() : template;
        this.config = config;
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel(ElementStyleConstant.LEFT_FLOW_LAYOUT);
        ElementHelper.set(panel, template.getExternal());

        this.listPanel = new JScrollPane(this.buildItem());
        panel.add(listPanel, BorderLayout.CENTER);
        return panel;
    }

    private JList<JComponent> buildItem() {
        DefaultListModel<JComponent> listModel = new DefaultListModel<>();
        JList<JComponent> list = new JList<>(listModel);
        list.setCellRenderer((list1, value, index, isSelected, cellHasFocus) -> value);

        if (this.config.data != null) {
            elements = new IGUIElement[this.config.data.length];
            for (int i = 0; i < this.config.data.length; i++) {
                Object dataItem = this.config.data[i];
                this.elements[i] = this.config.element.copy();
                this.elements[i].setData(dataItem);
                listModel.addElement(this.elements[i].build());
            }
        }

        return list;
    }

    @Override
    public boolean setData(Object data) {
        if (!(data instanceof Collection)) {
            return false;
        }

        Collection<?> listData = (Collection<?>) data;
        this.config.data = new Object[listData.size()];

        int i = 0;
        for (Object dataItem : listData) {
            this.config.data[i] = dataItem;
            i++;
        }

        if (listPanel != null) {
            JViewport viewport = listPanel.getViewport();
            for (Component item : viewport.getComponents()) {
                viewport.remove(item);
            }

            viewport.add(buildItem());
            viewport.revalidate();
            viewport.repaint();
        }

        return true;
    }

    @Override
    public Object getData() {
        if (this.elements == null) {
            return Arrays.asList(this.config.data);
        }

        List<Object> data = new ArrayList<>();
        for (IGUIElement element : this.elements) {
            data.add(element.getData());
        }

        return data.size() > 0 ? data : null;
    }

    @Override
    public Object call(String key, Object... params) {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIListElement(this.template, this.config);
    }

    public static class Config {
        public Object[] data;
        public IGUIElement element;
    }
}
