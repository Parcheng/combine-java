package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbsGUIControlComponent;
import com.parch.combine.gui.base.build.control.panel.GUIControlPanelInitConfig;
import com.parch.combine.gui.base.build.control.panel.GUIControlPanelLogicConfig;
import com.parch.combine.gui.base.build.control.panel.GUIPanelElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.panel", name = "GUI面板控件", logicConfigClass = GUIControlPanelLogicConfig.class, initConfigClass = GUIControlPanelInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlPanelComponent extends AbsGUIControlComponent<GUIControlPanelInitConfig, GUIControlPanelLogicConfig> {

    public GUIControlPanelComponent() {
        super(GUIControlPanelInitConfig.class, GUIControlPanelLogicConfig.class);
    }

    @Override
    public IGUIElement getElement(String elementId) {
        GUIControlPanelInitConfig initConfig = getInitConfig();
        GUIControlPanelLogicConfig logicConfig = getLogicConfig();

        GUIPanelElement.Config config = new GUIPanelElement.Config();
        config.data = logicConfig.value();

        GUIControlPanelLogicConfig.ElementConfig[] elementConfigs = logicConfig.bodyElements();
        if (elementConfigs == null) {
            return null;
        }

        config.elementConfigs = new GUIPanelElement.ElementItemConfig[elementConfigs.length];
        for (int i = 0; i < elementConfigs.length; i++) {
            GUIControlPanelLogicConfig.ElementConfig elementConfig = elementConfigs[i];
            config.elementConfigs[i] = new GUIPanelElement.ElementItemConfig();
            config.elementConfigs[i].dataField = elementConfig.dataField();
            config.elementConfigs[i].key = elementConfig.key() == null ? config.elementConfigs[i].dataField : elementConfig.key();
            config.elementConfigs[i].element = guiElementManager.get(elementConfig.elementId());
            if (config.elementConfigs[i].element == null) {
                return null;
            }
        }

        return new GUIPanelElement(getScopeKey(), elementId, logicConfig.data(), initConfig.template(), config);
    }
}
