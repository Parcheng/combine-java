package com.parch.combine.core.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程常量处理器
 */
public class ConstantHandler {

    private final static Map<String, Object> CONSTANT = new HashMap<>();

    /**
     * 保存流程配置集合
     *
     * @param constant 常量
     * @return 是否成功
     */
    public static boolean save(Map<String, Object> constant) {
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
    public static Map<String, Object> get() {
        return CONSTANT;
    }
}
