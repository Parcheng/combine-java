package com.parch.combine.file.components.input;

import com.parch.combine.file.base.helper.FilePathHelper;
import com.parch.combine.file.base.input.FileInputComponent;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.input.open.FileInputOpenErrorEnum;
import com.parch.combine.file.base.input.open.FileInputOpenInitConfig;
import com.parch.combine.file.base.input.open.FileInputOpenLogicConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component(order = 200, key = "input.open", name = "读取磁盘文件数据组件", logicConfigClass = FileInputOpenLogicConfig.class, initConfigClass = FileInputOpenInitConfig.class)
@ComponentResult(name = "打开的文件字节信息")
public class FileInputOpenComponent extends FileInputComponent<FileInputOpenInitConfig, FileInputOpenLogicConfig> {

    public FileInputOpenComponent() {
        super(FileInputOpenInitConfig.class, FileInputOpenLogicConfig.class);
    }

    @Override
    protected IComponentError execute(FileInfo fileInfo) {
        FileInputOpenInitConfig initConfig = getInitConfig();
        FileInputOpenLogicConfig logicConfig = getLogicConfig();

        String path = logicConfig.path();
        if (path == null) {
            return FileInputOpenErrorEnum.PATH_IS_NULL;
        }

        String filePath = FilePathHelper.getFinalPath(initConfig.useSystemDir(), initConfig.dir(), path);
        File file = new File(filePath);

        // 判断数据是否过大
        long fileSize = file.length();
        long max = initConfig.max() == null ? Integer.MAX_VALUE : initConfig.max() * 1024 * 1024;
        if (fileSize > max) {
            return FileInputOpenErrorEnum.FILE_OVERLENGTH;
        }

        byte[] buffer;
        try {
            FileInputStream fi = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead;
            while (offset < buffer.length && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 判断数据是否全部读取完成
            if (offset != buffer.length) {
                return FileInputOpenErrorEnum.FILE_DATA_SCARCITY;
            }
            fi.close();
        } catch (IOException e) {
            ComponentErrorHandler.print(FileInputOpenErrorEnum.FILE_DATA_SCARCITY, e);
            return FileInputOpenErrorEnum.OPEN_ERROR;
        }

        // 组装数据
        fileInfo.setName(FileNameUtil.getName(path));
        fileInfo.setType(FileNameUtil.getPostfix(path));
        fileInfo.setData(buffer);
        fileInfo.setSize(buffer.length);
        return null;
    }
}
