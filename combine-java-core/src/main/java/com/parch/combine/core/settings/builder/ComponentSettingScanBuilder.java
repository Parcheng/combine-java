package com.parch.combine.core.settings.builder;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.PackageScanUtil;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ComponentSettingScanBuilder {

    public static List<ComponentSetting> scanAndBuild(String scope, Class<?> baseClass) {
        return scanAndBuild(scope, baseClass.getPackage().getName());
    }

    public static List<ComponentSetting> scanAndBuild(String scope, String packagePath) {
        Set<Class<?>> packageClasses = PackageScanUtil.scan(packagePath);
        if (CheckEmptyUtil.isEmpty(packageClasses)) {
            return null;
        }

        ComponentCommonObjectSettingBuilder.loads(scope, packageClasses);

        List<ComponentSetting> componentSettings = new ArrayList<>();
        for (Class<?> clazz : packageClasses) {
            ComponentSetting componentSetting = ComponentSettingBuilder.build(scope, clazz);
            if (componentSetting != null) {
                componentSettings.add(componentSetting);
            }
        }

        ComponentCommonObjectSettingBuilder.clear(scope);
        componentSettings.sort(Comparator.comparing(ComponentSetting::getOrder));
        return componentSettings;
    }
}
