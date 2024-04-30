package com.parch.combine.component.mail.helper;

import com.parch.combine.common.util.FileNameUtil;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import java.io.File;

public class MimeMessageHelper {

    /**
     * 添加附件
     *
     * @param message 消息
     * @param data 附件字节数据
     * @param fileName 文件名
     * @throws Exception 异常
     */
    public static void addAttachment(BodyPart message, byte[] data, String fileName) throws Exception {
        DataSource source = new ByteArrayDataSource(data, FileNameUtil.getPostfix(fileName));
        DataHandler handler = new DataHandler(source);

        message.setDataHandler(handler);
        message.setFileName(fileName);
    }

    /**
     * 添加图片
     *
     * @param message 消息
     * @param data 二进制数据
     * @param fileType 文件类型
     * @throws Exception 异常
     */
    public static void addImage(BodyPart message, byte[] data, String fileType) throws Exception {
        DataSource source = new ByteArrayDataSource(data, fileType);
        DataHandler handler = new DataHandler(source);

        message.setDataHandler(handler);
        message.setHeader("Content-ID", "<image>");
    }

    /**
     * 添加图片
     *
     * @param message 消息
     * @param path 文件所在路径
     * @throws Exception 异常
     */
    public static void addImage(BodyPart message, String path) throws Exception {
        DataSource file = new FileDataSource(new File(path));

        message.setDataHandler(new DataHandler(file));
        message.setHeader("Content-ID", "<image>");
    }
}
