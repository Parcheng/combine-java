package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeGetLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;


@Component(key = "merge.get", order = 300, name = "获取合并请求组件", logicConfigClass = GitlabMergeGetLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "合并请求信息")
public class GitlabMergeGetComponent extends AbstractGitlabComponent<GitlabMergeGetLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeGetComponent() {
        super(GitlabMergeGetLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeGetLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequest res = api.getMergeRequestApi().getMergeRequest(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
