package com.parch.combine.core.ui.service;

import com.parch.combine.core.ui.vo.CombineInitVO;

import java.util.function.Consumer;

public interface ICombineJavaUIService {

    /**
     * 注册流程配置集合
     *
     * @param configJson 配置JSON
     * @param func       自定义函数
     */
    void register(String configJson, Consumer<CombineInitVO> func);

    /**
     * 获取流程配置集合
     *
     * @param key   页面KEY
     * @return 页面文本
     */
    String getPage(String key);
}
