package com.parch.combine.core.ui.tools;

import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.handler.CombineManagerHandler;
import com.parch.combine.core.ui.manager.CombineManager;

import java.util.*;

public class SubConfigHelper {

    public static ResultVO loadAndReset(String scopeKey, Object config) throws IllegalAccessException{
        ResultVO result = new ResultVO();
        if (config == null) {
            return result;
        }
        result.setElementIds(new ArrayList<>());
        result.setTriggerIds(new ArrayList<>());

        CombineManager combineManager = CombineManagerHandler.get(scopeKey);
        Set<Class<?>> parsedClass = new HashSet<>();
        parseAndResetFields(config, config.getClass(), parsedClass, result, combineManager);
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
                if (value != null) {
                    if (value instanceof Iterable) {
                        List<String> elementIds = new ArrayList<>();
                        for (Object item : (Iterable<?>) value) {
                            if (item instanceof Map) {
                                String id = manager.getPageElement().load((Map<String, Object>) item);
                                result.getElementIds().add(id);
                                field.set(config, id);
                            } else {
                                result.getElementIds().add(item == null ? null : item.toString());
                            }
                        }
                        field.set(config, elementIds);
                    } else if (value instanceof Map) {
                        String id = manager.getPageElement().load((Map<String, Object>) value);
                        result.getElementIds().add(id);
                        field.set(config, id);
                    } else {
                        result.getElementIds().add(value.toString());
                    }
                }
            }

            Trigger triggerAnnotation = field.getAnnotation(Trigger.class);
            if (triggerAnnotation != null) {
                Object value = field.get(config);
                if (value != null) {
                    if (value instanceof Iterable) {
                        List<String> triggerIds = new ArrayList<>();
                        for (Object item : (Iterable<?>) value) {
                            if (item instanceof Map) {
                                String id = manager.getTrigger().load((Map<String, Object>) item);
                                result.getTriggerIds().add(id);
                                triggerIds.add(id);
                            } else {
                                result.getTriggerIds().add(item == null ? null : item.toString());
                                triggerIds.add(item == null ? null : item.toString());
                            }
                        }
                        field.set(config, triggerIds);
                    } else if (value instanceof Map) {
                        String id = manager.getTrigger().load((Map<String, Object>) value);
                        result.getTriggerIds().add(id);
                        field.set(config, id);
                    } else {
                        result.getTriggerIds().add(value.toString());
                    }
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
