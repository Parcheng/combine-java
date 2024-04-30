package com.parch.combine.component.file.input.open;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.FilePathHelper;
import com.parch.combine.component.file.input.FileInputComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.error.IComponentError;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.common.util.FileNameUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件打开组件
 */
public class FileInputOpenComponent extends FileInputComponent<FileInputOpenInitConfig, FileInputOpenLogicConfig> {

    /**
     * 构造器
     */
    public FileInputOpenComponent() {
        super(FileInputOpenInitConfig.class, FileInputOpenLogicConfig.class);
    }

    @Override
    public List<String> init() {
        FileInputOpenInitConfig initConfig = getInitConfig();
        if (CheckEmptyUtil.isEmpty(initConfig.getDir())) {
            initConfig.setDir(CheckEmptyUtil.EMPTY);
        }
        return new ArrayList<>();
    }

    @Override
    protected IComponentError execute(FileInfo fileInfo) {
        FileInputOpenInitConfig initConfig = getInitConfig();
        FileInputOpenLogicConfig logicConfig = getLogicConfig();

        String filePath = FilePathHelper.getFinalPath(initConfig.getDir(), logicConfig.getPath());
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
