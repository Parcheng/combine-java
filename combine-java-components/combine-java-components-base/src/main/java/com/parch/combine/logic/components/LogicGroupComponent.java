package com.parch.combine.logic.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.group.LogicGroupInitConfig;
import com.parch.combine.logic.base.group.LogicGroupLogicConfig;

@Component(key = "group", name = "组合组件", logicConfigClass = LogicGroupLogicConfig.class, initConfigClass = LogicGroupInitConfig.class)
@ComponentResult(name = "组合执行结果")
public class LogicGroupComponent extends AbstractComponent<LogicGroupInitConfig, LogicGroupLogicConfig> {

    public LogicGroupComponent() {
        super(LogicGroupInitConfig.class, LogicGroupLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        String[] componentIds = getLogicConfig().components();
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
