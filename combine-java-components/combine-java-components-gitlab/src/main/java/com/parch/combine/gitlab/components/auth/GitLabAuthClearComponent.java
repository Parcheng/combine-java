package com.parch.combine.gitlab.components.auth;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.GitlabLogicConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;

@Component(key = "auth.clear", order = 100, name = "鉴权缓存清理组件", logicConfigClass = GitlabLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "是否成功")
public class GitLabAuthClearComponent extends AbstractComponent<GitlabInitConfig, GitlabLogicConfig> {

    public GitLabAuthClearComponent() {
        super(GitlabInitConfig.class, GitlabLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        try {
            GitLabApi api = GitlabApiCache.clear(getLogicConfig().key());
            if (api == null) {
                ComponentDataResult.success(false);
            }
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.CLEAR_FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.CLEAR_FAIL);
        }

        return ComponentDataResult.success(true);
    }
}
