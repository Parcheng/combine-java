package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.TreeElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.tree.register", order = 400, name = "树元素模板配置注册组件", logicConfigClass = TreeElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class TreeElementComponent extends AbstractElementComponent<TreeElementLogicConfig> {

    /**
     * 构造器
     */
    public TreeElementComponent() {
        super(TreeElementLogicConfig.class, "SYSTEM.TREE");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
