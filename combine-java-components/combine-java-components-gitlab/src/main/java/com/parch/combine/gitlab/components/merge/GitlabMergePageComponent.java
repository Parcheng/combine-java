package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergePageLogicConfig;
import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.MergeRequest;
import org.gitlab4j.api.models.Project;

import java.util.List;


@Component(key = "merge.page", name = "分页获取合并请求列表组件", logicConfigClass = GitlabMergePageLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "取合并请求列表")
public class GitlabMergePageComponent extends AbstractGitlabComponent<GitlabMergePageLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergePageComponent() {
        super(GitlabMergePageLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabMergePageLogicConfig logicConfig = this.getLogicConfig();
        try {
            Constants.MergeRequestState stat = Constants.MergeRequestState.forValue(logicConfig.state());
            List<MergeRequest> list = api.getMergeRequestApi().getMergeRequests(logicConfig.projectIdOrName(), stat, logicConfig.page(), logicConfig.pageSize());
            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
