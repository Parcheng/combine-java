package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.buttons.GUIButtonGroupElement;
import com.parch.combine.gui.base.build.control.buttons.GUIControlButtonGroupInitConfig;
import com.parch.combine.gui.base.build.control.buttons.GUIControlButtonGroupLogicConfig;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.buttons", order = 100, name = "GUI按钮组控件", logicConfigClass = GUIControlButtonGroupLogicConfig.class, initConfigClass = GUIControlButtonGroupInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlButtonGroupComponent extends AbstractGUIControlComponent<GUIControlButtonGroupInitConfig, GUIControlButtonGroupLogicConfig> {

    public GUIControlButtonGroupComponent() {
        super(GUIControlButtonGroupInitConfig.class, GUIControlButtonGroupLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlButtonGroupInitConfig initConfig = getInitConfig();
        GUIControlButtonGroupLogicConfig logicConfig = getLogicConfig();

        GUIButtonGroupElement.Config config = new GUIButtonGroupElement.Config();
        super.initConfig(config);
        config.defaultText = logicConfig.defaultText();
        config.events = logicConfig.events();

        GUIControlButtonGroupLogicConfig.ButtonItemConfig[] items = logicConfig.items();
        if (items != null) {
            config.value = new GUIButtonGroupElement.ItemConfig[items.length];
            for (int i = 0; i < items.length; i++) {
                config.value[i].value = items[i].text();
                config.value[i].type = items[i].type();
                config.value[i].eventKeys = items[i].eventKeys();
            }
        }

        return new GUIButtonGroupElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
