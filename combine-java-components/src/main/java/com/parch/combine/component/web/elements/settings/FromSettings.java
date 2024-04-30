package com.parch.combine.component.web.elements.settings;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.web.elements.entity.ElementEntity;

import java.util.List;
import java.util.UUID;

/**
 * 页面元素设置
 */
public class FromSettings extends BaseSettings{

    private String layout;

    private List<ItemConfig> items;

    public static class ItemConfig {

        private String id;

        private String fieldKey;

        private String fieldName;

        private String text;

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
