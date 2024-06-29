package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListCellRenderer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GUIListElement extends AbstractGUIComponentElement<GUIListElementTemplate, GUIListElement.Config, Object[]> {

    private JPanel panel = null;
    JList<JComponent> list = null;
    private GUISubElementConfig[][] elementConfigs = null;

    public GUIListElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIListElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "list", template, config, GUIListElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.sysTemplate.getExternal(), this.template.getExternal());
        panel.add(this.buildItem());
        return panel;
    }

    private JList<JComponent> buildItem() {
        DefaultListModel<JComponent> listModel = new DefaultListModel<>();
        this.list = new JList<>(listModel);
        list.setCellRenderer(this.getCellRenderer());
        list.setLayoutOrientation(this.config.orientation);

        super.loadTemplates(list, this.sysTemplate.getList(), this.template.getList());

        if (this.value != null) {
            int dataLength = this.value.length;
            int elementConfigLength = this.config.elementConfigs.length;

            this.elementConfigs = new GUISubElementConfig[dataLength][];
            for (int i = 0; i < dataLength; i++) {
                Object dataItem = this.value[i];

                this.elementConfigs[i] = new GUISubElementConfig[elementConfigLength];
                for (int j = 0; j < this.config.elementConfigs.length; j++) {
                    GUISubElementConfig configItem = this.config.elementConfigs[j];
                    this.elementConfigs[i][j] = GUISubElementConfig.copy(configItem);
                }

                JComponent[] body = GUISubElementHelper.build(dataItem, this.elementConfigs[i], this);
                JPanel item = new JPanel();
                for (JComponent jComponent : body) {
                    item.add(jComponent);
                }

                listModel.addElement(item);
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
    public boolean setValue(Object data) {
        if (!(data instanceof Collection)) {
            return false;
        }

        Collection<?> listData = (Collection<?>) data;
        this.value = listData.toArray(new Object[0]);

        int i = 0;
        for (Object dataItem : listData) {
            this.value[i] = dataItem;
            i++;
        }

        if (panel != null) {
            panel.removeAll();
            panel.add(buildItem());
            panel.revalidate();
            panel.repaint();
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.elementConfigs == null) {
            return Arrays.asList(this.value);
        }

        List<Object> data = new ArrayList<>();
        for (GUISubElementConfig[] item : this.elementConfigs) {
            data.add(GUISubElementHelper.getValue(item));
        }

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
    }
}
