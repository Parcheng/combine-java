package com.parch.combine.components.logic;

import com.parch.combine.core.component.settings.spi.AbsGetComponents;

/**
 * 获取逻辑组件实现类
 */
public class GetLogicComponents extends AbsGetComponents {

    public GetLogicComponents() {
        super("logic", "逻辑处理", GetLogicComponents.class);
    }
}
