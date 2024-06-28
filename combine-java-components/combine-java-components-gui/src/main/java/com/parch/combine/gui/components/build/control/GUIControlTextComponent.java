package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.text.GUIControlTextInitConfig;
import com.parch.combine.gui.base.build.control.text.GUIControlTextLogicConfig;
import com.parch.combine.gui.base.build.control.text.GUITextElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.text", name = "GUI文本控件", logicConfigClass = GUIControlTextLogicConfig.class, initConfigClass = GUIControlTextInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTextComponent extends AbstractGUIControlComponent<GUIControlTextInitConfig, GUIControlTextLogicConfig> {

    public GUIControlTextComponent() {
        super(GUIControlTextInitConfig.class, GUIControlTextLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTextInitConfig initConfig = getInitConfig();
        GUIControlTextLogicConfig logicConfig = getLogicConfig();

        GUITextElement.Config config = new GUITextElement.Config();
        super.initConfig(config);
        config.value = logicConfig.text();
        config.events = logicConfig.events();
        return new GUITextElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
