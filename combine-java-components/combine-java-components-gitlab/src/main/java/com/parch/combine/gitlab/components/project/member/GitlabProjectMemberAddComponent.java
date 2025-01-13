package com.parch.combine.gitlab.components.project.member;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.member.GitlabProjectMemberAddLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Member;
import java.util.Date;

@Component(key = "project.member.add", order = 402, name = "添加项目成员组件", logicConfigClass = GitlabProjectMemberAddLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目成员信息")
public class GitlabProjectMemberAddComponent extends AbstractGitlabComponent<GitlabProjectMemberAddLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectMemberAddComponent() {
        super(GitlabProjectMemberAddLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectMemberAddLogicConfig logicConfig = getLogicConfig();
        try {
            Date expiresAt = null;
            Long expire = logicConfig.expire();
            if (expire != null && expire > 0) {
                expiresAt = new Date();
                expiresAt = new Date(expiresAt.getTime() + expire * 1000);
            }

            Member member = api.getProjectApi().addMember(logicConfig.projectIdOrName(),
                    logicConfig.userId(), AccessLevel.valueOf(logicConfig.accessLevel()), expiresAt);
            return ComponentDataResult.success(this.objToMap(member));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
