package com.parch.combine.gui.base.build.frame;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface GUIBuildLogicConfig extends ILogicConfig {

    @Field(key = "domain", name = "域", type = FieldTypeEnum.TEXT, defaultValue = "$common")
    String domain();

    @Field(key = "icon", name = "图标", type = FieldTypeEnum.TEXT)
    String icon();

    @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "width", name = "宽度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer width();

    @Field(key = "height", name = "高度", type = FieldTypeEnum.NUMBER, isRequired = true)
    Integer height();

    @Field(key = "distanceTop", name = "距顶部距离", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Integer distanceTop();

    @Field(key = "distanceLeft", name = "距左侧距离", type = FieldTypeEnum.NUMBER, defaultValue = "0")
    Integer distanceLeft();

    @Field(key = "visible", name = "是否可见", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean visible();

    @Field(key = "close", name = "显示关闭按钮", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean close();

    @Field(key = "resizable", name = "是否允许改变大小", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean resizable();

    @Field(key = "elements", name = "内容元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(ElementGroupConfig.class)
    ElementGroupConfig elements();

    interface ElementGroupConfig {
        @Field(key = "top", name = "顶部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] top();

        @Field(key = "bottom", name = "底部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] bottom();

        @Field(key = "left", name = "左侧控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] left();

        @Field(key = "right", name = "右侧控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] right();

        @Field(key = "center", name = "中部控件元素集合", type = FieldTypeEnum.TEXT, isArray = true)
        String[] center();
    }
}
