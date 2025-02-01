package com.parch.combine.html.old.base.element.core;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.ConfigErrorEnum;
import com.parch.combine.html.common.cache.base.IRegisterComponent;
import com.parch.combine.html.common.cache.ElementConfigCache;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

public abstract class AbstractElementComponent<L extends ILogicConfig> extends AbstractComponent<IInvalidInitConfig, L> implements IRegisterComponent {

    protected String type;
    protected String jsLibName;
    protected String cssLibName;
    protected String templateLibName;

    /**
     * 构造器
     */
    public AbstractElementComponent(Class<L> logicClass, String type) {
        this(logicClass, type, null, null, null);
    }

    /**
     * 构造器
     */
    public AbstractElementComponent(Class<L> logicClass, String type, String jsLibName, String cssLibName, String templateLibName) {
        super(IInvalidInitConfig.class, logicClass);
        this.type = type;
        this.jsLibName = jsLibName;
        this.cssLibName = cssLibName;
        this.templateLibName = templateLibName;
    }

    @Override
    protected ComponentDataResult execute() {
        L logicConfig = getLogicConfig();
        ElementConfig config = getConfig();
        if (config == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.CONFIG_IS_NULL);
        }

        ElementConfigCache.INSTANCE.register(logicConfig.id(), type, jsLibName, cssLibName, templateLibName, config, manager);
        return ComponentDataResult.success(true);
    }

    protected abstract ElementConfig getConfig();

    @Override
    public ConfigTypeEnum getConfigType() {
        return ConfigTypeEnum.ELEMENT;
    }
}
