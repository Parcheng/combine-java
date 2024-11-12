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
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZErrorEnum;
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZInitConfig;
import com.parch.combine.file.base.operations.compress.sevenz.FileCompressSevenZLogicConfig;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZOutputFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;


@Component(order = 310, key = "operations.compress.7z", name = "7Z压缩解压组件", logicConfigClass = FileCompressSevenZLogicConfig.class, initConfigClass = FileCompressSevenZInitConfig.class)
@ComponentDesc("依赖 commons-compress，推荐版本 1.20")
@ComponentResult(name = "true 或抛出异常信息")
public class FileCompressSevenZComponent extends AbstractFileCompressComponent<FileCompressSevenZInitConfig, FileCompressSevenZLogicConfig> {

    public FileCompressSevenZComponent() {
        super(FileCompressSevenZInitConfig.class, FileCompressSevenZLogicConfig.class, FileCompressTypeEnum.SEVEN_Z);
    }

    @Override
    protected List<String> initConfig() {
        return null;
    }

    @Override
    protected ComponentDataResult execute(String sourcePath, String targetPath, boolean compress) {
        boolean success = compress ? z(sourcePath, targetPath) : unz(sourcePath, targetPath);
        if (!success) {
            return ComponentDataResult.fail(FileCompressSevenZErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }

    private boolean z(String source, String target) {
        FileHelper.mkdirs(target);

        File inputFile = new File(source);
        File outputFile = new File(target);
        try (SevenZOutputFile out = new SevenZOutputFile(outputFile)) {
            addToArchiveCompression(out, inputFile, ".", true);
        } catch (IOException e) {
            PrintErrorHelper.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

    private static void addToArchiveCompression(SevenZOutputFile out, File file, String dir, boolean isFirst) throws IOException {
        if (file.isFile()) {
            String name = dir + File.separator + file.getName();
            SevenZArchiveEntry entry = out.createArchiveEntry(file, name);
            out.putArchiveEntry(entry);
            try (FileInputStream in = new FileInputStream(file)) {
                byte[] buffer = new byte[1024];
                int count;
                while ((count = in.read(buffer)) > 0) {
                    out.write(buffer, 0, count);
                }
            }
            out.closeArchiveEntry();
        } else if (file.isDirectory()) {
            String name = isFirst ? dir : (dir + File.separator + file.getName());
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) {
                    addToArchiveCompression(out, child, name, false);
                }
            }
        }
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
            PrintErrorHelper.print(FileCompressErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }
}
