package com.parch.combine.component.web.elements.dataload;

/**
 * 数据加载配置
 */
public class ApiDataLoadEntity extends DataLoadEntity {

    private String url;

    private String mode;

    private Object params;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
