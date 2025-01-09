package com.parch.combine.gitlab.base.project;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitLabLoginConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.ProjectApi;

/**
 * Gitlab Project处理组件
 */
public abstract class AbstractGitlabProjectComponent<R extends GitLabLoginConfig> extends AbstractGitlabComponent<R> {

    protected GitLabApi overallApi;

    /**
     * 构造器
     */
    public AbstractGitlabProjectComponent(Class<R> rClass) {
        super(rClass);
    }

    protected ComponentDataResult execute(GitLabApi api) {
        this.overallApi = api;
        return this.execute(api.getProjectApi());
    }

    protected abstract ComponentDataResult execute(ProjectApi api);
}
