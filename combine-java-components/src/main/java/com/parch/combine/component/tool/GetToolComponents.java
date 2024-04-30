package com.parch.combine.component.tool;

import com.parch.combine.component.tool.crontab.SystemCrontabSettingHandler;
import com.parch.combine.component.tool.lock.ToolLockSettingHandler;
import com.parch.combine.component.tool.semaphore.ToolSemaphoreSettingHandler;
import com.parch.combine.component.tool.sleep.ToolSleepSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取数据处理组件加载器
 */
public class GetToolComponents extends AbsGetComponents {

    public GetToolComponents() {
        super("tool", "工具");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(ToolLockSettingHandler.get());
        setting.add(ToolSemaphoreSettingHandler.get());
        setting.add(ToolSleepSettingHandler.get());
        setting.add(SystemCrontabSettingHandler.get());
        return setting;
    }
}
