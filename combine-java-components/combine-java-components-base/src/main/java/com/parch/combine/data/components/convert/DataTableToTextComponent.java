package com.parch.combine.data.components.convert;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.convert.table2text.DataTableToTextInitConfig;
import com.parch.combine.data.base.convert.table2text.DataTableToTextLogicConfig;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component(order = 100, key = "convert.table-text", name = "表格转文本数据组件", logicConfigClass = DataTableToTextLogicConfig.class, initConfigClass = DataTableToTextInitConfig.class)
@ComponentResult(name = "文本行集合")
public class DataTableToTextComponent extends AbstractComponent<DataTableToTextInitConfig, DataTableToTextLogicConfig> {

    public DataTableToTextComponent() {
        super(DataTableToTextInitConfig.class, DataTableToTextLogicConfig.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ComponentDataResult execute() {
        List<String> text = new ArrayList<>();
        DataTableToTextLogicConfig config = getLogicConfig();
        String separator = config.separator();

        Object data = config.source();
        if (data == null) {
            return ComponentDataResult.success(text);
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

            List<String> rowCols = new ArrayList<>();
            if (tableRow instanceof Collection) {
                Collection<Object> list = (Collection<Object>) tableRow;
                for (Object item : list) {
                    rowCols.add(item == null ? CheckEmptyUtil.EMPTY : item.toString());
                }
            } else if (tableRow instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) tableRow;
                for (Map.Entry<String, Object> item : map.entrySet()) {
                    rowCols.add(item.getValue() == null ? CheckEmptyUtil.EMPTY : item.getValue().toString());
                }
            } else {
                rowCols.add(tableRow.toString());
            }

            text.add(StringUtil.join(rowCols, separator));
        }

        return ComponentDataResult.success(text);
    }
}
