package com.parch.combine.gitlab.components.project;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.GitlabProjectCreateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;

@Component(key = "project.create", order = 400, name = "创建项目组件", logicConfigClass = GitlabProjectCreateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目信息")
public class GitlabProjectCreateComponent extends AbstractGitlabComponent<GitlabProjectCreateLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectCreateComponent() {
        super(GitlabProjectCreateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectCreateLogicConfig logicConfig = getLogicConfig();
        try {
            String name = logicConfig.name();
            if (logicConfig.ifNotExist()) {
                Project project = api.getProjectApi().getProject(name);
                if (project != null) {
                    return ComponentDataResult.success(this.objToMap(project));
                }
            }

            Project newProject = new Project()
                    .withName(name)
                    .withDescription(logicConfig.desc())
                    .withNamespaceId(logicConfig.namespaceId())
                    .withVisibility(Visibility.forValue(logicConfig.visibility()));
            Project project = api.getProjectApi().createProject(newProject);
            return ComponentDataResult.success(this.objToMap(project));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
