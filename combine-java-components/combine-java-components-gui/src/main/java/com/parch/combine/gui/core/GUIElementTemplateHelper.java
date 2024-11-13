package com.parch.combine.gui.core;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.gui.core.style.ElementConfig;
import com.parch.combine.gui.core.style.ElementHelper;

import javax.swing.JComponent;
import java.util.HashMap;
import java.util.Map;

public class GUIElementTemplateHelper {

    private static Map<String, Object> TEMPLATE_MAP = new HashMap<>();
    private static Object frameTemplate;

    private GUIElementTemplateHelper() {}

    public synchronized static <T> T getControlTemplate(String type, Class<T> tClass) {
        return getTemplate(type, "control", tClass);
    }

    @SuppressWarnings("unchecked")
    private synchronized static <T> T getTemplate(String type, String subPath, Class<T> tClass) {
        Object templateObj = TEMPLATE_MAP.get(type);
        if (templateObj != null) {
            return (T) templateObj;
        }

        String path = "gui/template/" + subPath + "/" + type + "_template.json";
        String testConfigJson = ResourceFileUtil.read(path, GUIElementTemplateHelper.class.getClassLoader());
        if (CheckEmptyUtil.isNotEmpty(testConfigJson)) {
            T templateConfig = JsonUtil.string2Obj(testConfigJson, tClass);
            if (templateConfig != null) {
                TEMPLATE_MAP.put(type, templateConfig);
                return templateConfig;
            }
        }

        PrintLogUtil.printError("【GUI-TEMPLATE】【" + path + "】【加载模板数据为空】");
        return null;
    }

    @SuppressWarnings("unchecked")
    public synchronized static <T> T getFrameTemplate(Class<T> tClass) {
        if (frameTemplate != null) {
            return (T) frameTemplate;
        }

        String path = "gui/template/frame_template.json";
        String configJson = ResourceFileUtil.read(path, GUIElementTemplateHelper.class.getClassLoader());
        if (CheckEmptyUtil.isNotEmpty(configJson)) {
            T templateConfig = JsonUtil.string2Obj(configJson, tClass);
            if (templateConfig != null) {
                frameTemplate = templateConfig;
            }
            return templateConfig;
        }

        return null;
    }

    public static void loadTemplates(JComponent component, ElementConfig config) {
        if (component == null || config == null) {
            return;
        }

        ElementHelper.set(component, config);
    }
}
