package com.parch.combine.gitlab.components.project.member;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.member.GitlabProjectMemberDeleteLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

@Component(key = "project.member.delete", order = 402, name = "删除项目成员组件", logicConfigClass = GitlabProjectMemberDeleteLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "删除项目成员")
public class GitlabProjectMemberDeleteComponent extends AbstractGitlabComponent<GitlabProjectMemberDeleteLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectMemberDeleteComponent() {
        super(GitlabProjectMemberDeleteLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectMemberDeleteLogicConfig logicConfig = getLogicConfig();
        try {
            api.getProjectApi().removeMember(logicConfig.projectIdOrName(), logicConfig.userId());
            return ComponentDataResult.success(true);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(false);
        }
    }
}
