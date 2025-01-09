package com.parch.combine.gitlab.base.project;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import org.gitlab4j.api.GitLabApi;

/**
 * Gitlab Project处理组件
 */
public class GitlabProjectsComponent extends AbstractGitlabComponent<GitlabProjectsLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectsComponent() {
        super(GitlabProjectsLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        return null;
    }
}
