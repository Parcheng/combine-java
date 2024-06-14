package com.parch.combine.components.gui.control.select;

import com.parch.combine.components.gui.control.AbsGUIControlComponent;
import com.parch.combine.components.gui.core.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "control.select", name = "GUI下拉框控件", logicConfigClass = GUIControSelectLogicConfig.class, initConfigClass = GUIControlSelectInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlSelectComponent extends AbsGUIControlComponent<GUIControlSelectInitConfig, GUIControSelectLogicConfig> {

    public GUIControlSelectComponent() {
        super(GUIControlSelectInitConfig.class, GUIControSelectLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlSelectInitConfig initConfig = getInitConfig();
        GUIControSelectLogicConfig logicConfig = getLogicConfig();
        return new GUISelectElement(initConfig.template(), logicConfig.value(), logicConfig.options());
    }
}
