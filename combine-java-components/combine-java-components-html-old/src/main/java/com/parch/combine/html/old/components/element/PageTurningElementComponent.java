package com.parch.combine.html.old.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.element.PageTurningElementLogicConfig;
import com.parch.combine.html.old.base.element.core.AbstractElementComponent;
import com.parch.combine.html.old.base.element.core.ElementConfig;

@Component(key = "element.pageTurning.register", order = 400, name = "翻页元素模板配置注册组件", logicConfigClass = PageTurningElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PageTurningElementComponent extends AbstractElementComponent<PageTurningElementLogicConfig> {

    /**
     * 构造器
     */
    public PageTurningElementComponent() {
        super(PageTurningElementLogicConfig.class, "SYSTEM.PAGE_TURNING");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
