package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.button.GUIButtonElement;
import com.parch.combine.gui.base.build.control.button.GUIControlButtonInitConfig;
import com.parch.combine.gui.base.build.control.button.GUIControlButtonLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.button", name = "GUI按钮控件", logicConfigClass = GUIControlButtonLogicConfig.class, initConfigClass = GUIControlButtonInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlButtonComponent extends AbstractGUIControlComponent<GUIControlButtonInitConfig, GUIControlButtonLogicConfig> {

    public GUIControlButtonComponent() {
        super(GUIControlButtonInitConfig.class, GUIControlButtonLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlButtonInitConfig initConfig = getInitConfig();
        GUIControlButtonLogicConfig logicConfig = getLogicConfig();

        GUIButtonElement.Config config = new GUIButtonElement.Config();
        config.value = logicConfig.text();
        config.events = logicConfig.events();
        return new GUIButtonElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
