package com.parch.combine.component.web.elements.trigger;

import java.util.List;

/**
 * 配置类
 */
public class TriggerLoadDataEntity extends TriggerEntity {

    private List<String> loadIds;

    public List<String> getLoadIds() {
        return loadIds;
    }

    public void setLoadIds(List<String> loadIds) {
        this.loadIds = loadIds;
    }
}

