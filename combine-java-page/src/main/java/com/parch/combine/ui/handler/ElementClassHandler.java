package com.parch.combine.ui.handler;

import com.parch.combine.ui.base.ElementConfig;
import com.parch.combine.ui.base.TemplateConfig;

import java.util.HashMap;
import java.util.Map;

public class ElementClassHandler {

    private final static Map<String, ClassConfig> ELEMENT_CLASS_MAP = new HashMap<>(16);

    public synchronized static void register(String key, Class<? extends ElementConfig> elementConfigClass, Class<? extends TemplateConfig> templateConfigClass) {
        ELEMENT_CLASS_MAP.put(key, new ClassConfig(elementConfigClass, templateConfigClass));
    }

    public static ClassConfig get(String key) {
        return ELEMENT_CLASS_MAP.get(key);
    }

    public static class ClassConfig {

        private Class<? extends ElementConfig> elementConfigClass;

        private Class<? extends TemplateConfig> templateConfigClass;

        public ClassConfig(Class<? extends ElementConfig> elementConfigClass, Class<? extends TemplateConfig> templateConfigClass) {
            this.elementConfigClass = elementConfigClass;
            this.templateConfigClass = templateConfigClass;
        }

        public Class<? extends ElementConfig> getElementConfigClass() {
            return elementConfigClass;
        }

        public void setElementConfigClass(Class<? extends ElementConfig> elementConfigClass) {
            this.elementConfigClass = elementConfigClass;
        }

        public Class<? extends TemplateConfig> getTemplateConfigClass() {
            return templateConfigClass;
        }

        public void setTemplateConfigClass(Class<? extends TemplateConfig> templateConfigClass) {
            this.templateConfigClass = templateConfigClass;
        }
    }
}
