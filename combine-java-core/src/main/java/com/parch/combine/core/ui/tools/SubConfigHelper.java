package com.parch.combine.core.ui.tools;

import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.manager.CombineManager;

import java.util.*;

public class SubConfigHelper {

    public static ResultVO loadAndReset(Object config) throws IllegalAccessException{
        ResultVO result = new ResultVO();
        if (config == null) {
            return result;
        }

        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();
        result.setElementIds(new ArrayList<>());
        result.setTriggerIds(new ArrayList<>());

        Set<Class<?>> parsedClass = new HashSet<>();
        parseAndResetFields(config, config.getClass(), parsedClass, result, context.getManager());
        return result;
    }

    @SuppressWarnings("unchecked")
    private static void parseAndResetFields(Object config, Class<?> configClass, Set<Class<?>> parsedClass, ResultVO result, CombineManager manager) throws IllegalAccessException {
        // 防循环引用
        if (parsedClass.contains(configClass)) {
            return;
        }
        parsedClass.add(configClass);

        Class<?> superclass = configClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            parseAndResetFields(config, superclass, parsedClass, result, manager);
        }

        java.lang.reflect.Field[] fieldArr = configClass.getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArr) {
            field.setAccessible(true);
            SubElement subElementAnnotation = field.getAnnotation(SubElement.class);
            if (subElementAnnotation != null) {
                Object value = field.get(config);
                if (value instanceof Map) {
                    String id = manager.getPageElement().load((Map<String, Object>) value);
                    result.getElementIds().add(id);
                    field.set(config, id);
                } else {
                    result.getElementIds().add(value.toString());
                }
            }

            Trigger triggerAnnotation = field.getAnnotation(Trigger.class);
            if (triggerAnnotation != null) {
                Object value = field.get(config);
                if (value instanceof Map) {
                    String id = manager.getTrigger().load((Map<String, Object>) value);
                    result.getTriggerIds().add(id);
                    field.set(config, id);
                } else {
                    result.getTriggerIds().add(value.toString());
                }
            }
        }
    }

    public static class ResultVO {

        private List<String> elementIds;

        private List<String> triggerIds;

        public List<String> getElementIds() {
            return elementIds;
        }

        public void setElementIds(List<String> elementIds) {
            this.elementIds = elementIds;
        }

        public List<String> getTriggerIds() {
            return triggerIds;
        }

        public void setTriggerIds(List<String> triggerIds) {
            this.triggerIds = triggerIds;
        }
    }
}
