package com.parch.combine.core.ui.service;

import com.parch.combine.core.ui.base.HtmlConfig;
import com.parch.combine.core.ui.vo.CombineInitVO;

import java.util.function.Consumer;

public interface ICombineJavaPageService {

    /**
     * 注册页面
     *
     * @param config     页面配置
     * @param func       自定义函数
     */
    void register(String key, HtmlConfig config, Consumer<CombineInitVO> func);

    /**
     * 获取页面
     *
     * @param key   页面KEY
     * @return 页面文本
     */
    String getPage(String key);
}
