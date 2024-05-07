package com.parch.combine.core.ui.base.element;


public abstract class ElementConfig<L extends ElementLogicConfig, T extends ElementTemplateConfig> {

    private Class<L> logicConfigClass;

    private Class<T> templateConfigClass;

    public ElementConfig(Class<L> logicConfigClass, Class<T> templateConfigClass) {
        this.logicConfigClass = logicConfigClass;
        this.templateConfigClass = templateConfigClass;
    }


}
