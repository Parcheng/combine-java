package com.parch.combine.gui.base.build.control.tree;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.from", name = "GUI表单控件", logicConfigClass = GUIControlTreeLogicConfig.class, initConfigClass = GUIControlTreeInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTreeComponent extends AbstractGUIControlComponent<GUIControlTreeInitConfig, GUIControlTreeLogicConfig> {

    public GUIControlTreeComponent() {
        super(GUIControlTreeInitConfig.class, GUIControlTreeLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTreeInitConfig initConfig = getInitConfig();
        GUIControlTreeLogicConfig logicConfig = getLogicConfig();

        GUITreeElement.Config config = new GUITreeElement.Config();
        super.initConfig(config);
//        config.value = logicConfig.text();
//        config.events = logicConfig.events();
        return new GUITreeElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
