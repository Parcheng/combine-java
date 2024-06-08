package com.parch.combine.components.file.output.disk;

import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.components.file.output.FileOutputComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component(order = 400, key = "output.disk", name = "文件数据写入磁盘组件", logicConfigClass = FileOutputDiskLogicConfig.class, initConfigClass = FileOutputDiskInitConfig.class)
@ComponentResult(name = "文件写入磁盘是否成功")
public class FileOutputDiskComponent extends FileOutputComponent<FileOutputDiskInitConfig, FileOutputDiskLogicConfig> {

    public FileOutputDiskComponent() {
        super(FileOutputDiskInitConfig.class, FileOutputDiskLogicConfig.class);
    }

    @Override
    public DataResult execute(FileInfo fileInfo) {
        FileOutputDiskInitConfig initConfig = getInitConfig();
        FileOutputDiskLogicConfig logicConfig = getLogicConfig();

        String targetPath = logicConfig.targetPath();
        if (targetPath == null) {
            ComponentErrorHandler.print(FileOutputDiskErrorEnum.TARGET_PATH_IS_NULL);
            return DataResult.fail(FileOutputDiskErrorEnum.TARGET_PATH_IS_NULL);
        }

        // 组装文件写入路径
        String filePath = FilePathHelper.getFinalPath(initConfig.useSystemDir(), initConfig.dir(), targetPath);
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
