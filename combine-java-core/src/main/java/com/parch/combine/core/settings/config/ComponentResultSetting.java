package com.parch.combine.core.settings.config;

import java.util.List;

/**
 * 组件结果设置
 */
public class ComponentResultSetting {

    private String info;

    private boolean isDownload;

    private List<String> desc;

    public boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(boolean download) {
        isDownload = download;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
