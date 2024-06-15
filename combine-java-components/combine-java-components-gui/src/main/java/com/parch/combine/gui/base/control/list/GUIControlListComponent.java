package com.parch.combine.gui.base.control.list;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.list", name = "GUI列表控件", logicConfigClass = GUIControlListLogicConfig.class, initConfigClass = GUIControlListInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlListComponent extends AbsGUIControlComponent<GUIControlListInitConfig, GUIControlListLogicConfig> {

    public GUIControlListComponent() {
        super(GUIControlListInitConfig.class, GUIControlListLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlListInitConfig initConfig = getInitConfig();
        GUIControlListLogicConfig logicConfig = getLogicConfig();

        GUIListElement.Config config = new GUIListElement.Config();
        config.values = logicConfig.values();
        config.options = logicConfig.options();
        return new GUIListElement(initConfig.template(), config);
    }
}
