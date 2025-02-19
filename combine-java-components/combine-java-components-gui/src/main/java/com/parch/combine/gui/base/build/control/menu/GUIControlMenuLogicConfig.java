package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlMenuLogicConfig extends GUIControlLogicConfig {

    @Field(key = "title", name = "导航标题", type = FieldTypeEnum.TEXT)
    String title();

    @Field(key = "checked", name = "默认选中的菜单", type = FieldTypeEnum.TEXT)
    String checked();

    @Field(key = "items", name = "子菜单项", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(MenuConfig.class)
    MenuConfig[] items();

    @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(EventConfig.class)
    EventConfig[] events();

    interface MenuConfig {

        @Field(key = "key", name = "KEY", type = FieldTypeEnum.TEXT)
        @FieldDesc("用户勾选菜单时进行匹配，默认使用 text 文本")
        String key();

        @Field(key = "text", name = "显示文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "items", name = "子菜单项", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(MenuConfig.class)
        MenuConfig[] items();
    }
}
