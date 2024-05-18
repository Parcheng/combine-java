package com.parch.combine.core.ui.settings.builder;

import com.parch.combine.core.common.settings.builder.CommonObjectSettingBuilder;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PackageScanUtil;
import com.parch.combine.core.ui.base.DomConfig;
import com.parch.combine.core.ui.base.dataload.ApiDataLoadConfig;
import com.parch.combine.core.ui.base.dataload.DataLoadTypeEnum;
import com.parch.combine.core.ui.base.dataload.FileDataLoadConfig;
import com.parch.combine.core.ui.base.dataload.FlowDataLoadConfig;
import com.parch.combine.core.ui.base.trigger.*;
import com.parch.combine.core.ui.settings.config.PageElementSetting;

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

        buildCommonObjectSetting(scope);
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

    private static void buildCommonObjectSetting(String scope) {
        List<Class<?>> systemCommonObject = new ArrayList<>();
        systemCommonObject.add(DomConfig.class);
        systemCommonObject.add(ApiDataLoadConfig.class);
        systemCommonObject.add(DataLoadTypeEnum.class);
        systemCommonObject.add(FileDataLoadConfig.class);
        systemCommonObject.add(FlowDataLoadConfig.class);
        systemCommonObject.add(TriggerCallFlowConfig.class);
        systemCommonObject.add(TriggerCallFuncConfig.class);
        systemCommonObject.add(TriggerCallUrlConfig.class);
        systemCommonObject.add(TriggerCustomConfig.class);
        systemCommonObject.add(TriggerLoadConfig.class);
        systemCommonObject.add(TriggerLoadDataConfig.class);
        systemCommonObject.add(TriggerSkipConfig.class);
        CommonObjectSettingBuilder.loads(scope, systemCommonObject);
    }
}
