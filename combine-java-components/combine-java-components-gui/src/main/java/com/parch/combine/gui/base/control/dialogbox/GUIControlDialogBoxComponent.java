package com.parch.combine.gui.base.control.dialogbox;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.slider", name = "GUI滑块模块", logicConfigClass = GUIControlDialogBoxLogicConfig.class, initConfigClass = GUIControlDialogBoxInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlDialogBoxComponent extends AbsGUIControlComponent<GUIControlDialogBoxInitConfig, GUIControlDialogBoxLogicConfig> {

    public GUIControlDialogBoxComponent() {
        super(GUIControlDialogBoxInitConfig.class, GUIControlDialogBoxLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlDialogBoxInitConfig initConfig = getInitConfig();
        GUIControlDialogBoxLogicConfig logicConfig = getLogicConfig();

        GUIDialogBoxElement.Config config = new GUIDialogBoxElement.Config();
        return new GUIDialogBoxElement(initConfig.template(), config);
    }
}
