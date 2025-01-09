package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeCreateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.MergeRequestParams;

import java.util.List;

@Component(key = "merge.create", order = 300, name = "创建分支合并请求组件", logicConfigClass = GitlabMergeCreateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "合并结果信息")
public class GitlabMergeCreateComponent extends AbstractGitlabComponent<GitlabMergeCreateLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeCreateComponent() {
        super(GitlabMergeCreateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergeCreateLogicConfig logicConfig = this.getLogicConfig();
        try {
            MergeRequestParams mr = new MergeRequestParams()
                    .withSourceBranch(logicConfig.source())
                    .withTargetBranch(logicConfig.target())
                    .withTitle(logicConfig.title())
                    .withDescription(logicConfig.desc())
                    .withAssigneeIds(List.of(logicConfig.assigneeIds()));

            MergeRequest res = api.getMergeRequestApi().createMergeRequest(logicConfig.projectIdOrName(), mr);
            if (res != null && logicConfig.hasMerged()) {
                res = api.getMergeRequestApi().acceptMergeRequest(logicConfig.projectIdOrName(), res.getId());
            }

            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
