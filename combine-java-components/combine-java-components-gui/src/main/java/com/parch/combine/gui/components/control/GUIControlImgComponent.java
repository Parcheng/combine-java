package com.parch.combine.gui.components.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.control.AbsGUIControlComponent;
import com.parch.combine.gui.base.control.img.GUIControlImgInitConfig;
import com.parch.combine.gui.base.control.img.GUIControlImgLogicConfig;
import com.parch.combine.gui.base.control.img.GUIImgElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "control.img", name = "GUI图片模块", logicConfigClass = GUIControlImgLogicConfig.class, initConfigClass = GUIControlImgInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlImgComponent extends AbsGUIControlComponent<GUIControlImgInitConfig, GUIControlImgLogicConfig> {

    public GUIControlImgComponent() {
        super(GUIControlImgInitConfig.class, GUIControlImgLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlImgInitConfig initConfig = getInitConfig();
        GUIControlImgLogicConfig logicConfig = getLogicConfig();

        GUIImgElement.Config config = new GUIImgElement.Config();
        config.path = logicConfig.path();
        config.width = logicConfig.width();
        config.height = logicConfig.height();
        return new GUIImgElement(initConfig.template(), config);
    }
}
