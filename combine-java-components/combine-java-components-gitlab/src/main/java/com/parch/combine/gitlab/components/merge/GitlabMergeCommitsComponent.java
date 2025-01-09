package com.parch.combine.gitlab.components.merge;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.merge.GitlabMergeCommitsLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Commit;

import java.util.List;

@Component(key = "merge.commits", name = "分页获取合并的提交列表组件", logicConfigClass = GitlabMergeCommitsLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "提交记录列表")
public class GitlabMergeCommitsComponent extends AbstractGitlabComponent<GitlabMergeCommitsLogicConfig> {

    /**
     * 构造器
     */
    public GitlabMergeCommitsComponent() {
        super(GitlabMergeCommitsLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            GitlabMergeCommitsLogicConfig logicConfig = getLogicConfig();
            List<Commit> list = api.getMergeRequestApi().getCommits(logicConfig.projectIdOrName(), logicConfig.mergedRequestId(), logicConfig.page(), logicConfig.pageSize());
            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
