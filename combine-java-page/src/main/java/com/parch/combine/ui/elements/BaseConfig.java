package com.parch.combine.ui.elements;

import com.parch.combine.ui.base.ElementConfig;
import com.parch.combine.ui.base.TemplateConfig;

public class BaseConfig<E extends ElementConfig, T extends TemplateConfig> {

    private Class<E> elementConfigClass;

    private Class<T> templateConfigClass;

    public BaseConfig(Class<E> elementConfigClass, Class<T> templateConfigClass) {
        this.elementConfigClass = elementConfigClass;
        this.templateConfigClass = templateConfigClass;
    }


}
