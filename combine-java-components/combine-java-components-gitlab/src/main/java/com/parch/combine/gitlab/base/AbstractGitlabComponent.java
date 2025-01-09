package com.parch.combine.gitlab.base;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;

/**
 * Gitlab组件
 */
public abstract class AbstractGitlabComponent<R extends GitLabLoginConfig> extends AbstractComponent<GitlabInitConfig, R> {

    /**
     * 构造器
     */
    public AbstractGitlabComponent(Class<R> rClass) {
        super(GitlabInitConfig.class, rClass);
    }

    protected ComponentDataResult execute() {
        GitLabLoginConfig logicConfig = getLogicConfig();
        GitLabApi api = GitlabApiCache.get(logicConfig.key());
        if (api == null) {
            return ComponentDataResult.fail(GitLabAuthErrorEnum.NO_AUTH_CACHE);
        }

        return this.execute(api);
    }

    protected abstract ComponentDataResult execute(GitLabApi api);
}
