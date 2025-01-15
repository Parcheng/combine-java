package com.parch.combine.gitlab.components.auth;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.GitlabLogicConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;

@Component(key = "auth.check", order = 100, name = "鉴权缓存检查组件", logicConfigClass = GitlabLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否存在")
public class GitLabAuthCheckComponent extends AbstractComponent<GitlabInitConfig, GitlabLogicConfig> {

    public GitLabAuthCheckComponent() {
        super(GitlabInitConfig.class, GitlabLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        try {
            GitLabApi api = GitlabApiCache.get(getLogicConfig().key());
            return ComponentDataResult.success(api != null);
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.CLEAR_FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.CLEAR_FAIL);
        }
    }
}
