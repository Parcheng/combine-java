package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ListCellRenderer;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GUIListElement extends AbstractGUIComponentElement<GUIListElementTemplate, GUIListElement.Config, Object[]> {

    private JPanel emptyPanel = null;
    private DefaultListModel<JComponent> listModel = null;
    private GUISubElementConfig[][] elementConfigs = null;

    public GUIListElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIListElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "list", template, config, GUIListElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());

        this.emptyPanel = new JPanel();
        super.loadTemplates(this.emptyPanel, this.sysTemplate.getEmpty(), this.template.getEmpty());
        this.buildEmptyText();
        this.emptyPanel.setVisible(false);
        panel.add(emptyPanel);


        this.listModel = new DefaultListModel<>();
        JList<JComponent> list = new JList<>(this.listModel);
        list.setModel(this.listModel);
        list.setCellRenderer(this.getCellRenderer());
        list.setLayoutOrientation(this.config.orientation);
        super.loadTemplates(list, this.sysTemplate.getList(), this.template.getList());
        this.buildListItems();
        panel.add(list);

        return panel;
    }

    private void buildEmptyText() {
        if (this.emptyPanel == null) {
            return;
        }

        if (this.config.emptyTipText != null) {
            for (String textItem : this.config.emptyTipText) {
                JLabel label = new JLabel(textItem);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                this.emptyPanel.add(label);
            }
        }
    }

    private void buildListItems() {
        if (this.listModel == null) {
            return;
        }

        boolean isShow = false;
        if (this.value != null && this.value.length > 0) {
            int dataLength = this.value.length;
            int elementConfigLength = this.config.elementConfigs.length;

            this.elementConfigs = new GUISubElementConfig[dataLength][];
            for (int i = 0; i < dataLength; i++) {
                Object dataItem = this.value[i];

                this.elementConfigs[i] = new GUISubElementConfig[elementConfigLength];
                JComponent[] body = GUISubElementHelper.copyAndBuild(dataItem, this.elementConfigs[i], this.config.elementConfigs, this);

                JPanel item = new JPanel();
                for (JComponent jComponent : body) {
                    item.add(jComponent);
                }

                listModel.addElement(item);
            }

            isShow = true;
        }

        if (this.emptyPanel != null){
            this.emptyPanel.setVisible(isShow);
        }
    }

    private ListCellRenderer<JComponent> getCellRenderer() {
        return (list, value, index, isSelected, cellHasFocus) -> {
            super.loadTemplates(value, this.sysTemplate.getItem(), this.template.getItem());
            return value;
        };
    }

    @Override
    public boolean setValue(Object data) {
        if (!(data instanceof Collection)) {
            return false;
        }

        Collection<?> listData = (Collection<?>) data;
        this.value = listData.toArray(new Object[0]);

        if (this.listModel != null) {
            this.listModel.removeAllElements();
            this.buildListItems();
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.elementConfigs == null) {
            return Arrays.asList(this.value);
        }

        List<Object> data = GUISubElementHelper.getValueList(this.elementConfigs);
        return data.size() > 0 ? data : null;
    }

    @Override
    public IGUIElement copy() {
        return new GUIListElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return GUIElementCallFunctionHelper.buildElementFunction(this);
    }

    public static class Config extends GUIElementConfig<Object[]> {
        public int orientation;
        public GUISubElementConfig[] elementConfigs;

        public String[] emptyTipText;
    }
}
