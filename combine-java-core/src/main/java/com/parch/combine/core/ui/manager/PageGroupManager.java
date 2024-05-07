//package com.parch.combine.core.ui.manager;
//
//import com.parch.combine.core.common.util.FlowKeyUtil;
//import com.parch.combine.core.component.vo.FlowInitVO;
//import com.parch.combine.core.ui.vo.PageElementGroupVO;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Consumer;
//
//public class PageGroupManager {
//
//    /**
//     * 流程组件集合配置缓存池
//     */
//    private final Map<String, List<String>> ELEMENTS_ID_MAP = new HashMap<>();
//
//    protected PageElementLogicManager pageElementManager;
//
//    public PageGroupManager(PageElementLogicManager pageElementManager) {
//        this.pageElementManager = pageElementManager;
//    }
//
//    /**
//     * 保存流程配置集合
//     *
//     * @param groups 流程配置集合
//     * @return 是否成功
//     */
//    protected boolean init(List<PageElementGroupVO> groups, Consumer<FlowInitVO> func) {
//        for (PageElementGroupVO group : groups) {
//            FlowInitVO initResult = component.init(scopeKey, flowConfigs.get(urlPath));
//            initResult.setFlowKey(urlPath);
//
//            // 成功才保存
//            if (initResult.isSuccess()) {
//                String[] keyArr = FlowKeyUtil.parseKey(urlPath);
//                save(keyArr[0], keyArr[1], initResult.getComponentIds());
//            }
//
//            // 调用自定义处理函数
//            if (func != null) {
//                func.accept(initResult);
//            }
//        }
//
//        return true;
//    }
//
//    /**
//     * 保存流程配置集合
//     *
//     * @param domain 域
//     * @param function 功能
//     * @return 是否成功
//     */
//    public boolean save(String domain, String function, List<String> configs) {
//        return save(getKey(domain, function), configs);
//    }
//
//    /**
//     * 保存流程配置集合
//     *
//     * @param key KEY
//     * @return 是否成功
//     */
//    private boolean save(String key, List<String> configs) {
//        FLOW_COMPONENT_ID_MAP.put(key, configs);
//        return true;
//    }
//
//    /**
//     * 获取流程配置集合
//     *
//     * @param domain 域
//     * @param function 功能
//     * @return 流程配置集合
//     */
//    public List<String> list(String domain, String function) {
//        return list(getKey(domain, function));
//    }
//
//    /**
//     * 获取流程配置集合
//     *
//     * @param key KEY
//     * @return 流程配置集合
//     */
//    private List<String> list(String key) {
//        return FLOW_COMPONENT_ID_MAP.get(key);
//    }
//
//    /**
//     * 获取KEY
//     *
//     * @param domain 领域
//     * @param function 函数
//     * @return KEY
//     */
//    private String getKey(String domain, String function) {
//        return domain + "=>" + function;
//    }
//}
