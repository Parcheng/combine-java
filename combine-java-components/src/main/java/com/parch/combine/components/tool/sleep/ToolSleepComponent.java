package com.parch.combine.components.tool.sleep;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 休眠组件
 */
@Component(key = "sleep", name = "休眠组件", logicConfigClass = ToolSleepLogicConfig.class, initConfigClass = ToolSleepInitConfig.class)
@ComponentResult(name = "true 或报错")
public class ToolSleepComponent extends AbsComponent<ToolSleepInitConfig, ToolSleepLogicConfig> {

    /**
     * 构造器
     */
    public ToolSleepComponent() {
        super(ToolSleepInitConfig.class, ToolSleepLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        ToolSleepLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getTime() == null) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "休眠时间为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        ToolSleepLogicConfig logicConfig = getLogicConfig();
        try {
            Thread.sleep(logicConfig.getTime());
        } catch (InterruptedException e) {
            ComponentErrorHandler.print(ToolSleepErrorEnum.FAIL, e);
            return DataResult.fail(ToolSleepErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
