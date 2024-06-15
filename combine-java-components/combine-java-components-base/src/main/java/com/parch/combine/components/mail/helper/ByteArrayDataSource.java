package com.parch.combine.components.mail.helper;

import javax.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ByteArrayDataSource implements DataSource {
    private final byte[] data;
    private final String type;

    public ByteArrayDataSource(byte[] data, String type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(data);
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        throw new IOException("Cannot write to ByteArrayDataSource");
    }

    @Override
    public String getContentType() {
        switch (type) {
            case "txt":
                return "text/plain";
            case "html":
                return "text/html";
            case "doc":
            case "docx":
                return "application/msword";
            case "xls":
            case "xlsx":
                return "application/msexcel";
            case "ppt":
            case "pptx":
                return "application/msppt";
            case "pdf":
                return "application/pdf";
            case "jpg":
            case "jpeg":
            case "png":
            case "gif":
                return "image/" + type;
            case "mp3":
                return "audio/mp3";
            case "mp4":
                return "video/mp4";
            default:
                return "application/octet-stream";
        }
    }

    @Override
    public String getName() {
        return null;
    }
}