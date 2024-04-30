package com.parch.combine.components.file.operations.delete;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component(order = 320, key = "operations.delete", name = "文件/目录删除组件", logicConfigClass = FileDeleteLogicConfig.class, initConfigClass = FileDeleteInitConfig.class)
@ComponentResult(name = "被删除文件数量")
public class FileDeleteComponent extends AbsComponent<FileDeleteInitConfig, FileDeleteLogicConfig> {

    /**
     * 构造器
     */
    public FileDeleteComponent() {
        super(FileDeleteInitConfig.class, FileDeleteLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        FileDeleteLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "源路径不能为空"));
        }

        return result;
    }


    @Override
    protected DataResult execute() {
        FileDeleteInitConfig initConfig = getInitConfig();
        FileDeleteLogicConfig logicConfig = getLogicConfig();
        File source = new File(FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), logicConfig.getSource()));
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
