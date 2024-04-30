package com.parch.combine.core.base;

public abstract class InitConfig {

    /**
     * 配置ID（用于同类型组件关联不同配置时使用）
     */
    private String id;

    /**
     * 组件类型
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
