package com.parch.combine.core.ui.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public class HtmlHeaderLinkConfig {

    @Field(key = "rel", name = "定义当前文档与链接资源之间的关系", type = FieldTypeEnum.TEXT)
    private String rel;

    @Field(key = "href", name = "属性用于指定链接资源的URL", type = FieldTypeEnum.TEXT)
    private String href;

    @Field(key = "type", name = "用于指定链接资源的MIME类型", type = FieldTypeEnum.TEXT)
    private String type;

    @Field(key = "media", name = "允许指定样式表适用于哪些媒体类型", type = FieldTypeEnum.TEXT)
    private String media;

    @Field(key = "sizes", name = "使用 link 标签链接到多个尺寸的图标时，可以使用 sizes 属性指定图标的大小", type = FieldTypeEnum.TEXT)
    private String sizes;

    @Field(key = "integrity", name = "用于确保外部资源的完整性，可以与 crossorigin 属性一起使用", type = FieldTypeEnum.TEXT)
    @FieldDesc("通过为资源提供一个基于内容的哈希值（如SHA-256），可以确保资源未被篡改")
    private String integrity;

    @Field(key = "crossorigin", name = "当链接到跨域资源时，可以指定资源的CORS（跨源资源共享）设置", type = FieldTypeEnum.TEXT, isRequired = true)
    private String crossorigin;

    @Field(key = "preload", name = "用于提前加载重要的资源，例如字体、图片或脚本", type = FieldTypeEnum.TEXT, isRequired = true)
    private String preload;

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getIntegrity() {
        return integrity;
    }

    public void setIntegrity(String integrity) {
        this.integrity = integrity;
    }

    public String getCrossorigin() {
        return crossorigin;
    }

    public void setCrossorigin(String crossorigin) {
        this.crossorigin = crossorigin;
    }

    public String getPreload() {
        return preload;
    }

    public void setPreload(String preload) {
        this.preload = preload;
    }
}
