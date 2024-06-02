package com.parch.combine.components.file.output;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.base.old.InitConfig;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.base.FileInfo;

import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件输出组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class FileOutputComponent<T extends IInitConfig, R extends FileOutputLogicConfig> extends AbsComponent<T, R> {

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public FileOutputComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        FileOutputLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig,  "数据来源不能为空"));
        }
        if (logicConfig.getCharset() == null) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "字符集编码不合规"));
        }
        result.addAll(initFileConfig());
        return result;
    }

    public abstract List<String> initFileConfig();

    @Override
    public final DataResult execute() {
        FileOutputLogicConfig logicConfig = getLogicConfig();

        // 获取数据
        Object dataObj = DataVariableHelper.parseValue(logicConfig.getSource(), true);
        if (dataObj == null) {
            return DataResult.fail(FileExportErrorEnum.DATA_IS_NULL);
        }

        // 处理要写入的数据
        FileInfo fileInfo;
        if (dataObj instanceof FileInfo) {
            fileInfo = (FileInfo) dataObj;
            if (fileInfo.getData() == null) {
                return DataResult.fail(FileExportErrorEnum.DATA_IS_NULL);
            }
        } else {
            fileInfo = new FileInfo();
            if (dataObj instanceof byte[]) {
                fileInfo.setData((byte[]) dataObj);
            } else {
                fileInfo.setData(dataObj.toString().getBytes(logicConfig.getCharset()));
            }
        }

        // 重新设置文件长度
        fileInfo.setSize(fileInfo.getData().length);

        // 返回结果
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
     * 文件保存异常信息
     */
    public enum FileExportErrorEnum implements IComponentError {

        DATA_IS_NULL("数据不存在", "数据异常"),
        ;

        private String msg;

        private String showMsg;

        FileExportErrorEnum(String msg, String showMsg) {
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
