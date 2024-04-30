package com.parch.combine.components.file.output.disk;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.base.FileInfo;
import com.parch.combine.components.file.output.FileOutputComponent;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件写盘组件
 */
@Component(order = 400, key = "output.disk", name = "文件数据写入磁盘组件", logicConfigClass = FileOutputDiskLogicConfig.class, initConfigClass = FileOutputDiskInitConfig.class)
@ComponentResult(name = "文件写入磁盘是否成功")
public class FileOutputDiskComponent extends FileOutputComponent<FileOutputDiskInitConfig, FileOutputDiskLogicConfig> {

    /**
     * 构造器
     */
    public FileOutputDiskComponent() {
        super(FileOutputDiskInitConfig.class, FileOutputDiskLogicConfig.class);
    }


    @Override
    public List<String> initFileConfig() {
        List<String> result = new ArrayList<>();
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
        String filePath = FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), logicConfig.getTargetPath());
        File file = new File(filePath);

        // 创建目录
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }

        // 文件写入数据
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(fileInfo.getData());
        } catch (IOException e) {
            ComponentErrorHandler.print(FileOutputDiskErrorEnum.FAIL, e);
            return DataResult.fail(FileOutputDiskErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }
}
