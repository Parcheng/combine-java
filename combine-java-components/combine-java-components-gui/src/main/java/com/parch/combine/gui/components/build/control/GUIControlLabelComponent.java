package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbsGUIControlComponent;
import com.parch.combine.gui.base.build.control.label.GUIControlLabelInitConfig;
import com.parch.combine.gui.base.build.control.label.GUIControlLabelLogicConfig;
import com.parch.combine.gui.base.build.control.label.GUILabelElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.label", name = "GUI标题控件", logicConfigClass = GUIControlLabelLogicConfig.class, initConfigClass = GUIControlLabelInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlLabelComponent extends AbsGUIControlComponent<GUIControlLabelInitConfig, GUIControlLabelLogicConfig> {

    public GUIControlLabelComponent() {
        super(GUIControlLabelInitConfig.class, GUIControlLabelLogicConfig.class);
    }

    @Override
    public IGUIElement getElement(String elementId) {
        GUIControlLabelInitConfig initConfig = getInitConfig();
        GUIControlLabelLogicConfig logicConfig = getLogicConfig();

        GUILabelElement.Config config = new GUILabelElement.Config();
        config.text = logicConfig.text();
        config.events = logicConfig.events();
        return new GUILabelElement(getScopeKey(), elementId, logicConfig.data(), initConfig.template(), config);
    }
}
