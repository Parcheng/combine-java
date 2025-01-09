package com.parch.combine.gitlab.components.branch;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.branch.GitlabBranchAddLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.Project;

@Component(key = "branch.delete", name = "删除分支组件", logicConfigClass = GitlabBranchAddLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否删除成功")
public class GitlabBranchDeleteComponent extends AbstractGitlabComponent<GitlabBranchAddLogicConfig> {

    /**
     * 构造器
     */
    public GitlabBranchDeleteComponent() {
        super(GitlabBranchAddLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabBranchAddLogicConfig logicConfig = this.getLogicConfig();
        try {
            api.getRepositoryApi().deleteBranch(logicConfig.projectIdOrName(), logicConfig.name());
            return ComponentDataResult.success(true);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(false);
        }
    }
}
