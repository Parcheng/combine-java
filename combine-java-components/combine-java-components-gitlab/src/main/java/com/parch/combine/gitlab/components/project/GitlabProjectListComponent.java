package com.parch.combine.gitlab.components.project;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.GitlabProjectListLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import java.util.List;

@Component(key = "project.list", order = 400, name = "获取项目列表组件", logicConfigClass = GitlabProjectListLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目列表")
public class GitlabProjectListComponent extends AbstractGitlabComponent<GitlabProjectListLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectListComponent() {
        super(GitlabProjectListLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            List<Project> list = api.getProjectApi().getProjects();
            if (list == null) {
                return ComponentDataResult.success(null);
            }

            return ComponentDataResult.success(this.objToMap(list));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
