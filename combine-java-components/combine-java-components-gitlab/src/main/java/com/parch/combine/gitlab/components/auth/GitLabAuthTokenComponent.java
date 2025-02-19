package com.parch.combine.gitlab.components.auth;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthTokenLogicConfig;
import com.parch.combine.gitlab.helper.GitlabApiCache;

@Component(key = "auth.token", order = 100, name = "秘钥登录组件", logicConfigClass = GitLabAuthTokenLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitLabAuthTokenComponent extends AbstractComponent<GitlabInitConfig, GitLabAuthTokenLogicConfig> {

    public GitLabAuthTokenComponent() {
        super(GitlabInitConfig.class, GitLabAuthTokenLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        try {
            GitlabApiCache.register(getLogicConfig());
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.AUTH_FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.AUTH_FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
