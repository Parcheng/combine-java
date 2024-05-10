package com.parch.combine.core.ui.base;

import com.parch.combine.core.common.base.ICheck;
import com.parch.combine.core.common.base.IInit;
import com.parch.combine.core.common.settings.annotations.*;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.ui.tools.ConfigErrorMsgTool;

import java.util.ArrayList;
import java.util.List;

public class HtmlConfig implements IInit, ICheck {

    @Field(key ="lang" , name = "HTML语言", type = FieldTypeEnum.TEXT, defaultValue = "en")
    private String lang;

    @Field(key ="tempPath" , name = "模板根路径", type = FieldTypeEnum.TEXT, defaultValue = "系统内置模板根路径")
    private String tempPath;

    @Field(key ="metas" , name = "mate标签配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = HtmlHeaderMetaConfig.class)
    private List<HtmlHeaderMetaConfig> metas;

    @Field(key ="links" , name = "link标签配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
    @FieldObject(type = HtmlHeaderLinkConfig.class)
    private List<HtmlHeaderLinkConfig> links;

    @Field(key ="scripts" , name = "加载script脚本集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> scripts;

    @Field(key ="groupIds" , name = "页面元素组ID集合", type = FieldTypeEnum.TEXT, isArray = true)
    private List<String> groupIds;

    @Field(key = "configs", name = "页面元素配置集合", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<HtmlElementConfig> configs;

    @Override
    public void init() {
        if (CheckEmptyUtil.isEmpty(lang)) {
            lang = "en";
        }
        if (CheckEmptyUtil.isEmpty(tempPath)) {
            tempPath = "/static/template/page/management_template.json";
        }
    }

    @Override
    public List<String> check() {
        List<String> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(this.configs)) {
            result.add(ConfigErrorMsgTool.fieldCheckError("configs", "配置集合不能为空"));
        }
        if (CheckEmptyUtil.isNotEmpty(this.metas)) {
            for (int i = 0; i < this.metas.size(); i++) {
                for (String item : this.metas.get(i).check()) {
                    result.add(ConfigErrorMsgTool.fieldCheckError("mates", i + 1, item));
                }
            }
        }
        if (CheckEmptyUtil.isNotEmpty(this.links)) {
            for (int i = 0; i < this.links.size(); i++) {
                for (String item : this.links.get(i).check()) {
                    result.add(ConfigErrorMsgTool.fieldCheckError("links", i + 1, item));
                }
            }
        }

        return result;
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

    public List<HtmlHeaderMetaConfig> getMetas() {
        return metas;
    }

    public void setMetas(List<HtmlHeaderMetaConfig> metas) {
        this.metas = metas;
    }

    public List<HtmlHeaderLinkConfig> getLinks() {
        return links;
    }

    public void setLinks(List<HtmlHeaderLinkConfig> links) {
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
