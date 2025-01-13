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
import com.parch.combine.gitlab.base.project.hook.GitlabProjectHookUpdateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ProjectHook;

@Component(key = "project.hook.update", order = 401, name = "项目Webhook修改组件", logicConfigClass = GitlabProjectHookUpdateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "HOOK信息")
public class GitlabProjectHookUpdateComponent extends AbstractGitlabComponent<GitlabProjectHookUpdateLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectHookUpdateComponent() {
        super(GitlabProjectHookUpdateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectHookUpdateLogicConfig logicConfig = getLogicConfig();
        try {
            ProjectHook hook = api.getProjectApi().getHook(logicConfig.idOrName(), logicConfig.hookId());
            if (hook == null) {
                return ComponentDataResult.success(null);
            }

            if (logicConfig.url() != null) {
                hook.setUrl(logicConfig.url());
            }
            if (logicConfig.doPushEvents() != null) {
                hook.setPushEvents(logicConfig.doPushEvents());
            }
            if (logicConfig.doIssuesEvents() != null) {
                hook.setIssuesEvents(logicConfig.doIssuesEvents());
            }
            if (logicConfig.doMergeRequestsEvents() != null) {
                hook.setMergeRequestsEvents(logicConfig.doMergeRequestsEvents());
            }
            hook = api.getProjectApi().modifyHook(hook);
            return ComponentDataResult.success(this.objToMap(hook));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
