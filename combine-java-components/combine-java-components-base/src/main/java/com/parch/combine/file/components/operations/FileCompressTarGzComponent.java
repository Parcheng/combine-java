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
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.GZIPOutputStream;

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
        // 创建一个GZIP输出流
        try (GZIPOutputStream gzipOS = new GZIPOutputStream(new FileOutputStream(target));
             TarArchiveOutputStream taos = new TarArchiveOutputStream(gzipOS)) {
            taos.setLongFileMode(TarArchiveOutputStream.LONGFILE_GNU);

            // 递归地将目录内容添加到tar.gz文件
            tarGzCompressDirectory(Paths.get(source), Paths.get(target).getParent(), taos);
        } catch (IOException e) {
            PrintErrorHelper.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private void tarGzCompressDirectory(Path sourcePath, Path outputDir, TarArchiveOutputStream taos) throws IOException {
        Files.walkFileTree(sourcePath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                addFileToTar(file, taos, sourcePath, outputDir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                if (!Files.isSameFile(dir, outputDir)) {
                    addFileToTar(dir, taos, sourcePath, outputDir);
                }
                return FileVisitResult.CONTINUE;
            }
        });
        taos.finish();
    }

    private static void addFileToTar(Path file, TarArchiveOutputStream taos, Path sourcePath, Path outputDir) throws IOException {
        TarArchiveEntry tarEntry = new TarArchiveEntry(file.toFile(), file.toString().substring(sourcePath.toString().length() + 1).replace("\\", "/"));
        taos.putArchiveEntry(tarEntry);

        if (!Files.isDirectory(file)) {
            Files.copy(file, taos);
        }
        taos.closeArchiveEntry();
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
