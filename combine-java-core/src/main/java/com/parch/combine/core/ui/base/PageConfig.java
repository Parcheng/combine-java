package com.parch.combine.core.ui.base;

import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.List;

public class PageConfig {

    @Field(key ="lang" , name = "HTML语言", type = FieldTypeEnum.TEXT, defaultValue = "en")
    private String lang;

    @Field(key ="tempPath" , name = "模板根路径", type = FieldTypeEnum.TEXT, defaultValue = "系统内置模板根路径")
    private String tempPath;

    @Field(key ="metas" , name = "mate标签配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = HtmlMetaItem.class)
    private List<HtmlMetaItem> metas;

    @Field(key ="links" , name = "link标签配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = HtmlLinkItem.class)
    private List<HtmlLinkItem> links;

    @Field(key ="scripts" , name = "加载script脚本集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> scripts;

    @Field(key ="groupIds" , name = "页面元素组ID集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> groupIds;

    @Field(key = "configs", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<HtmlElementConfig> configs;

    public void init() {
        if (CheckEmptyUtil.isEmpty(getTempPath())) {
            setTempPath("/static/template/page/management_template.json");
        }
    }

    /**
     * mate配置
     */
    public static class HtmlMetaItem {

        @Field(key = "name", name = "页面的媒体信息名称", type = FieldTypeEnum.TEXT, isRequired = true)
        private String name;

        @Field(key = "content", name = "页面的媒体信息内容", type = FieldTypeEnum.TEXT, isRequired = true)
        private String content;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    /**
     * mate配置
     */
    public static class HtmlLinkItem {

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

    public static class HtmlElementConfig extends DomConfig {

        @Field(key = "key", name = "配置KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        @FieldDesc("对应模板中每部分的KEY，系统内置模板包含：header、footer、left、right、content 五部分")
        private String key;

        @Field(key = "defaultShowGroupId", name = "默认展示的元素组ID", type = FieldTypeEnum.TEXT)
        public String defaultShowGroupId;

        public HtmlElementConfig() {}

        public HtmlElementConfig(String key, String tag) {
            this.key = key;
            this.setTag(tag == null ? "div" : tag);
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getDefaultShowGroupId() {
            return defaultShowGroupId;
        }

        public void setDefaultShowGroupId(String defaultShowGroupId) {
            this.defaultShowGroupId = defaultShowGroupId;
        }
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

    public List<HtmlMetaItem> getMetas() {
        return metas;
    }

    public void setMetas(List<HtmlMetaItem> metas) {
        this.metas = metas;
    }

    public List<HtmlLinkItem> getLinks() {
        return links;
    }

    public void setLinks(List<HtmlLinkItem> links) {
        this.links = links;
    }

    public List<String> getScripts() {
        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<String> groupIds) {
        this.groupIds = groupIds;
    }

    public List<HtmlElementConfig> getConfigs() {
        return configs;
    }

    public void setConfigs(List<HtmlElementConfig> configs) {
        this.configs = configs;
    }
}
