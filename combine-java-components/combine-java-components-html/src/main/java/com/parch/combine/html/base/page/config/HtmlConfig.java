package com.parch.combine.html.base.page.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface HtmlConfig extends ILogicConfig {

    @Field(key ="lang" , name = "HTML语言", type = FieldTypeEnum.TEXT, defaultValue = "en")
    String lang();

    @Field(key ="tempPath" , name = "模板根路径", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认系统内置模板根路径")
    String tempPath();

    @Field(key ="metas" , name = "mate标签配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(HtmlHeaderMetaConfig.class)
    HtmlHeaderMetaConfig[] metas();

    @Field(key ="links" , name = "link标签配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(HtmlHeaderLinkConfig.class)
    HtmlHeaderLinkConfig[] links();

    @Field(key ="scripts" , name = "加载script脚本集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] scripts();

    @Field(key ="groupIds" , name = "页面元素组ID集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] groupIds();

    @Field(key = "modules", name = "页面元素配置集合", type = FieldTypeEnum.CONFIG, isRequired = true, isArray = true)
    @FieldObject(HtmlElementConfig.class)
    HtmlElementConfig[] modules();
}
