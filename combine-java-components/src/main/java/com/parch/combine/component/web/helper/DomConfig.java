package com.parch.combine.component.web.helper;

import com.parch.combine.component.web.elements.trigger.TriggerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTML元素配置
 */
public class DomConfig {

    /**
     * 标签名
     */
    private String tag;

    /**
     * DOM ID
     */
    private String id;

    /**
     * name属性
     */
    private String name;

    /**
     * name属性
     */
    private String text;

    /**
     * class属性
     */
    private String classes;

    /**
     * 样式集合
     */
    private String style;

    /**
     * 其他自定义属性
     */
    private Map<String, String> properties;

    /**
     * 其他自定义属性
     */
    private List<ElementEvent> events;

    /**
     * 触发配置
     */
    private TriggerEntity trigger;

    /**
     * 元素事件
     */
    public static class ElementEvent {

        /**
         * 事件类型
         */
        String type;

        /**
         * 函数名称
         */
        String functionName;

        /**
         * 函数参数
         */
        List<String> functionParams;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getFunctionName() {
            return functionName;
        }

        public void setFunctionName(String functionName) {
            this.functionName = functionName;
        }

        public List<String> getFunctionParams() {
            return functionParams;
        }

        public void setFunctionParams(List<String> functionParams) {
            this.functionParams = functionParams;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public List<ElementEvent> getEvents() {
        return events;
    }

    public void setEvents(List<ElementEvent> events) {
        this.events = events;
    }

    public synchronized void addEvent(ElementEvent event) {
        if (this.events == null) {
            this.events = new ArrayList<>();
        }
        this.events.add(event);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TriggerEntity getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerEntity trigger) {
        this.trigger = trigger;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

