package com.parch.combine.components.mail;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface MailInitConfig extends IInitConfig {

    @Field(key = "host", name = "服务主机", type = FieldTypeEnum.TEXT, isRequired = true)
    String host();

    @Field(key = "port", name = "端口", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer port();

    @Field(key = "mail", name = "发件方邮箱", type = FieldTypeEnum.TEXT, isRequired = true)
    String mail();

    @Field(key = "authCode", name = "授权码", type = FieldTypeEnum.TEXT, isRequired = true)
    String authCode();

    @Field(key = "timeout", name = "超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "10000")
    Long timeout();

    @Field(key = "debug", name = "是否打印日志", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean debug();
}
