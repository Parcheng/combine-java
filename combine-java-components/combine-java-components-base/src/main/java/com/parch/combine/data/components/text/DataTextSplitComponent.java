package com.parch.combine.data.components.text;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableFlagHelper;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.text.split.DataTextSplitErrorEnum;
import com.parch.combine.data.base.text.split.DataTextSplitInitConfig;
import com.parch.combine.data.base.text.split.DataTextSplitLogicConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component(order = 2, key = "text.split", name = "文本拆分组件", logicConfigClass = DataTextSplitLogicConfig.class, initConfigClass = DataTextSplitInitConfig.class)
@ComponentResult(name = "拆分后的文本集合")
public class DataTextSplitComponent extends AbstractComponent<DataTextSplitInitConfig, DataTextSplitLogicConfig> {

    public DataTextSplitComponent() {
        super(DataTextSplitInitConfig.class, DataTextSplitLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataTextSplitLogicConfig logicConfig = getLogicConfig();
        List<String> result = null;

        try {
            String regex = logicConfig.regex();
            if (regex == null) {
                return ComponentDataResult.fail(DataTextSplitErrorEnum.REGEX_IS_NULL);
            }

            String source = logicConfig.source();
            Object data = DataVariableHelper.parseValue(source, false);
            if (data != null) {
                String[] dataArr = JsonUtil.serialize(data).split(regex.toString());
                result = new ArrayList<>(dataArr.length);
                Collections.addAll(result, dataArr);
            }
            if (logicConfig.isReplace() && DataVariableFlagHelper.hasParseFlag(source)) {
                Object finalResult = result;
                DataVariableHelper.replaceValue(source, old -> finalResult);
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(DataTextSplitErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataTextSplitErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
