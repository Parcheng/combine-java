package com.parch.combine.file.components.operations;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.file.base.operations.compress.FileCompressErrorEnum;
import com.parch.combine.file.base.operations.copy.FileCopyInitConfig;
import com.parch.combine.file.base.operations.copy.FileCopyLogicConfig;
import com.parch.combine.file.base.operations.delete.FileDeleteErrorEnum;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component(order = 320, key = "operations.copy", name = "文件/目录拷贝组件", logicConfigClass = FileCopyLogicConfig.class, initConfigClass = FileCopyInitConfig.class)
@ComponentResult(name = "true 或抛出异常信息")
public class FileCopyComponent extends AbstractComponent<FileCopyInitConfig, FileCopyLogicConfig> {

    public FileCopyComponent() {
        super(FileCopyInitConfig.class, FileCopyLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        boolean success = copy();
        if (!success) {
            return ComponentDataResult.fail(FileDeleteErrorEnum.FAIL);
        }

        return ComponentDataResult.success(true);
    }

    private boolean copy() {
        FileCopyInitConfig initConfig = getInitConfig();
        FileCopyLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        String targetPath = logicConfig.target();
        if (sourcePath == null) {
            PrintErrorHelper.print(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
            return false;
        }
        if (targetPath == null) {
            PrintErrorHelper.print(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
            return false;
        }

        Boolean useSystemDir = initConfig.useSystemDir();
        String dir = initConfig.dir();
        File source = new File(FilePathHelper.getFinalPath(useSystemDir, dir, sourcePath));
        if (!source.exists()) {
            PrintErrorHelper.print(FileCompressErrorEnum.FILE_NOT_EXIST);
            return false;
        }

        try {
            // 目录不存在则创建目录
            String destPathStr = FilePathHelper.getFinalPath(useSystemDir, dir, targetPath);
            Path destPath = Paths.get(destPathStr);
            Path directory = destPath.getParent();
            if (directory != null && !Files.exists(directory)) {
                Files.createDirectories(directory);
            }

            // 创建文件（如果它不存在）
            if (!Files.exists(destPath)) {
                Files.createFile(destPath);
            }

            try (InputStream is = new FileInputStream(source);
                 OutputStream os = new FileOutputStream(destPathStr)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            PrintErrorHelper.print(FileDeleteErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

}
