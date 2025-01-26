package com.parch.combine.html.base.element;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface BannerElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "type", name = "内容类型（img | text）", type = FieldTypeEnum.TEXT, defaultValue = "text")
        String bannerType();

        @Field(key = "hasClose", name = "是否允许关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        Boolean hasClose();

        @Field(key = "content", name = "内容", type = FieldTypeEnum.TEXT)
        String content();
    }
}
