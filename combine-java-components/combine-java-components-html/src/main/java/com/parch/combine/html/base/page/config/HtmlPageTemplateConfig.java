package com.parch.combine.html.base.page.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.Map;

public interface HtmlPageTemplateConfig {

    @Field(key ="constant" , name = "全局常量定义", type = FieldTypeEnum.MAP)
    Map<String, Object> constant();

    @Field(key ="metas" , name = "mate标签配置集合", type = FieldTypeEnum.CONFIG)
    @FieldObject(HtmlHeaderMetaConfig.class)
    HtmlHeaderMetaConfig[] metas();

    @Field(key ="links" , name = "link标签配置集合", type = FieldTypeEnum.CONFIG)
    @FieldObject(HtmlHeaderLinkConfig.class)
    HtmlHeaderLinkConfig[] links();

    @Field(key ="scripts" , name = "加载script脚本集合", type = FieldTypeEnum.TEXT)
    String[] scripts();

    @Field(key ="groupIds" , name = "页面元素组ID集合", type = FieldTypeEnum.TEXT)
    String[] groupIds();

    @Field(key = "modules", name = "页面元素配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(HtmlElementConfig.class)
    HtmlElementConfig[] modules();
}
