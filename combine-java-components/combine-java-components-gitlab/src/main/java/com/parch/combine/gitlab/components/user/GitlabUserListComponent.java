package com.parch.combine.gitlab.components.user;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.user.GitlabUserListLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

import java.util.List;

@Component(key = "user.list", order = 500, name = "获取用户列表组件", logicConfigClass = GitlabUserListLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "用户列表")
public class GitlabUserListComponent extends AbstractGitlabComponent<GitlabUserListLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserListComponent() {
        super(GitlabUserListLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            List<User> list = api.getUserApi().getUsers();
            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
