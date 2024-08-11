package com.parch.combine.file.base.operations.compress;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.file.base.operations.FileOperationsInitConfig;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;

import java.util.List;

public abstract class AbstractFileCompressComponent<T extends FileOperationsInitConfig, R extends FileCompressLogicConfig> extends AbstractComponent<T, R> {

    protected FileCompressTypeEnum type;

    public AbstractFileCompressComponent(Class<T> initClass, Class<R> logicClass, FileCompressTypeEnum type) {
        super(initClass, logicClass);
        this.type = type;
    }

    protected abstract List<String> initConfig();

    @Override
    public ComponentDataResult execute() {
        FileOperationsInitConfig initConfig = getInitConfig();
        FileCompressLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        String targetPath = logicConfig.target();
        if (sourcePath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
            return ComponentDataResult.fail(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
        }
        if (targetPath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
            return ComponentDataResult.fail(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
        }

        boolean compress = false;
        String postfix = FileNameUtil.getPostfix(sourcePath);
        if (postfix == null) {
            compress = true;
            postfix = FileNameUtil.getPostfix(targetPath);
        }
        if (postfix == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.NO_COMPRESS_FILE);
            return ComponentDataResult.fail(FileCompressErrorEnum.NO_COMPRESS_FILE);
        }

        FileCompressTypeEnum type = FileCompressTypeEnum.get(postfix);
        if (this.type != type) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TYPE_ERROR);
            return ComponentDataResult.fail(FileCompressErrorEnum.TYPE_ERROR, postfix);
        }

        Boolean useSystemDir = initConfig.useSystemDir();
        String dir = initConfig.dir();
        String fullSourcePath = FilePathHelper.getFinalPath(useSystemDir, dir, sourcePath);
        String fullTargetPath = FilePathHelper.getFinalPath(useSystemDir, dir, targetPath);
        return execute(fullSourcePath, fullTargetPath, compress);
    }

    protected abstract ComponentDataResult execute(String sourcePath, String targetPath, boolean compress);
}
