package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.html.GUIControlHtmlInitConfig;
import com.parch.combine.gui.base.build.control.html.GUIControlHtmlLogicConfig;
import com.parch.combine.gui.base.build.control.html.GUIHtmlElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.html", order = 100, name = "GUI页面模块", logicConfigClass = GUIControlHtmlLogicConfig.class, initConfigClass = GUIControlHtmlInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlHtmlComponent extends AbstractGUIControlComponent<GUIControlHtmlInitConfig, GUIControlHtmlLogicConfig> {

    public GUIControlHtmlComponent() {
        super(GUIControlHtmlInitConfig.class, GUIControlHtmlLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlHtmlInitConfig initConfig = getInitConfig();
        GUIControlHtmlLogicConfig logicConfig = getLogicConfig();

        GUIHtmlElement.Config config = new GUIHtmlElement.Config();
        super.initConfig(config);
        config.value = logicConfig.path();
        config.errorText = logicConfig.errorText();
        config.events = logicConfig.events();
        return new GUIHtmlElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
