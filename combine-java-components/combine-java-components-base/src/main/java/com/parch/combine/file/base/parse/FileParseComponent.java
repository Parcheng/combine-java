package com.parch.combine.file.base.parse;

import com.parch.combine.file.base.FilePostfixEnum;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.FileParamKey;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileParseComponent<T extends IInitConfig, R extends FileParseLogicConfig> extends AbstractComponent<T, R> {

    protected List<String> fileTypes = new ArrayList<>(1);

    public FileParseComponent(Class<T> initConfigClass, Class<R> logicConfigClass, FilePostfixEnum fileType, FilePostfixEnum ... fileTypes) {
        super(initConfigClass, logicConfigClass);

        this.fileTypes.add(fileType.name());
        for (FilePostfixEnum item : fileTypes) {
            this.fileTypes.add(item.name());
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public final ComponentDataResult execute() {
        FileInfo fileInfo = new FileInfo();;

        // 根据Source获取文件信息（Source为空表示从接口上传文件）
        FileParseLogicConfig readLogicConfig = getLogicConfig();

        Object data = readLogicConfig.source();
        if (data == null) {
            Map<String, Object> params = ComponentContextHandler.getParams();
            Object fileInfoObj = params.get(FileParamKey.FILE_OBJ_KEY);

            // 解析文件数据，组装成 FileInfo 结构
            if (fileInfoObj instanceof Map) {
                Map<String, Object> fileInfoMap = (Map<String, Object>) fileInfoObj;
                fileInfo.setName((String) fileInfoMap.get(FileParamKey.PARAM_FILE_NAME_KEY));
                fileInfo.setType((String) fileInfoMap.get(FileParamKey.PARAM_FILE_TYPE_KEY));
                Object dataObj = fileInfoMap.get(FileParamKey.PARAM_FILE_DATA_KEY);
                if (dataObj instanceof byte[]) {
                    fileInfo.setData((byte[]) dataObj);
                } else {
                    fileInfo.setData(dataObj.toString().getBytes());
                }
            }
        } else {
            if (data instanceof FileInfo) {
                fileInfo = (FileInfo) data;
            }
        }

        if (fileInfo.getType() == null || !fileTypes.contains(fileInfo.getType().toUpperCase())) {
            return ComponentDataResult.fail(FileParseErrorEnum.FILE_TYPE_ERROR);
        }

        // 判断文件是否为空
        if (fileInfo.getData() == null || fileInfo.getData().length == 0) {
            return ComponentDataResult.fail(FileParseErrorEnum.FILE_IS_NULL);
        }

        fileInfo.setSize(fileInfo.getData().length);
        return execute(fileInfo);
    }

    /**
     * 执行
     *
     * @param fileInfo 文件信息
     * @return 结果
     */
    protected abstract ComponentDataResult execute(FileInfo fileInfo);

    /**
     * 文件上传异常枚举
     */
    private enum FileParseErrorEnum implements IComponentError {
        FILE_IS_NULL("上传文件为空", "上传文件为空"),
        FILE_TYPE_ERROR("非法的文件类型", "非法的文件类型");

        private String msg;

        private String showMsg;

        FileParseErrorEnum(String msg, String showMsg) {
            this.msg = msg;
            this.showMsg = showMsg;
        }

        @Override
        public String getMsg() {
            return this.msg;
        }

        @Override
        public String getShowMsg() {
            return this.showMsg;
        }
    }
}
