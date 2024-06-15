package com.parch.combine.ui.core.vo;

import java.util.List;
import java.util.Map;

public class CombineLoadVO {

    private List<String> dataLoadIds;

    private List<String> elementTemplateIds;

    private List<String> elementIds;

    private Map<String, List<String>> groupElementMap;

    private List<String> pageKeys;

    public List<String> getDataLoadIds() {
        return dataLoadIds;
    }

    public void setDataLoadIds(List<String> dataLoadIds) {
        this.dataLoadIds = dataLoadIds;
    }

    public List<String> getElementTemplateIds() {
        return elementTemplateIds;
    }

    public void setElementTemplateIds(List<String> elementTemplateIds) {
        this.elementTemplateIds = elementTemplateIds;
    }

    public List<String> getElementIds() {
        return elementIds;
    }

    public void setElementIds(List<String> elementIds) {
        this.elementIds = elementIds;
    }

    public Map<String, List<String>> getGroupElementMap() {
        return groupElementMap;
    }

    public void setGroupElementMap(Map<String, List<String>> groupElementMap) {
        this.groupElementMap = groupElementMap;
    }

    public List<String> getPageKeys() {
        return pageKeys;
    }

    public void setPageKeys(List<String> pageKeys) {
        this.pageKeys = pageKeys;
    }
}
