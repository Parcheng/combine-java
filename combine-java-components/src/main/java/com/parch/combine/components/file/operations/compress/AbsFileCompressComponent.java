package com.parch.combine.components.file.operations.compress;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FileNameUtil;
import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.components.file.operations.FileOperationsInitConfig;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件写盘组件
 */
public abstract class AbsFileCompressComponent<T extends FileOperationsInitConfig, R extends FileCompressLogicConfig> extends AbsComponent<T, R> {

    protected FileCompressTypeEnum type;

    /**
     * 构造器
     */
    public AbsFileCompressComponent(Class<T> initClass, Class<R> logicClass, FileCompressTypeEnum type) {
        super(initClass, logicClass);
        this.type = type;
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        FileCompressLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "源路径不能为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getTarget())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "目标路径不能为空"));
        }

        List<String> subList = initConfig();
        if (subList != null) {
            result.addAll(subList);
        }

        return result;
    }

    protected abstract List<String> initConfig();

    @Override
    public DataResult execute() {
        FileOperationsInitConfig initConfig = getInitConfig();
        FileCompressLogicConfig logicConfig = getLogicConfig();

        Object sourcePath = DataVariableHelper.parseValue(logicConfig.getSource(), false);
        Object targetPath = DataVariableHelper.parseValue(logicConfig.getTarget(), false);
        if (sourcePath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
            return DataResult.fail(FileCompressErrorEnum.SOURCE_PATH_IS_NULL);
        }
        if (targetPath == null) {
            ComponentErrorHandler.print(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
            return DataResult.fail(FileCompressErrorEnum.TARGET_PATH_IS_NULL);
        }

        boolean compress = false;
        String postfix = FileNameUtil.getPostfix(sourcePath.toString());
        if (postfix == null) {
            compress = true;
            postfix = FileNameUtil.getPostfix(targetPath.toString());
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

        String fullSourcePath = FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), sourcePath.toString());
        String fullTargetPath = FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), targetPath.toString());
        return execute(fullSourcePath, fullTargetPath, compress);
    }

    protected abstract DataResult execute(String sourcePath, String targetPath, boolean compress);
}
