package com.parch.combine.component.file.read;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.base.InitConfig;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.IComponentError;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.component.file.FileParamKey;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.Map;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileReadComponent<T extends InitConfig, R extends FileReadLogicConfig> extends AbsComponent<T, R> {

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public FileReadComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    /**
     * 初始化公共方法
     *
     * @param logicConfig 逻辑配置
     */
    protected void init(FileDataReadLogicConfig logicConfig) {
        if (logicConfig.getStartRow() == null) {
            logicConfig.setStartRow(1);
        }
        if (logicConfig.getStartIndex() == null) {
            logicConfig.setStartIndex(1);
        }
        if (logicConfig.getStartSkipCount() == null) {
            logicConfig.setStartSkipCount(0);
        }
        if (logicConfig.getEndDiscardCount() == null) {
            logicConfig.setEndDiscardCount(0);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public final DataResult execute() {
        FileInfo fileInfo = new FileInfo();;

        // 根据Source获取文件信息（Source为空表示从接口上传文件）
        FileReadLogicConfig readLogicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(readLogicConfig.getSource())) {
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
            Object fileInfoObj = DataVariableHelper.parseValue(readLogicConfig.getSource(), true);
            if (fileInfoObj instanceof FileInfo) {
                fileInfo = (FileInfo) fileInfoObj;
            }
        }

        // 判断文件是否为空
        if (fileInfo.getData() == null || fileInfo.getData().length == 0) {
            return DataResult.fail(FileReadErrorEnum.FILE_IS_NULL);
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
    protected abstract DataResult execute(FileInfo fileInfo);

    /**
     * 文件上传异常枚举
     */
    private enum FileReadErrorEnum implements IComponentError {
        FILE_IS_NULL("上传文件为空", "上传文件为空");

        private String msg;

        private String showMsg;

        FileReadErrorEnum(String msg, String showMsg) {
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
