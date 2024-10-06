package com.parch.combine.html.base.dataload.api;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.AbstractDataLoadComponent;
import com.parch.combine.html.base.dataload.DataLoadConfig;
import com.parch.combine.html.base.dataload.DataLoadInitConfig;
import com.parch.combine.html.base.dataload.DataLoadTypeEnum;

@Component(key = "dataload.api", name = "注册外部API加载组件", logicConfigClass = ApiDataLoadLogicConfig.class, initConfigClass = DataLoadInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ApiDataLoadComponent extends AbstractDataLoadComponent<ApiDataLoadLogicConfig> {

    /**
     * 构造器
     */
    public ApiDataLoadComponent() {
        super(ApiDataLoadLogicConfig.class, DataLoadTypeEnum.API);
    }

    @Override
    protected DataLoadConfig getConfig() {
        return getLogicConfig().config();
    }
}
