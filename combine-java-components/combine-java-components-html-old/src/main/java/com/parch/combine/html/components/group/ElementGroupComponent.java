package com.parch.combine.html.components.group;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.dataload.ApiDataLoadLogicConfig;
import com.parch.combine.html.common.cache.base.IRegisterComponent;
import com.parch.combine.html.common.cache.ElementGroupConfigCache;
import com.parch.combine.html.base.group.ElementGroupLogicConfig;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

@Component(key = "group.register", order = 500, name = "元素组配置注册组件", logicConfigClass = ApiDataLoadLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class ElementGroupComponent extends AbstractComponent<IInvalidInitConfig, ElementGroupLogicConfig> implements IRegisterComponent {

    /**
     * 构造器
     */
    public ElementGroupComponent() {
        super(IInvalidInitConfig.class, ElementGroupLogicConfig.class);
    }

    @Override
    protected ComponentDataResult execute() {
        ElementGroupLogicConfig logicConfig = getLogicConfig();
        ElementGroupConfigCache.INSTANCE.register(logicConfig.id(), logicConfig.elements());
        return ComponentDataResult.success(true);
    }

    @Override
    public ConfigTypeEnum getConfigType() {
        return ConfigTypeEnum.ELEMENT_GROUP;
    }
}
