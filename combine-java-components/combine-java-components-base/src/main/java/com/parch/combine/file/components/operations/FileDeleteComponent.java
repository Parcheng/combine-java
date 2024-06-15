package com.parch.combine.file.components.operations;

import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.file.base.operations.delete.FileDeleteErrorEnum;
import com.parch.combine.file.base.operations.delete.FileDeleteInitConfig;
import com.parch.combine.file.base.operations.delete.FileDeleteLogicConfig;

import java.io.File;

@Component(order = 320, key = "operations.delete", name = "文件/目录删除组件", logicConfigClass = FileDeleteLogicConfig.class, initConfigClass = FileDeleteInitConfig.class)
@ComponentResult(name = "被删除文件数量")
public class FileDeleteComponent extends AbsComponent<FileDeleteInitConfig, FileDeleteLogicConfig> {

    public FileDeleteComponent() {
        super(FileDeleteInitConfig.class, FileDeleteLogicConfig.class);
    }

    @Override
    protected DataResult execute() {
        FileDeleteInitConfig initConfig = getInitConfig();
        FileDeleteLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        if (sourcePath == null) {
            ComponentErrorHandler.print(FileDeleteErrorEnum.SOURCE_PATH_IS_NULL);
            return DataResult.fail(FileDeleteErrorEnum.SOURCE_PATH_IS_NULL);
        }

        File source = new File(FilePathHelper.getFinalPath(initConfig.useSystemDir(), initConfig.dir(), sourcePath));
        if (!source.exists()) {
            ComponentErrorHandler.print(FileDeleteErrorEnum.FILE_NOT_EXIST);
            return DataResult.fail(FileDeleteErrorEnum.FILE_NOT_EXIST);
        }

        try {
            int count = delete(source);
            return DataResult.success(count);
        } catch (Exception e) {
            ComponentErrorHandler.print(FileDeleteErrorEnum.FAIL, e);
            return DataResult.fail(FileDeleteErrorEnum.FAIL);
        }
    }

    private int delete(File file) {
        int count = 0;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File f : files) {
                    count += delete(f);
                }
            }
        }
        if (file.delete()) {
            count++;
        }

        return count;
    }

}
