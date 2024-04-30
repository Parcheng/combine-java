package com.parch.combine.component.web.helper;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 页面构建帮助类
 */
public class HtmlBuileHelper {

    public static String build(DomConfig domConfig, DomConfig tempDomConfig, boolean isSelfClose) {
        String tagName = null;
        Map<String, String> properties = null;
        String body = null;
        if (domConfig != null) {
            tagName = domConfig.getTag();
            properties = buildProperties(domConfig);
            body = domConfig.getText();
        }

        if (tempDomConfig != null) {
            if (CheckEmptyUtil.isEmpty(tagName)) {
                tagName = CheckEmptyUtil.isEmpty(tempDomConfig.getTag()) ? "div" : tempDomConfig.getTag();
            }
            if (properties == null) {
                properties = buildProperties(tempDomConfig);
            } else {
                properties.putAll(buildProperties(tempDomConfig));
            }
            if (CheckEmptyUtil.isEmpty(body)) {
                body = tempDomConfig.getText();
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
    public static Map<String, String> buildProperties(DomConfig element) {
        Map<String, String> properties = element.getProperties() == null ? new HashMap<>() : element.getProperties();

        // ID, name, Class
        properties.put("id", element.getId());
        properties.put("name", element.getName());
        properties.put("class", element.getClasses());

        // 样式
        if (element.getStyle() != null) {
            properties.put("style", element.getStyle());
        }

        // 事件
        if (CheckEmptyUtil.isNotEmpty(element.getEvents())) {
            for (DomConfig.ElementEvent event : element.getEvents()) {
                properties.put(event.getType(), event.getFunctionName() + "(" + StringUtil.join(event.getFunctionParams(), ",") + ")");
            }
        }

        return properties;
    }
}
