package com.parch.combine.html.common.cache.base;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
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

    @SuppressWarnings("unchecked")
    public CacheModelBuilder(String id, String type, Object interfaceObj, CombineManager manager) {
        this.id = id;
        this.subCaches = new ArrayList<>();
        this.manager = manager;

        this.configMap = (Map<String, Object>) parse(interfaceObj);
        configMap.put(ConfigFiledConstant.ID, id);
        configMap.put(ConfigFiledConstant.TYPE, type);
    }

    private Object parse(Object configObj) {
        if (configObj == null) {
            return null;
        }

        List<Map<String, Object>> configs = new ArrayList<>();
        Class<?> objClass = configObj.getClass();
        boolean dataIsArray = objClass.isArray();
        Object[] interfaceObjects = dataIsArray ? (Object[]) configObj : new Object[]{ configObj };

        for (Object interfaceObj : interfaceObjects) {
            Map<String, Object> config = new HashMap<>();

            Class<?>[] interfaces = interfaceObj.getClass().getInterfaces();
            for (Class<?> interfaceClass : interfaces) {
                Method[] methods = interfaceClass.getMethods();
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
                    } else if (field.type() == FieldTypeEnum.COMPONENT) {
                        this.parseSubCache(field.isArray() ? (String[]) value : new String[]{(String) value});
                    }

                    if (value != null) {
                        config.put(method.getName(), value);
                    }
                }
            }

            configs.add(config);
        }

        return dataIsArray ? configs : (CheckEmptyUtil.isEmpty(configs) ? null : configs.get(0));
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
        model.json = JsonUtil.obj2String(configMap);
        model.subCaches = subCaches;
        return model;
    }
}
