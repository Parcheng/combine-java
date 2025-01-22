package com.parch.combine.logic.base.loop;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;

public interface LogicLoopInitConfig extends IInitConfig {

    String INDEX_FILED = "$index";
    String ITEM_FILED = "$item";

    @Field(key = "indexPropertyName", name = "索引属性名", type = FieldTypeEnum.TEXT, defaultValue = INDEX_FILED)
    String indexPropertyName();

    @Field(key = "itemPropertyName", name = "迭代项属性名", type = FieldTypeEnum.TEXT, defaultValue = ITEM_FILED)
    String itemPropertyName();
}
