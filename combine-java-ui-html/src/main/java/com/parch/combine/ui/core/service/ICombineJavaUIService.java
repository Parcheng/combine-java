package com.parch.combine.ui.core.service;

import com.parch.combine.ui.core.vo.CombineConfigVO;
import com.parch.combine.ui.core.vo.CombineRegisterVO;


public interface ICombineJavaUIService {

    /**
     * 注册页面
     *
     * @param config 配置对象
     * @return 结果
     */
    CombineRegisterVO register(CombineConfigVO config);

    /**
     * 获取页面
     *
     * @param key   页面KEY
     * @return 页面文本
     */
    String getPage(String key);
}
