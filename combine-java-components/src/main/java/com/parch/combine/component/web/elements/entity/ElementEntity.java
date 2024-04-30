package com.parch.combine.component.web.elements.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.web.elements.dataload.DataLoadEntity;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.settings.BaseSettings;

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

    private String id;

    @JsonIgnore
    private ElementTypeEnum type;

    private String tempPath;

    private Object data;

    private Object defaultData;

    private DataLoadEntity load;

    private Boolean refresh;

    private S settings;

    public ElementEntity(ElementTypeEnum type) {
        this.type = type;
        this.tempPath = "./template/elements/" + type.name().toLowerCase() + "_template.json";
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

    public S getSettings() {
        return settings;
    }

    public void setSettings(S settings) {
        this.settings = settings;
    }

    public Object getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(Object defaultData) {
        this.defaultData = defaultData;
    }
}
