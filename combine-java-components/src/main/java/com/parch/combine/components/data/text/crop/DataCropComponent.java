package com.parch.combine.components.data.text.crop;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Component(order = 2, key = "text.crop", name = "文本数据裁剪组件", logicConfigClass = DataCropLogicConfig.class, initConfigClass = DataCropInitConfig.class)
@ComponentDesc("支持文本矩阵（二维数组），文本集合，单文本格式的数据")
@ComponentResult(name = "裁剪后的文本（集合/矩阵）")
public class DataCropComponent extends AbsComponent<DataCropInitConfig, DataCropLogicConfig> {

    /**
     * 构造器
     */
    public DataCropComponent() {
        super(DataCropInitConfig.class, DataCropLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected DataResult execute() {
        DataCropLogicConfig config = getLogicConfig();
        Object data = DataVariableHelper.parseValue(config.getSource(), false);
        if (data == null) {
            return DataResult.success(null);
        }

        List<Object> result = new ArrayList<>();

        boolean isArray = false;
        Collection<Object> lines;
        if (data instanceof Collection) {
            lines = (Collection<Object>) data;
            isArray = true;
        } else {
            lines = new ArrayList<>();
            lines.add(data);
        }

        int startRowIndex = config.getStartRow() - 1;
        int startIndex = config.getStartIndex()  - 1;
        int startSkipCount = config.getStartSkipCount() + startIndex;
        int endDiscardCount = config.getEndDiscardCount();

        int rowIndex = 0;
        for (Object rowObj : lines) {
            // 跳过指定行数
            if (rowIndex++ < startRowIndex || rowObj == null) {
                continue;
            }

            if (rowObj instanceof Collection) {
                Collection<Object> rowCols = (Collection<Object>) rowObj;
                // 每行跳过前 startSkipCount 项，忽略掉后 endDiscardCount项
                int colCount = rowCols.size();
                if (colCount > endDiscardCount + startSkipCount) {
                    colCount = colCount - endDiscardCount;
                } else {
                    continue;
                }

                // 首行跳过前 startIndex 项
                int colIndex = 0;
                List<String> newCols = new ArrayList<>();
                for (Object rowCol : rowCols) {
                    // 跳过指定列数
                    if (colIndex >= startSkipCount && colIndex <= colCount) {
                        newCols.add(rowCol == null ? null : rowCol.toString());
                    }

                    colIndex++;
                }
                result.add(newCols);
            } else {
                String rowText = rowObj.toString();
                // 跳过前 startSkipCount个字符，忽略掉最后endDiscardCount个字符
                if (rowText.length() > startSkipCount + endDiscardCount) {
                    result.add(rowText.substring(startSkipCount, rowText.length() - endDiscardCount));
                } else {
                    result.add(CheckEmptyUtil.EMPTY);
                }
            }
        };

        return DataResult.success(isArray ? result : (result.size() > 0 ? result.get(0) : null));
    }
}
