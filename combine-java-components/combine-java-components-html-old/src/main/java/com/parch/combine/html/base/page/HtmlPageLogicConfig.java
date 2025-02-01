package com.parch.combine.html.base.page;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.page.config.HtmlElementConfig;
import com.parch.combine.html.base.page.config.HtmlHeaderLinkConfig;
import com.parch.combine.html.base.page.config.HtmlHeaderMetaConfig;

import java.util.Map;

public interface HtmlPageLogicConfig extends ILogicConfig {

    @Field(key ="hasCache" , name = "是否对构建后的页面缓存", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    @FieldDesc("如果为 true, 则首次生成页面代码后进行缓存, 再次调用该组件时会从缓存直接获取页面代码")
    Boolean hasCache();

    @Field(key ="constant" , name = "全局常量定义", type = FieldTypeEnum.MAP)
    Map<String, Object> constant();

    @Field(key ="lang" , name = "HTML语言", type = FieldTypeEnum.TEXT, defaultValue = "en")
    String lang();

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
