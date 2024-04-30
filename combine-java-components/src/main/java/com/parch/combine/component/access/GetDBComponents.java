package com.parch.combine.component.access;

import com.parch.combine.component.access.redis.command.RedisCommandSettingHandler;
import com.parch.combine.component.access.redis.lock.RedisLockSettingHandler;
import com.parch.combine.component.access.redis.lua.RedisLuaSettingHandler;
import com.parch.combine.component.access.rocketmq.consumer.RocketMQConsumerSettingHandler;
import com.parch.combine.component.access.rocketmq.product.RocketMQProductSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.component.access.mysql.MysqlSettingHandler;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取DB组件实现类
 */
public class GetDBComponents extends AbsGetComponents {

    public GetDBComponents() {
        super("access", "外部接入软件组件");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(MysqlSettingHandler.get());
        setting.add(RedisCommandSettingHandler.get());
        setting.add(RedisLockSettingHandler.get());
        setting.add(RedisLuaSettingHandler.get());
        setting.add(RocketMQConsumerSettingHandler.get());
        setting.add(RocketMQProductSettingHandler.get());
        return setting;
    }
}
