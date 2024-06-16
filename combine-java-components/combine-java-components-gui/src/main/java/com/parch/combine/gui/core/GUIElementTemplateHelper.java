package com.parch.combine.gui.core;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;

import java.util.HashMap;
import java.util.Map;

public class GUIElementTemplateHelper {

    private static Map<String, Object> TEMPLATE_MAP = new HashMap<>();

    private GUIElementTemplateHelper() {}

    @SuppressWarnings("unchecked")
    public synchronized static <T> T getTemplate(String type, Class<T> tClass) {
        Object templateObj = TEMPLATE_MAP.get(type);
        if (templateObj != null) {
            return (T) templateObj;
        }

        String path = "gui/template/" + type + "_template.json";
        String testConfigJson = ResourceFileUtil.read(path);
        if (CheckEmptyUtil.isNotEmpty(testConfigJson)) {
            T templateConfig = JsonUtil.deserialize(testConfigJson, tClass);
            if (templateConfig != null) {
                TEMPLATE_MAP.put(type, templateConfig);
                return templateConfig;
            }
        }

        PrintUtil.printError("【GUI-TEMPLATE】【" + path + "】【加载模板数据为空】");
        return null;
    }
}
