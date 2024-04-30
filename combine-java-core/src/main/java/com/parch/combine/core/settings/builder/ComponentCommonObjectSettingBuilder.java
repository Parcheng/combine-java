package com.parch.combine.core.settings.builder;

import com.parch.combine.core.settings.annotations.ComponentCommonObject;
import com.parch.combine.core.settings.annotations.Invalid;
import com.parch.combine.core.settings.config.*;

import java.util.*;

public class ComponentCommonObjectSettingBuilder {

    private static Map<String, Map<String, List<ComponentCommonObjectSetting>>> CACHE = new HashMap<>();

    public static void loads(String scope, Collection<Class<?>> objClasses) {
        Map<String, List<ComponentCommonObjectSetting>> commonObjectSettingScopeMap = CACHE.computeIfAbsent(scope, k -> new HashMap<>(8));

        for (Class<?> clazz : objClasses) {
            ComponentCommonObject componentCommonObjectAnnotation = clazz.getAnnotation(ComponentCommonObject.class);
            if (componentCommonObjectAnnotation == null || clazz.getAnnotation(Invalid.class) != null) {
                continue;
            }

            ComponentCommonObjectSetting commonObjectSetting = new ComponentCommonObjectSetting();
            commonObjectSetting.setKey(componentCommonObjectAnnotation.key());
            commonObjectSetting.setName(componentCommonObjectAnnotation.name());
            commonObjectSetting.setOrder(componentCommonObjectAnnotation.order());
            commonObjectSetting.setClassType(clazz);
            commonObjectSetting.setDesc(new ArrayList<>(Arrays.asList(componentCommonObjectAnnotation.desc())));

            List<ComponentCommonObjectSetting> commonObjectSettings = commonObjectSettingScopeMap.computeIfAbsent(commonObjectSetting.getKey(), k -> new ArrayList<>());
            commonObjectSettings.add(commonObjectSetting);
        }

        for (List<ComponentCommonObjectSetting> componentCommonObjectSettings : commonObjectSettingScopeMap.values()) {
            for (ComponentCommonObjectSetting componentCommonObjectSetting : componentCommonObjectSettings) {
                componentCommonObjectSetting.setProperties(ComponentPropertySettingBuilder.build(scope, componentCommonObjectSetting.thisClassType()));
            }
        }
    }

    public static List<ComponentCommonObjectSetting> get(String scope) {
        Map<String, List<ComponentCommonObjectSetting>> commonObjectSettingScopeMap = CACHE.get(scope);
        if (commonObjectSettingScopeMap == null) {
            return null;
        }

        List<ComponentCommonObjectSetting> result = new ArrayList<>();
        for (List<ComponentCommonObjectSetting> commonObjectSettingScopes : commonObjectSettingScopeMap.values()) {
            result.addAll(commonObjectSettingScopes);
        }

        result.sort(Comparator.comparingInt(ComponentCommonObjectSetting::getOrder));
        return result;
    }

    public static List<ComponentCommonObjectSetting> get(String scope, String key) {
        Map<String, List<ComponentCommonObjectSetting>> commonObjectSettingScopeMap = CACHE.get(scope);
        if (commonObjectSettingScopeMap == null) {
            return null;
        }

        return commonObjectSettingScopeMap.get(key);
    }

    public static void clear(String scope) {
        CACHE.remove(scope);
    }
}
