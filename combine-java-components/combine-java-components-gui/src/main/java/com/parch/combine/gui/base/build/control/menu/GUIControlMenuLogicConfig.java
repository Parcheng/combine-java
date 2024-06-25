package com.parch.combine.gui.base.build.control.menu;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.gui.base.build.GUIControlLogicConfig;
import com.parch.combine.gui.core.event.EventConfig;

public interface GUIControlMenuLogicConfig extends GUIControlLogicConfig {

    @Field(key = "checkedPath", name = "默认选中的菜单", type = FieldTypeEnum.TEXT, isArray = true)
    String[] checkedPath();

    @Field(key = "items", name = "子菜单项", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(MenuConfig.class)
    MenuConfig[] items();

    interface MenuConfig {

        @Field(key = "key", name = "KEY", type = FieldTypeEnum.TEXT)
        @FieldDesc("用户勾选菜单时进行匹配，默认使用 text 文本")
        String key();

        @Field(key = "text", name = "显示文本", type = FieldTypeEnum.TEXT, isRequired = true)
        String text();

        @Field(key = "items", name = "子菜单项", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldObject(MenuConfig.class)
        MenuConfig[] items();

        @Field(key = "events", name = "事件配置集合", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldObject(EventConfig.class)
        EventConfig[] events();
    }
}
