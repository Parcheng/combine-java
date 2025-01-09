package com.parch.combine.gitlab.components.branch;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.branch.AbstractGitlabBatchComponent;
import com.parch.combine.gitlab.base.branch.GitlabBranchAddLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;

@Component(key = "branch.create", name = "创建项目分支组件", logicConfigClass = GitlabBranchAddLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "创建的分支信息")
public class GitlabBranchAddComponent extends AbstractGitlabBatchComponent<GitlabBranchAddLogicConfig> {

    /**
     * 构造器
     */
    public GitlabBranchAddComponent() {
        super(GitlabBranchAddLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api, Project project) {
        GitlabBranchAddLogicConfig logicConfig = this.getLogicConfig();
        try {
            Branch branch = api.getRepositoryApi().createBranch(project.getId(), logicConfig.name(), logicConfig.source());
            return ComponentDataResult.success(this.objToMap(branch));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
