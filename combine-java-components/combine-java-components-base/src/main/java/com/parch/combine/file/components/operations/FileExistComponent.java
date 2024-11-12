package com.parch.combine.file.components.operations;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.file.base.operations.exist.FileExistErrorEnum;
import com.parch.combine.file.base.operations.exist.FileExistInitConfig;
import com.parch.combine.file.base.operations.exist.FileExistLogicConfig;

import java.io.File;

@Component(order = 320, key = "operations.exist", name = "检查文件/目录存在组件", logicConfigClass = FileExistLogicConfig.class, initConfigClass = FileExistInitConfig.class)
@ComponentResult(name = "文件/目录是否存在: true/false")
public class FileExistComponent extends AbstractComponent<FileExistInitConfig, FileExistLogicConfig> {

    public FileExistComponent() {
        super(FileExistInitConfig.class, FileExistLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        FileExistInitConfig initConfig = getInitConfig();
        FileExistLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        if (sourcePath == null) {
            PrintErrorHelper.print(FileExistErrorEnum.SOURCE_PATH_IS_NULL);
            return ComponentDataResult.fail(FileExistErrorEnum.SOURCE_PATH_IS_NULL);
        }

        File source = new File(FilePathHelper.getFinalPath(initConfig.useSystemDir(), initConfig.dir(), sourcePath));
        return ComponentDataResult.success(source.exists());
    }
}
