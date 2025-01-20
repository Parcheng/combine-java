package com.parch.combine.gitlab.components.branch;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.branch.GitlabBranchAddLogicConfig;
import com.parch.combine.gitlab.base.branch.GitlabBranchGetLogicConfig;
import com.parch.combine.gitlab.base.branch.GitlabBranchLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;

@Component(key = "branch.get", order = 200, name = "获取项目分支信息组件", logicConfigClass = GitlabBranchGetLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目分支信息")
public class GitlabBranchGetComponent extends AbstractGitlabComponent<GitlabBranchGetLogicConfig> {

    /**
     * 构造器
     */
    public GitlabBranchGetComponent() {
        super(GitlabBranchGetLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabBranchGetLogicConfig logicConfig = this.getLogicConfig();
        try {
            Branch branch = api.getRepositoryApi().getBranch(logicConfig.projectIdOrName(), logicConfig.name());
            return ComponentDataResult.success(this.objToMap(branch));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
