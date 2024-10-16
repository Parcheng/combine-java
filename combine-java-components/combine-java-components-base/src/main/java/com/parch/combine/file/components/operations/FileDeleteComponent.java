package com.parch.combine.file.components.operations;

import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.operations.delete.FileDeleteErrorEnum;
import com.parch.combine.file.base.operations.delete.FileDeleteInitConfig;
import com.parch.combine.file.base.operations.delete.FileDeleteLogicConfig;

import java.io.File;

@Component(order = 320, key = "operations.delete", name = "文件/目录删除组件", logicConfigClass = FileDeleteLogicConfig.class, initConfigClass = FileDeleteInitConfig.class)
@ComponentResult(name = "被删除文件数量")
public class FileDeleteComponent extends AbstractComponent<FileDeleteInitConfig, FileDeleteLogicConfig> {

    public FileDeleteComponent() {
        super(FileDeleteInitConfig.class, FileDeleteLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        FileDeleteInitConfig initConfig = getInitConfig();
        FileDeleteLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        if (sourcePath == null) {
            PrintErrorHelper.print(FileDeleteErrorEnum.SOURCE_PATH_IS_NULL);
            return ComponentDataResult.fail(FileDeleteErrorEnum.SOURCE_PATH_IS_NULL);
        }

        File source = new File(FilePathHelper.getFinalPath(initConfig.useSystemDir(), initConfig.dir(), sourcePath));
        if (!source.exists()) {
            PrintErrorHelper.print(FileDeleteErrorEnum.FILE_NOT_EXIST);
            return ComponentDataResult.fail(FileDeleteErrorEnum.FILE_NOT_EXIST);
        }

        try {
            int count = delete(source);
            return ComponentDataResult.success(count);
        } catch (Exception e) {
            PrintErrorHelper.print(FileDeleteErrorEnum.FAIL, e);
            return ComponentDataResult.fail(FileDeleteErrorEnum.FAIL);
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
