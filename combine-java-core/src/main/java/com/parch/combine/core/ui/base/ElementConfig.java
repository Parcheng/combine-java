package com.parch.combine.core.ui.base;


import com.parch.combine.core.common.util.TypeConversionUtil;

import java.util.Map;

public abstract class ElementConfig<L extends ElementLogicConfig, T extends ElementTemplateConfig> {

    private Class<L> logicConfigClass;

    private Class<T> templateConfigClass;

    public ElementConfig(Class<L> logicConfigClass, Class<T> templateConfigClass) {
        this.logicConfigClass = logicConfigClass;
        this.templateConfigClass = templateConfigClass;
    }

    public L buildLogicConfig(Map<String, Object> config) {
        if (config == null) {
            return build(logicConfigClass);
        }
        return TypeConversionUtil.parseJava(config, logicConfigClass);
    }

    public T buildTemplateConfig(Map<String, Object> config) {
        if (config == null) {
            return build(templateConfigClass);
        }
        return TypeConversionUtil.parseJava(config, templateConfigClass);
    }

    private static <M> M build(Class<M> mClass) {
        try {
            return mClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
