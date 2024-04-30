package com.parch.combine.components.tool;

import com.parch.combine.core.settings.spi.AbsGetComponents;

/**
 * 获取数据处理组件加载器
 */
public class GetToolComponents extends AbsGetComponents {

    public GetToolComponents() {
        super("tool", "工具", GetToolComponents.class);
    }
}
