package com.parch.combine.file.components.operations;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FileHelper;
import com.parch.combine.file.base.operations.compress.AbstractFileCompressComponent;
import com.parch.combine.file.base.operations.compress.FileCompressTypeEnum;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipErrorEnum;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipInitConfig;
import com.parch.combine.file.base.operations.compress.zip.FileCompressZipLogicConfig;

import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

//    private boolean zip(String source, String target) {
//        FileHelper.mkdirs(target);
//        try (FileInputStream fis = new FileInputStream(source);
//             ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(target))) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//
//            while ((bytesRead = fis.read(buffer)) != -1) {
//                zos.write(buffer, 0, bytesRead);
//            }
//        } catch (IOException e) {
//            PrintErrorHelper.print(FileCompressZipErrorEnum.FAIL, e);
//            return false;
//        }
//
//        return true;
//    }

    public boolean zip(String source, String target) {
        // 创建ZIP文件的父目录
        File outputFile = new File(target);
        outputFile.getParentFile().mkdirs();

        // 创建ZIP输出流
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(outputFile))) {
            zipSubDirectory(Paths.get(source), zos);
        } catch (IOException e) {
            PrintErrorHelper.print(FileCompressZipErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private void zipSubDirectory(Path path, ZipOutputStream zos) throws IOException {
        // 遍历当前路径中的所有文件和目录
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path filePath : stream) {
                // 构建ZIP条目的名称
                String zipEntryName = path.relativize(filePath).toString();
                if (Files.isDirectory(filePath)) {
                    // 如果是目录，则创建ZIP条目并递归压缩
                    ZipEntry zipEntry = new ZipEntry(zipEntryName + "/");
                    zos.putNextEntry(zipEntry);
                    zipSubDirectory(filePath, zos); // 递归调用
                } else {
                    // 如果是文件，则创建ZIP条目并压缩文件内容
                    ZipEntry zipEntry = new ZipEntry(zipEntryName);
                    zos.putNextEntry(zipEntry);
                    try (BufferedInputStream bis = new BufferedInputStream(Files.newInputStream(filePath))) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = bis.read(buffer)) != -1) {
                            zos.write(buffer, 0, length);
                        }
                    }
                    zos.closeEntry();
                }
            }
        }
    }

    private boolean unzip(String zipFilePath, String destDirectory) {
        // 创建目标目录
        try {
            Files.createDirectories(Paths.get(destDirectory));

            // 创建ZIP输入流
            try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath))) {
                ZipEntry zipEntry;
                // 循环读取ZIP文件中的条目
                while ((zipEntry = zis.getNextEntry()) != null) {
                    String filePath = destDirectory + File.separator + zipEntry.getName();
                    if (!zipEntry.isDirectory()) {
                        // 如果是文件，则解压
                        extractFile(zis, filePath);
                    } else {
                        // 如果是目录，则创建目录
                        Files.createDirectories(Paths.get(filePath));
                    }
                    zis.closeEntry();
                }
            }
        } catch (IOException e) {
            PrintErrorHelper.print(FileCompressZipErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private void extractFile(ZipInputStream zis, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = zis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
        }
    }

//    private boolean unzip(String source, String target) {
//        File fileToExtract = new File(source);
//        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(fileToExtract))) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//
//            while ((bytesRead = zis.read(buffer)) != -1) {
//                // 获取当前读取到的条目信息
//                ZipEntry entry = zis.getNextEntry();
//                if (entry != null) {
//                    // 创建输出流并将数据写入新文件或目录
//                    String targetPath = target + "/" + entry.getName();
//                    FileHelper.mkdirs(targetPath);
//                    try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(targetPath))){
//                        for (int i = 0; i < bytesRead; ++i) {
//                            outputStream.write(buffer[i]);
//                        }
//                    }
//                }
//            }
//        } catch (IOException e) {
//            PrintErrorHelper.print(FileCompressZipErrorEnum.FAIL, e);
//            return false;
//        }
//
//        return true;
//    }
}
