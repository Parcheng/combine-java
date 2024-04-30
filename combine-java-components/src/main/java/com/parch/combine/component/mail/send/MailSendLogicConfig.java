package com.parch.combine.component.mail.send;

import com.parch.combine.component.mail.MailLogicConfig;
import com.parch.combine.component.mail.helper.MailContentTypeEnum;

import java.util.List;

/**
 * 逻辑配置类
 */
public class MailSendLogicConfig extends MailLogicConfig {

    private List<String> to;

    private String subject;

    private List<MailBody> body;

    public static class MailBody {
        MailContentTypeEnum type;
        String content;
        String path;
        String fileName;
        String fileType;

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
