package com.parch.combine.ui.elements.settings;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.web.WebSettingCanstant;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;
import java.util.UUID;

/**
 * 页面元素设置
 */
public class FromSettings extends BaseSettings{

    @Field(key = "layout", name = "布局 VERTICAL | INLINE | HORIZONTAL", type = FieldTypeEnum.TEXT, defaultValue = "HORIZONTAL")
    private String layout;

    @Field(key = "items", name = "表单项配置", type = FieldTypeEnum.OBJECT, isRequired = true, isArray = true)
    private List<ItemConfig> items;

    public static class ItemConfig {

        @Field(key = "id", name = "配置项ID（DOM元素ID）", type = FieldTypeEnum.TEXT)
        private String id;

        @Field(key = "fieldKey", name = "表单字段KEY", type = FieldTypeEnum.TEXT, isRequired = true)
        private String fieldKey;

        @Field(key = "fieldName", name = "表单字段名称", type = FieldTypeEnum.TEXT, isRequired = true)
        private String fieldName;

        @Field(key = "text", name = "text 和 element 配置任意一项即可", type = FieldTypeEnum.TEXT)
        private String text;

        @Field(key = "hide", name = "是否隐藏", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
        private Boolean hide;

        @Field(key = "element", name = "表单字段元素", type = FieldTypeEnum.OBJECT)
        @FieldRef(key = WebSettingCanstant.ELEMENT_ENTITY_KEY)
        private ElementEntity<?> element;

        public String getId() {
            if (CheckEmptyUtil.isEmpty(id)) {
                id = UUID.randomUUID().toString();
            }
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public ElementEntity<?> getElement() {
            return element;
        }

        public void setElement(ElementEntity<?> element) {
            this.element = element;
        }

        public String getFieldKey() {
            return fieldKey;
        }

        public void setFieldKey(String fieldKey) {
            this.fieldKey = fieldKey;
        }

        public Boolean getHide() {
            return hide;
        }

        public void setHide(Boolean hide) {
            this.hide = hide;
        }
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public List<ItemConfig> getItems() {
        return items;
    }

    public void setItems(List<ItemConfig> items) {
        this.items = items;
    }
}