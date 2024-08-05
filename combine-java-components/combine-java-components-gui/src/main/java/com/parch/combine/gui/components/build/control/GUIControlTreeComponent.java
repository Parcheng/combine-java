package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.tree.GUIControlTreeInitConfig;
import com.parch.combine.gui.base.build.control.tree.GUIControlTreeLogicConfig;
import com.parch.combine.gui.base.build.control.tree.GUITreeElement;
import com.parch.combine.gui.core.element.IGUIElement;

import java.util.UUID;

@Component(key = "build.control.tree", name = "GUI树控件", logicConfigClass = GUIControlTreeLogicConfig.class, initConfigClass = GUIControlTreeInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTreeComponent extends AbstractGUIControlComponent<GUIControlTreeInitConfig, GUIControlTreeLogicConfig> {

    public GUIControlTreeComponent() {
        super(GUIControlTreeInitConfig.class, GUIControlTreeLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTreeInitConfig initConfig = getInitConfig();
        GUIControlTreeLogicConfig logicConfig = getLogicConfig();

        GUITreeElement.Config config = new GUITreeElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.events = logicConfig.events();
        config.items = buildItems(logicConfig.items());
        return new GUITreeElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }

    private GUITreeElement.ItemConfig[] buildItems(GUIControlTreeLogicConfig.ItemConfig[] configs) {
        if (configs == null || configs.length == 0) {
            return null;
        }

        GUITreeElement.ItemConfig[] items = new GUITreeElement.ItemConfig[configs.length];
        for (int i = 0; i < configs.length; i++) {
            GUIControlTreeLogicConfig.ItemConfig config = configs[i];
            GUITreeElement.ItemConfig item = new GUITreeElement.ItemConfig();
            item.text = config.text();
            item.key = config.key();
            if (item.key == null) {
                item.key = UUID.randomUUID().toString();;
            }
            item.children = buildItems(config.children());
            items[i] = item;
        }

        return items;
    }
}
