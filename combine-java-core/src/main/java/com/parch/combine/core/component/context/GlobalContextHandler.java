package com.parch.combine.core.component.context;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.json.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局上下文处理器
 */
public class GlobalContextHandler {

    private static GlobalContext CONTEXT = null;

    public static GlobalContext init(String path) {
        String configJson = ResourceFileUtil.read(path, GlobalContextHandler.class.getClassLoader());
        if (CheckEmptyUtil.isNotEmpty(configJson)) {
            CONTEXT = JsonUtil.string2Obj(configJson, GlobalContext.class);
        }
        if (CONTEXT == null) {
            CONTEXT = new GlobalContext();
        }

        return CONTEXT;
    }

    public static GlobalContext get() {
        return CONTEXT;
    }
}
