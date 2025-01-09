package com.parch.combine.gitlab.components.project;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.GitlabProjectGetLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.ProjectApi;
import org.gitlab4j.api.models.Project;

/**
 * Gitlab Project处理组件
 */
@Component(key = "project.list", name = "获取项目列表组件", logicConfigClass = GitlabProjectGetLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目信息")
public class GitlabProjectGetComponent extends AbstractGitlabComponent<GitlabProjectGetLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectGetComponent() {
        super(GitlabProjectGetLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            Project project = api.getProjectApi().getProject(getLogicConfig().name());
            if (project == null) {
                return ComponentDataResult.success(null);
            }

            return ComponentDataResult.success(this.objToMap(project));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(e.getMessage(), GitLabAuthErrorEnum.FAIL.getShowMsg());
        }
    }
}
