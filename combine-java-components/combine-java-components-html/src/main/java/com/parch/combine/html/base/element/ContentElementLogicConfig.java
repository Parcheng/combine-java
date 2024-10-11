package com.parch.combine.html.base.element;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.html.base.element.core.ElementConfig;

public interface ContentElementLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "页面元素配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    @FieldObject(Config.class)
    Config config();

    interface Config extends ElementConfig {

        @Field(key = "top", name = "顶部图片配置", type = FieldTypeEnum.CONFIG)
        @FieldObject(ContentImgConfig.class)
        ContentImgConfig top();

        @Field(key = "left", name = "左侧图片配置", type = FieldTypeEnum.CONFIG)
        @FieldObject(ContentImgConfig.class)
        ContentImgConfig left();

        @Field(key = "right", name = "右侧图片配置", type = FieldTypeEnum.CONFIG)
        @FieldObject(ContentImgConfig.class)
        ContentImgConfig right();

        @Field(key = "content", name = "内容配置", type = FieldTypeEnum.CONFIG)
        @FieldObject(ContentConfig.class)
        ContentConfig content();
    }

    interface ContentImgConfig {

        @Field(key = "img", name = "图片地址", type = FieldTypeEnum.TEXT, isRequired = true)
        String img();

        @Field(key = "size", name = "尺寸（1-100）%", type = FieldTypeEnum.NUMBER, isRequired = true)
        Integer size();

        @Field(key = "height", name = "高度 px", type = FieldTypeEnum.NUMBER)
        Integer height();
    }

    interface ContentConfig {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT)
        String title();

        @Field(key = "textSize", name = "文本尺寸（1-100）%", type = FieldTypeEnum.NUMBER, isRequired = true)
        Integer size();

        @Field(key = "text", name = "每一行内容文本", type = FieldTypeEnum.TEXT, isArray = true)
        String[] text();
    }
}
