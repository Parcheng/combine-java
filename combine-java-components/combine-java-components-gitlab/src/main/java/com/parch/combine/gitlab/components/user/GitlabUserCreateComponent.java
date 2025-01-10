package com.parch.combine.gitlab.components.user;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.gitlab.base.AbstractGitlabComponent;
import com.parch.combine.gitlab.base.GitlabInitConfig;
import com.parch.combine.gitlab.base.auth.GitLabAuthErrorEnum;
import com.parch.combine.gitlab.base.user.GitlabUserCreateLogicConfig;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

@Component(key = "user.create", order = 500, name = "创建用户组件", logicConfigClass = GitlabUserCreateLogicConfig.class, initConfigClass = GitlabInitConfig.class)
@ComponentResult(name = "用户信息")
public class GitlabUserCreateComponent extends AbstractGitlabComponent<GitlabUserCreateLogicConfig> {

    /**
     * 构造器
     */
    public GitlabUserCreateComponent() {
        super(GitlabUserCreateLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute(GitLabApi api) {
        GitlabUserCreateLogicConfig logicConfig = getLogicConfig();
        try {
            String password = logicConfig.password();
            Boolean sendResetPasswordEmail = logicConfig.sendResetPasswordEmail();
            if (sendResetPasswordEmail == null) {
                sendResetPasswordEmail = CheckEmptyUtil.isEmpty(password);
            }

            User user = new User().withEmail(logicConfig.email())
                    .withUsername(logicConfig.username())
                    .withName(logicConfig.name())
                    .withSkipConfirmation(logicConfig.skipConfirmation());
            User newUser = api.getUserApi().createUser(user, password, sendResetPasswordEmail);
            return ComponentDataResult.success(this.objToMap(newUser));
        } catch (GitLabApiException e) {
            PrintErrorHelper.print(GitLabAuthErrorEnum.FAIL, e);
            return ComponentDataResult.success(null);
        }
    }
}
