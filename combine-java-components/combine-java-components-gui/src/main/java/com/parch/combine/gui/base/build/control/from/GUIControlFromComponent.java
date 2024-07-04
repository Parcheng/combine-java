package com.parch.combine.gui.base.build.control.from;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.from", name = "GUI表单控件", logicConfigClass = GUIControlFromLogicConfig.class, initConfigClass = GUIControlFromInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlFromComponent extends AbstractGUIControlComponent<GUIControlFromInitConfig, GUIControlFromLogicConfig> {

    public GUIControlFromComponent() {
        super(GUIControlFromInitConfig.class, GUIControlFromLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlFromInitConfig initConfig = getInitConfig();
        GUIControlFromLogicConfig logicConfig = getLogicConfig();

        GUIFromElement.Config config = new GUIFromElement.Config();
        super.initConfig(config);
        config.value = logicConfig.text();
        config.events = logicConfig.events();
        return new GUIFromElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
