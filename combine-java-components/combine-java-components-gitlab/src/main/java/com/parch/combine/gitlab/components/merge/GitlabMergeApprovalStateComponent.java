package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeApprovalStateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ApprovalState;

@Component(key = "merge.approval.state", order = 300, name = "获取批准状态组件", logicConfigClass = GitlabMergeApprovalStateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc({
        "用于获取合并请求的批准状态，包括是否满足了批准规则的要求",
        "在合并请求的审批流程中，可以使用此方法来判断合并请求是否已经满足了所有批准条件，例如是否达到了最小批准人数，从而决定是否可以进行合并操作"
})
@ComponentResult(name = "合并请求的批准状态信息")
public class GitlabMergeApprovalStateComponent extends AbstractGitlabComponent<GitlabMergeApprovalStateLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeApprovalStateComponent() {
        super(GitlabMergeApprovalStateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeApprovalStateLogicConfig logicConfig = this.getLogicConfig();
        try {
            ApprovalState res = api.getMergeRequestApi().getApprovalState(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
