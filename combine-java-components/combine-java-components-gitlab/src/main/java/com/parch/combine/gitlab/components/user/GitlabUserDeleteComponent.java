package com.parch.combine.gitlab.components.user;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.user.GitlabUserDeleteLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

@Component(key = "user.delete", order = 500, name = "删除用户组件", logicConfigClass = GitlabUserDeleteLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitlabUserDeleteComponent extends AbstractGitlabComponent<GitlabUserDeleteLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserDeleteComponent() {
        super(GitlabUserDeleteLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            api.getUserApi().deleteUser(getLogicConfig().idOrName());
            return ComponentDataResult.success(true);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(false);
        }
    }
}
