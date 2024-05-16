package com.parch.combine.core.common.settings.builder;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Invalid;
import com.parch.combine.core.common.settings.config.CommonObjectSetting;

import java.util.*;

public class CommonObjectSettingBuilder {

    private static Map<String, Map<String, List<CommonObjectSetting>>> CACHE = new HashMap<>();

    public static void loads(String scope, Collection<Class<?>> objClasses) {
        Map<String, List<CommonObjectSetting>> commonObjectSettingScopeMap = CACHE.computeIfAbsent(scope, k -> new HashMap<>(8));

        for (Class<?> clazz : objClasses) {
            CommonObject componentCommonObjectAnnotation = clazz.getAnnotation(CommonObject.class);
            if (componentCommonObjectAnnotation == null || clazz.getAnnotation(Invalid.class) != null) {
                continue;
            }

            CommonObjectSetting commonObjectSetting = new CommonObjectSetting();
            commonObjectSetting.setKey(componentCommonObjectAnnotation.key());
            commonObjectSetting.setName(componentCommonObjectAnnotation.name());
            commonObjectSetting.setOrder(componentCommonObjectAnnotation.order());
            commonObjectSetting.setClassType(clazz);
            commonObjectSetting.setDesc(new ArrayList<>(Arrays.asList(componentCommonObjectAnnotation.desc())));

            List<CommonObjectSetting> commonObjectSettings = commonObjectSettingScopeMap.computeIfAbsent(commonObjectSetting.getKey(), k -> new ArrayList<>());
            commonObjectSettings.add(commonObjectSetting);
        }

        for (List<CommonObjectSetting> componentCommonObjectSettings : commonObjectSettingScopeMap.values()) {
            for (CommonObjectSetting componentCommonObjectSetting : componentCommonObjectSettings) {
                componentCommonObjectSetting.setProperties(PropertySettingBuilder.build(scope, componentCommonObjectSetting.thisClassType()));
            }
        }
    }

    public static List<CommonObjectSetting> get(String scope) {
        Map<String, List<CommonObjectSetting>> commonObjectSettingScopeMap = CACHE.get(scope);
        if (commonObjectSettingScopeMap == null) {
            return null;
        }

        List<CommonObjectSetting> result = new ArrayList<>();
        for (List<CommonObjectSetting> commonObjectSettingScopes : commonObjectSettingScopeMap.values()) {
            result.addAll(commonObjectSettingScopes);
        }

        result.sort(Comparator.comparingInt(CommonObjectSetting::getOrder));
        return result;
    }

    public static List<CommonObjectSetting> get(String scope, String key) {
        Map<String, List<CommonObjectSetting>> commonObjectSettingScopeMap = CACHE.get(scope);
        if (commonObjectSettingScopeMap == null) {
            return null;
        }

        return commonObjectSettingScopeMap.get(key);
    }

    public static void clear(String scope) {
        CACHE.remove(scope);
    }
}
