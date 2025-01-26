package com.parch.combine.call.base.api;

import com.parch.combine.call.base.CallLogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface CallApiLogicConfig extends CallLogicConfig {

    @Field(key = "mode", name = "调用方式", type = FieldTypeEnum.TEXT, isRequired = true)
    @FieldDesc("可选值 POST | GET")
    @FieldEg(eg = "GET", desc = "以GET方式请求")
    String mode();

    @Field(key = "retry", name = "重试次数", type = FieldTypeEnum.NUMBER, defaultValue = "1")
    Integer retry();

    @Field(key = "timeout", name = "超时时间", type = FieldTypeEnum.NUMBER, defaultValue = "5000")
    Integer timeout();
}
