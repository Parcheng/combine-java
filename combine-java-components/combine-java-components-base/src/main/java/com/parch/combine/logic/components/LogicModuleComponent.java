package com.parch.combine.logic.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.module.LogicModuleInitConfig;
import com.parch.combine.logic.base.module.LogicModuleLogicConfig;

@Component(key = "module", name = "模块定义组件", logicConfigClass = LogicModuleLogicConfig.class, initConfigClass = LogicModuleInitConfig.class)
@ComponentResult(name = "模块执行结果")
public class LogicModuleComponent extends AbstractComponent<LogicModuleInitConfig, LogicModuleLogicConfig> {

    public LogicModuleComponent() {
        super(LogicModuleInitConfig.class, LogicModuleLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        String[] componentIds = getLogicConfig().components();
        ComponentDataResult result = SubComponentTool.execute(manager, componentIds);
        if (result.getSuccess()) {
            Object out = getLogicConfig().out();
            if (out == null) {
                return ComponentDataResult.success(null);
            } else {
                return ComponentDataResult.success(DataVariableHelper.parse(out));
            }
        }

        return ComponentDataResult.success(result);
    }
}
