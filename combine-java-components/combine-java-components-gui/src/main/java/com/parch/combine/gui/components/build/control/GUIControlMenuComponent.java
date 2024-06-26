package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.menu.GUIControlMenuInitConfig;
import com.parch.combine.gui.base.build.control.menu.GUIControlMenuLogicConfig;
import com.parch.combine.gui.base.build.control.menu.GUIMenuElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.menu", name = "GUI菜单模块", logicConfigClass = GUIControlMenuLogicConfig.class, initConfigClass = GUIControlMenuInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlMenuComponent extends AbstractGUIControlComponent<GUIControlMenuInitConfig, GUIControlMenuLogicConfig> {

    public GUIControlMenuComponent() {
        super(GUIControlMenuInitConfig.class, GUIControlMenuLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlMenuInitConfig initConfig = getInitConfig();
        GUIControlMenuLogicConfig logicConfig = getLogicConfig();

        GUIMenuElement.Config config = new GUIMenuElement.Config();
        config.value = logicConfig.checkedPath();
        config.items = buildItems(logicConfig.items());
        return new GUIMenuElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }

    private GUIMenuElement.ConfigDataItem[] buildItems(GUIControlMenuLogicConfig.MenuConfig[] configs) {
        if (configs == null || configs.length == 0) {
            return null;
        }

        GUIMenuElement.ConfigDataItem[] items = new GUIMenuElement.ConfigDataItem[configs.length];
        for (int i = 0; i < configs.length; i++) {
            GUIControlMenuLogicConfig.MenuConfig config = configs[i];
            GUIMenuElement.ConfigDataItem item = new GUIMenuElement.ConfigDataItem();
            item.text = config.text();
            item.key = config.key();
            item.events = config.events();
            if (item.key == null) {
                item.key = item.text;
            }
            item.items = buildItems(config.items());
            items[i] = item;
        }

        return items;
    }
}
