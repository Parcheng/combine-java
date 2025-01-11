package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeApprovalsLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;


@Component(key = "merge.approvals", order = 300, name = "获取批准信息组件", logicConfigClass = GitlabMergeApprovalsLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc({
        "用于获取合并请求的批准信息，包括哪些用户已经批准了该合并请求",
        "在合并请求的审批过程中，你可以通过此方法实时跟踪哪些用户已经对合并请求进行了批准，从而了解合并请求的当前审批状态，确保合并操作符合项目的规定和要求"
})
@ComponentResult(name = "合并请求的批准信息")
public class GitlabMergeApprovalsComponent extends AbstractGitlabComponent<GitlabMergeApprovalsLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeApprovalsComponent() {
        super(GitlabMergeApprovalsLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeApprovalsLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequest res = api.getMergeRequestApi().getApprovals(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
