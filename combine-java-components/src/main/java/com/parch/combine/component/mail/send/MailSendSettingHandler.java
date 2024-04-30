package com.parch.combine.component.mail.send;

import com.parch.combine.component.mail.helper.MailContentTypeEnum;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class MailSendSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("send", "邮件发送组件", true, MailSendComponent.class);
        builder.addDesc("邮件发送");

        builder.addProperty(PropertyTypeEnum.INIT, "host", FieldTypeEnum.TEXT, "服务主机",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "port", FieldTypeEnum.NUMBER, "端口",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "mail", FieldTypeEnum.TEXT, "发件邮箱",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "authCode", FieldTypeEnum.SELECT, "授权码",  true, false);
        builder.addProperty(PropertyTypeEnum.INIT, "timeout", FieldTypeEnum.NUMBER, "超时时间",  false, false, "10000");
        builder.addProperty(PropertyTypeEnum.INIT, "debug", FieldTypeEnum.BOOLEAN, "是否打印日志",  false, false, "false");


        builder.addProperty(PropertyTypeEnum.LOGIC, "to", FieldTypeEnum.TEXT, "收件人",  true, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "subject", FieldTypeEnum.TEXT, "主题",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "body", FieldTypeEnum.TEXT, "内容配置",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "body.type", FieldTypeEnum.SELECT, "内容类型",  true, false);
        builder.setPropertyOption(PropertyTypeEnum.LOGIC, "body.type", Arrays.asList(MailContentTypeEnum.values()));
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "body.type", "如果类型为文本，内容信息必填");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "body.type", "如果类型为图片，路径和内容信息至少要填一项");
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "body.type", "如果类型为文件，路径和内容信息至少要填一项");
        builder.addProperty(PropertyTypeEnum.LOGIC, "body.content", FieldTypeEnum.TEXT, "内容信息",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "body.path", FieldTypeEnum.TEXT, "路径",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "body.fileName", FieldTypeEnum.TEXT, "文件名称",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "body.fileType", FieldTypeEnum.TEXT, "文件类型",  false, false);


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "to", "[\"a@qq.com\",\"b@qq.com\"]", "邮件发送给 a@qq.com 和 b@qq.com");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "subject", "办公用品审批", "邮件的标题为“办公用品审批”");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.type", "TEXT", "内容类型为文本类型");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.type", "IMAGE", "图片类型，可以有文件名+文件类型");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.type", "FILE", "内容类型为文件，一定要有文件名");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.fileName", "申请.txt", "文件名称为“申请.txt”");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.fileType", "jpg", "jpg格式的突破类型");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "body.content", "申请办公用品", "邮件内容/邮件附件内容为“申请办公用品”");


        builder.setResult("邮件发送成功：true", false);
        return builder.get();
    }
}
