package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.ui.base.ElementConfig;
import com.parch.combine.core.ui.base.ElementLogicConfig;
import com.parch.combine.core.ui.handler.ElementClassHandler;

import java.util.*;

public class PageElementLogicManager extends AbsPreLoadConfigManager<ElementLogicConfig>{

    @Override
    protected ElementLogicConfig initConfig(String id, String type, Map<String, Object> configMap) {
        ElementConfig<?,?> elementConfig = ElementClassHandler.get(type);
        if (elementConfig == null) {
            PrintUtil.printError("【ui】【elementLogic】【" + id + "】【" + type + "】类型未注册");
            return null;
        }

        ElementLogicConfig newConfig = elementConfig.buildLogicConfig(configMap);
        if (newConfig == null) {
            return null;
        }

        newConfig.init();
        return newConfig;
    }
}
