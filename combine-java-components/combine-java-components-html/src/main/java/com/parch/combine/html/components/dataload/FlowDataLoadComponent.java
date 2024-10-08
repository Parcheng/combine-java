package com.parch.combine.html.components.dataload;

import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.html.base.dataload.core.AbstractDataLoadComponent;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.base.dataload.core.DataLoadConfig;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.html.common.enums.DataLoadTypeEnum;
import com.parch.combine.html.base.dataload.FlowDataLoadLogicConfig;

@Component(key = "dataload.flow.register", order = 100, name = "流程数据加载配置注册组件", logicConfigClass = FlowDataLoadLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class FlowDataLoadComponent extends AbstractDataLoadComponent<ApiDataLoadLogicConfig> {

    /**
     * 构造器
     */
    public FlowDataLoadComponent() {
        super(ApiDataLoadLogicConfig.class, DataLoadTypeEnum.API);
    }

    @Override
    protected DataLoadConfig getConfig() {
        return getLogicConfig().config();
    }
}
