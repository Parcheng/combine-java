package com.parch.combine.components.web.elements.settings;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;
import java.util.UUID;

/**
 * 页面元素设置
 */
public class TabSettings extends BaseSettings{

    @Field(key = "items", name = "页签项配置集合", type = FieldTypeEnum.OBJECT, isArray = true, isRequired = true)
    @FieldObject(type = TabItemSettings.class)
    private List<TabItemSettings> items;

    public static class TabItemSettings {

        @Field(key = "id", name = "页签ID", type = FieldTypeEnum.TEXT, defaultValue = "随机生成")
        private String id;

        @Field(key = "title", name = "页签标题", type = FieldTypeEnum.TEXT, isRequired = true)
        private String title;

        @Field(key = "show", name = "页签是否默认显示", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean show;

        @Field(key = "checked", name = "页签是否默认选中", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean checked;

        @Field(key = "hasClose", name = "页签是否允许关闭", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
        private Boolean hasClose;

        @Field(key = "body", name = "页签内容配置", type = FieldTypeEnum.OBJECT, isRequired = true)
        @FieldObject(type = SubElementSettings.class)
        private SubElementSettings body;

        public String getId() {
            if (CheckEmptyUtil.isEmpty(id)) {
                id = UUID.randomUUID().toString();
            }
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getShow() {
            return show;
        }

        public void setShow(Boolean show) {
            this.show = show;
        }

        public Boolean getChecked() {
            return checked;
        }

        public void setChecked(Boolean checked) {
            this.checked = checked;
        }

        public Boolean getHasClose() {
            return hasClose;
        }

        public void setHasClose(Boolean hasClose) {
            this.hasClose = hasClose;
        }

        public SubElementSettings getBody() {
            return body;
        }

        public void setBody(SubElementSettings body) {
            this.body = body;
        }
    }

    public List<TabItemSettings> getItems() {
        return items;
    }

    public void setItems(List<TabItemSettings> items) {
        this.items = items;
    }
}
