package com.parch.combine.components.mail.send;

import com.parch.combine.components.mail.MailLogicConfig;
import com.parch.combine.components.mail.helper.MailContentTypeEnum;
import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import java.util.List;

public interface MailSendLogicConfig extends MailLogicConfig {

    @Field(key = "to", name = "收件人", type = FieldTypeEnum.TEXT, isRequired = true, isArray = true)
    @FieldEg(eg = "[\"a@qq.com\",\"b@qq.com\"]", desc = "邮件发送给 a@qq.com 和 b@qq.com")
    List<String> to();

    @Field(key = "subject", name = "主题", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldEg(eg = "办公用品审批", desc = "邮件的标题为“办公用品审批”")
    String subject();

    @Field(key = "body", name = "内容配置", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(MailBody.class)
    List<MailBody> body();

    interface MailBody {

        @Field(key = "type", name = "内容类型", type = FieldTypeEnum.SELECT)
        @FieldSelect(enumClass = MailContentTypeEnum.class)
        @FieldDesc({"如果类型为文本，内容信息必填", "如果类型为图片，路径和内容信息至少要填一项", "如果类型为文件，路径和内容信息至少要填一项"})
        @FieldEg(eg = "TEXT", desc = "内容类型为文本类型")
        @FieldEg(eg = "IMAGE", desc = "图片类型，可以有文件名+文件类型")
        @FieldEg(eg = "FILE", desc = "内容类型为文件，一定要有文件名")
        String type();

        @Field(key = "content", name = "内容信息", type = FieldTypeEnum.ANY)
        @FieldEg(eg = "申请办公用品", desc = "邮件内容/邮件附件内容为“申请办公用品”")
        Object content();

        @Field(key = "path", name = "路径", type = FieldTypeEnum.TEXT)
        String path();

        @Field(key = "fileName", name = "文件名称", type = FieldTypeEnum.TEXT)
        @FieldEg(eg = "申请.txt", desc = "文件名称为“申请.txt”")
        String fileName();

        @Field(key = "fileType", name = "文件类型", type = FieldTypeEnum.TEXT)
        @FieldEg(eg = "jpg", desc = "jpg格式的突破类型")
        String fileType();
    }
}
