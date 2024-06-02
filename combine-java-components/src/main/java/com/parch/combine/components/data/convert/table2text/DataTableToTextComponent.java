package com.parch.combine.components.data.convert.table2text;

import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.tools.variable.TextExpressionHelper;
import com.parch.combine.core.component.vo.DataResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 文件读取组件
 */
@Component(order = 100, key = "convert.table-text", name = "表格转文本数据组件", logicConfigClass = DataTableToTextLogicConfig.class, initConfigClass = DataTableToTextInitConfig.class)
@ComponentResult(name = "文本行集合")
public class DataTableToTextComponent extends AbsComponent<DataTableToTextInitConfig, DataTableToTextLogicConfig> {

    /**
     * 构造器
     */
    public DataTableToTextComponent() {
        super(DataTableToTextInitConfig.class, DataTableToTextLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected DataResult execute() {
        List<String> text = new ArrayList<>();
        DataTableToTextLogicConfig config = getLogicConfig();

        TextExpressionHelper.getText(config.getSource(), null, true);
        Object data = DataVariableHelper.parseValue(config.getSource(), true);
        if (data == null) {
            return DataResult.success(text);
        }

        Collection<Object> tableRows;
        if (data instanceof Collection) {
            tableRows = (Collection<Object>) data;
        } else {
            tableRows = new ArrayList<>();
            tableRows.add(data);
        }

        for (Object tableRow: tableRows) {
            if (tableRow == null) {
                continue;
            }

            Collection<Object> rowCols;
            if (tableRow instanceof Collection) {
                rowCols = (Collection<Object>) tableRow;
            } else {
                rowCols = new ArrayList<>();
                rowCols.add(tableRow);
            }

            List<String> newCols = new ArrayList<>();
            for (Object rowCol : rowCols) {
                newCols.add(rowCol == null ? null : rowCol.toString());
            }

            text.add(StringUtil.join(newCols, config.getSeparator()));
        }

        return DataResult.success(text);
    }
}
