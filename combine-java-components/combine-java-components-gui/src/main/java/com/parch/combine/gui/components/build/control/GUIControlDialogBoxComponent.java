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
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

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

        GUISubElementConfig[] elements = GUISubElementHelper.convert(guiElementManager, logicConfig.bodyElements());
        if (elements == null) {
            return null;
        }

        config.elementConfigs = elements;
        return new GUIDialogBoxElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
