package com.parch.combine.core.handler;

import com.parch.combine.core.error.SystemErrorEnum;
import com.parch.combine.core.error.SystemErrorHandler;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.settings.ComponentSettingHandler;
import com.parch.combine.core.vo.ComponentInitVO;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 组件处理器
 */
public class ComponentClassHandler {

    /**
     * 组件Class池
     */
    private static Map<String, Class<? extends AbsComponent<?,?>>> COMPONENT_CLASS_MAP = new HashMap<>();

    /**
     * 初始化组件
     */
    public static List<ComponentInitVO> init() {
        // 所有组件
        List<ComponentInitVO> components = ComponentSettingHandler.getComponents();

        // 注册所有组件
        for (ComponentInitVO vo : components) {
            register(vo.getKey(), vo.getComponent());
        }

        return components;
    }

    /**
     * 注册组件
     *
     * @param key 组件Key
     * @param clazz 组件Class对象
     */
    public static void register(String key, Class<? extends AbsComponent<?, ?>> clazz) {
        COMPONENT_CLASS_MAP.put(key, clazz);
    }

    /**
     * 构建组件
     *
     * @param id 组件ID
     * @param type 组件类型
     */
    public static AbsComponent<?,?> build(String id, String type, String scopeKey, Map<String, Object> logicConfig) {
        AbsComponent<?,?> component = null;
        try {
            Class<? extends AbsComponent<?, ?>> clazz = COMPONENT_CLASS_MAP.get(type);
            component = clazz.getDeclaredConstructor().newInstance();
            component.setId(id);
            component.setType(type);
            component.setScopeKey(scopeKey);
            component.initConfig(logicConfig);
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            SystemErrorHandler.print(SystemErrorEnum.COMPONENT_BUILD_ERROR, e);
            return null;
        } catch (Exception e) {
            SystemErrorHandler.print(SystemErrorEnum.SYSTEM_ERROR, e);
            return null;
        }

        return component;
    }

    /**
     * 检查组件配置
     *
     * @param component 组件
     * @return 错误信息集合
     */
    public static List<String> init(AbsComponent<?,?> component) {
        return component.init();
    }
}
