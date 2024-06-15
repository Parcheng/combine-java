package com.parch.combine.redis.base.lua;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;

public interface RedisLuaLogicConfig extends ILogicConfig {

    @Field(key = "keys", name = "脚本KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("用于脚本中 KEYS[i] 取值")
    @FieldEg(eg = "[\"test_key\"]", desc = "集合中只有一个为 test_key 的脚本KEY")
    String[] keys();

    @Field(key = "args", name = "脚本参数集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("用于脚本中 ARGV[i] 取值")
    @FieldEg(eg = "[\"1\"]", desc = "集合中只有一个为 test_key 的脚本参数")
    String[] args();

    @Field(key = "scriptLines", name = "要执行的脚本", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    @FieldDesc("其中集合每一项表示每一行脚本")
    @FieldEg(eg = "[\"local num = redis.call('incr', KEYS[1])\",\"return num\", \"end\"]", desc = "执行该语句")
    String[] scriptLines();
}
