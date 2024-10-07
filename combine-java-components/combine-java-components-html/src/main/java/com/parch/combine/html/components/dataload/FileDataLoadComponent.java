package com.parch.combine.html.components.dataload;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.AbstractDataLoadComponent;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.dataload.DataLoadConfig;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.html.base.dataload.DataLoadTypeEnum;
import com.parch.combine.html.base.dataload.FileDataLoadLogicConfig;

@Component(key = "dataload.file.register", order = 100, name = "文件数据加载配置注册组件", logicConfigClass = FileDataLoadLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class FileDataLoadComponent extends AbstractDataLoadComponent<ApiDataLoadLogicConfig> {

    /**
     * 构造器
     */
    public FileDataLoadComponent() {
        super(ApiDataLoadLogicConfig.class, DataLoadTypeEnum.FILE);
    }

    @Override
    protected DataLoadConfig getConfig() {
        return getLogicConfig().config();
    }
}
