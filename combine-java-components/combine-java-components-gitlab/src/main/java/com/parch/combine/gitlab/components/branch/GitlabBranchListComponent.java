package com.parch.combine.gitlab.components.branch;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.branch.GitlabBranchListLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;

import java.util.List;

@Component(key = "branch.list", order = 200, name = "获取项目分支列表组件", logicConfigClass = GitlabBranchListLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "分支列表")
public class GitlabBranchListComponent extends AbstractGitlabComponent<GitlabBranchListLogicConfig> {

    /**
     * 构造器
     */
    public GitlabBranchListComponent() {
        super(GitlabBranchListLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            List<Branch> branches = api.getRepositoryApi().getBranches(getLogicConfig().projectIdOrName());
            return ComponentDataResult.success(this.objToMap(branches));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
