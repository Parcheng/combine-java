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
import com.parch.combine.gitlab.base.user.GitlabUserListLogicConfig;
import com.parch.combine.gitlab.base.user.GitlabUserUpdateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

import java.util.List;

@Component(key = "user.update", order = 500, name = "修改用户组件", logicConfigClass = GitlabUserUpdateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "用户信息")
public class GitlabUserUpdateComponent extends AbstractGitlabComponent<GitlabUserUpdateLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserUpdateComponent() {
        super(GitlabUserUpdateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabUserUpdateLogicConfig logicConfig = getLogicConfig();
        try {
            User res = null;
            Object idOrName = getLogicConfig().idOrName();
            if (idOrName instanceof String) {
                res = api.getUserApi().getUser((String) idOrName);
            } else if (DataTypeIsUtil.isInteger(idOrName)) {
                res = api.getUserApi().getUser(DataParseUtil.getInteger(idOrName, null));
            } else {
                return ComponentDataResult.fail("用户ID或名称只能是数字和字符串类型", "数据异常");
            }

            if (res == null) {
                return ComponentDataResult.success(null);
            }

            User user = api.getUserApi().updateUser(res, getLogicConfig().password());
            return ComponentDataResult.success(this.objToMap(user));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
