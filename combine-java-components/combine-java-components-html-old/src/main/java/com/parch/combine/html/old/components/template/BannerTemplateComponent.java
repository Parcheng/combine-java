package com.parch.combine.html.old.components.template;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.old.base.template.core.AbstractTemplateComponent;
import com.parch.combine.html.old.base.template.BannerElementTemplateLogicConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;

@Component(key = "template.banner.register", order = 300, name = "横幅元素模板配置注册组件", logicConfigClass = BannerElementTemplateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class BannerTemplateComponent extends AbstractTemplateComponent<BannerElementTemplateLogicConfig> {

    /**
     * 构造器
     */
    public BannerTemplateComponent() {
        super(BannerElementTemplateLogicConfig.class, "SYSTEM.BANNER");
    }

    @Override
    protected ElementTemplateConfig getConfig() {
        return getLogicConfig().config();
    }
}
