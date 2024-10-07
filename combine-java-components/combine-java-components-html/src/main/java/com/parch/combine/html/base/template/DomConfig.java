package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

import java.util.List;
import java.util.Map;

@CommonObject(name = "页面DOM元素配置")
public interface DomConfig{

    @Field(key = "tag", name = "DOM标签", type = FieldTypeEnum.TEXT, isRequired = true)
    String tag();

    @Field(key = "id", name = "DOM的ID", type = FieldTypeEnum.TEXT)
    String id();

    @Field(key = "name", name = "DOM的name属性", type = FieldTypeEnum.TEXT)
    String name();

    @Field(key = "text", name = "DOM的文本内容", type = FieldTypeEnum.TEXT)
    String text();

    @Field(key = "classes", name = "DOM的class属性", type = FieldTypeEnum.TEXT)
    String classes();

    @Field(key = "alternateClass", name = "交替样式集合", type = FieldTypeEnum.TEXT, isArray = true)
    String[] alternateClass();

    @Field(key = "style", name = "DOM的style属性", type = FieldTypeEnum.TEXT)
    String style();

    @Field(key = "trigger", name = "DOM的其他（或自定义）属性", type = FieldTypeEnum.MAP)
    @FieldDesc("格式为“属性名:属性值”的键值对")
    Map<String, String> properties();

    @Field(key = "trigger", name = "DOM的事件集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(ElementEvent.class)
    ElementEvent[] events();

    interface ElementEvent {

        @Field(key = "type", name = "事件类型", type = FieldTypeEnum.TEXT, isRequired = true)
        String type();

        @Field(key = "functionName", name = "事件触发调用的函数名称", type = FieldTypeEnum.TEXT, isRequired = true)
        String functionName();

        @Field(key = "functionParams", name = "函数参数集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] functionParams();
    }
}

