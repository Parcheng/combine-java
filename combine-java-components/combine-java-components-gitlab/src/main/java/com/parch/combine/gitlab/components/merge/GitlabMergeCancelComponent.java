package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeCancelLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;


@Component(key = "merge.cancel", order = 300, name = "取消分支合并请求组件", logicConfigClass = GitlabMergeCancelLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "取消结果信息")
public class GitlabMergeCancelComponent extends AbstractGitlabComponent<GitlabMergeCancelLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeCancelComponent() {
        super(GitlabMergeCancelLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeCancelLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequest res = api.getMergeRequestApi().cancelMergeRequest(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
