package com.parch.combine.html.base.page.config;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

import java.util.Map;

public interface HtmlPageTemplateConfig {

    @Field(key ="constant" , name = "全局常量定义", type = FieldTypeEnum.MAP)
    Map<String, Object> constant();

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

    @Field(key = "modules", name = "页面元素配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(HtmlElementConfig.class)
    HtmlElementConfig[] modules();
}
