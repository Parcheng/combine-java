package com.parch.combine.components.data.text.split;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataFindHandler;
import com.parch.combine.core.component.tools.variable.DataVariableFlagHelper;

import com.parch.combine.core.component.vo.DataResult;

import java.util.*;

/**
 * 运算组件
 */
@Component(order = 2, key = "text.split", name = "文本拆分组件", logicConfigClass = DataTextSplitLogicConfig.class, initConfigClass = DataTextSplitInitConfig.class)
@ComponentResult(name = "拆分后的文本集合")
public class DataTextSplitComponent extends AbsComponent<DataTextSplitInitConfig, DataTextSplitLogicConfig> {

    /**
     * 构造器
     */
    public DataTextSplitComponent() {
        super(DataTextSplitInitConfig.class, DataTextSplitLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataTextSplitLogicConfig logicConfig = getLogicConfig();

        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "数据源为空为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getRegex())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, DataTextSplitErrorEnum.REGEX_IS_NULL.getMsg()));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        DataTextSplitLogicConfig logicConfig = getLogicConfig();
        List<String> result = null;

        try {
            Object regex = DataVariableHelper.parseValue(logicConfig.getRegex(), false);
            if (regex == null) {
                return DataResult.fail(DataTextSplitErrorEnum.REGEX_IS_NULL);
            }

            Object data = DataVariableHelper.parseValue(logicConfig.getSource(), false);
            if (data != null) {
                String[] dataArr = JsonUtil.serialize(data).split(regex.toString());
                result = new ArrayList<>(dataArr.length);
                Collections.addAll(result, dataArr);
            }
            if (logicConfig.getIsReplace() && DataVariableFlagHelper.hasParseFlag(logicConfig.getSource())) {
                Object finalResult = result;
                DataVariableHelper.replaceValue(logicConfig.getSource(), old -> finalResult);
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(DataTextSplitErrorEnum.FAIL, e);
            return DataResult.fail(DataTextSplitErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
