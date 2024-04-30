package com.parch.combine.core.handler;

import com.parch.combine.common.constant.CommonConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.vo.FlowInitVO;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 流程配置处理器
 */
public class BlockHandler {

    /**
     * 保存流程配置集合
     *
     * @param configs 配置集合
     * @return 是否成功
     */
    protected static boolean init(List<Map<String, Object>> configs, Consumer<FlowInitVO> func) {
        if (CheckEmptyUtil.isNotEmpty(configs)) {
            FlowInitVO initResult = ComponentHandler.init(configs);
            initResult.setFlowKey(CommonConstant.PLACEHOLDER);

            // 调用自定义处理函数
            if (func != null) {
                func.accept(initResult);
            }
        }

        return true;
    }
}
