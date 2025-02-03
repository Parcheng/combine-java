package com.parch.combine.html.components.element;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.element.BannerElementLogicConfig;
import com.parch.combine.html.base.element.core.AbstractElementComponent;
import com.parch.combine.html.base.element.core.ElementConfig;

@Component(key = "element.banner.register", order = 400, name = "横幅元素模板配置注册组件", logicConfigClass = BannerElementLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class BannerElementComponent extends AbstractElementComponent<BannerElementLogicConfig> {

    /**
     * 构造器
     */
    public BannerElementComponent() {
        super(BannerElementLogicConfig.class, "SYSTEM.BANNER");
    }

    @Override
    protected ElementConfig getConfig() {
        return getLogicConfig().config();
    }
}
