package com.parch.combine.tool.base.countdown;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.tools.thread.ThreadPoolConfig;

public interface ToolCountDownLatchInitConfig extends IInitConfig {

    @Field(key = "pool", name = "线程池配置", type = FieldTypeEnum.OBJECT, isRequired = true)
    @FieldObject(ThreadPoolConfig.class)
    ThreadPoolConfig pool();

}
