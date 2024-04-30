package com.parch.combine.core.settings.spi;

import com.parch.combine.core.settings.config.ComponentClassifySetting;
import com.parch.combine.core.vo.ComponentInitVO;

import java.util.List;

/**
 * 获取组件接口
 */
public interface IGetComponents {

    /**
     * 获取组件
     *
     * @return 组件集合
     */
    List<ComponentInitVO> get();

    /**
     * 获取组件设置
     *
     * @return 组件设置集合
     */
    ComponentClassifySetting getSetting();
}
