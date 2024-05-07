package com.parch.combine.core.ui.settings.builder;

import com.parch.combine.core.common.settings.annotations.Invalid;
import com.parch.combine.core.common.settings.builder.CommonObjectSettingBuilder;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.settings.annotations.PageElement;
import com.parch.combine.core.ui.settings.annotations.PageElementDesc;
import com.parch.combine.core.ui.settings.config.PageElementSetting;

import java.util.ArrayList;
import java.util.Arrays;

public class PageElementSettingBuilder {

    @SuppressWarnings("unchecked")
    public static PageElementSetting build(String scope, Class<?> pageElementClass) {
        PageElement annotation = pageElementClass.getAnnotation(PageElement.class);
        if (annotation == null || pageElementClass.getAnnotation(Invalid.class) != null) {
            return null;
        }

        // 组件
        PageElementSetting setting = new PageElementSetting();
        setting.setOrder(annotation.order());
        setting.setKey(scope + "." + annotation.key());
        setting.setName(annotation.name());
        setting.setDesc(new ArrayList<>());
        setting.setElementClass((Class<? extends ElementConfig<?, ?>>) pageElementClass);

        PageElementDesc descAnnotation = pageElementClass.getAnnotation(PageElementDesc.class);
        if (descAnnotation != null) {
            setting.getDesc().addAll(Arrays.asList(descAnnotation.value()));
        }

//        // 逻辑配置
//        componentSetting.setLogicConfig(PropertySettingBuilder.build(scope, componentAnnotation.logicConfigClass()));
//
//        // 初始化配置
//        Class<? extends InitConfig> initConfigClass = componentAnnotation.initConfigClass();
//        if (initConfigClass.getAnnotation(Invalid.class) == null) {
//            componentSetting.setInitConfig(PropertySettingBuilder.build(scope, initConfigClass));
//        }

        // 公共对象
        setting.setCommonObjects(CommonObjectSettingBuilder.get(scope));

        return setting;
    }
}
