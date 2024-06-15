package com.parch.combine.tool.components;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.tool.base.sleep.ToolSleepErrorEnum;
import com.parch.combine.tool.base.sleep.ToolSleepInitConfig;
import com.parch.combine.tool.base.sleep.ToolSleepLogicConfig;

@Component(key = "sleep", name = "休眠组件", logicConfigClass = ToolSleepLogicConfig.class, initConfigClass = ToolSleepInitConfig.class)
@ComponentResult(name = "true 或报错")
public class ToolSleepComponent extends AbsComponent<ToolSleepInitConfig, ToolSleepLogicConfig> {

    public ToolSleepComponent() {
        super(ToolSleepInitConfig.class, ToolSleepLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        ToolSleepLogicConfig logicConfig = getLogicConfig();
        try {
            Thread.sleep(logicConfig.time());
        } catch (InterruptedException e) {
            ComponentErrorHandler.print(ToolSleepErrorEnum.FAIL, e);
            return DataResult.fail(ToolSleepErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
