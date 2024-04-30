package com.parch.combine.core.handler;

import com.parch.combine.common.constant.CommonConstant;
import com.parch.combine.common.constant.SymbolConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.vo.FlowInitVO;
import com.parch.combine.core.vo.FlowAspectVO;

import java.util.*;
import java.util.function.Consumer;

/**
 * 流程切面处理器
 */
public class FlowAspectHandler {

    /**
     * 前置流程配置
     */
    private static final List<AspectConfig> BEFORE_FLOWS = new ArrayList<>();

    /**
     * 后置流程配置
     */
    private static final List<AspectConfig> AFTER_FLOWS = new ArrayList<>();

    /**
     * 初始化前置流程集合
     *
     * @param interceptors 拦截器配置集合
     * @return 是否成功
     */
    protected static boolean initBefore(List<FlowAspectVO> interceptors, Consumer<FlowInitVO> func) {
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
    protected static boolean initAfter(List<FlowAspectVO> interceptors, Consumer<FlowInitVO> func) {
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
    private static boolean init(List<AspectConfig> data, List<FlowAspectVO> aspects, Consumer<FlowInitVO> func) {
        if (CheckEmptyUtil.isNotEmpty(aspects)) {
            for (FlowAspectVO aspect : aspects) {
                // 初始化拦截器的组件
                FlowInitVO initResult = ComponentHandler.init(aspect.getFlow());
                initResult.setFlowKey(CheckEmptyUtil.isEmpty(aspect.getId()) ? CommonConstant.PLACEHOLDER : aspect.getId());

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

    public static DataResult executeBefore(String flowKey) {
        return execute(flowKey, BEFORE_FLOWS);
    }

    public static DataResult executeAfter(String flowKey) {
        return execute(flowKey, AFTER_FLOWS);
    }

    /**
     * 执行拦截器
     *
     * @param flowKey 流程KEY
     * @return 结果
     */
    private static DataResult execute(String flowKey, List<AspectConfig> aspectList) {
        DataResult result;
        for (AspectConfig item : aspectList) {
            if (item.contain(flowKey)) {
                result = ComponentHandler.executeComponents(item.getComponents());
                if (item.getFailStop() && !result.getSuccess()) {
                    return result;
                }
            }
        }

        return DataResult.success(true);
    }

    /**
     * 拦截器配置
     */
    private static class AspectConfig {
        private String id;

        private Integer order;

        private Boolean failStop;

        private List<String> components;

        private List<String[]> includes;

        private List<String[]> excludes;

        public AspectConfig(String id, Integer order, Boolean failStop, List<String> components, List<String[]> includes, List<String[]> excludes) {
            this.id = id;
            this.order = order == null ? Integer.MAX_VALUE : order;
            this.failStop = failStop == null ? true : failStop;
            this.components = components;
            this.includes = includes;
            this.excludes = excludes;
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
                    boolean domainPass = SymbolConstant.ASTERISK.equals(include[0]) || flowKeyArr[0].equals(include[0]);
                    boolean functionPass = SymbolConstant.ASTERISK.equals(include[1]) || flowKeyArr[1].equals(include[1]);
                    if (domainPass && functionPass) {
                        return true;
                    }
                }

                return false;
            }

            if (CheckEmptyUtil.isNotEmpty(excludes)) {
                for (String[] exclude : excludes) {
                    boolean domainPass = SymbolConstant.ASTERISK.equals(exclude[0]) || flowKeyArr[0].equals(exclude[0]);
                    boolean functionPass = SymbolConstant.ASTERISK.equals(exclude[1]) || flowKeyArr[1].equals(exclude[1]);
                    if (domainPass && functionPass) {
                        return false;
                    }
                }

                return true;
            }

            return true;
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
