package com.parch.combine.gui.base.module.from;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "module.from", name = "GUI表单模块", logicConfigClass = GUIModuleFromLogicConfig.class, initConfigClass = GUIModuleFromInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIModuleFromComponent extends AbsGUIControlComponent<GUIModuleFromInitConfig, GUIModuleFromLogicConfig> {

    public GUIModuleFromComponent() {
        super(GUIModuleFromInitConfig.class, GUIModuleFromLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIModuleFromInitConfig initConfig = getInitConfig();
        GUIModuleFromLogicConfig logicConfig = getLogicConfig();

        GUIModuleFromElement.Config config = new GUIModuleFromElement.Config();
        config.text = logicConfig.text();
        return new GUIModuleFromElement(initConfig.template(), config);
    }
}
