package com.parch.combine.gitlab.components.project.member;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.member.GitlabProjectMemberListLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Member;

import java.util.List;

@Component(key = "project.member.list", order = 402, name = "获取项目成员列表组件", logicConfigClass = GitlabProjectMemberListLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目成员列表")
public class GitlabProjectMemberListComponent extends AbstractGitlabComponent<GitlabProjectMemberListLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectMemberListComponent() {
        super(GitlabProjectMemberListLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectMemberListLogicConfig logicConfig = getLogicConfig();
        try {
            List<Member> members = api.getProjectApi().getAllMembers(logicConfig.projectIdOrName());
            return ComponentDataResult.success(this.objToMap(members));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
