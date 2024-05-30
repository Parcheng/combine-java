package com.parch.combine.components.file.input.open;

import com.parch.combine.components.file.helper.FilePathHelper;
import com.parch.combine.components.file.input.FileInputComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.TextExpressionHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件打开组件
 */
@Component(order = 200, key = "input.open", name = "读取磁盘文件数据组件", logicConfigClass = FileInputOpenLogicConfig.class, initConfigClass = FileInputOpenInitConfig.class)
@ComponentResult(name = "打开的文件字节信息")
public class FileInputOpenComponent extends FileInputComponent<FileInputOpenInitConfig, FileInputOpenLogicConfig> {

    /**
     * 构造器
     */
    public FileInputOpenComponent() {
        super(FileInputOpenInitConfig.class, FileInputOpenLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return new ArrayList<>();
    }

    @Override
    protected IComponentError execute(FileInfo fileInfo) {
        FileInputOpenInitConfig initConfig = getInitConfig();
        FileInputOpenLogicConfig logicConfig = getLogicConfig();

        String path = TextExpressionHelper.getText(logicConfig.getPath());
        if (path == null) {
            return FileInputOpenErrorEnum.PATH_IS_NULL;
        }

        String filePath = FilePathHelper.getFinalPath(initConfig.getUseSystemDir(), initConfig.getDir(), logicConfig.getPath());
        File file = new File(filePath);

        // 判断数据是否过大
        long fileSize = file.length();
        long max = initConfig.getMax() == null ? Integer.MAX_VALUE : initConfig.getMax() * 1024 * 1024;
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
        fileInfo.setName(FileNameUtil.getName(logicConfig.getPath()));
        fileInfo.setType(FileNameUtil.getPostfix(logicConfig.getPath()));
        fileInfo.setData(buffer);
        fileInfo.setSize(buffer.length);
        return null;
    }
}
