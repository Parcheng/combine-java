package com.parch.combine.gui.components.build.control;

import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.select.GUIControSelectLogicConfig;
import com.parch.combine.gui.base.build.control.select.GUIControlSelectInitConfig;
import com.parch.combine.gui.base.build.control.select.GUISelectElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

@Component(key = "build.control.select", name = "GUI下拉框控件", logicConfigClass = GUIControSelectLogicConfig.class, initConfigClass = GUIControlSelectInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlSelectComponent extends AbstractGUIControlComponent<GUIControlSelectInitConfig, GUIControSelectLogicConfig> {

    public GUIControlSelectComponent() {
        super(GUIControlSelectInitConfig.class, GUIControSelectLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlSelectInitConfig initConfig = getInitConfig();
        GUIControSelectLogicConfig logicConfig = getLogicConfig();

        GUISelectElement.Config config = new GUISelectElement.Config();
        config.value = logicConfig.value();
        config.options = logicConfig.options();
        config.events = logicConfig.events();
        return new GUISelectElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
