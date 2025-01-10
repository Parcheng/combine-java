package com.parch.combine.gitlab.components.user;

import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.user.GitlabUserGetLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

@Component(key = "user.get", order = 500, name = "获取用户信息组件", logicConfigClass = GitlabUserGetLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "用户信息")
public class GitlabUserGetComponent extends AbstractGitlabComponent<GitlabUserGetLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserGetComponent() {
        super(GitlabUserGetLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        try {
            Object idOrName = getLogicConfig().idOrName();
            User res = null;
            if (idOrName instanceof String) {
                res = api.getUserApi().getUser((String) idOrName);
            } else if (DataTypeIsUtil.isInteger(idOrName)) {
                res = api.getUserApi().getUser(DataParseUtil.getInteger(idOrName, null));
            } else {
                return ComponentDataResult.fail("用户ID或名称只能是数字和字符串类型", "数据异常");
            }

            return ComponentDataResult.success(this.objToMap(res));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
