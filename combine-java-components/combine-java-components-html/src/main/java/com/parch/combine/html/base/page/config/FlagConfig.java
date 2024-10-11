package com.parch.combine.html.base.page.config;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface FlagConfig {

    @Field(key = "element", name = "元素标识", type = FieldTypeEnum.TEXT, defaultValue = "$e")
    @FieldDesc("在变量中使用 #{$e.my_from} 时，表示获取 ID 为 my_from 的元素实例的值")
    String element();

    @Field(key = "constant", name = "常量标识", type = FieldTypeEnum.TEXT, defaultValue = "$c")
    @FieldDesc("在变量中使用 #{$c.name} 时，表示从常量中取 name 属性的值")
    String constant();

    @Field(key = "dataLoad", name = "数据加载标识", type = FieldTypeEnum.TEXT, defaultValue = "$ld")
    @FieldDesc("在变量中使用 #{$ld.user_list} 时，表示获取 ID 为 user_list 的数据加载的缓存结果")
    String dataLoad();

    @Field(key = "localStorage", name = "浏览器localStorage标识", type = FieldTypeEnum.TEXT, defaultValue = "$ls")
    @FieldDesc("在变量中使用 #{$ls.token} 时，表示从浏览器 localStorage 中取 token 属性的值")
    String localStorage();
}
