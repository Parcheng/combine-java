package com.parch.combine.gitlab.components.project;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.GitlabProjectUpdateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Project;
import org.gitlab4j.api.models.Visibility;

@Component(key = "project.update", order = 400, name = "更新项目组件", logicConfigClass = GitlabProjectUpdateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "项目信息")
public class GitlabProjectUpdateComponent extends AbstractGitlabComponent<GitlabProjectUpdateLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectUpdateComponent() {
        super(GitlabProjectUpdateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectUpdateLogicConfig logicConfig = getLogicConfig();
        try {
            Project project = api.getProjectApi().getProject(logicConfig.idOrName());
            if (project == null) {
                return ComponentDataResult.success(null);
            }

            if (logicConfig.name() != null) {
                project.withName(logicConfig.name());
            }
            if (logicConfig.desc() != null) {
                project.withDescription(logicConfig.desc());
            }
            if (logicConfig.namespaceId() != null) {
                project.withNamespaceId(logicConfig.namespaceId());
            }
            if (logicConfig.visibility() != null) {
                project.withVisibility(Visibility.forValue(logicConfig.visibility()));
            }
            project = api.getProjectApi().updateProject(project);
            return ComponentDataResult.success(this.objToMap(project));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
