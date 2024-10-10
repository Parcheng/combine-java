package com.parch.combine.html.common.cache.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.html.common.canstant.ConfigFiledConstant;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CacheModelBuilder {

    private final String id;
    private final Map<String, Object> configMap;
    private final List<BaseCacheModel.SubCacheModel> subCaches;
    private final CombineManager manager;

    public CacheModelBuilder(String id, String type, Object interfaceObj, CombineManager manager) {
        this.id = id;
        this.subCaches = new ArrayList<>();
        this.manager = manager;
        this.configMap = parse(interfaceObj);
        configMap.put(ConfigFiledConstant.ID, id);
        configMap.put(ConfigFiledConstant.TYPE, type);
    }

    private Map<String, Object> parse(Object interfaceObj) {
        if (interfaceObj == null) {
            return null;
        }

        Map<String, Object> config = new HashMap<>();
        Method[] methods = interfaceObj.getClass().getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            Object value = null;
            try {
                value = method.invoke(interfaceObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                PrintHelper.printComponentError(e);
            }

            if (value == null) {
                continue;
            }

            if (field.type() == FieldTypeEnum.CONFIG) {
                value = this.parse(value);
            } else if (field.type() != FieldTypeEnum.COMPONENT) {
                this.parseSubCache(field.isArray() ? (String[]) value : new String[]{(String) value});
            }

            if (value != null) {
                config.put(method.getName(), value);
            }
        }

        return config;
    }

    private void parseSubCache(String[] componentIds) {
        for (String componentId : componentIds) {
            AbstractComponent<?,?> component = manager.getComponent().getComponent(componentId);
            if (component instanceof IRegisterComponent) {
                ConfigTypeEnum type = ((IRegisterComponent) component).getConfigType();
                if (type == ConfigTypeEnum.TRIGGER || type == ConfigTypeEnum.ELEMENT) {
                    BaseCacheModel.SubCacheModel sub = new BaseCacheModel.SubCacheModel();
                    sub.componentId = componentId;
                    sub.type = type;
                    subCaches.add(sub);
                }
            }
        }
    }

    public void addProperty(String key, Object value) {
        configMap.put(key, value);
    }

    public <T extends BaseCacheModel> T build(T model) {
        model.id = id;
        model.json = JsonUtil.serialize(configMap);
        model.subCaches = subCaches;
        return model;
    }
}
