package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.list.GUIControlListInitConfig;
import com.parch.combine.gui.base.build.control.list.GUIControlListLogicConfig;
import com.parch.combine.gui.base.build.control.list.GUIListElement;
import com.parch.combine.gui.base.build.control.list.ListOrientationEnum;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

@Component(key = "build.control.list", order = 100, name = "GUI列表控件", logicConfigClass = GUIControlListLogicConfig.class, initConfigClass = GUIControlListInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlListComponent extends AbstractGUIControlComponent<GUIControlListInitConfig, GUIControlListLogicConfig> {

    public GUIControlListComponent() {
        super(GUIControlListInitConfig.class, GUIControlListLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlListInitConfig initConfig = getInitConfig();
        GUIControlListLogicConfig logicConfig = getLogicConfig();

        GUIListElement.Config config = new GUIListElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.orientation = ListOrientationEnum.get(logicConfig.orientation()).getValue();
        config.emptyTipText = logicConfig.emptyTipText();

        GUISubElementConfig[] elements = GUISubElementConfig.convert(guiElementManager, logicConfig.bodyElements());
        if (elements == null) {
            return null;
        }

        config.elementConfigs = elements;
        return new GUIListElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
