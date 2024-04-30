package com.parch.combine.component.web.elements.settings;

import com.parch.combine.common.util.CheckEmptyUtil;

import java.util.List;
import java.util.UUID;

/**
 * 页面元素设置
 */
public class TabSettings extends BaseSettings{

    List<TabItemSettings> items;

    public static class TabItemSettings {

        private String id;

        private String title;

        private Boolean show;

        private Boolean checked;

        private Boolean hasClose;

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
