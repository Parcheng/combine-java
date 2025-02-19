package com.parch.combine.redis.base.lua;

import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldDesc;
import com.parch.combine.core.component.settings.annotations.FieldEg;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

public interface RedisLuaLogicConfig extends ILogicConfig {

    @Field(key = "keys", name = "脚本KEY集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("用于脚本中 KEYS[i] 取值")
    @FieldEg(eg = "[\"test_key\"]", desc = "集合中只有一个为 test_key 的脚本KEY")
    String[] keys();

    @Field(key = "args", name = "脚本参数集合", type = FieldTypeEnum.TEXT, isArray = true)
    @FieldDesc("用于脚本中 ARGV[i] 取值")
    @FieldEg(eg = "[\"1\"]", desc = "集合中只有一个为 test_key 的脚本参数")
    String[] args();

    @Field(key = "precompileKey", name = "脚本预编译KEY", type = FieldTypeEnum.TEXT)
    @FieldDesc("默认使用脚本代码作为KEY, 需要预编译时，建议设置预编译KEY")
    String precompileKey();

    @Field(key = "hasPrecompile", name = "是否预编译", type = FieldTypeEnum.BOOLEAN, defaultValue = "false")
    Boolean hasPrecompile();

    @Field(key = "scriptLines", name = "要执行的脚本", type = FieldTypeEnum.TEXT, isArray = true, isRequired = true)
    @FieldDesc("其中集合每一项表示每一行脚本")
    @FieldEg(eg = "[\"local num = redis.call('incr', KEYS[1])\",\"return num\", \"end\"]", desc = "执行该语句")
    String[] scriptLines();
}
