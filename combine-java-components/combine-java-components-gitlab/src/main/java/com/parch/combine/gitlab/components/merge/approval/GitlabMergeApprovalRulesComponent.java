package com.parch.combine.gitlab.components.merge.approval;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.approval.GitlabMergeApprovalRulesLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.ApprovalRule;

import java.util.List;


@Component(key = "merge.approval.rules", order = 300, name = "获取批准规则组件", logicConfigClass = GitlabMergeApprovalRulesLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc({
        "用于获取合并请求的批准规则信息。批准规则定义了合并请求必须满足的条件才能被合并，例如需要多少个批准者、哪些用户或组可以批准等",
        "当需要了解或管理合并请求的批准流程时，可以使用此方法获取当前的批准规则配置，以便进行查看、修改或优化"
})
@ComponentResult(name = "合并请求的批准规则信息")
public class GitlabMergeApprovalRulesComponent extends AbstractGitlabComponent<GitlabMergeApprovalRulesLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeApprovalRulesComponent() {
        super(GitlabMergeApprovalRulesLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeApprovalRulesLogicConfig logicConfig = this.getLogicConfig();
        try {
            List<ApprovalRule> list = api.getMergeRequestApi().getApprovalRules(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
