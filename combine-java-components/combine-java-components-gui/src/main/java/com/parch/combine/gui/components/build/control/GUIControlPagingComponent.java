package com.parch.combine.gui.components.build.control;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.base.build.control.paging.GUIControlPagingInitConfig;
import com.parch.combine.gui.base.build.control.paging.GUIControlPagingLogicConfig;
import com.parch.combine.gui.base.build.control.paging.GUIPagingElement;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.paging", order = 100, name = "GUI分页控件", logicConfigClass = GUIControlPagingLogicConfig.class, initConfigClass = GUIControlPagingInitConfig.class)
@ComponentResult(name = "控件构建失败的错误信息或 true")
public class GUIControlPagingComponent extends AbstractGUIControlComponent<GUIControlPagingInitConfig, GUIControlPagingLogicConfig> {

    public GUIControlPagingComponent() {
        super(GUIControlPagingInitConfig.class, GUIControlPagingLogicConfig.class);
    }

    @Override
    public IGUIElement getElement() {
        GUIControlPagingInitConfig initConfig = getInitConfig();
        GUIControlPagingLogicConfig logicConfig = getLogicConfig();

        GUIPagingElement.Config config = new GUIPagingElement.Config();
        super.initConfig(config);

        config.showPageTagCount = initConfig.showCount();
        config.previousText = initConfig.previousText();
        config.nextText = initConfig.nextText();
        config.firstText = initConfig.firstText();
        config.lastText = initConfig.lastText();

        GUIControlPagingLogicConfig.PageValue pageValue = logicConfig.value();
        config.value = new GUIPagingElement.ConfigValue();
        config.value.page = pageValue.page();
        config.value.pageSize = pageValue.pageSize();
        config.value.dataCount = pageValue.dataCount();

        return new GUIPagingElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
