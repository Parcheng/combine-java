package com.parch.combine.gitlab.components.auth;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.auth.GitLabAuthInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthLoginLoginConfig;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;

@Component(key = "auth.login", name = "登录验证组件", logicConfigClass = GitLabAuthLoginLoginConfig.class, initConfigClass = GitLabAuthInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitLabAuthLoginComponent extends AbstractComponent<GitLabAuthInitConfig, GitLabAuthLoginLoginConfig> {

    public GitLabAuthLoginComponent() {
        super(GitLabAuthInitConfig.class, GitLabAuthLoginLoginConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        try {
            GitLabApi api = GitlabApiCache.register(getLogicConfig());
            if (api == null) {
                return ComponentDataResult.success(false);
            }
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.AUTH_FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.AUTH_FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
