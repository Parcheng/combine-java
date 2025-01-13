package com.parch.combine.gitlab.components.project.hook;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.hook.GitlabProjectHookGetLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ProjectHook;

@Component(key = "project.hook.get", order = 401, name = "获取项目Webhook组件", logicConfigClass = GitlabProjectHookGetLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目Webhook信息")
public class GitlabProjectHookGetComponent extends AbstractGitlabComponent<GitlabProjectHookGetLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectHookGetComponent() {
        super(GitlabProjectHookGetLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectHookGetLogicConfig logicConfig = getLogicConfig();
        try {
            ProjectHook hook = api.getProjectApi().getHook(logicConfig.projectIdOrName(), logicConfig.hookId());
            return ComponentDataResult.success(this.objToMap(hook));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
