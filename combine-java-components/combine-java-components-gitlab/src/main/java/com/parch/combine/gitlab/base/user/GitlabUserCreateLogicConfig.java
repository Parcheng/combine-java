package com.parch.combine.gitlab.base.user;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gitlab.base.GitlabLogicConfig;

public interface GitlabUserCreateLogicConfig extends GitlabLogicConfig {

    @Field(key = "name", name = "用户名", type = FieldTypeEnum.TEXT, isRequired = true)
    String username();

    @Field(key = "password", name = "密码", type = FieldTypeEnum.TEXT)
    @FieldDesc("如果密码为空，则会发送重置密码邮件")
    String password();

    @Field(key = "name", name = "名称", type = FieldTypeEnum.TEXT, isRequired = true)
    String name();

    @Field(key = "email", name = "邮箱", type = FieldTypeEnum.TEXT, isRequired = true)
    String email();

    @Field(key = "skipConfirmation", name = "是否跳过邮件确认步骤", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean skipConfirmation();

    @Field(key = "sendResetPasswordEmail", name = "是否发送重置密码邮件", type = FieldTypeEnum.BOOLEAN)
    @FieldDesc("默认：如果设置了密码该字段为 false，未设置密码该字段为 true")
    Boolean sendResetPasswordEmail();

}
