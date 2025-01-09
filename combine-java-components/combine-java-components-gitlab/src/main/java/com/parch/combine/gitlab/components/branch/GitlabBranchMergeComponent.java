package com.parch.combine.gitlab.components.branch;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.branch.AbstractGitlabBatchComponent;
import com.parch.combine.gitlab.base.branch.GitlabBranchMergeLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.MergeRequestParams;
import org.gitlab4j.api.models.Project;

import java.util.List;

@Component(key = "branch.merge", name = "提交分支合并请求组件", logicConfigClass = GitlabBranchMergeLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "合并结果信息")
public class GitlabBranchMergeComponent extends AbstractGitlabBatchComponent<GitlabBranchMergeLogicConfig> {

    /**
     * 构造器
     */
    public GitlabBranchMergeComponent() {
        super(GitlabBranchMergeLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api, Project project) {
        GitlabBranchMergeLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequestParams mr = new MergeRequestParams()
                    .withSourceBranch(logicConfig.source())
                    .withTargetBranch(logicConfig.target())
                    .withTitle(logicConfig.title())
                    .withDescription(logicConfig.desc())
                    .withAssigneeIds(List.of(logicConfig.assigneeIds()));

            MergeRequest res = api.getMergeRequestApi().createMergeRequest(project.getId(), mr);
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
