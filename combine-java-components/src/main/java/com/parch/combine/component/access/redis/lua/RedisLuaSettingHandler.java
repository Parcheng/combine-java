package com.parch.combine.component.access.redis.lua;

import com.parch.combine.component.access.redis.RedisSettingsHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class RedisLuaSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("redis.lua", "Redis脚本组件", true, RedisLuaComponent.class);
        // builder.addDesc("");

        RedisSettingsHandler.build(builder);


        builder.addProperty(PropertyTypeEnum.LOGIC, "keys", FieldTypeEnum.TEXT, "脚本KEY集合", false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "keys", "用于脚本中 KEYS[i] 取值");
        builder.addProperty(PropertyTypeEnum.LOGIC, "args", FieldTypeEnum.TEXT, "脚本参数集合", false, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "args", "用于脚本中 ARGV[i] 取值");
        builder.addProperty(PropertyTypeEnum.LOGIC, "scriptLines", FieldTypeEnum.TEXT, "要执行的脚本", true, true);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "scriptLines", "其中集合每一项表示每一行脚本");


        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "keys", "[\"test_key\"]", "集合中只有一个为 test_key 的脚本KEY");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "args", "[\"1\"]", "集合中只有一个为 test_key 的脚本参数");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "scriptLines", "[\"local num = redis.call('incr', KEYS[1])\",\"return num\", \"end\"", "执行该语句");


        builder.setResult("脚本执行结果", false);
        return builder.get();
    }
}
