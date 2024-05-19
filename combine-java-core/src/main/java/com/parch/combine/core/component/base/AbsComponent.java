package com.parch.combine.core.component.base;

import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.error.SystemErrorEnum;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.handler.CombineManagerHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.common.util.TypeConversionUtil;
import java.util.*;

/**
 * 组件公共父类
 */
public abstract class AbsComponent<T extends InitConfig, R extends LogicConfig> {

    private String id;

    private String type;

    private String scopeKey;

    private String name;

    private T initConfig;

    private R logicConfig;

    private Class<T> initConfigClass;

    private Class<R> logicConfigClass;

    protected CombineManager manager;

    /**
     * 构造器
     *
     * @param initConfigClass 初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public AbsComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        this.initConfigClass = initConfigClass;
        this.logicConfigClass = logicConfigClass;
    }

    /**
     * 初始化配置
     *
     * @param logicConfig 逻辑配置
     */
    public final void initConfig(Map<String, Object> logicConfig) {
        // 加载逻辑配置
        if (logicConfig == null) {
            logicConfig = new HashMap<>();
        }
        this.logicConfig = TypeConversionUtil.parseJava(logicConfig, logicConfigClass);
        this.logicConfig.init();
        this.initConfig = manager.getInitConfig().get(this.logicConfig.getRef(), this.logicConfig.getType(), initConfigClass);
    }

    /**
     * 运行
     *
     * @return 结果
     */
    public DataResult run() {
        try {
            // 向上下文中设置当前执行的组件对象
            ComponentContextHandler.setCurrComponent(this);

            // 执行组件逻辑
            DataResult result = this.execute();

            // 将执行结果设置到上下文中
            ComponentContextHandler.setResultData(this.getId(), result);

            // 清当前执行的组件对象
            ComponentContextHandler.clearCurrComponent();

            return result;
        } catch (Exception e) {
            ComponentErrorHandler.print(SystemErrorEnum.SYSTEM_ERROR, e);
            return DataResult.fail(SystemErrorEnum.SYSTEM_ERROR);
        } finally {
            // 将执当前组件加入到已执行组件集合中
            ComponentContextHandler.addExecutedComponent(this);
        }
    }

    /**
     * 检测配置
     *
     * @return 初始化错误信息集合
     */
    public abstract List<String> init();

    /**
     * 执行
     *
     * @return 结果
     */
    protected abstract DataResult execute();

    /**
     * 结束函数
     *
     * @return 是否成功
     */
    public boolean end() {
        return true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getInitConfig() {
        return initConfig;
    }

    public void setInitConfig(T initConfig) {
        this.initConfig = initConfig;
    }

    public R getLogicConfig() {
        return logicConfig;
    }

    public void setLogicConfig(R logicConfig) {
        this.logicConfig = logicConfig;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScopeKey() {
        return scopeKey;
    }

    public void setScopeKey(String scopeKey) {
        this.scopeKey = scopeKey;
        manager = CombineManagerHandler.get(scopeKey);
    }
}
