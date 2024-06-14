package com.parch.combine.components.gui.control.checkbox;

import com.parch.combine.components.gui.control.AbsGUIControlComponent;
import com.parch.combine.components.gui.core.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "control.checkbox", name = "GUI多选框控件", logicConfigClass = GUIControlCheckboxLogicConfig.class, initConfigClass = GUIControlCheckboxInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlCheckboxComponent extends AbsGUIControlComponent<GUIControlCheckboxInitConfig, GUIControlCheckboxLogicConfig> {

    public GUIControlCheckboxComponent() {
        super(GUIControlCheckboxInitConfig.class, GUIControlCheckboxLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlCheckboxInitConfig initConfig = getInitConfig();
        GUIControlCheckboxLogicConfig logicConfig = getLogicConfig();

        GUICheckboxElement.Config config = new GUICheckboxElement.Config();
        config.values = logicConfig.values();
        config.options = logicConfig.options();
        return new GUICheckboxElement(initConfig.template(), config);
    }
}
