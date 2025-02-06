package com.parch.combine.logic.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.packages.LogicPackagesInitConfig;
import com.parch.combine.logic.base.packages.LogicPackagesLogicConfig;

@Component(key = "packages", name = "包装组件", logicConfigClass = LogicPackagesLogicConfig.class, initConfigClass = LogicPackagesInitConfig.class)
@ComponentDesc("用于将多个组件包装成一个组件来执行，并可以指定执行结果")
@ComponentResult(name = "配置的输出结果")
public class LogicPackagesComponent extends AbstractComponent<LogicPackagesInitConfig, LogicPackagesLogicConfig> {

    public LogicPackagesComponent() {
        super(LogicPackagesInitConfig.class, LogicPackagesLogicConfig.class);
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
