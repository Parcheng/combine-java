package com.parch.combine.gitlab.components.project;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.project.GitlabProjectDeleteLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

@Component(key = "project.delete", order = 400, name = "删除项目组件", logicConfigClass = GitlabProjectDeleteLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitlabProjectDeleteComponent extends AbstractGitlabComponent<GitlabProjectDeleteLogicConfig> {


    /**
     * 构造器
     */
    public GitlabProjectDeleteComponent() {
        super(GitlabProjectDeleteLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabProjectDeleteLogicConfig logicConfig = getLogicConfig();
        try {
            api.getProjectApi().deleteProject(logicConfig.idOrName());
            return ComponentDataResult.success(true);
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(false);
        }
    }
}
