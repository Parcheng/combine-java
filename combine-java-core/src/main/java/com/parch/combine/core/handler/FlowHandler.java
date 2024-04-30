package com.parch.combine.core.handler;

import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.core.vo.FlowInitVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 流程配置处理器
 */
public class FlowHandler {

    /**
     * 流程组件集合配置缓存池
     */
    private static final Map<String, List<String>> FLOW_COMPONENT_ID_MAP = new HashMap<>();

    /**
     * 保存流程配置集合
     *
     * @param flowConfigs 流程配置集合
     * @return 是否成功
     */
    protected static boolean init(Map<String, List<Map<String, Object>>> flowConfigs, Consumer<FlowInitVO> func) {
        // 初始化每个接口的逻辑配置
        Set<String> urlPaths = flowConfigs.keySet();
        for (String urlPath : urlPaths) {
            FlowInitVO initResult = ComponentHandler.init(flowConfigs.get(urlPath));
            initResult.setFlowKey(urlPath);

            // 成功才保存
            if (initResult.isSuccess()) {
                String[] keyArr = FlowKeyUtil.parseKey(urlPath);
                FlowHandler.save(keyArr[0], keyArr[1], initResult.getComponentIds());
            }

            // 调用自定义处理函数
            if (func != null) {
                func.accept(initResult);
            }
        }

        return true;
    }

    /**
     * 保存流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 是否成功
     */
    public static boolean save(String domain, String function, List<String> configs) {
        return save(getKey(domain, function), configs);
    }

    /**
     * 保存流程配置集合
     *
     * @param key KEY
     * @return 是否成功
     */
    private static boolean save(String key, List<String> configs) {
        FLOW_COMPONENT_ID_MAP.put(key, configs);
        return true;
    }

    /**
     * 获取流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 流程配置集合
     */
    public static List<String> list(String domain, String function) {
        return list(getKey(domain, function));
    }

    /**
     * 获取流程配置集合
     *
     * @param key KEY
     * @return 流程配置集合
     */
    private static List<String> list(String key) {
        return FLOW_COMPONENT_ID_MAP.get(key);
    }

    /**
     * 获取KEY
     *
     * @param domain 领域
     * @param function 函数
     * @return KEY
     */
    private static String getKey(String domain, String function) {
        return domain + "=>" + function;
    }
}
