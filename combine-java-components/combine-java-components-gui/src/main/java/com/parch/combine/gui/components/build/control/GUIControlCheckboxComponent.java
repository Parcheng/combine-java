package com.parch.combine.gui.components.build.control;

import com.parch.combine.gui.base.build.AbsGUIControlComponent;
import com.parch.combine.gui.base.build.control.checkbox.GUICheckboxElement;
import com.parch.combine.gui.base.build.control.checkbox.GUIControlCheckboxInitConfig;
import com.parch.combine.gui.base.build.control.checkbox.GUIControlCheckboxLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "build.control.checkbox", name = "GUI多选框控件", logicConfigClass = GUIControlCheckboxLogicConfig.class, initConfigClass = GUIControlCheckboxInitConfig.class)
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
        config.events = logicConfig.events();
        return new GUICheckboxElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
