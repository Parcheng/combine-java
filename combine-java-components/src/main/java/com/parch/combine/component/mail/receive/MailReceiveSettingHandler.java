package com.parch.combine.component.mail.receive;

import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;


/**
 * 设置处理器
 */
public class MailReceiveSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("receive", "邮件收取组件", true, MailReceiveComponent.class);
//        builder.addDesc("邮件收取");

        builder.addProperty(PropertyTypeEnum.INIT, "host", FieldTypeEnum.TEXT, "服务主机",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "port", FieldTypeEnum.NUMBER, "端口",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "mail", FieldTypeEnum.TEXT, "发件邮箱",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "authCode", FieldTypeEnum.SELECT, "授权码",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "timeout", FieldTypeEnum.NUMBER, "超时时间",  false, false, "10000");
        builder.addProperty(PropertyTypeEnum.INIT, "debug", FieldTypeEnum.BOOLEAN, "是否打印日志",  false, false, "false");


        builder.setResult("获取的邮件列表", false);
        return builder.get();
    }
}
