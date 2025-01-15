package com.parch.combine.gitlab.components.project.member;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.member.GitlabProjectMemberSaveLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Member;
import java.util.Date;

@Component(key = "project.member.save", order = 402, name = "保存项目成员组件", logicConfigClass = GitlabProjectMemberSaveLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目成员信息")
public class GitlabProjectMemberSaveComponent extends AbstractGitlabComponent<GitlabProjectMemberSaveLogicConfig> {

    /**
     * 构造器
     */
    public GitlabProjectMemberSaveComponent() {
        super(GitlabProjectMemberSaveLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectMemberSaveLogicConfig logicConfig = getLogicConfig();
        try {
            Date expiresAt = null;
            Long expire = logicConfig.expire();
            if (expire != null && expire > 0) {
                expiresAt = new Date();
                expiresAt = new Date(expiresAt.getTime() + expire * 1000);
            }

            Member member = api.getProjectApi().getMember(logicConfig.projectIdOrName(), logicConfig.userId());
            if (member == null) {
                member = api.getProjectApi().addMember(logicConfig.projectIdOrName(),
                        logicConfig.userId(), AccessLevel.valueOf(logicConfig.accessLevel()), expiresAt);
            } else {
                member = api.getProjectApi().updateMember(logicConfig.projectIdOrName(),
                        logicConfig.userId(), AccessLevel.valueOf(logicConfig.accessLevel()), expiresAt);
            }

            return ComponentDataResult.success(this.objToMap(member));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
