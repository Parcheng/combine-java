package com.parch.combine.gitlab.components.merge.approval;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.approval.GitlabMergeApprovalStatusLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Issue;

import java.util.List;


@Component(key = "merge.approval.status", order = 300, name = "取消分支合并请求组件", logicConfigClass = GitlabMergeApprovalStatusLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc({
        "用于获取合并请求的批准状态，包括批准者信息、是否满足批准规则等求",
        "在合并请求的审批过程中，可以通过此方法获取详细的批准状态信息，以便更好地管理和控制合并请求的审批流程，确保代码质量和项目规范的遵循"
})
@ComponentResult(name = "取消结果信息")
public class GitlabMergeApprovalStatusComponent extends AbstractGitlabComponent<GitlabMergeApprovalStatusLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeApprovalStatusComponent() {
        super(GitlabMergeApprovalStatusLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeApprovalStatusLogicConfig logicConfig = this.getLogicConfig();
        try {
            List<Issue> list = api.getMergeRequestApi().getApprovalStatus(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
