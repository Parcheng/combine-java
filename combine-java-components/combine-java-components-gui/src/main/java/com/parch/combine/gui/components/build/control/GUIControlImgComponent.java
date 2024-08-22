package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.img.GUIControlImgInitConfig;
import com.parch.combine.gui.base.build.control.img.GUIControlImgLogicConfig;
import com.parch.combine.gui.base.build.control.img.GUIImgElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.img", order = 100, name = "GUI图片模块", logicConfigClass = GUIControlImgLogicConfig.class, initConfigClass = GUIControlImgInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlImgComponent extends AbstractGUIControlComponent<GUIControlImgInitConfig, GUIControlImgLogicConfig> {

    public GUIControlImgComponent() {
        super(GUIControlImgInitConfig.class, GUIControlImgLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlImgInitConfig initConfig = getInitConfig();
        GUIControlImgLogicConfig logicConfig = getLogicConfig();

        GUIImgElement.Config config = new GUIImgElement.Config();
        super.initConfig(config);
        config.value = logicConfig.path();
        config.width = logicConfig.width();
        config.height = logicConfig.height();
        config.events = logicConfig.events();
        return new GUIImgElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
