package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.panel.GUIControlPanelInitConfig;
import com.parch.combine.gui.base.build.control.panel.GUIControlPanelLogicConfig;
import com.parch.combine.gui.base.build.control.panel.GUIPanelElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.panel", name = "GUI面板控件", logicConfigClass = GUIControlPanelLogicConfig.class, initConfigClass = GUIControlPanelInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlPanelComponent extends AbstractGUIControlComponent<GUIControlPanelInitConfig, GUIControlPanelLogicConfig> {

    public GUIControlPanelComponent() {
        super(GUIControlPanelInitConfig.class, GUIControlPanelLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlPanelInitConfig initConfig = getInitConfig();
        GUIControlPanelLogicConfig logicConfig = getLogicConfig();

        GUIPanelElement.Config config = new GUIPanelElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();

        GUISubElementConfig[] elements = GUISubElementHelper.convert(guiElementManager, logicConfig.bodyElements());
        if (elements == null) {
            return null;
        }

        config.elementConfigs = elements;
        return new GUIPanelElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
