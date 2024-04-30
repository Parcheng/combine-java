package com.parch.combine.component.file.read.bytes;

import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.read.FileReadComponent;
import com.parch.combine.core.vo.DataResult;
import java.util.List;

/**
 * 文件读取组件
 */
public class FileReadBytesComponent extends FileReadComponent<FileReadBytesInitConfig, FileReadBytesLogicConfig> {

    /**
     * 构造器
     */
    public FileReadBytesComponent() {
        super(FileReadBytesInitConfig.class, FileReadBytesLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    protected DataResult execute(FileInfo fileInfo) {
        return DataResult.success(fileInfo.getData());
    }
}
