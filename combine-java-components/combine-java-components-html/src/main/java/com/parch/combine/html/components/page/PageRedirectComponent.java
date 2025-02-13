package com.parch.combine.html.components.page;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.page.PageRedirectLogicConfig;

@Component(key = "page.redirect", order = 602, name = "页面重定向组件", logicConfigClass = PageRedirectLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "重定向地址")
public class PageRedirectComponent extends AbstractComponent<IInvalidInitConfig, PageRedirectLogicConfig> {

    /**
     * 构造器
     */
    public PageRedirectComponent() {
        super(IInvalidInitConfig.class, PageRedirectLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        PageRedirectLogicConfig logicConfig = getLogicConfig();

        ComponentDataResult result = ComponentDataResult.success(logicConfig.linkTo());
        result.setDataFlag("redirect");
        result.setStop(true);
        return result;
    }
}
