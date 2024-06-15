package com.parch.combine.components.call.api;

import com.parch.combine.components.call.CallLogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

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
