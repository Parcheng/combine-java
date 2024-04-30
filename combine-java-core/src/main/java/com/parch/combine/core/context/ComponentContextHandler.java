package com.parch.combine.core.context;

import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 全局上下文对象
 *
 * @author parch
 * @date 2023/6/1
 */
public class ComponentContextHandler {

    /**
     * 线程ThreadLocal缓存
     */
    private static final ThreadLocal<ComponentContext> CACHE = new ThreadLocal<>();

    /**
     * 初始化全局上下文对象
     *
     * @param params 入参
     * @return 上下文对象
     */
    public static void init(String key, Map<String, Object> params, Map<String, String> headers) {
        ComponentContext context = new ComponentContext();
        context.setFlowKey(key);
        context.setParams(params == null ? new HashMap<>(0) : params);
        context.setHeaders(headers == null ? new HashMap<>(0) : headers);
        context.setResultMap(new LinkedHashMap<>());
        context.setData(new HashMap<>());
        context.setExecutedComponents(new ArrayList<>());
        context.setVariables(new HashMap<>());

        // 初始化 requestId
        String requestIdKey = GlobalContextHandler.get().getRequestIdKey();
        if (context.getHeaders().containsKey(requestIdKey)) {
            context.setRequestId((context.getHeaders().get(requestIdKey)));
        } else if (context.getParams().containsKey(requestIdKey)) {
            context.setRequestId((String) context.getParams().get(requestIdKey));
        } else {
            context.setRequestId(UUID.randomUUID().toString());
        }

        CACHE.set(context);
    }

    /**
     * 获取全局上下文对象
     *
     * @return 上下文对象
     */
    public static ComponentContext getContext() {
        return CACHE.get();
    }

    /**
     * 获取流程变量集合
     *
     * @return 流程变量集合
     */
    public static Map<String, Object> getVariables() {
        return CACHE.get().getVariables();
    }

    /**
     * 重新设置全局上下文对象
     *
     * @param context 上下文对象
     */
    public static void resetContext(ComponentContext context) {
        CACHE.set(context);
    }

    /**
     * 获取上一个组件的执行结果
     *
     * @return 执行结果
     */
    public static DataResult getLastResultData() {
        return CACHE.get().getLastResult();
    }

    /**
     * 获取组件的结果集合
     *
     * @return 结果
     */
    public static Map<String, DataResult> getResultData() {
        return CACHE.get().getResultMap();
    }

    /**
     * 获取某个组件的结果
     *
     * @param key 组件Key
     * @return 结果
     */
    public static DataResult getResultData(String key) {
        return CACHE.get().getResultMap().get(key);
    }

    /**
     * 设置某个组件的结果
     *
     * @param key 组件Key
     */
    public static void setResultData(String key, DataResult dataResult) {
        CACHE.get().getResultMap().put(key, dataResult);
        CACHE.get().setLastResult(dataResult);
    }

    /**
     * 获取流程KEY
     *
     * @return 流程KEY
     */
    public static String getFlowKey() {
        return CACHE.get().getFlowKey();
    }

    /**
     * 获取请求ID
     *
     * @return 流程KEY
     */
    public static String getRequestId() {
        return CACHE.get().getRequestId();
    }


    /**
     * 获取某个组件的结果
     *
     * @return 结果
     */
    public static Map<String, Object> getParams() {
        return CACHE.get().getParams();
    }

    /**
     * 获取流程请求头集合
     *
     * @return 请求头集合
     */
    public static Map<String, String> getHeader() {
        return CACHE.get().getHeaders();
    }

    /**
     * 设置某个组件的结果
     *
     * @param key KEY
     * @param data 数据
     */
    public static void setRuntimeData(String key, Object data) {
        Map<String, Object> runtimeData;
        synchronized (runtimeData = CACHE.get().getData()) {
            runtimeData.put(key, data);
        }
    }

    /**
     * 添加某个组件的结果
     *
     * @param key KEY
     * @param data 数据
     */
    @SuppressWarnings("unchecked")
    public static void addRuntimeData(String key, Object data) {
        Map<String, Object> runtimeData;
        synchronized (runtimeData = CACHE.get().getData()) {
            Collection<Object> listValue;
            Object value = runtimeData.get(key);
            if (!(value instanceof Collection)) {
                listValue = new ArrayList<>();
                if (value != null) {
                    listValue.add(value);
                }
            } else {
                listValue = (Collection<Object>) value;
            }

            listValue.add(data);
        }
    }

    /**
     * 获取某个组件的结果
     *
     * @param key KEY
     * @return 数据
     */
    public static Object getRuntimeData(String key) {
        return CACHE.get().getData().get(key);
    }

    /**
     * 设置当前执行的组件
     *
     * @param component 当前组件
     */
    public static void setCurrComponent(AbsComponent<?, ?> component) {
        CACHE.get().setCurrComponent(component);
    }

    /**
     * 清除当前执行的组件
     */
    public static void clearCurrComponent() {
        CACHE.get().setCurrComponent(null);
    }

    /**
     * 获取当前执行的组件
     *
     * @return 当前组件
     */
    public static AbsComponent<?, ?> getCurrComponent() {
        return CACHE.get() == null ? null : CACHE.get().getCurrComponent();
    }

    /**
     * 添加已经执行过的组件
     *
     * @param component 组件
     */
    public static void addExecutedComponent(AbsComponent<?, ?> component) {
        CACHE.get().getExecutedComponents().add(component);
    }

    /**
     * 获取已经执行过的组件集合
     *
     * @return 当前组件
     */
    public static List<AbsComponent<?, ?>> getExecutedComponents() {
        return CACHE.get().getExecutedComponents();
    }

    /**
     * 清除上下文
     */
    public static void clear() {
        CACHE.remove();
    }
}
