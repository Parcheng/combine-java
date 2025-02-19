package com.parch.combine.core.component.tools.compare;

import com.parch.combine.core.common.util.json.JsonUtil;

/**
 * 包含比较处理器
 */
public class ContainCompare {

    /**
     * 比较
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();
        Object target = config.parseTargetValue();
        if (source == null || target == null) {
            return CompareResult.fail();
        }

        String sourceJson = JsonUtil.obj2String(source);
        String targetJson = JsonUtil.obj2String(target);
        boolean success = sourceJson.contains(targetJson);

        return success ? CompareResult.success() : CompareResult.fail();
    }
}
