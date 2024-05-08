package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.TypeConversionUtil;
import com.parch.combine.core.ui.base.dataload.ApiDataLoadConfig;
import com.parch.combine.core.ui.base.dataload.DataLoadConfig;
import com.parch.combine.core.ui.base.dataload.DataLoadTypeEnum;
import com.parch.combine.core.ui.base.dataload.FileDataLoadConfig;

import java.util.Map;

public class DataLoadManager extends AbsPreLoadConfigManager<DataLoadConfig>{

    @Override
    protected DataLoadConfig initConfig(String id, String type, Map<String, Object> configMap) {
        if (configMap == null) {
            PrintUtil.printError("【ui】【dataLoad】【" + id + "】【" + type + "】配置为空");
            return null;
        }

        DataLoadTypeEnum dataLoadType = DataLoadTypeEnum.get(type);
        switch (dataLoadType) {
            case API:
                return TypeConversionUtil.parseJava(configMap, ApiDataLoadConfig.class);
            case FILE:
                return TypeConversionUtil.parseJava(configMap, FileDataLoadConfig.class);
            default:
                PrintUtil.printError("【ui】【dataLoad】【" + id + "】【" + type + "】类型不存在");
                return null;
        }
    }
}
