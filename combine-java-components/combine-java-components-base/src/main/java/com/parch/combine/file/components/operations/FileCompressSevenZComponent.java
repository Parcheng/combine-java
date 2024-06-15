package com.parch.combine.file.components.operations;

import com.parch.combine.file.base.helper.FileHelper;
import com.parch.combine.file.base.operations.compress.AbsFileCompressComponent;
import com.parch.combine.file.base.operations.compress.FileCompressErrorEnum;
import com.parch.combine.file.base.operations.compress.FileCompressTypeEnum;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZErrorEnum;
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZInitConfig;
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZLogicConfig;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;


@Component(order = 310, key = "operations.compress.7z", name = "7Z压缩解压组件", logicConfigClass = FileCompressSevenZLogicConfig.class, initConfigClass = FileCompressSevenZInitConfig.class)
@ComponentDesc("依赖 commons-compress，推荐版本 1.20")
@ComponentResult(name = "true 或抛出异常信息")
public class FileCompressSevenZComponent extends AbsFileCompressComponent<FileCompressSevenZInitConfig, FileCompressSevenZLogicConfig> {

    public FileCompressSevenZComponent() {
        super(FileCompressSevenZInitConfig.class, FileCompressSevenZLogicConfig.class, FileCompressTypeEnum.ZIP);
    }

    @Override
    protected List<String> initConfig() {
        return null;
    }

    @Override
    protected DataResult execute(String sourcePath, String targetPath, boolean compress) {
        boolean success = compress ? z(sourcePath, targetPath) : unz(sourcePath, targetPath);
        if (!success) {
            return DataResult.fail(FileCompressSevenZErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    private boolean z(String source, String target) {
        FileHelper.mkdirs(target);

        File inputFile = new File(source);
        File outputFile = new File(target);
        try (SevenZOutputFile sevenZ = new SevenZOutputFile(outputFile)) {
            SevenZArchiveEntry entry = sevenZ.createArchiveEntry(inputFile, inputFile.getName());

            sevenZ.putArchiveEntry(entry);

            byte[] buffer = new byte[1024];
            int bytesRead;

            try (InputStream inStream = Files.newInputStream(inputFile.toPath())) {
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    sevenZ.write(buffer, 0, bytesRead);
                }
            }

            sevenZ.closeArchiveEntry();
        } catch (IOException e) {
            ComponentErrorHandler.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private boolean unz(String source, String target) {
        try (SevenZFile sevenZ = new SevenZFile(new File(source))) {
            ArchiveEntry entry;

            while ((entry = sevenZ.getNextEntry()) != null) {
                String targetPath = target + "/" + entry.getName();
                FileHelper.mkdirs(targetPath);

                byte[] buffer = new byte[4096];
                int bytesRead;
                try (FileOutputStream outputStream = new FileOutputStream(targetPath)) {
                    while ((bytesRead = sevenZ.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }
}
