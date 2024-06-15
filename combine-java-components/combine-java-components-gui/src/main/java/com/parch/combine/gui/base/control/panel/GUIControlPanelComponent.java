package com.parch.combine.gui.base.control.panel;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.panel", name = "GUI面板控件", logicConfigClass = GUIControlPanelLogicConfig.class, initConfigClass = GUIControlPanelInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlPanelComponent extends AbsGUIControlComponent<GUIControlPanelInitConfig, GUIControlPanelLogicConfig> {

    public GUIControlPanelComponent() {
        super(GUIControlPanelInitConfig.class, GUIControlPanelLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlPanelInitConfig initConfig = getInitConfig();
        GUIControlPanelLogicConfig logicConfig = getLogicConfig();

        GUIPanelElement.Config config = new GUIPanelElement.Config();
//        config.values = logicConfig.values();
//        config.options = logicConfig.options();
        return new GUIPanelElement(initConfig.template(), config);
    }
}
