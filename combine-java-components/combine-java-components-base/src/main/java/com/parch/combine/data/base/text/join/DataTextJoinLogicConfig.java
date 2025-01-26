package com.parch.combine.data.base.text.join;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface DataTextJoinLogicConfig extends ILogicConfig {

    @Field(key = "items", name = "配置集合", type = FieldTypeEnum.CONFIG, isArray = true, isRequired = true)
    @FieldObject(JoinItem.class)
    JoinItem[] items();

    interface JoinItem {

        @Field(key = "texts", name = "要拼接的数据集合", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
        String[] texts();

        @Field(key = "target", name = "结果写入目标", type = FieldTypeEnum.EXPRESSION, parseExpression = false)
        String target();

        @Field(key = "separator", name = "拼接分隔符", type = FieldTypeEnum.TEXT)
        String separator();
    }
}
