package com.parch.combine.gitlab.components.user;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.user.GitlabUserCurrentLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

@Component(key = "user.current", order = 500, name = "获取当前用户组件", logicConfigClass = GitlabUserCurrentLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "用户信息")
public class GitlabUserCurrentComponent extends AbstractGitlabComponent<GitlabUserCurrentLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserCurrentComponent() {
        super(GitlabUserCurrentLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            User user = api.getUserApi().getCurrentUser();
            return ComponentDataResult.success(this.objToMap(user));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
