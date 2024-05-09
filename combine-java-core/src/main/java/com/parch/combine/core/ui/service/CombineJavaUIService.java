package com.parch.combine.core.ui.service;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.vo.CombineConfigVO;
import com.parch.combine.core.ui.vo.CombineInitVO;
import java.util.function.Consumer;

public class CombineJavaUIService implements ICombineJavaUIService {

    private CombineManager combineManager = new CombineManager();

    public void registerAsPath(String path, Consumer<CombineInitVO> func) {
        String configJson = ResourceFileUtil.read(path);
        register(configJson, func);
    }

    @Override
    public void register(String configJson, Consumer<CombineInitVO> func) {
        if (CheckEmptyUtil.isEmpty(configJson)) {
            return;
        }

        CombineConfigVO config = JsonUtil.deserialize(configJson, CombineConfigVO.class);
        if (config == null) {
            return;
        }

        combineManager.init(config, func);
    }

    @Override
    public String getPage(String key) {
        return combineManager.getPage();
    }
}
