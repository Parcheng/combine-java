package com.parch.combine.html.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.template.AbstractTemplateComponent;
import com.parch.combine.html.base.template.PageTurningElementTemplateLogicConfig;
import com.parch.combine.html.base.template.ElementTemplateConfig;

@Component(key = "template.pageTurning.register", order = 300, name = "翻页元素模板配置注册组件", logicConfigClass = PageTurningElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PageTurningTemplateComponent extends AbstractTemplateComponent<PageTurningElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public PageTurningTemplateComponent() {
        super(PageTurningElementTemplateLogicConfig.class, "SYSTEM.PAGE_TURNING");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
