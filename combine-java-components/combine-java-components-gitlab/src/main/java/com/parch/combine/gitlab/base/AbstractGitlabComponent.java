package com.parch.combine.gitlab.base;

import com.fasterxml.jackson.databind.JsonNode;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.helper.GitlabApiCache;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;

import java.util.Map;

/**
 * Gitlab组件
 */
public abstract class AbstractGitlabComponent<R extends GitlabLogicConfig> extends AbstractComponent<GitlabInitConfig, R> {

    /**
     * 构造器
     */
    public AbstractGitlabComponent(Class<R> rClass) {
        super(GitlabInitConfig.class, rClass);
    }

    protected ComponentDataResult execute() {
        GitlabLogicConfig logicConfig = getLogicConfig();
        GitLabApi api = GitlabApiCache.get(logicConfig.key());
        if (api == null) {
            return ComponentDataResult.fail(GitLabAuthErrorEnum.NO_AUTH_CACHE);
        }

        try {
            return this.execute(api);
        } catch (Exception e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.fail(GitLabAuthErrorEnum.FAIL);
        }
    }

    protected abstract ComponentDataResult execute(GitLabApi api) throws GitLabApiException;

    protected Map<?, ?> objToMap(Object obj) {
        JsonNode dataTree = JsonUtil.objToTree(obj);
        return JsonUtil.treeToObj(dataTree, Map.class);
    }
}
