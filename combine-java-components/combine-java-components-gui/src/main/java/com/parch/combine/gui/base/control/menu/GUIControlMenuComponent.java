package com.parch.combine.gui.base.control.menu;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.slider", name = "GUI滑块模块", logicConfigClass = GUIControlMenuLogicConfig.class, initConfigClass = GUIControlMenuInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlMenuComponent extends AbsGUIControlComponent<GUIControlMenuInitConfig, GUIControlMenuLogicConfig> {

    public GUIControlMenuComponent() {
        super(GUIControlMenuInitConfig.class, GUIControlMenuLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlMenuInitConfig initConfig = getInitConfig();
        GUIControlMenuLogicConfig logicConfig = getLogicConfig();

        GUIMenuElement.Config config = new GUIMenuElement.Config();
        return new GUIMenuElement(initConfig.template(), config);
    }
}
