package com.parch.combine.component.web.page;

import com.parch.combine.component.web.WebSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class WebPageSettingHandler {

    /**
     * 构建
     *
     * @param builder 构建对象
     */
    public static void build(ComponentSettingBuilder builder) {
        builder.addProperty(PropertyTypeEnum.INIT, "baseUrl", FieldTypeEnum.TEXT, "根URL",  false, false);
        builder.addPropertyEg(PropertyTypeEnum.INIT, "baseUrl", "http://127.0.0.1:8080/combine", "前端根URL为 127.0.0.1:8080/combine");


        builder.addProperty(PropertyTypeEnum.LOGIC, "lang", FieldTypeEnum.TEXT, "语言",  false, false, "en");

        builder.addProperty(PropertyTypeEnum.LOGIC, "metas", FieldTypeEnum.OBJECT, "页面的媒体信息集合",  false, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "metas.name", FieldTypeEnum.TEXT, "页面的媒体信息名称",  true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "metas.content", FieldTypeEnum.TEXT, "页面的媒体信息内容",  true, false);

        builder.addProperty(PropertyTypeEnum.LOGIC, "links", FieldTypeEnum.OBJECT, "页面的LINK信息集合",  false, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.rel", FieldTypeEnum.TEXT, "定义当前文档与链接资源之间的关系",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.href", FieldTypeEnum.TEXT, "属性用于指定链接资源的URL",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.type", FieldTypeEnum.TEXT, "用于指定链接资源的MIME类型",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.media", FieldTypeEnum.TEXT, "允许指定样式表适用于哪些媒体类型",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.sizes", FieldTypeEnum.TEXT, "使用 link 标签链接到多个尺寸的图标时，可以使用 sizes 属性指定图标的大小",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.integrity", FieldTypeEnum.TEXT, "用于确保外部资源的完整性，可以与 crossorigin 属性一起使用",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "links.integrity", "通过为资源提供一个基于内容的哈希值（如SHA-256），可以确保资源未被篡改");
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.crossorigin", FieldTypeEnum.TEXT, "当链接到跨域资源时，可以指定资源的CORS（跨源资源共享）设置",  false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "links.preload", FieldTypeEnum.TEXT, "用于提前加载重要的资源，例如字体、图片或脚本",  false, false);

        builder.addProperty(PropertyTypeEnum.LOGIC, "scripts", FieldTypeEnum.TEXT, "页面的脚本地址集合",  false, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "elements", FieldTypeEnum.TEXT, "页面中要使用的元素组ID集合",  false, true);
        builder.addProperty(PropertyTypeEnum.LOGIC, "loadElements", FieldTypeEnum.TEXT, "指定初始加载那些元素组",  false, true);
    }

    /**
     * 构建
     *
     * @param builder 构建对象
     * @param parentKey 上级KEY
     */
    public static void buildHtmlElement(ComponentSettingBuilder builder, String parentKey) {
        builder.addPropertyRef(PropertyTypeEnum.LOGIC, parentKey, WebSettingHandler.DOM_KEY, WebSettingHandler.DOM_NAME);
        builder.addProperty(PropertyTypeEnum.LOGIC, parentKey + ".showElement", FieldTypeEnum.OBJECT, "默认展示的元素组ID",  false, false);

        WebSettingHandler.buildEntity(builder);
        WebSettingHandler.buildDomConfig(builder);
        WebSettingHandler.buildTrigger(builder);
        WebSettingHandler.buildEntityDataLoad(builder);
    }
}
