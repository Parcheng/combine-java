package com.parch.combine.gitlab.components.project.hook;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.hook.GitlabProjectHookListLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ProjectHook;

import java.util.List;

@Component(key = "project.hook.list", order = 401, name = "获取项目Webhook列表组件", logicConfigClass = GitlabProjectHookListLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目Webhook信息列表")
public class GitlabProjectHookListComponent extends AbstractGitlabComponent<GitlabProjectHookListLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectHookListComponent() {
        super(GitlabProjectHookListLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectHookListLogicConfig logicConfig = getLogicConfig();
        try {
            List<ProjectHook> hooks = api.getProjectApi().getHooks(logicConfig.projectIdOrName());
            return ComponentDataResult.success(this.objToMap(hooks));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
