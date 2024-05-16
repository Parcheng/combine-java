package com.parch.combine.core.ui.tools;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.base.SubConfig;
import com.parch.combine.core.ui.base.element.SubElement;
import com.parch.combine.core.ui.base.trigger.Trigger;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.handler.CombineManagerHandler;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import org.apache.commons.collections4.IterableUtils;

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
        parseAndResetFields(config, config.getClass(), result, combineManager);
        return result;
    }

    @SuppressWarnings("unchecked")
    private static void parseAndResetFields(Object config, Class<?> configClass, ResultVO result, CombineManager manager) throws IllegalAccessException {
        if (config == null) {
            return;
        }

        Class<?> superclass = configClass.getSuperclass();
        if (superclass != null && superclass != Object.class) {
            parseAndResetFields(config, superclass, result, manager);
        }

        java.lang.reflect.Field[] fieldArr = configClass.getDeclaredFields();
        for (java.lang.reflect.Field field : fieldArr) {
            field.setAccessible(true);

            Object value = field.get(config);
            if (value == null) {
                continue;
            }

            SubElement subElementAnnotation = field.getAnnotation(SubElement.class);
            if (subElementAnnotation != null) {
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

                continue;
            }

            Trigger triggerAnnotation = field.getAnnotation(Trigger.class);
            if (triggerAnnotation != null) {
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

                continue;
            }

            if (value instanceof Iterable) {
                Iterable<?> iterableValue = (Iterable<?>) value;
                if (!CheckEmptyUtil.isEmpty(iterableValue)) {
                    for (Object item : iterableValue) {
                        if (item == null) {
                            continue;
                        }

                        PageElement pageElementAnnotation = item.getClass().getAnnotation(PageElement.class);
                        SubConfig subConfigAnnotation = item.getClass().getAnnotation(SubConfig.class);
                        if (subConfigAnnotation != null || pageElementAnnotation != null) {
                            parseAndResetFields(item, item.getClass(), result, manager);
                        }
                    }
                }
            } else {
                PageElement pageElementAnnotation = value.getClass().getAnnotation(PageElement.class);
                SubConfig subConfigAnnotation = value.getClass().getAnnotation(SubConfig.class);
                if (subConfigAnnotation != null || pageElementAnnotation != null) {
                    parseAndResetFields(value, value.getClass(), result, manager);
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
