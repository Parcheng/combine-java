package com.parch.combine.data.components.text;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.text.join.DataTextJoinInitConfig;
import com.parch.combine.data.base.text.join.DataTextJoinLogicConfig;
import com.parch.combine.data.base.text.split.DataTextSplitErrorEnum;
import java.util.ArrayList;
import java.util.List;

@Component(order = 2, key = "text.join", name = "批量文本拼接组件", logicConfigClass = DataTextJoinLogicConfig.class, initConfigClass = DataTextJoinInitConfig.class)
@ComponentResult(name = "文件本拼接结果集合")
public class DataTextJoinComponent extends AbstractComponent<DataTextJoinInitConfig, DataTextJoinLogicConfig> {

    public DataTextJoinComponent() {
        super(DataTextJoinInitConfig.class, DataTextJoinLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataTextJoinLogicConfig logicConfig = getLogicConfig();
        List<String> result = new ArrayList<>();

        try {
            DataTextJoinLogicConfig.JoinItem[] items = logicConfig.items();
            for (DataTextJoinLogicConfig.JoinItem item : items) {
                String[] texts = item.texts();
                String separator = item.separator();
                String itemResult = StringUtil.join(texts, separator == null ? CheckEmptyUtil.EMPTY : separator);
                result.add(itemResult);

                String target = item.target();
                if (CheckEmptyUtil.isNotEmpty(target)) {
                    DataVariableHelper.replaceValue(target, old -> itemResult);
                }
            }
        } catch (Exception e) {
            PrintErrorHelper.print(DataTextSplitErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataTextSplitErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
