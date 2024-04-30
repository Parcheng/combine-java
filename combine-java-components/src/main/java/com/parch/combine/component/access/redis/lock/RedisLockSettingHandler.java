package com.parch.combine.component.access.redis.lock;

import com.parch.combine.component.access.redis.RedisSettingsHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.ComponentSetting;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

/**
 * 设置处理器
 */
public class RedisLockSettingHandler {

    /**
     * 获取
     *
     * @return 组件设置
     */
    public static ComponentSetting get() {
        ComponentSettingBuilder builder = new ComponentSettingBuilder("redis.lock", "Redis分布式锁组件", true, RedisLockComponent.class);
        builder.addDesc("简单版分布式锁，暂时不支持“未获取到锁时自旋等待功能”、“流程未执行完锁自动续期功能”");


        RedisSettingsHandler.build(builder);
        builder.addProperty(PropertyTypeEnum.INIT, "autoUnLock", FieldTypeEnum.BOOLEAN, "是否在流程结束时自动释放锁",  true, false, "true");


        builder.addProperty(PropertyTypeEnum.LOGIC, "count", FieldTypeEnum.NUMBER, "锁状态：正数表示加锁，否则解锁", true, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "key", FieldTypeEnum.TEXT, "锁的KEY", false, false, "随机生成");
        builder.addProperty(PropertyTypeEnum.LOGIC, "value", FieldTypeEnum.TEXT, "锁的值", false, false);
        builder.addPropertyDesc(PropertyTypeEnum.LOGIC, "value", "如果不为空，则解锁时必须与加锁时的 KEY 和 VALUE 都一致才能成功解锁");
        builder.addProperty(PropertyTypeEnum.LOGIC, "keyPrefix", FieldTypeEnum.TEXT, "锁的KEY前缀", false, false);
        builder.addProperty(PropertyTypeEnum.LOGIC, "expire", FieldTypeEnum.NUMBER, "锁有效期（毫秒）", false, false, "锁不会过期");



        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "count", "1", "加锁");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "count", "-1", "解锁");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "key", "user_12345", "锁的 KEY 为 user_12345");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "value", "ORDER_NO_123445", "锁的值为 ORDER_NO_123445");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "keyPrefix", "order_", "锁的 KEY 会在前面拼接 order_");
        builder.addPropertyEg(PropertyTypeEnum.LOGIC, "expire", "10000", "有效期为 10000 毫秒");


        builder.setResult("是否成功获取锁：true | false", false);
        return builder.get();
    }
}
