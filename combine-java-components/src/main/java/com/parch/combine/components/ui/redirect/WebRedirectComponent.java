package com.parch.combine.components.ui.redirect;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.DataResultFlag;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.tools.variable.TextExpressionHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 休眠组件
 */
@Component(key = "redirect", name = "页面重定向", logicConfigClass = WebRedirectLogicConfig.class, initConfigClass = WebRedirectInitConfig.class)
@ComponentDesc("后端只返回重定向标识“redirect”，重定向实现需要前端实现")
@ComponentResult(name = "重定向标识和页面地址")
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
        String finalPath = TextExpressionHelper.getText(logicConfig.getPath());
        if (finalPath == null) {
            return DataResult.fail(WebRedirectErrorEnum.PATH_IS_NULL);
        }

        DataResult result = DataResult.success(finalPath);
        result.setDataFlag(DataResultFlag.REDIRECT);
        result.setStop(true);
        return result;
    }
}
