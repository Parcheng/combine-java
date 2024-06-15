package com.parch.combine.file.base.operations.compress;

import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.file.base.operations.FileOperationsInitConfig;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.vo.DataResult;

import java.util.List;

public abstract class AbsFileCompressComponent<T extends FileOperationsInitConfig, R extends FileCompressLogicConfig> extends AbsComponent<T, R> {

    protected FileCompressTypeEnum type;

    public AbsFileCompressComponent(Class<T> initClass, Class<R> logicClass, FileCompressTypeEnum type) {
        super(initClass, logicClass);
        this.type = type;
    }

    protected abstract List<String> initConfig();

    @Override
    public DataResult execute() {
        FileOperationsInitConfig initConfig = getInitConfig();
        FileCompressLogicConfig logicConfig = getLogicConfig();

        String sourcePath = logicConfig.source();
        String targetPath = logicConfig.target();
        if (sourcePath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
            return DataResult.fail(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
        }
        if (targetPath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
            return DataResult.fail(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
        }

        boolean compress = false;
        String postfix = FileNameUtil.getPostfix(sourcePath);
        if (postfix == null) {
            compress = true;
            postfix = FileNameUtil.getPostfix(targetPath);
        }
        if (postfix == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.NO_COMPRESS_FILE);
            return DataResult.fail(FileCompressErrorEnum.NO_COMPRESS_FILE);
        }

        FileCompressTypeEnum type = FileCompressTypeEnum.get(postfix);
        if (this.type != type) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TYPE_ERROR);
            return DataResult.fail(FileCompressErrorEnum.TYPE_ERROR, postfix);
        }

        Boolean useSystemDir = initConfig.useSystemDir();
        String dir = initConfig.dir();
        String fullSourcePath = FilePathHelper.getFinalPath(useSystemDir, dir, sourcePath);
        String fullTargetPath = FilePathHelper.getFinalPath(useSystemDir, dir, targetPath);
        return execute(fullSourcePath, fullTargetPath, compress);
    }

    protected abstract DataResult execute(String sourcePath, String targetPath, boolean compress);
}
