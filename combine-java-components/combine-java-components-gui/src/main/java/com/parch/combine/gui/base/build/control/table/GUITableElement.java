package com.parch.combine.gui.base.build.control.table;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.gui.core.element.AbstractGUIComponentElement;
import com.parch.combine.gui.core.element.GUIElementConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.call.IGUIElementCallFunction;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.Component;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class GUITableElement extends AbstractGUIComponentElement<GUITableElementTemplate, GUITableElement.Config, Map<String, Object>[]> {

    private JPanel panel = null;
    private GUISubElementConfig[][] elementConfigs = null;

    public GUITableElement(String scopeKey, String domain, String elementId, Map<String, Object> data, GUITableElementTemplate template, Config config) {
        super(scopeKey, domain, elementId, data, "table", template, config, GUITableElementTemplate.class);
    }

    @Override
    public JComponent build() {
        this.panel = new JPanel();
        super.loadTemplates(this.panel, this.sysTemplate.getExternal(), this.template.getExternal());
        panel.add(buildTable());
        return this.panel;
    }

    private JTable buildTable() {
        // 创建默认表格模型
        DefaultTableModel model = new DefaultTableModel();
        buildHeader(model);
        buildBody(model);

        // 创建表格并将模型设置为默认
        JTable table = new JTable(model);
        table.setShowGrid(false);
        if (this.config.rowHeight != null) {
            table.setRowHeight(this.config.rowHeight);
        }
        if (this.config.rowMargin != null) {
            table.setRowMargin(this.config.rowMargin);
        }
        super.loadTemplates(table, this.sysTemplate.getTable(), this.template.getTable());
        table.setDefaultRenderer(Object.class, this.getCellRenderer());

        return table;
    }

    private TableCellRenderer getCellRenderer() {
        int sysCellCount = sysTemplate.getCells() == null ? 0 : sysTemplate.getCells().size();
        int cellCount = template.getCells() == null ? 0 : template.getCells().size();
        return new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComponent component = (JComponent) value;
                if (component != null) {
                    if (row == 0) {
                        loadTemplates(component, sysTemplate.getHeader(), template.getHeader());
                    } else {
                        loadTemplates(component, sysCellCount == 0 ? null : sysTemplate.getCells().get(row % sysCellCount),
                                cellCount == 0 ? null : template.getCells().get(row % cellCount));
                    }
                }
                return component;
            }
        };
    }

    private void buildHeader(DefaultTableModel model) {
        model.setColumnIdentifiers(this.config.headNames);

        Object[] header = new Object[this.config.headNames.length];
        for (int j = 0; j < this.config.headNames.length; j++) {
            JLabel headerCell = new JLabel(this.config.headNames[j]);
            header[j] = headerCell;
        }
        model.addRow(header);
    }

    private void buildBody(DefaultTableModel model) {
        if (this.value == null) {
            return;
        }

        int dataCount = this.value.length;
        int colCount = this.config.headNames.length;
        int colConfigCount = this.config.columnConfigs.length;
        this.elementConfigs = new GUISubElementConfig[this.value.length][];

        JLabel emptyLabel = new JLabel(CheckEmptyUtil.EMPTY);

        for (int i = 0; i < dataCount; i++) {
            Map<String, Object> dataItem = this.value[i];

            // 复制元素配置
            this.elementConfigs[i] = new GUISubElementConfig[colConfigCount];
            JComponent[] body = GUISubElementHelper.copyAndBuild(dataItem, this.elementConfigs[i], this.config.columnConfigs, this);

            // 构建行数据
            Object[] row = new Object[colCount];
            for (int j = 0; j < colCount; j++) {
                if (j < colConfigCount) {
                    row[j] = body[j];
                } else {
                    row[j] = emptyLabel;
                }
            }

            // 添加行
            model.addRow(row);
        }

        if (this.config.minRow != null && this.config.minRow > this.value.length) {
            int fillRowCount = this.config.minRow - this.value.length;
            for (int i = 0; i < fillRowCount; i++) {
                Object[] row = new Object[colCount];
                for (int j = 0; j < colCount; j++) {
                    row[j] = emptyLabel;
                }
                model.addRow(row);
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setValue(Object data) {
        if (!(data instanceof Collection)) {
            return false;
        }

        Collection<?> listData = (Collection<?>) data;

        int i = 0;
        Map<String, Object>[] newValue = new Map[listData.size()];
        for (Object dataItem : listData) {
            if (!(dataItem instanceof Map)) {
                return false;
            }

            newValue[i] = (Map<String, Object>) dataItem;
            i++;
        }

        this.value = newValue;
        if (panel != null) {
            panel.removeAll();
            panel.add(buildTable());
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

        List<Object> data = GUISubElementHelper.getValueList(this.elementConfigs);
        return data.size() > 0 ? data : null;
    }

    @Override
    public Map<String, IGUIElementCallFunction> initCallFunction() {
        return null;
    }

    @Override
    public IGUIElement copy() {
        return new GUITableElement(this.scopeKey, this.domain, this.id, this.data, this.template, this.config);
    }

    public static class Config extends GUIElementConfig<Map<String, Object>[]> {
        public Integer minRow;
        public Integer rowHeight;
        public Integer rowMargin;
        public String[] headNames;
        public GUISubElementConfig[] columnConfigs;
    }
}
