package com.parch.combine.file.base.input;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.vo.DataResult;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileInputComponent<T extends IInitConfig, R extends ILogicConfig> extends AbsComponent<T, R> {

    public FileInputComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public final DataResult execute() {
        FileInfo fileInfo = new FileInfo();
        IComponentError error = execute(fileInfo);
        if (error != null) {
            DataResult.fail(error);
        }

        // 返回结果
        return DataResult.success(fileInfo);
    }

    /**
     * 执行
     *
     * @param fileInfo 文件信息
     * @return 结果
     */
    protected abstract IComponentError execute(FileInfo fileInfo);
}
