package com.parch.combine.component.file.output.disk;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.FilePathHelper;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.output.FileOutputComponent;
import com.parch.combine.core.vo.DataResult;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件写盘组件
 */
public class FileExportDiskComponent extends FileOutputComponent<FileOutputDiskInitConfig, FileOutputDiskLogicConfig> {

    /**
     * 构造器
     */
    public FileExportDiskComponent() {
        super(FileOutputDiskInitConfig.class, FileOutputDiskLogicConfig.class);
    }


    @Override
    public List<String> initFileConfig() {
        List<String> result = new ArrayList<>();
        FileOutputDiskInitConfig initConfig = getInitConfig();
        if (CheckEmptyUtil.isEmpty(initConfig.getDir())) {
            initConfig.setDir(CheckEmptyUtil.EMPTY);
        }

        FileOutputDiskLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getTargetPath())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "磁盘写入路径不能为空"));
        }
        return result;
    }

    @Override
    public DataResult execute(FileInfo fileInfo) {
        FileOutputDiskInitConfig initConfig = getInitConfig();
        FileOutputDiskLogicConfig logicConfig = getLogicConfig();

        // 组装文件写入路径
        String filePath = FilePathHelper.getFinalPath(initConfig.getDir(), logicConfig.getTargetPath());

        // 文件写入数据
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(fileInfo.getData());
        } catch (IOException e) {
            ComponentErrorHandler.print(FileOutputDiskErrorEnum.FAIL, e);
            return DataResult.fail(FileOutputDiskErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
