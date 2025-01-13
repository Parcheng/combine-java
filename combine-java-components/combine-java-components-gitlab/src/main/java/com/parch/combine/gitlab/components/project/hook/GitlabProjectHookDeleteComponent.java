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
import com.parch.combine.gitlab.base.project.hook.GitlabProjectHookDeleteLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ProjectHook;

@Component(key = "project.hook.delete", order = 401, name = "项目Webhook删除组件", logicConfigClass = GitlabProjectHookDeleteLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitlabProjectHookDeleteComponent extends AbstractGitlabComponent<GitlabProjectHookDeleteLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectHookDeleteComponent() {
        super(GitlabProjectHookDeleteLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectHookDeleteLogicConfig logicConfig = getLogicConfig();
        try {

            api.getProjectApi().deleteHook(logicConfig.idOrName(), logicConfig.hookId());
            return ComponentDataResult.success(true);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(false);
        }
    }
}
