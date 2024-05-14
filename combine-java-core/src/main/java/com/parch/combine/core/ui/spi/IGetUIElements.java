package com.parch.combine.core.ui.spi;

import com.parch.combine.core.ui.settings.config.PageElementClassifySetting;
import com.parch.combine.core.ui.vo.PageElementClassInitVO;

import java.util.List;

/**
 * 获取页面元素接口
 */
public interface IGetUIElements {

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    List<PageElementClassInitVO> get();

    /**
     * 获取组件设置
     *
     * @return 组件设置集合
     */
    PageElementClassifySetting getSetting();
}