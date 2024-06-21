package com.parch.combine.gui.base.control.list;

import com.parch.combine.gui.core.element.AbsGUIElement;
import com.parch.combine.gui.core.element.IGUIElement;

import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;
import java.awt.BorderLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GUIListElement extends AbsGUIElement<GUIListElementTemplate, GUIListElement.Config> {

    JScrollPane listPanel = null;
    private IGUIElement[] elements = null;

    public GUIListElement(GUIListElementTemplate template, Config config) {
        super("list", template, config, GUIListElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

//        this.listPanel = new JScrollPane(this.buildItem());
//        this.listPanel.setBorder(null);
//        this.listPanel.setOpaque(false);
//        panel.add(this.listPanel, BorderLayout.WEST);
        panel.add(this.buildItem());
        return panel;
    }

    private JList<JComponent> buildItem() {
        DefaultListModel<JComponent> listModel = new DefaultListModel<>();
        JList<JComponent> list = new JList<>(listModel);
        list.setCellRenderer(this.getCellRenderer());
        list.setLayoutOrientation(this.config.orientation);

        super.loadTemplates(list, this.sysTemplate.getList(), this.template.getList());

        if (this.config.data != null) {
            this.elements = new IGUIElement[this.config.data.length];
            for (int i = 0; i < this.config.data.length; i++) {
                Object dataItem = this.config.data[i];
                this.elements[i] = this.config.element.copy();
                this.elements[i].setData(dataItem);
                listModel.addElement(this.elements[i].build(this.frame));
            }
        }

        return list;
    }

    private ListCellRenderer<JComponent> getCellRenderer() {
        return (list, value, index, isSelected, cellHasFocus) -> {
            super.loadTemplates(value, this.sysTemplate.getItem(), this.template.getItem());
            return value;
        };
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
            listPanel.removeAll();
            listPanel.add(buildItem());
            listPanel.revalidate();
            listPanel.repaint();
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
        public int orientation;
        public IGUIElement element;
    }
}
