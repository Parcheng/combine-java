package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeDoLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;

@Component(key = "merge.do", order = 300, name = "执行合并组件", logicConfigClass = GitlabMergeDoLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "合并结果信息")
public class GitlabMergeDoComponent extends AbstractGitlabComponent<GitlabMergeDoLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeDoComponent() {
        super(GitlabMergeDoLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeDoLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequest res = api.getMergeRequestApi().acceptMergeRequest(logicConfig.projectIdOrName(), logicConfig.mergedRequestId());
            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
