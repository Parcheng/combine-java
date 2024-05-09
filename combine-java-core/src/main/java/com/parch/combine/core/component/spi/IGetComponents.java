package com.parch.combine.core.component.spi;

import com.parch.combine.core.component.settings.config.ComponentClassifySetting;
import com.parch.combine.core.component.vo.ComponentClassInitVO;

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
    List<ComponentClassInitVO> get();

    /**
     * 获取组件设置
     *
     * @return 组件设置集合
     */
    ComponentClassifySetting getSetting();
}
