package com.parch.combine.core.ui.manager;

import java.util.HashMap;
import java.util.Map;

public class PageConstantManager {

    private final Map<String, Object> CONSTANT = new HashMap<>();

    /**
     * 保存流程配置集合
     *
     * @param constant 常量
     * @return 是否成功
     */
    protected boolean save(Map<String, Object> constant) {
        if (constant != null) {
            synchronized (CONSTANT) {
                CONSTANT.putAll(constant);
            }
        }
        return true;
    }

    /**
     * 获取
     *
     * @return 常量集合
     */
    public Map<String, Object> get() {
        return CONSTANT;
    }
}
