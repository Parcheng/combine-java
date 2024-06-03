package com.parch.combine.components.file.operations.copy;

import com.parch.combine.components.file.operations.compress.FileCompressErrorEnum;
import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.components.file.operations.delete.FileDeleteErrorEnum;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import java.io.*;

@Component(order = 320, key = "operations.copy", name = "文件/目录拷贝组件", logicConfigClass = FileCopyLogicConfig.class, initConfigClass = FileCopyInitConfig.class)
@ComponentResult(name = "true 或抛出异常信息")
public class FileCopyComponent extends AbsComponent<FileCopyInitConfig, FileCopyLogicConfig> {

    public FileCopyComponent() {
        super(FileCopyInitConfig.class, FileCopyLogicConfig.class);
    }

    @Override
    protected DataResult execute() {
        boolean success = copy();
        if (!success) {
            return DataResult.fail(FileDeleteErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    private boolean copy() {
        FileCopyInitConfig initConfig = getInitConfig();
        FileCopyLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        String targetPath = logicConfig.target();
        if (sourcePath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
            return false;
        }
        if (targetPath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
            return false;
        }

        Boolean useSystemDir = initConfig.useSystemDir();
        String dir = initConfig.dir();
        File source = new File(FilePathHelper.getFinalPath(useSystemDir, dir, sourcePath));
        File dest = new File(FilePathHelper.getFinalPath(useSystemDir, dir, targetPath));

        try {
            File parentDir = dest.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            try (InputStream is = new FileInputStream(source);
                 OutputStream os = new FileOutputStream(dest)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(FileDeleteErrorEnum.FAIL, e);
            return false;
        }

        return true;
    }

}
