package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.manager.AbsPreLoadConfigManager;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.component.base.InitConfig;
import java.util.Map;

/**
 * 流程初始化配置处理器
 */
public class InitConfigManager extends AbsPreLoadConfigManager<InitConfig> {

    @Override
    protected <T extends InitConfig> T initConfig(String id, String type, Map<String, Object> configMap, Class<T> clazz) {
        T initConfigObj;
        if (configMap == null) {
            try {
                initConfigObj = clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                return null;
            }
        } else {
            initConfigObj = TypeConversionUtil.parseJava(configMap, clazz);
        }

        initConfigObj.init();
        return initConfigObj;
    }
}
