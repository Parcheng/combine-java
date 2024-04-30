package com.parch.combine.component.file.input.upload;

import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.IComponentError;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.FileParamKey;
import com.parch.combine.component.file.input.FileInputComponent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 文件读取组件
 */
public class FileInputUploadComponent extends FileInputComponent<FileInputUploadInitConfig, FileInputUploadLogicConfig> {

    private final static String DEFAULT_SEPARATOR = "|";

    /**
     * 构造器
     */
    public FileInputUploadComponent() {
        super(FileInputUploadInitConfig.class, FileInputUploadLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return new ArrayList<>();
    }

    @Override
    @SuppressWarnings("unchecked")
    protected IComponentError execute(FileInfo fileInfo) {
        Map<String, Object> params = ComponentContextHandler.getParams();
        Object fileInfoObj = params.get(FileParamKey.FILE_OBJ_KEY);

        // 解析文件数据，组装成 FileInfo 结构
        if (!(fileInfoObj instanceof Map)) {
            return FileInputUploadErrorEnum.DATA_ERROR;
        }

        Map<String, Object> fileInfoMap = (Map<String, Object>) fileInfoObj;
        fileInfo.setName((String) fileInfoMap.get(FileParamKey.PARAM_FILE_NAME_KEY));
        fileInfo.setType((String) fileInfoMap.get(FileParamKey.PARAM_FILE_TYPE_KEY));

        // 处理byte数据
        Object dataObj = fileInfoMap.get(FileParamKey.PARAM_FILE_DATA_KEY);
        if (dataObj instanceof byte[]) {
            fileInfo.setData((byte[]) dataObj);
        } else {
            fileInfo.setData(dataObj.toString().getBytes());
        }

        fileInfo.setSize(fileInfo.getData().length);
        return null;
    }
}
