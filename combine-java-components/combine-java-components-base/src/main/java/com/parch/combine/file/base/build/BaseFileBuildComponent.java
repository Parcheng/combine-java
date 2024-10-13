package com.parch.combine.file.base.build;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.util.UUID;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class BaseFileBuildComponent<T extends IInitConfig, R extends BaseFileBuildLogicConfig> extends AbstractComponent<T, R> {

    public BaseFileBuildComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public final ComponentDataResult execute() {
        BaseFileBuildLogicConfig logicConfig = getLogicConfig();

        // 根据数据类型，调用不通的处理方法
        FileBuildInfo buildInfo = build();

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
     * 构建文件
     *
     * @return 文件构建信息
     */
    protected abstract FileBuildInfo build();

    /**
     * 文件上传异常枚举
     */
    protected enum FileBuildErrorEnum implements IComponentError {
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
