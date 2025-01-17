package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.base.template.TreeElementTemplateLogicConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;

@Component(key = "template.tree.register", order = 300, name = "树元素模板配置注册组件", logicConfigClass = TreeElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TreeTemplateComponent extends AbstractTemplateComponent<TreeElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public TreeTemplateComponent() {
        super(TreeElementTemplateLogicConfig.class, "SYSTEM.TREE");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
