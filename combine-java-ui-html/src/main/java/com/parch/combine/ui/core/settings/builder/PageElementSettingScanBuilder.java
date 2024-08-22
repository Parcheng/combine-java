package com.parch.combine.ui.core.settings.builder;

import com.parch.combine.core.common.settings.builder.CommonObjectSettingBuilder;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PackageScanUtil;
import com.parch.combine.ui.core.base.DomConfig;
import com.parch.combine.ui.core.base.dataload.ApiDataLoadConfig;
import com.parch.combine.ui.core.base.dataload.DataLoadTypeEnum;
import com.parch.combine.ui.core.base.dataload.FileDataLoadConfig;
import com.parch.combine.ui.core.base.dataload.FlowDataLoadConfig;
import com.parch.combine.ui.core.settings.config.PageElementSetting;
import com.parch.combine.ui.core.base.trigger.*;

import java.util.*;

public class PageElementSettingScanBuilder {

    public static List<PageElementSetting> scanAndBuild(String scope, Class<?> baseClass) {
        return scanAndBuild(scope, baseClass.getPackage().getName());
    }

    public static List<PageElementSetting> scanAndBuild(String scope, String packagePath) {
        Set<Class<?>> packageClasses = PackageScanUtil.scan(packagePath);
        if (CheckEmptyUtil.isEmpty(packageClasses)) {
            return null;
        }

        // buildCommonObjectSetting(scope);
        // CommonObjectSettingBuilder.loads(scope, packageClasses);

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

    private static void buildCommonObjectSetting(String scope) {
        CommonObjectSettingBuilder.load(scope, DomConfig.class);
        CommonObjectSettingBuilder.load(scope, ApiDataLoadConfig.class);
        CommonObjectSettingBuilder.load(scope, DataLoadTypeEnum.class);
        CommonObjectSettingBuilder.load(scope, FileDataLoadConfig.class);
        CommonObjectSettingBuilder.load(scope, FlowDataLoadConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerCallFlowConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerCallFuncConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerCallUrlConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerCustomConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerLoadConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerLoadDataConfig.class);
        CommonObjectSettingBuilder.load(scope, TriggerSkipConfig.class);
    }
}
