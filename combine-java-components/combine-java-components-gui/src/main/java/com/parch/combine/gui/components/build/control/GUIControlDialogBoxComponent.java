package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbsGUIControlComponent;
import com.parch.combine.gui.base.build.control.dialogbox.GUIControlDialogBoxInitConfig;
import com.parch.combine.gui.base.build.control.dialogbox.GUIControlDialogBoxLogicConfig;
import com.parch.combine.gui.base.build.control.dialogbox.GUIDialogBoxElement;
import com.parch.combine.gui.base.build.control.panel.GUIControlPanelLogicConfig;
import com.parch.combine.gui.base.build.control.panel.GUIPanelElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.dialogbox", name = "GUI对话窗模块", logicConfigClass = GUIControlDialogBoxLogicConfig.class, initConfigClass = GUIControlDialogBoxInitConfig.class)
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
        config.title = logicConfig.title();
        config.width = logicConfig.width();
        config.height = logicConfig.height();
        config.visible = logicConfig.visible();

//        GUIControlPanelLogicConfig.ElementConfig[] elementConfigs = logicConfig.bodyElements();
//        if (elementConfigs == null) {
//            return null;
//        }
//
//        config.elementConfigs = new GUIPanelElement.ElementItemConfig[elementConfigs.length];
//        for (int i = 0; i < elementConfigs.length; i++) {
//            GUIControlPanelLogicConfig.ElementConfig elementConfig = elementConfigs[i];
//            config.elementConfigs[i] = new GUIPanelElement.ElementItemConfig();
//            config.elementConfigs[i].dataField = elementConfig.dataField();
//            config.elementConfigs[i].key = elementConfig.key() == null ? config.elementConfigs[i].dataField : elementConfig.key();
//            config.elementConfigs[i].events = elementConfig.events();
//            config.elementConfigs[i].element = guiElementManager.get(elementConfig.elementId());
//            if (config.elementConfigs[i].element == null) {
//                return null;
//            }
//        }

        return new GUIDialogBoxElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
