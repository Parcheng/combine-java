package com.parch.combine.html.components.page;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.page.HtmlPageErrorEnum;
import com.parch.combine.html.base.page.HtmlPageInitConfig;
import com.parch.combine.html.base.page.HtmlPageLogicConfig;
import com.parch.combine.html.base.page.builder.HtmlBuilder;
import com.parch.combine.html.common.cache.PageHtmlCache;

@Component(key = "page.build", order = 600, name = "构建页面组件", logicConfigClass = HtmlPageLogicConfig.class, initConfigClass = HtmlPageInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class HtmlPageComponent extends AbstractComponent<HtmlPageInitConfig, HtmlPageLogicConfig> {

    /**
     * 构造器
     */
    public HtmlPageComponent() {
        super(HtmlPageInitConfig.class, HtmlPageLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        HtmlPageInitConfig initConfig = getInitConfig();
        HtmlPageLogicConfig logicConfig = getLogicConfig();

        Boolean hasCache = logicConfig.hasCache();
        if (hasCache) {
            PageHtmlCache.PageCacheModel model = PageHtmlCache.INSTANCE.get(logicConfig.id());
            if (model != null) {
                return ComponentDataResult.success(model.html);
            }
        }

        HtmlBuilder builder = new HtmlBuilder(logicConfig, initConfig, manager);
        boolean success = builder.build();
        if (!success) {
            return ComponentDataResult.fail(HtmlPageErrorEnum.FAIL);
        }

        if (hasCache) {
            PageHtmlCache.INSTANCE.register(logicConfig.id(), builder.getHtml());
        }

        return ComponentDataResult.success(builder.getHtml());
    }
}
