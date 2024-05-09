package com.parch.combine.core.ui.vo;

import java.util.List;
import java.util.Map;

public class CombineConfigVO {

    private Map<String, Object> constant;

    private List<Map<String, Object>> dataLoads;

    private List<Map<String, Object>> templates;

    private List<Map<String, Object>> elements;

    private List<PageElementGroupVO> groups;

    private Map<String, Map<String, Object>> pages;

    public Map<String, Object> getConstant() {
        return constant;
    }

    public void setConstant(Map<String, Object> constant) {
        this.constant = constant;
    }

    public List<Map<String, Object>> getTemplates() {
        return templates;
    }

    public void setTemplates(List<Map<String, Object>> templates) {
        this.templates = templates;
    }

    public List<Map<String, Object>> getElements() {
        return elements;
    }

    public void setElements(List<Map<String, Object>> elements) {
        this.elements = elements;
    }

    public Map<String, Map<String, Object>> getPages() {
        return pages;
    }

    public void setPages(Map<String, Map<String, Object>> pages) {
        this.pages = pages;
    }

    public List<Map<String, Object>> getDataLoads() {
        return dataLoads;
    }

    public void setDataLoads(List<Map<String, Object>> dataLoads) {
        this.dataLoads = dataLoads;
    }

    public List<PageElementGroupVO> getGroups() {
        return groups;
    }

    public void setGroups(List<PageElementGroupVO> groups) {
        this.groups = groups;
    }
}
