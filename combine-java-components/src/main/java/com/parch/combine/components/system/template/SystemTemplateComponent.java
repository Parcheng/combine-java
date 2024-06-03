package com.parch.combine.components.system.template;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

@Component(key = "template", name = "模板组件", logicConfigClass = SystemTemplateLogicConfig.class, initConfigClass = SystemTemplateInitConfig.class)
@ComponentResult(name = "模板中组件报错信息或 true")
public class SystemTemplateComponent extends AbsComponent<SystemTemplateInitConfig, SystemTemplateLogicConfig> {

    public SystemTemplateComponent() {
        super(SystemTemplateInitConfig.class, SystemTemplateLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        SystemTemplateInitConfig initConfig = getInitConfig();
        SystemTemplateLogicConfig logicConfig = getLogicConfig();

        String variableKey = initConfig.variableKey();
        String[] configs = logicConfig.configs();

        ComponentContextHandler.getVariables().put(variableKey, configs);
        DataResult result = manager.getComponent().executeComponents(Arrays.asList(configs));
        if (!result.getSuccess()) {
            return DataResult.fail(result.getErrMsg(), result.getShowMsg());
        }

        return DataResult.success(true);
    }
}
