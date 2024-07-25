package com.parch.combine.gui.base.build.control.paging;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.gui.base.build.AbstractGUIControlComponent;
import com.parch.combine.gui.core.element.IGUIElement;

@Component(key = "build.control.from", name = "GUI表单控件", logicConfigClass = GUIControlPagingLogicConfig.class, initConfigClass = GUIControlPagingInitConfig.class)
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
//        config.value = logicConfig.text();
//        config.events = logicConfig.events();
        return new GUIPagingElement(getScopeKey(), this.domain, this.elementId, logicConfig.data(), initConfig.template(), config);
    }
}
