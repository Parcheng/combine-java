package com.parch.combine.tool.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.tool.base.sleep.ToolSleepErrorEnum;
import com.parch.combine.tool.base.sleep.ToolSleepInitConfig;
import com.parch.combine.tool.base.sleep.ToolSleepLogicConfig;

@Component(key = "sleep", name = "休眠组件", logicConfigClass = ToolSleepLogicConfig.class, initConfigClass = ToolSleepInitConfig.class)
@ComponentResult(name = "true 或报错")
public class ToolSleepComponent extends AbstractComponent<ToolSleepInitConfig, ToolSleepLogicConfig> {

    public ToolSleepComponent() {
        super(ToolSleepInitConfig.class, ToolSleepLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        ToolSleepLogicConfig logicConfig = getLogicConfig();
        try {
            Thread.sleep(logicConfig.time());
        } catch (InterruptedException e) {
            PrintErrorHelper.print(ToolSleepErrorEnum.FAIL, e);
            return ComponentDataResult.fail(ToolSleepErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
