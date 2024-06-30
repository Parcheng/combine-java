package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.table.GUIControlTableInitConfig;
import com.parch.combine.gui.base.build.control.table.GUIControlTableLogicConfig;
import com.parch.combine.gui.base.build.control.table.GUITableElement;
import com.parch.combine.gui.core.element.IGUIElement;
import com.parch.combine.gui.core.element.sub.GUISubElementConfig;
import com.parch.combine.gui.core.element.sub.GUISubElementHelper;

@Component(key = "build.control.table", name = "GUI表格控件", logicConfigClass = GUIControlTableLogicConfig.class, initConfigClass = GUIControlTableInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlTableComponent extends AbstractGUIControlComponent<GUIControlTableInitConfig, GUIControlTableLogicConfig> {

    public GUIControlTableComponent() {
        super(GUIControlTableInitConfig.class, GUIControlTableLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlTableInitConfig initConfig = getInitConfig();
        GUIControlTableLogicConfig logicConfig = getLogicConfig();

        GUITableElement.Config config = new GUITableElement.Config();
        super.initConfig(config);
        config.value = logicConfig.value();
        config.headNames = logicConfig.headNames();
        config.minRow = logicConfig.minRow();
        config.rowHeight = logicConfig.rowHeight();
        config.rowMargin = logicConfig.rowMargin();

        GUISubElementConfig[] elements = GUISubElementHelper.convert(guiElementManager, logicConfig.rowElements());
        if (elements == null) {
            return null;
        }

        config.columnConfigs = elements;
        return new GUITableElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
