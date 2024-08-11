package com.parch.combine.data.components.convert;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.convert.text2table.DataTextToTableInitConfig;
import com.parch.combine.data.base.convert.text2table.DataTextToTableLogicConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Component(order = 100, key = "convert.text-table", name = "文本转为表格组件", logicConfigClass = DataTextToTableLogicConfig.class, initConfigClass = DataTextToTableInitConfig.class)
@ComponentResult(name = "表格格式的数据（二维数组的矩阵）")
public class DataTextToTableComponent extends AbstractComponent<DataTextToTableInitConfig, DataTextToTableLogicConfig> {

    public DataTextToTableComponent() {
        super(DataTextToTableInitConfig.class, DataTextToTableLogicConfig.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected ComponentDataResult execute() {
        List<List<String>> table = new ArrayList<>();
        DataTextToTableLogicConfig config = getLogicConfig();

        Object data = config.source();
        if (data == null) {
            return ComponentDataResult.success(table);
        }

        Collection<String> textLines;
        if (data instanceof Collection) {
            textLines = (Collection<String>) data;
        } else {
            textLines = new ArrayList<>();
            textLines.add(data.toString());
        }

        String line;
        for (Object lineObj : textLines) {
            if (lineObj == null) {
                table.add(new ArrayList<>());
            } else {
                line = lineObj.toString();
                table.add(new ArrayList<>(Arrays.asList(line.split(config.separator()))));
            }
        }

        return ComponentDataResult.success(table);
    }
}
