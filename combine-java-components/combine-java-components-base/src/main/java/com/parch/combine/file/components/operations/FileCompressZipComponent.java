package com.parch.combine.file.components.operations;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FileHelper;
import com.parch.combine.file.base.operations.compress.AbstractFileCompressComponent;
import com.parch.combine.file.base.operations.compress.FileCompressTypeEnum;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipErrorEnum;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipInitConfig;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipLogicConfig;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Component(order = 310, key = "operations.compress.zip", name = "ZIP压缩解压组件", logicConfigClass = FileCompressZipLogicConfig.class, initConfigClass = FileCompressZipInitConfig.class)
@ComponentResult(name = "true 或抛出异常信息")
public class FileCompressZipComponent extends AbstractFileCompressComponent<FileCompressZipInitConfig, FileCompressZipLogicConfig> {

    public FileCompressZipComponent() {
        super(FileCompressZipInitConfig.class, FileCompressZipLogicConfig.class, FileCompressTypeEnum.ZIP);
    }

    @Override
    protected List<String> initConfig() {
        return null;
    }

    @Override
    protected ComponentDataResult execute(String sourcePath, String targetPath, boolean compress) {
        boolean success = compress ? zip(sourcePath, targetPath) : unzip(sourcePath, targetPath);
        if (!success) {
            return ComponentDataResult.fail(FileCompressZipErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }

    private boolean zip(String source, String target) {
        FileHelper.mkdirs(target);
        try (FileInputStream fis = new FileInputStream(source);
             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                zos.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(FileCompressZipErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private boolean unzip(String source, String target) {
        File fileToExtract = new File(source);
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToExtract))) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = zis.read(buffer)) != -1) {
                // 获取当前读取到的条目信息
                ZipEntry entry = zis.getNextEntry();
                if (entry != null) {
                    // 创建输出流并将数据写入新文件或目录
                    String targetPath = target + "/" + entry.getName();
                    FileHelper.mkdirs(targetPath);
                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetPath))){
                        for (int i = 0; i < bytesRead; ++i) {
                            outputStream.write(buffer[i]);
                        }
                    }
                }
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(FileCompressZipErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }
}
