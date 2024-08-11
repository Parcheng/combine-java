package com.parch.combine.ui.components.redirect;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.DataResultFlag;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;

@Component(key = "redirect", name = "页面重定向", logicConfigClass = WebRedirectLogicConfig.class, initConfigClass = WebRedirectInitConfig.class)
@ComponentDesc("后端只返回重定向标识“redirect”，重定向实现需要前端实现")
@ComponentResult(name = "重定向标识和页面地址")
public class WebRedirectComponent extends AbstractComponent<WebRedirectInitConfig, WebRedirectLogicConfig> {

    public WebRedirectComponent() {
        super(WebRedirectInitConfig.class, WebRedirectLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        WebRedirectLogicConfig logicConfig = getLogicConfig();
        String finalPath = logicConfig.path();
        if (finalPath == null) {
            return ComponentDataResult.fail(WebRedirectErrorEnum.PATH_IS_NULL);
        }

        ComponentDataResult result = ComponentDataResult.success(finalPath);
        result.setDataFlag(DataResultFlag.REDIRECT);
        result.setStop(true);
        return result;
    }
}
