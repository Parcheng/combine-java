package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.from.GUIControlFromInitConfig;
import com.parch.combine.gui.base.build.control.from.GUIControlFromLogicConfig;
import com.parch.combine.gui.base.build.control.from.GUIFromElement;
import com.parch.combine.gui.base.build.control.from.LayoutEnum;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;

@Component(key = "build.control.from", order = 100, name = "GUI表单控件", logicConfigClass = GUIControlFromLogicConfig.class, initConfigClass = GUIControlFromInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlFromComponent extends AbstractGUIControlComponent<GUIControlFromInitConfig, GUIControlFromLogicConfig> {

    public GUIControlFromComponent() {
        super(GUIControlFromInitConfig.class, GUIControlFromLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlFromInitConfig initConfig = getInitConfig();
        GUIControlFromLogicConfig logicConfig = getLogicConfig();

        GUIFromElement.Config config = new GUIFromElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.layout = LayoutEnum.get(logicConfig.layout());
        config.rate = logicConfig.rate();
        config.labelRate = logicConfig.labelRate();
        config.elementRate = logicConfig.elementRate();

        GUIFromElement.ItemConfig[] elements = GUISubElementConfig.convert(guiElementManager, logicConfig.items(),
                (elementItem, configItem) -> {
                    elementItem.label = configItem.label();
                    elementItem.rate = configItem.rate();
                    elementItem.labelRate = configItem.labelRate();
                    elementItem.elementRate = configItem.elementRate();
                }, GUIFromElement.ItemConfig.class);
        if (elements == null) {
            return null;
        }

        config.items = elements;
        return new GUIFromElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
