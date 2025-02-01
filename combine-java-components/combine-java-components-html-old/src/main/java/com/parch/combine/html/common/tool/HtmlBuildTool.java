package com.parch.combine.html.common.tool;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.html.old.base.template.core.DomConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面构建帮助类
 */
public class HtmlBuildTool {

    public static String build(DomConfig domConfig, DomConfig tempDomConfig, boolean isSelfClose) {
        return build(null, domConfig, tempDomConfig, isSelfClose);
    }

    public static String build(String defaultId, DomConfig domConfig, DomConfig tempDomConfig, boolean isSelfClose) {
        String tagName = null;
        Map<String, String> properties = null;
        String body = null;
        if (domConfig != null) {
            tagName = domConfig.tag();
            properties = buildProperties(defaultId, domConfig);
            body = domConfig.text();
        }

        if (tempDomConfig != null) {
            if (CheckEmptyUtil.isEmpty(tagName)) {
                String tempTag = tempDomConfig.tag();
                tagName = CheckEmptyUtil.isEmpty(tempTag) ? "div" : tempTag;
            }
            if (properties == null) {
                properties = buildProperties(defaultId, tempDomConfig);
            } else {
                Map<String, String> tempProperties = buildProperties(null, tempDomConfig);
                for (String key : tempProperties.keySet()) {
                    Object sourceValue = properties.get(key);
                    if (sourceValue == null) {
                        properties.put(key, tempProperties.get(key));
                    }
                }
            }
            if (CheckEmptyUtil.isEmpty(body)) {
                body = tempDomConfig.text();
            }
        }

        return build(tagName, body, properties, isSelfClose);
    }

    public static String build(String tagName, String body, Map<String, String> properties, boolean isSelfClose) {
        StringBuilder code = new StringBuilder();
        code.append("\n<").append(tagName);
        if (properties != null) {
            properties.forEach((k, v) -> {
                if (CheckEmptyUtil.isNotEmpty(v)) {
                    code.append(" ").append(k).append("=\"").append(v).append("\"");
                }
            });
        }

        if (isSelfClose) {
            code.append("/>");
        } else {
            code.append(">");
            if (CheckEmptyUtil.isNotEmpty(body)) {
                code.append(body).append("\n");
            }
            code.append("</").append(tagName).append(">");
        }

        return code.toString();
    }

    /**
     * 构建标签属性集合
     *
     * @param element 元素配置
     * @return 结果
     */
    public static Map<String, String> buildProperties(String defaultId, DomConfig element) {
        Map<String, String> properties = element.properties();
        if (properties == null) {
            properties = new HashMap<>();
        }

        // ID, name, Class
        String id = element.id();
        properties.put("id", CheckEmptyUtil.isEmpty(id) ? defaultId : id);
        properties.put("name", element.name());
        properties.put("class", element.classes());

        // 样式
        String style = element.style();
        if (style != null) {
            properties.put("style", style);
        }

        // 事件
        DomConfig.ElementEvent[] events = element.events();
        if (CheckEmptyUtil.isNotEmpty(events)) {
            for (DomConfig.ElementEvent event : events) {
                properties.put(event.type(), event.functionName() + "(" + StringUtil.join(event.functionParams(), ",") + ")");
            }
        }

        return properties;
    }
}
