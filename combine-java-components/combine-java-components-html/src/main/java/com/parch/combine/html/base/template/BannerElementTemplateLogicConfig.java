package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.template.core.DomConfig;
import com.parch.combine.html.base.template.core.ElementTemplateConfig;


public interface BannerElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "close", name = "关闭标识DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig close();

        @Field(key = "img", name = "图片内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig img();

        @Field(key = "text", name = "文本内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig text();
    }
}
