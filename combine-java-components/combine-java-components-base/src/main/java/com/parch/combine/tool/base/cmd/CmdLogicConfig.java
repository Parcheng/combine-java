package com.parch.combine.tool.base.cmd;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;

public interface CmdLogicConfig extends ILogicConfig {

    @Field(key = "command", name = "命令集合", type = FieldTypeEnum.TEXT, isRequired = true, isArray = true)
    String[] commands();
}
