package com.parch.combine.file.base.build;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileBuildComponent<T extends IInitConfig, R extends FileBuildLogicConfig> extends AbstractComponent<T, R> {

    public FileBuildComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final ComponentDataResult execute() {
        FileBuildLogicConfig logicConfig = getLogicConfig();
        Object data = logicConfig.source();
        if (data == null) {
            return ComponentDataResult.fail(FileBuildErrorEnum.DATA_IS_NULL);
        }

        // 根据数据类型，调用不通的处理方法
        FileBuildInfo buildInfo;
        if (data instanceof List) {
            List<?> list = (List<?>) data;
            if (CheckEmptyUtil.isEmpty(list)) {
                return ComponentDataResult.fail(FileBuildErrorEnum.DATA_IS_NULL);
            }

            Object firstItem = list.get(0);
            if (firstItem instanceof Map) {
                buildInfo = buildFromObjectList((List<Map<String, Object>>) data);
            } else if (firstItem instanceof String) {
                buildInfo = buildFromTextList((List<String>) data);
            } else {
                return ComponentDataResult.fail(FileBuildErrorEnum.DATA_TYPE_ERROR);
            }
        } else if (data instanceof Map) {
            buildInfo = buildFromObject((Map<String, Object>) data);
        } else if (data instanceof byte[]) {
            buildInfo = buildFromByte((byte[]) data);
        } else if (data instanceof String) {
            buildInfo = buildFromText(data.toString());
        } else {
            return ComponentDataResult.fail(FileBuildErrorEnum.DATA_TYPE_ERROR);
        }

        // 检验构建后的文件信息
        if (buildInfo == null) {
            return ComponentDataResult.fail(FileBuildErrorEnum.BUILD_IS_FAIL);
        }
        if (buildInfo.getError() != null) {
            return ComponentDataResult.fail(buildInfo.getError());
        }
        if (buildInfo.getData() == null) {
            return ComponentDataResult.fail(FileBuildErrorEnum.BUILD_IS_FAIL);
        }

        // 文件名为空则生成随机文件名
        String fileName = logicConfig.fileName();
        if (CheckEmptyUtil.isEmpty(fileName)) {
            fileName = UUID.randomUUID().toString();
        }

        // 构建文件信息对象返回
        FileInfo fileInfo = new FileInfo();
        fileInfo.setName(fileName);
        fileInfo.setType(buildInfo.getType());
        fileInfo.setData(buildInfo.getData());
        fileInfo.setSize(buildInfo.getData().length);
        return ComponentDataResult.success(fileInfo);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromObject(Map<String, Object> data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param dataList 数据集合
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromObjectList(List<Map<String, Object>> dataList) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromByte(byte[] data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromText(String data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param dataList 数据集合
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromTextList(List<String> dataList) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 文件上传异常枚举
     */
    private enum FileBuildErrorEnum implements IComponentError {
        DATA_IS_NULL("数据为空","数据为空"),
        BUILD_IS_FAIL("文件创建失败","文件创建失败"),
        DATA_TYPE_ERROR("数据类型不合规","数据类型不合规");

        private String msg;
        private String showMsg;

        FileBuildErrorEnum(String msg, String showMsg) {
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

    /**
     * 文件构建信息
     */
    protected static class FileBuildInfo {
        private String type;
        private byte[] data;
        private IComponentError error;

        public FileBuildInfo(String type, byte[] data) {
            this.type = type;
            this.data = data;
        }

        public FileBuildInfo(IComponentError error) {
            this.error = error;
        }

        public String getType() {
            return type;
        }

        public byte[] getData() {
            return data;
        }

        public IComponentError getError() {
            return error;
        }
    }
}
