package com.parch.combine.gitlab.components.project.hook;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.hook.GitlabProjectHookAddLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ProjectHook;

@Component(key = "project.hook.add", order = 401, name = "项目Webhook添加组件", logicConfigClass = GitlabProjectHookAddLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc("在项目中设置Webhook，以便在特定事件发生时（如代码推送、合并请求等）自动触发外部服务或脚本")
@ComponentResult(name = "HOOK信息")
public class GitlabProjectHookAddComponent extends AbstractGitlabComponent<GitlabProjectHookAddLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectHookAddComponent() {
        super(GitlabProjectHookAddLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectHookAddLogicConfig logicConfig = getLogicConfig();
        try {

            ProjectHook hook = api.getProjectApi().addHook(logicConfig.projectIdOrName(), logicConfig.url(),
                    logicConfig.doPushEvents(), logicConfig.doIssuesEvents(), logicConfig.doMergeRequestsEvents());
            return ComponentDataResult.success(this.objToMap(hook));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
