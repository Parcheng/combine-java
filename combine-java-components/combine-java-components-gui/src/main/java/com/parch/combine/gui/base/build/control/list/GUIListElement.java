package com.parch.combine.gui.base.build.control.list;

import com.parch.combine.gui.core.call.GUIElementCallFunctionHelper;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import com.parch.combine.gui.core.style.config.ElementGridConfig;
import com.parch.combine.gui.core.style.enums.GridFillEnum;

import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GUIListElement extends AbstractGUIComponentElement<GUIListElementTemplate, GUIListElement.Config, Object[]> {

    private JPanel emptyPanel = null;
    private JPanel listPanel = null;
    private GUISubElementConfig[][] elementConfigs = null;

    public GUIListElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUIListElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "list", template, config, GUIListElementTemplate.class);
    }

    @Override
    public JComponent build() {
        JPanel panel = new JPanel();
        super.loadTemplates(panel, this.template.getExternal());

        this.emptyPanel = new JPanel();
        super.addSubComponent(panel, this.emptyPanel, this.template.getEmpty());
        this.buildEmptyText();
        this.emptyPanel.setVisible(false);

        this.listPanel = new JPanel();
        super.addSubComponent(panel, listPanel, this.template.getList());
        this.buildListItems();

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
        if (this.listPanel == null) {
            return;
        }

        boolean isShow = true;
        if (this.value != null && this.value.length > 0) {
            int dataLength = this.value.length;

            this.elementConfigs = new GUISubElementConfig[dataLength][];
            for (int i = 0; i < dataLength; i++) {
                Object dataItem = this.value[i];

                JPanel item = new JPanel();
                super.addSubComponent(this.listPanel, item, this.template.getItem(), buildGridConfig(i + 1));

                this.elementConfigs[i] = GUISubElementHelper.copyAndBuild(dataItem, this.config.elementConfigs, this);
                GUISubElementHelper.setSubComponent(item, this.elementConfigs[i]);
            }

            isShow = false;
        }

        if (this.emptyPanel != null){
            this.emptyPanel.setVisible(isShow);
        }
    }

    private ElementGridConfig buildGridConfig(int line) {
        ElementGridConfig itemGridConfig = new ElementGridConfig();
        itemGridConfig.setFill(GridFillEnum.NONE.getKey());
        itemGridConfig.setWeightX(1D);
        itemGridConfig.setPositionX(1);
        itemGridConfig.setPositionY(line);
        return itemGridConfig;
    }

    @Override
    public synchronized boolean setValue(Object data) {
        if (data == null) {
            data = Collections.emptyList();
        }
        if (!(data instanceof Collection)) {
            return false;
        }

        Collection<?> listData = (Collection<?>) data;
        this.value = listData.toArray(new Object[0]);

        if (this.listPanel != null) {
            this.listPanel.removeAll();
            this.buildListItems();
            this.listPanel.revalidate();
            this.listPanel.repaint();
        }

        return true;
    }

    @Override
    public Object getValue() {
        if (this.elementConfigs == null) {
            return Arrays.asList(this.value);
        }

        List<Object> data = GUISubElementHelper.batchGetValue(this.elementConfigs);
        return !data.isEmpty() ? data : null;
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
