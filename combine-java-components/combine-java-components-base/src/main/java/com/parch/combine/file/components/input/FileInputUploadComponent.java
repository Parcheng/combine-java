package com.parch.combine.file.components.input;

import com.parch.combine.file.base.input.FileInputComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.FileParamKey;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.input.upload.FileInputUploadErrorEnum;
import com.parch.combine.file.base.input.upload.FileInputUploadInitConfig;
import com.parch.combine.file.base.input.upload.FileInputUploadLogicConfig;

import java.util.Map;

@Component(order = 200, key = "input.upload", name = "读取上传文件数据组件", logicConfigClass = FileInputUploadLogicConfig.class, initConfigClass = FileInputUploadInitConfig.class)
@ComponentResult(name = "上传的文件字节信息")
public class FileInputUploadComponent extends FileInputComponent<FileInputUploadInitConfig, FileInputUploadLogicConfig> {

    public FileInputUploadComponent() {
        super(FileInputUploadInitConfig.class, FileInputUploadLogicConfig.class);
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
