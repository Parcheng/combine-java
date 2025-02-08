package com.parch.combine.gui.base.build.control.img;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlImgLogicConfig extends GUIControlLogicConfig {

    @Field(key = "path", name = "图片路径", type = FieldTypeEnum.TEXT, isRequired = true)
    String path();

    @Field(key = "width", name = "图片宽度", type = FieldTypeEnum.NUMBER)
    @FieldDesc("默认以图片的实际宽度为准")
    Integer width();

    @Field(key = "height", name = "图片高度", type = FieldTypeEnum.NUMBER)
    @FieldDesc("默认以图片的实际高度为准")
    Integer height();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();
}
