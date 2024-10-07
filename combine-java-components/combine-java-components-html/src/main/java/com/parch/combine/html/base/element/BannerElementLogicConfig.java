package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

import java.util.List;

public interface BannerElementLogicConfig extends ILogicConfig {

    @Field(key = "type", name = "内容类型（img | text）", type = FieldTypeEnum.TEXT, defaultValue = "text")
    String bannerType();

    @Field(key = "hasClose", name = "是否允许关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean hasClose();

    @Field(key = "content", name = "内容", type = FieldTypeEnum.TEXT)
    String content();
}
