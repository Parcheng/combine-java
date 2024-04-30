package com.parch.combine.components.mail.send;

import com.parch.combine.components.mail.MailLogicConfig;
import com.parch.combine.components.mail.helper.MailContentTypeEnum;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import java.util.List;

/**
 * 逻辑配置类
 */
public class MailSendLogicConfig extends MailLogicConfig {

    @ComponentField(key = "to", name = "收件人", type = FieldTypeEnum.TEXT, isRequired = true, isArray = true)
    @ComponentFieldEg(eg = "[\"a@qq.com\",\"b@qq.com\"]", desc = "邮件发送给 a@qq.com 和 b@qq.com")
    private List<String> to;

    @ComponentField(key = "subject", name = "主题", type = FieldTypeEnum.TEXT, isRequired = true)
    @ComponentFieldEg(eg = "办公用品审批", desc = "邮件的标题为“办公用品审批”")
    private String subject;

    @ComponentField(key = "body", name = "内容配置", type = FieldTypeEnum.OBJECT, isArray = true)
    @ComponentFieldObject(type = MailBody.class)
    private List<MailBody> body;

    public static class MailBody {

        @ComponentField(key = "type", name = "内容类型", type = FieldTypeEnum.SELECT)
        @ComponentFieldSelect(enumClass = MailContentTypeEnum.class)
        @ComponentFieldDesc({"如果类型为文本，内容信息必填", "如果类型为图片，路径和内容信息至少要填一项", "如果类型为文件，路径和内容信息至少要填一项"})
        @ComponentFieldEg(eg = "TEXT", desc = "内容类型为文本类型")
        @ComponentFieldEg(eg = "IMAGE", desc = "图片类型，可以有文件名+文件类型")
        @ComponentFieldEg(eg = "FILE", desc = "内容类型为文件，一定要有文件名")
        private MailContentTypeEnum type;

        @ComponentField(key = "content", name = "内容信息", type = FieldTypeEnum.TEXT)
        @ComponentFieldEg(eg = "申请办公用品", desc = "邮件内容/邮件附件内容为“申请办公用品”")
        private String content;

        @ComponentField(key = "path", name = "路径", type = FieldTypeEnum.TEXT)
        private String path;

        @ComponentField(key = "fileName", name = "文件名称", type = FieldTypeEnum.TEXT)
        @ComponentFieldEg(eg = "申请.txt", desc = "文件名称为“申请.txt”")
        private String fileName;

        @ComponentField(key = "fileType", name = "文件类型", type = FieldTypeEnum.TEXT)
        @ComponentFieldEg(eg = "jpg", desc = "jpg格式的突破类型")
        private String fileType;

        public MailContentTypeEnum getType() {
            return type;
        }

        public void setType(String type) {
            this.type = MailContentTypeEnum.get(type);
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getFileType() {
            return fileType;
        }

        public void setFileType(String fileType) {
            this.fileType = fileType;
        }
    }

    public List<String> getTo() {
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<MailBody> getBody() {
        return body;
    }

    public void setBody(List<MailBody> body) {
        this.body = body;
    }
}
