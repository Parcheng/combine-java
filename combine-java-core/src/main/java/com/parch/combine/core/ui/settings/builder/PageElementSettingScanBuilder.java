package com.parch.combine.core.ui.settings.builder;

import com.parch.combine.core.common.settings.builder.CommonObjectSettingBuilder;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PackageScanUtil;
import com.parch.combine.core.ui.settings.config.PageElementSetting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class PageElementSettingScanBuilder {

    public static List<PageElementSetting> scanAndBuild(String scope, Class<?> baseClass) {
        return scanAndBuild(scope, baseClass.getPackage().getName());
    }

    public static List<PageElementSetting> scanAndBuild(String scope, String packagePath) {
        Set<Class<?>> packageClasses = PackageScanUtil.scan(packagePath);
        if (CheckEmptyUtil.isEmpty(packageClasses)) {
            return null;
        }

        CommonObjectSettingBuilder.loads(scope, packageClasses);

        List<PageElementSetting> settings = new ArrayList<>();
        for (Class<?> clazz : packageClasses) {
            PageElementSetting setting = PageElementSettingBuilder.build(scope, clazz);
            if (setting != null) {
                settings.add(setting);
            }
        }

        CommonObjectSettingBuilder.clear(scope);
        settings.sort(Comparator.comparing(PageElementSetting::getOrder));
        return settings;
    }
}
