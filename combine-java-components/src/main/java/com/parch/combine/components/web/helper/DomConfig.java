package com.parch.combine.components.web.helper;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HTML元素配置
 */
public class DomConfig {

    @Field(key = "tag", name = "DOM标签", type = FieldTypeEnum.TEXT, isRequired = true)
    private String tag;

    @Field(key = "id", name = "DOM的ID", type = FieldTypeEnum.TEXT)
    private String id;

    @Field(key = "name", name = "DOM的name属性", type = FieldTypeEnum.TEXT)
    private String name;

    @Field(key = "text", name = "DOM的文本内容", type = FieldTypeEnum.TEXT)
    private String text;

    @Field(key = "classes", name = "DOM的class属性", type = FieldTypeEnum.TEXT)
    private String classes;

    @Field(key = "style", name = "DOM的style属性", type = FieldTypeEnum.TEXT)
    private String style;

    @Field(key = "trigger", name = "DOM的其他（或自定义）属性", type = FieldTypeEnum.OBJECT)
    @FieldDesc("格式为“属性名:属性值”的键值对")
    private Map<String, String> properties;

    @Field(key = "trigger", name = "DOM的事件集合", type = FieldTypeEnum.OBJECT, isArray = true)
    private List<ElementEvent> events;

    public static class ElementEvent {

        @Field(key = "type", name = "事件类型", type = FieldTypeEnum.TEXT, isRequired = true)
        String type;

        @Field(key = "functionName", name = "事件触发调用的函数名称", type = FieldTypeEnum.TEXT, isRequired = true)
        String functionName;

        @Field(key = "functionParams", name = "函数参数集合", type = FieldTypeEnum.TEXT, isArray = true)
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

