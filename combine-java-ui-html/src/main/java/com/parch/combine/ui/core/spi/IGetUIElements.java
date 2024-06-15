package com.parch.combine.ui.core.spi;

import com.parch.combine.ui.core.settings.config.PageElementClassifySetting;
import com.parch.combine.ui.core.vo.PageElementClassInitVO;

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
