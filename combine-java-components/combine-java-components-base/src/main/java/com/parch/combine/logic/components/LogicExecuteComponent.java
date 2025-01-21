package com.parch.combine.logic.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.execute.LogicExecuteInitConfig;
import com.parch.combine.logic.base.execute.LogicExecuteLogicConfig;

@Component(key = "execute", name = "组件执行组件", logicConfigClass = LogicExecuteLogicConfig.class, initConfigClass = LogicExecuteInitConfig.class)
@ComponentResult(name = "组件执行结果")
public class LogicExecuteComponent extends AbstractComponent<LogicExecuteInitConfig, LogicExecuteLogicConfig> {

    public LogicExecuteComponent() {
        super(LogicExecuteInitConfig.class, LogicExecuteLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        LogicExecuteLogicConfig logicConfig = getLogicConfig();
        LogicExecuteLogicConfig.MappingItem[] mappingItems = logicConfig.mappings();
        if (CheckEmptyUtil.isNotEmpty(mappingItems)) {
            for (LogicExecuteLogicConfig.MappingItem item : mappingItems) {
                DataVariableHelper.replaceValue(item.target(), item.value(), false);
            }
        }

        String[] componentIds = logicConfig.components();
        ComponentDataResult result = SubComponentTool.execute(manager, componentIds);
        if (result.getSuccess()) {
            String out = getLogicConfig().out();
            if (CheckEmptyUtil.isEmpty(out)) {
                return ComponentDataResult.success(result);
            } else {
                return ComponentDataResult.success(DataVariableHelper.parseValue(out, true));
            }
        }

        return ComponentDataResult.success(result);
    }
}
