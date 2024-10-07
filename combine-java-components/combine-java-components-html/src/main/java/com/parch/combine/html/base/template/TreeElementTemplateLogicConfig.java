package com.parch.combine.html.base.template;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldRef;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;


import java.util.List;

public interface TreeElementTemplateLogicConfig extends ILogicConfig {

    @Field(key = "config", name = "样式模板配置", type = FieldTypeEnum.CONFIG, isRequired = true)
    Config config();

    interface Config extends ElementTemplateConfig {

        @Field(key = "tree", name = "树DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig tree();

        @Field(key = "item", name = "树项DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig item();

        @Field(key = "levelItems", name = "每个层级的树项DOM配置", type = FieldTypeEnum.CONFIG, isArray = true)
        @FieldRef(DomConfig.class)
        List<DomConfig> levelItems();

        @Field(key = "itemActive", name = "树项选中时DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemActive();

        @Field(key = "itemText", name = "树项文本DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemText();

        @Field(key = "itemTree", name = "树项子树DOM配置", type = FieldTypeEnum.CONFIG)
        @FieldRef(DomConfig.class)
        DomConfig itemTree();
    }
}
