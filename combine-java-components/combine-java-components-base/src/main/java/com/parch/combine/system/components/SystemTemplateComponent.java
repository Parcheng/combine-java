package com.parch.combine.system.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.system.base.template.SystemTemplateErrorEnum;
import com.parch.combine.system.base.template.SystemTemplateInitConfig;
import com.parch.combine.system.base.template.SystemTemplateLogicConfig;

@Component(key = "template", name = "模板组件", logicConfigClass = SystemTemplateLogicConfig.class, initConfigClass = SystemTemplateInitConfig.class)
@ComponentResult(name = "模板中组件报错信息或 true")
public class SystemTemplateComponent extends AbstractComponent<SystemTemplateInitConfig, SystemTemplateLogicConfig> {

    public SystemTemplateComponent() {
        super(SystemTemplateInitConfig.class, SystemTemplateLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SystemTemplateInitConfig initConfig = getInitConfig();
        SystemTemplateLogicConfig logicConfig = getLogicConfig();

        String[] componentIds = null;
        String key = logicConfig.key();
        SystemTemplateInitConfig.SystemTemplate[] configs = initConfig.templates();
        for (SystemTemplateInitConfig.SystemTemplate item : configs) {
            if (key.equals(item.key())) {
                componentIds = item.components();
            }
        }

        if (componentIds == null) {
            return ComponentDataResult.fail(SystemTemplateErrorEnum.TEMPLATE_IS_NULL);
        }

        ComponentContextHandler.getVariables().put(initConfig.variableKey(), logicConfig.configs());
        ComponentDataResult result = SubComponentTool.execute(manager, componentIds);
        if (!result.getSuccess()) {
            return ComponentDataResult.fail(result.getErrMsg(), result.getShowMsg());
        }

        return ComponentDataResult.success(true);
    }
}
