package com.parch.combine.gitlab.base.auth.login;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;

@Component(key = "auth.login", name = "登录验证组件", logicConfigClass = GitLabAuthLoginLoginConfig.class, initConfigClass = GitLabAuthLoginInitConfig.class)
@ComponentResult(name = "获取的邮件列表")
public class GitLabAuthLoginComponent extends AbstractComponent<GitLabAuthLoginInitConfig, GitLabAuthLoginLoginConfig> {

    public GitLabAuthLoginComponent() {
        super(GitLabAuthLoginInitConfig.class, GitLabAuthLoginLoginConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        try {
            GitLabApi api = GitlabApiCache.register(getLogicConfig());
            if (api == null) {
                return ComponentDataResult.fail(GitLabAuthErrorEnum.FAIL);
            }
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
