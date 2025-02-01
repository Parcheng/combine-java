package com.parch.combine.html.components.page;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.ConfigClearComponent;
import com.parch.combine.html.old.base.ConfigClearLogicConfig;
import com.parch.combine.html.common.cache.PageHtmlCache;

@Component(key = "page.clear", order = 499, name = "页面缓存清除组件", logicConfigClass = ConfigClearLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class PageClearComponent extends ConfigClearComponent {

    /**
     * 构造器
     */
    public PageClearComponent() {
        super(PageHtmlCache.INSTANCE);
    }
}
