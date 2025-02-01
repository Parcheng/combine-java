package com.parch.combine.html.old.base.template;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldRef;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.old.base.template.core.DomConfig;
import com.parch.combine.html.old.base.template.core.ElementTemplateConfig;


public interface VideoElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "video", name = "视频DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig video();

        @Field(key = "mp4", name = "mp4格式视频DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig mp4();

        @Field(key = "ogg", name = "ogg格式视频DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig ogg();

        @Field(key = "webm", name = "webm格式视频DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig webm();

        @Field(key = "content", name = "不兼容展示的内容DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig content();
    }
}
