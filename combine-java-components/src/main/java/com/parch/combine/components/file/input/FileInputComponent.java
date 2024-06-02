package com.parch.combine.components.file.input;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.old.InitConfig;
import com.parch.combine.core.component.base.old.LogicConfig;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.vo.DataResult;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileInputComponent<T extends IInitConfig, R extends ILogicConfig> extends AbsComponent<T, R> {

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
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

    /**
     * 文件异常信息
     */
    public enum FileInputErrorEnum implements IComponentError {

        DATA_IS_NULL("数据不存在", "数据异常"),
        ;

        private String msg;

        private String showMsg;

        FileInputErrorEnum(String msg, String showMsg) {
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
