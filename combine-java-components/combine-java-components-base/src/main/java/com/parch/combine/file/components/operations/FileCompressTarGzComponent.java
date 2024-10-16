package com.parch.combine.file.components.operations;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FileHelper;
import com.parch.combine.file.base.operations.compress.AbstractFileCompressComponent;
import com.parch.combine.file.base.operations.compress.FileCompressErrorEnum;
import com.parch.combine.file.base.operations.compress.FileCompressTypeEnum;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.operations.compress.targz.FileCompressTarGzErrorEnum;
import com.parch.combine.file.base.operations.compress.targz.FileCompressTarGzInitConfig;
import com.parch.combine.file.base.operations.compress.targz.FileCompressTarGzLogicConfig;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component(order = 310, key = "operations.compress.targz", name = "tar.gz压缩解压组件", logicConfigClass = FileCompressTarGzLogicConfig.class, initConfigClass = FileCompressTarGzInitConfig.class)
@ComponentDesc("依赖 commons-compress，推荐版本 1.20")
@ComponentResult(name = "true 或抛出异常信息")
public class FileCompressTarGzComponent extends AbstractFileCompressComponent<FileCompressTarGzInitConfig, FileCompressTarGzLogicConfig> {

    public FileCompressTarGzComponent() {
        super(FileCompressTarGzInitConfig.class, FileCompressTarGzLogicConfig.class, FileCompressTypeEnum.TAR_GZ);
    }

    @Override
    protected List<String> initConfig() {
        return null;
    }

    @Override
    protected ComponentDataResult execute(String sourcePath, String targetPath, boolean compress) {
        boolean success = compress ? tar(sourcePath, targetPath) : untar(sourcePath, targetPath);
        if (!success) {
            return ComponentDataResult.fail(FileCompressTarGzErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }

    private boolean tar(String source, String target) {
        File srcFile = new File(source);
        if (!srcFile.isDirectory()) {
            PrintErrorHelper.print(FileCompressErrorEnum.FILE_IS_NULL);
            return false;
        }

        FileHelper.mkdirs(target);
        Path destPath = Paths.get(target);
        try (OutputStream fileOutputStream = Files.newOutputStream(destPath);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
             GzipCompressorOutputStream gzipOutputStream = new GzipCompressorOutputStream(bufferedOutputStream);
             TarArchiveOutputStream tarArchiveOutputStream = new TarArchiveOutputStream(gzipOutputStream)) {

            addFilesToTarGz(source, tarArchiveOutputStream, "");
        } catch (Exception e) {
            PrintErrorHelper.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }


        return true;
    }

    private void addFilesToTarGz(String sourceDir, TarArchiveOutputStream tarArchiveOutputStream, String currentDir) throws IOException {
        File sourceFile = new File(sourceDir);
        File[] files = sourceFile.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    String newDir = currentDir + file.getName() + "/";
                    TarArchiveEntry tarEntry = new TarArchiveEntry(newDir);
                    tarArchiveOutputStream.putArchiveEntry(tarEntry);
                    tarArchiveOutputStream.closeArchiveEntry();
                    addFilesToTarGz(file.getAbsolutePath(), tarArchiveOutputStream, newDir);
                } else {
                    String entryName = currentDir + file.getName();
                    TarArchiveEntry tarEntry = new TarArchiveEntry(file, entryName);
                    tarArchiveOutputStream.putArchiveEntry(tarEntry);
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
                        tarArchiveOutputStream.write(buffer, 0, bytesRead);
                    }
                    bufferedInputStream.close();
                    tarArchiveOutputStream.closeArchiveEntry();
                }
            }
        }
    }

    private boolean untar(String source, String target) {
        Path srcPath = Paths.get(source);
        if (!Files.exists(srcPath) || Files.isDirectory(srcPath)) {
            PrintErrorHelper.print(FileCompressErrorEnum.FILE_IS_NULL);
            return false;
        }

        try (InputStream fileInputStream = Files.newInputStream(srcPath);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
             GzipCompressorInputStream gzipInputStream = new GzipCompressorInputStream(bufferedInputStream);
             TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(gzipInputStream)) {

            TarArchiveEntry entry;
            while ((entry = tarArchiveInputStream.getNextTarEntry()) != null) {
                String targetPath = target + "/" + entry.getName();
                FileHelper.mkdirs(targetPath);
                try (OutputStream outputStream = Files.newOutputStream(new File(targetPath).toPath());
                     BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = tarArchiveInputStream.read(buffer)) != -1) {
                        bufferedOutputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (Exception e) {
            PrintErrorHelper.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }
}
