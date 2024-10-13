package com.parch.combine.file.base.build.pdf;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.file.base.build.BaseFileBuildLogicConfig;

public interface FileBuildPdfLogicConfig extends BaseFileBuildLogicConfig {

    @Field(key = "header", name = "文档头信息", type = FieldTypeEnum.CONFIG)
    @FieldObject(HeaderConfig.class)
    HeaderConfig header();

    @Field(key = "pages", name = "页面配置", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(PageConfig.class)
    PageConfig[] pages();

    interface HeaderConfig {

        @Field(key = "title", name = "标题", type = FieldTypeEnum.TEXT, defaultValue = "NONE")
        String title();

        @Field(key = "author", name = "作者", type = FieldTypeEnum.TEXT, defaultValue = "NONE")
        String author();

        @Field(key = "subject", name = "主题", type = FieldTypeEnum.TEXT, defaultValue = "NONE")
        String subject();

        @Field(key = "keywords", name = "关键字", type = FieldTypeEnum.TEXT, defaultValue = "NONE")
        @FieldDesc("多个关键字用逗号分隔")
        String keywords();
    }

    interface PageConfig {

        @Field(key = "type", name = "纸张类型", type = FieldTypeEnum.TEXT, isRequired = true)
        String type();

        @Field(key = "contents", name = "文本配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(ContentConfig.class)
        ContentConfig[] contents();
    }

    interface ContentConfig {

        @Field(key = "x", name = "X轴位置", type = FieldTypeEnum.NUMBER, isRequired = true)
        Float x();

        @Field(key = "y", name = "Y轴位置", type = FieldTypeEnum.NUMBER, isRequired = true)
        Float y();

        @Field(key = "texts", name = "文本集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        String[] texts();

        @Field(key = "fontPath", name = "字体文件路径", type = FieldTypeEnum.TEXT, isRequired = true)
        String fontPath();

        @Field(key = "fontSize", name = "文字大小", type = FieldTypeEnum.NUMBER, defaultValue = "12")
        Integer fontSize();
    }
}
