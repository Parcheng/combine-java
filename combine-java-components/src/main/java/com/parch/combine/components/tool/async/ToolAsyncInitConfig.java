package com.parch.combine.components.tool.async;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.component.tools.thread.ThreadPoolConfig;

public class ToolAsyncInitConfig extends InitConfig {

    @Field(key = "pool", name = "线程池配置", type = FieldTypeEnum.NUMBER)
    @FieldObject(type = ThreadPoolConfig.class)
    private ThreadPoolConfig pool;

    @Override
    public void init() {
        if (this.pool == null) {
            pool = new ThreadPoolConfig();
        }
        pool.init();
    }

    public ThreadPoolConfig getPool() {
        return pool;
    }

    public void setPool(ThreadPoolConfig pool) {
        this.pool = pool;
    }
}
