package com.parch.combine.gitlab.base.branch;

import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;

public abstract class AbstractGitlabBatchComponent<R extends GitlabBranchLogicConfig> extends AbstractGitlabComponent<R> {

    /**
     * 构造器
     */
    public AbstractGitlabBatchComponent(Class<R> rClass) {
        super(rClass);
    }

    protected ComponentDataResult execute(GitLabApi api) {
        String projectName = getLogicConfig().projectName();
        Project project = null;
        try {
            project = api.getProjectApi().getProject(projectName);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
        }

        if (project == null) {
            return ComponentDataResult.fail(projectName + "项目不存在");
        }

        return execute(api, project);
    }

    protected abstract ComponentDataResult execute(GitLabApi api, Project project);
}
