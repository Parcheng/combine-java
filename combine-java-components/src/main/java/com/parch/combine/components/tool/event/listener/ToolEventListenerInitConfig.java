package com.parch.combine.components.tool.event.listener;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.tools.thread.ThreadPoolConfig;

public interface ToolEventListenerInitConfig extends IInitConfig {

    @Field(key = "pool", name = "线程池配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(ThreadPoolConfig.class)
    ThreadPoolConfig pool();
}
