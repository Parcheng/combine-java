package com.parch.combine.tool.components;

import com.parch.combine.core.component.spi.AbstractGetComponents;

/**
 * 获取数据处理组件加载器
 */
public class GetToolComponents extends AbstractGetComponents {

    public GetToolComponents() {
        super("tool", "工具", GetToolComponents.class);
    }
}
