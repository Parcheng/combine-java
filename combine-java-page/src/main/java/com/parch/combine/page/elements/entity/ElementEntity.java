package com.parch.combine.page.elements.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.web.ElementDomConfig;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.dataload.DataLoadEntity;
import com.parch.combine.components.web.elements.enums.ElementTypeEnum;
import com.parch.combine.components.web.elements.settings.BaseSettings;
import com.parch.combine.components.web.page.WebPageInitConfig;
import com.parch.combine.core.settings.annotations.ComponentField;
import com.parch.combine.core.settings.annotations.ComponentFieldRef;
import com.parch.combine.core.settings.annotations.ComponentFieldSelect;
import com.parch.combine.core.settings.config.FieldTypeEnum;

import java.util.UUID;

/**
 * 元素配置
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes(value = {
        @JsonSubTypes.Type(value = TextElementEntity.class, name = "TEXT"),
        @JsonSubTypes.Type(value = TableElementEntity.class, name = "TABLE"),
        @JsonSubTypes.Type(value = ButtonElementEntity.class, name = "BUTTON"),
        @JsonSubTypes.Type(value = WindowElementEntity.class, name = "WINDOW"),
        @JsonSubTypes.Type(value = PaginationElementEntity.class, name = "PAGINATION"),
        @JsonSubTypes.Type(value = PageTurningElementEntity.class, name = "PAGE_TURNING"),
        @JsonSubTypes.Type(value = ListElementEntity.class, name = "LIST"),
        @JsonSubTypes.Type(value = TagElementEntity.class, name = "TAG"),
        @JsonSubTypes.Type(value = PopElementEntity.class, name = "POP"),
        @JsonSubTypes.Type(value = TabElementEntity.class, name = "TAB"),
        @JsonSubTypes.Type(value = BreadcrumbElementEntity.class, name = "BREADCRUMB"),
        @JsonSubTypes.Type(value = FromElementEntity.class, name = "FROM"),
        @JsonSubTypes.Type(value = TreeElementEntity.class, name = "TREE"),
        @JsonSubTypes.Type(value = NavBarElementEntity.class, name = "NAV_BAR"),
        @JsonSubTypes.Type(value = ContentElementEntity.class, name = "CONTENT"),
        @JsonSubTypes.Type(value = ThumbnailElementEntity.class, name = "THUMBNAIL"),
        @JsonSubTypes.Type(value = InputElementEntity.class, name = "INPUT"),
        @JsonSubTypes.Type(value = SelectElementEntity.class, name = "SELECT"),
        @JsonSubTypes.Type(value = TextareaElementEntity.class, name = "TEXTAREA"),
        @JsonSubTypes.Type(value = RadioElementEntity.class, name = "RADIO"),
        @JsonSubTypes.Type(value = CheckboxElementEntity.class, name = "CHECKBOX"),
        @JsonSubTypes.Type(value = AudioElementEntity.class, name = "AUDIO"),
        @JsonSubTypes.Type(value = VideoElementEntity.class, name = "VIDEO"),
        @JsonSubTypes.Type(value = LineElementEntity.class, name = "LINE"),
        @JsonSubTypes.Type(value = TitleElementEntity.class, name = "TITLE")
})
public abstract class ElementEntity<S extends BaseSettings> {

    @ComponentField(key = "id", name = "元素ID", type = FieldTypeEnum.TEXT, defaultValue = "随机字符粗")
    private String id;

    @JsonIgnore
    @ComponentField(key = "type", name = "元素类型", type = FieldTypeEnum.SELECT, isRequired = true)
    @ComponentFieldSelect(enumClass = ElementTypeEnum.class)
    private ElementTypeEnum type;

    @ComponentField(key = "tempPath", name = "模板文件路径", type = FieldTypeEnum.TEXT, defaultValue = "系统内置模板")
    private String tempPath;

    @ComponentField(key = "data", name = "初始数据", type = FieldTypeEnum.OBJECT)
    private Object data;

    @ComponentField(key = "defaultData", name = "默认数据", type = FieldTypeEnum.OBJECT)
    private Object defaultData;

    @ComponentField(key = "load", name = "数据加载配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DATA_LOAD_KEY)
    private DataLoadEntity load;

    @ComponentField(key = "defaultLoad", name = "是否默认加载（构建元素时加载数据）", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    private Boolean defaultLoad;

    @ComponentField(key = "refresh", name = "是否支持刷新", type = FieldTypeEnum.BOOLEAN)
    private Boolean refresh;

    @ComponentField(key = "external", name = "外部DOM配置", type = FieldTypeEnum.OBJECT)
    @ComponentFieldRef(key = WebSettingCanstant.DOM_KEY)
    private ElementDomConfig external;

    public ElementEntity(ElementTypeEnum type) {
        this.type = type;
        this.tempPath = WebPageInitConfig.SYSTEM_URL_FLAG + "/template/elements/" + type.name().toLowerCase() + "_template.json";
    }

    public synchronized String getId() {
        if (CheckEmptyUtil.isEmpty(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ElementTypeEnum getType() {
        return type;
    }

    public void setType(String type) {}

    public void setType(ElementTypeEnum type) {
        this.type = type;
    }

    public String getTempPath() {
        return tempPath;
    }

    public DataLoadEntity getLoad() {
        return load;
    }

    public void setLoad(DataLoadEntity load) {
        this.load = load;
    }

    public void setTempPath(String tempPath) {
        if (CheckEmptyUtil.isNotEmpty(tempPath)) {
            this.tempPath = tempPath;
        }
    }

    public Boolean getRefresh() {
        return refresh == null ? true : refresh;
    }

    public void setRefresh(Boolean refresh) {
        this.refresh = refresh;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(Object defaultData) {
        this.defaultData = defaultData;
    }

    public abstract S getSettings();

    public abstract void setSettings(S settings);

    public ElementDomConfig getExternal() {
        return external;
    }

    public void setExternal(ElementDomConfig external) {
        this.external = external;
    }

    public Boolean getDefaultLoad() {
        return defaultLoad;
    }

    public void setDefaultLoad(Boolean defaultLoad) {
        this.defaultLoad = defaultLoad;
    }
}
