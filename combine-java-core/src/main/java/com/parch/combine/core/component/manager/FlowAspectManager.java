package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.vo.FlowAspectVO;
import com.parch.combine.core.component.vo.CombineInitVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 流程切面处理器
 */
class FlowAspectManager {

    private String scopeKey;

    /**
     * 前置流程配置
     */
    private final List<AspectConfig> BEFORE_FLOWS = new ArrayList<>();

    /**
     * 后置流程配置
     */
    private final List<AspectConfig> AFTER_FLOWS = new ArrayList<>();

    protected ComponentManager component;

    public FlowAspectManager(String scopeKey, ComponentManager component) {
        this.scopeKey = scopeKey;
        this.component = component;
    }

    /**
     * 初始化前置流程集合
     *
     * @param interceptors 拦截器配置集合
     * @return 是否成功
     */
    protected boolean initBefore(List<FlowAspectVO> interceptors, Consumer<CombineInitVO> func) {
        synchronized(BEFORE_FLOWS) {
            return init(BEFORE_FLOWS, interceptors, func);
        }
    }

    /**
     * 初始化后置流程集合
     *
     * @param interceptors 拦截器配置集合
     * @return 是否成功
     */
    protected boolean initAfter(List<FlowAspectVO> interceptors, Consumer<CombineInitVO> func) {
        synchronized(AFTER_FLOWS) {
            return init(AFTER_FLOWS, interceptors, func);
        }
    }

    /**
     * 初始化拦截器
     *
     * @param aspects 切面配置集合
     * @return 是否成功
     */
    private boolean init(List<AspectConfig> data, List<FlowAspectVO> aspects, Consumer<CombineInitVO> func) {
        if (CheckEmptyUtil.isNotEmpty(aspects)) {
            for (FlowAspectVO aspect : aspects) {
                // 初始化拦截器的组件
                CombineInitVO initResult = component.init(aspect.getFlow());
                initResult.setFlowKey(CheckEmptyUtil.isEmpty(aspect.getId()) ? "-" : aspect.getId());

                // 构建配置对象并保存
                AspectConfig config = new AspectConfig(aspect.getId(), aspect.getOrder(), aspect.getFailStop(),
                        initResult.getComponentIds(), aspect.getIncludes(), aspect.getExcludes());
                data.add(config);

                // 调用自定义处理函数
                if (func != null) {
                    func.accept(initResult);
                }
            }

            // 按优先级排序
            data.sort(Comparator.comparing(AspectConfig::getOrder));
        }

        return true;
    }

    public ComponentDataResult executeBefore(String flowKey) {
        return execute(flowKey, BEFORE_FLOWS);
    }

    public ComponentDataResult executeAfter(String flowKey) {
        return execute(flowKey, AFTER_FLOWS);
    }

    /**
     * 执行拦截器
     *
     * @param flowKey 流程KEY
     * @return 结果
     */
    private ComponentDataResult execute(String flowKey, List<AspectConfig> aspectList) {
        ComponentDataResult result = null;
        for (AspectConfig item : aspectList) {
            if (item.contain(flowKey)) {
                result = component.executeComponents(item.getComponents());
                if (item.getFailStop() && !result.getSuccess()) {
                    return result;
                }
            }
        }

        return result == null ? ComponentDataResult.success(true) : result;
    }

    /**
     * 拦截器配置
     */
    private static class AspectConfig {
        private final String id;

        private final Integer order;

        private final Boolean failStop;

        private final List<String> components;

        private final List<String[]> includes;

        private final List<String[]> excludes;

        public AspectConfig(String id, Integer order, Boolean failStop, List<String> components, List<String[]> includes, List<String[]> excludes) {
            this.id = id;
            this.order = order == null ? Integer.MAX_VALUE : order;
            this.failStop = failStop == null || failStop;
            this.components = components;
            this.includes = CheckEmptyUtil.isEmpty(includes) ? null : includes.stream().map(this::replacePath).collect(Collectors.toList());
            this.excludes = CheckEmptyUtil.isEmpty(excludes) ? null : excludes.stream().map(this::replacePath).collect(Collectors.toList());
        }

        /**
         * 判断拦截器是否包含该流程
         *
         * @param flowKey 流程KEY
         * @return 是否包含
         */
        public boolean contain(String flowKey) {
            String[] flowKeyArr = FlowKeyUtil.parseKey(flowKey);
            if (CheckEmptyUtil.isNotEmpty(includes)) {
                for (String[] include : includes) {
                    if (match(flowKeyArr, include)) {
                        return true;
                    }
                }

                return false;
            }

            if (CheckEmptyUtil.isNotEmpty(excludes)) {
                for (String[] exclude : excludes) {
                    if (match(flowKeyArr, exclude)) {
                        return false;
                    }
                }

                return true;
            }

            return true;
        }

        private String[] replacePath(String[] configPath) {
            if (configPath == null || configPath.length == 0) {
                configPath = new String[]{"*", "*"};
            } else if  (configPath.length == 1) {
                configPath = new String[]{configPath[0], "*"};
            }

            if (CheckEmptyUtil.isEmpty(configPath[0])) {
                configPath[0] = "*";
            }

            configPath[0] = configPath[0].replaceAll("\\$", "[\\$]+*").replaceAll("[*]+", ".*?");
            configPath[1] = configPath[1].replaceAll("[*]+", ".*?");
            return configPath;
        }

        private boolean match(String[] flowArr, String[] config) {
            return flowArr[0].matches(config[0]) && flowArr[0].matches(config[1]);
        }

        public String getId() {
            return id;
        }

        public List<String> getComponents() {
            return components;
        }

        public Integer getOrder() {
            return order;
        }

        public Boolean getFailStop() {
            return failStop;
        }
    }
}
