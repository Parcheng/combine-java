package com.parch.combine.components.file.operations.copy;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.components.file.operations.delete.FileDeleteErrorEnum;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component(order = 320, key = "operations.copy", name = "文件/目录拷贝组件", logicConfigClass = FileCopyLogicConfig.class, initConfigClass = FileCopyInitConfig.class)
@ComponentResult(name = "true 或抛出异常信息")
public class FileCopyComponent extends AbsComponent<FileCopyInitConfig, FileCopyLogicConfig> {

    /**
     * 构造器
     */
    public FileCopyComponent() {
        super(FileCopyInitConfig.class, FileCopyLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        FileCopyLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "源路径不能为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getTarget())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "目标路径不能为空"));
        }

        return result;
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

        File source = new File(FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), logicConfig.getSource()));
        File dest = new File(FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), logicConfig.getTarget()));

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
