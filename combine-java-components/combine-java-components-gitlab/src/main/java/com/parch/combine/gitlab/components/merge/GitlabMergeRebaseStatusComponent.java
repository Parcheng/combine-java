package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeRebaseStatusLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;


@Component(key = "merge.rebase.status", order = 300, name = "获取基线状态", logicConfigClass = GitlabMergeRebaseStatusLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentDesc({
    "用于获取合并请求的重新基线状态，即源分支是否需要重新基线到目标分支",
    "在合并请求的处理过程中，如果发现源分支与目标分支之间存在较大的差异或冲突，可以使用此组件来判断源分支是否需要重新基线到目标分支，以确保合并操作的顺利进行和代码的兼容性"
})
@ComponentResult(name = "基线状态信息")
public class GitlabMergeRebaseStatusComponent extends AbstractGitlabComponent<GitlabMergeRebaseStatusLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeRebaseStatusComponent() {
        super(GitlabMergeRebaseStatusLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeRebaseStatusLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequest res = api.getMergeRequestApi().getRebaseStatus(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
