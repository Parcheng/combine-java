package com.parch.combine.components.data.convert.text2table;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.tools.variable.TextExpressionHelper;
import com.parch.combine.core.component.vo.DataResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Component(order = 100, key = "convert.text-table", name = "文本转为表格组件", logicConfigClass = DataTextToTableLogicConfig.class, initConfigClass = DataTextToTableInitConfig.class)
@ComponentResult(name = "表格格式的数据（二维数组的矩阵）")
public class DataTextToTableComponent extends AbsComponent<DataTextToTableInitConfig, DataTextToTableLogicConfig> {

    /**
     * 构造器
     */
    public DataTextToTableComponent() {
        super(DataTextToTableInitConfig.class, DataTextToTableLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected DataResult execute() {
        List<List<String>> table = new ArrayList<>();
        DataTextToTableLogicConfig config = getLogicConfig();

        Object data = TextExpressionHelper.getObject(config.getSource());
        if (data == null) {
            return DataResult.success(table);
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
                table.add(new ArrayList<>(Arrays.asList(line.split(config.getSeparator()))));
            }
        }

        return DataResult.success(table);
    }
}
