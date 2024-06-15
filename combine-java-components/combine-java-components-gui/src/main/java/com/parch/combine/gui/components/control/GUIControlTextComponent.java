package com.parch.combine.gui.components.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.text.GUIControlTextInitConfig;
import com.parch.combine.gui.base.control.text.GUIControlTextLogicConfig;
import com.parch.combine.gui.base.control.text.GUITextElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.text", name = "GUI文本控件", logicConfigClass = GUIControlTextLogicConfig.class, initConfigClass = GUIControlTextInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTextComponent extends AbsGUIControlComponent<GUIControlTextInitConfig, GUIControlTextLogicConfig> {

    public GUIControlTextComponent() {
        super(GUIControlTextInitConfig.class, GUIControlTextLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTextInitConfig initConfig = getInitConfig();
        GUIControlTextLogicConfig logicConfig = getLogicConfig();

        GUITextElement.Config config = new GUITextElement.Config();
        config.text = logicConfig.text();
        return new GUITextElement(initConfig.template(), config);
    }
}
