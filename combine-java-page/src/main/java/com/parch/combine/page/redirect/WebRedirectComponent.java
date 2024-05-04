package com.parch.combine.page.redirect;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 休眠组件
 */
@Component(key = "redirect", name = "页面重定向", logicConfigClass = WebRedirectLogicConfig.class, initConfigClass = WebRedirectInitConfig.class)
@ComponentResult(name = "重定向的页面地址")
public class WebRedirectComponent extends AbsComponent<WebRedirectInitConfig, WebRedirectLogicConfig> {

    /**
     * 构造器
     */
    public WebRedirectComponent() {
        super(WebRedirectInitConfig.class, WebRedirectLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        WebRedirectLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getPath())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "重定向地址为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        WebRedirectLogicConfig logicConfig = getLogicConfig();
        Object finalPath = DataVariableHelper.parseValue(logicConfig.getPath(), false);
        if (finalPath == null) {
            return DataResult.fail(WebRedirectErrorEnum.PATH_IS_NULL);
        }

        DataResult result = DataResult.success("$web-component-redirect:" + finalPath);
        result.setStop(true);
        return result;
    }
}
