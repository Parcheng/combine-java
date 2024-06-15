package com.parch.combine.gui.components.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.label.GUIControlLabelInitConfig;
import com.parch.combine.gui.base.control.label.GUIControlLabelLogicConfig;
import com.parch.combine.gui.base.control.label.GUILabelElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.label", name = "GUI标题控件", logicConfigClass = GUIControlLabelLogicConfig.class, initConfigClass = GUIControlLabelInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlLabelComponent extends AbsGUIControlComponent<GUIControlLabelInitConfig, GUIControlLabelLogicConfig> {

    public GUIControlLabelComponent() {
        super(GUIControlLabelInitConfig.class, GUIControlLabelLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlLabelInitConfig initConfig = getInitConfig();
        GUIControlLabelLogicConfig logicConfig = getLogicConfig();

        GUILabelElement.Config config = new GUILabelElement.Config();
        config.text = logicConfig.text();
        return new GUILabelElement(initConfig.template(), config);
    }
}
