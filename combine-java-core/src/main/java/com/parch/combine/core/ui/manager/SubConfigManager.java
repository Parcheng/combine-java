package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.ui.tools.SubConfigHelper;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class SubConfigManager {

    private final Map<String, List<String>> CONFIGS_SUB_ELEMENTS = new HashMap<>();
    private final Map<String, List<String>> CONFIGS_SUB_TRIGGERS = new HashMap<>();

    public void build(String key, Object config) {
        SubConfigHelper.ResultVO suvVO;
        try {
            suvVO = SubConfigHelper.loadAndReset(config);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            PrintUtil.printError("【ui】【subconfig】【" + key + "】子元素解析异常：" + e.getMessage());
            return;
        }

        if (CheckEmptyUtil.isNotEmpty(suvVO.getElementIds())) {
            CONFIGS_SUB_ELEMENTS.put(key, suvVO.getElementIds());
        }
        if (CheckEmptyUtil.isNotEmpty(suvVO.getTriggerIds())) {
            CONFIGS_SUB_TRIGGERS.put(key, suvVO.getTriggerIds());
        }
    }

    public List<String> getSubElements(String key) {
        return CONFIGS_SUB_ELEMENTS.get(key);
    }

    public List<String> getSubTriggers(String key) {
        return CONFIGS_SUB_TRIGGERS.get(key);
    }
}
