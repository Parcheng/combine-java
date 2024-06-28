package com.parch.combine.core.component.handler;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.tuple.ThreeTuples;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.config.ConfigHelper;
import com.parch.combine.core.component.error.SystemErrorEnum;
import com.parch.combine.core.component.error.SystemErrorHandler;
import com.parch.combine.core.component.settings.ComponentSettingHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.vo.ComponentClassInitVO;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 组件处理器
 */
public class ComponentClassHandler {

    /**
     * 组件Class池
     */
    private static Map<String, Class<? extends AbstractComponent<?,?>>> COMPONENT_CLASS_MAP = new HashMap<>();

    /**
     * 初始化组件
     */
    public static List<ComponentClassInitVO> init() {
        // 所有组件
        List<ComponentClassInitVO> components = ComponentSettingHandler.getComponents();

        // 注册所有组件
        for (ComponentClassInitVO vo : components) {
            vo.setErrorMsg(register(vo.getKey(), vo.getComponent()));
        }

        return components;
    }

    /**
     * 注册组件
     *
     * @param key 组件Key
     * @param clazz 组件Class对象
     */
    public static List<String> register(String key, Class<? extends AbstractComponent<?, ?>> clazz) {
        List<String> errors = new ArrayList<>();
        Component componentAnnotation = clazz.getAnnotation(Component.class);
        if (componentAnnotation == null) {
            errors.add( "组件类必填使用 Component 注解");
            return errors;
        } else {
            List<String> initErrors = ConfigHelper.check(componentAnnotation.initConfigClass());
            if (CheckEmptyUtil.isNotEmpty(initErrors)) {
                for (String item : initErrors) {
                    errors.add("【初始化配置】" + item);
                }
            }

            List<String> logicErrors = ConfigHelper.check(componentAnnotation.logicConfigClass());
            if (CheckEmptyUtil.isNotEmpty(logicErrors)) {
                for (String item : logicErrors) {
                    errors.add("【逻辑配置】" + item);
                }
            }
        }

        if (CheckEmptyUtil.isEmpty(errors)) {
            COMPONENT_CLASS_MAP.put(key, clazz);
        }

        return errors;
    }

    /**
     * 构建组件
     *
     * @param id 组件ID
     * @param type 组件类型
     */
    public static ThreeTuples<Boolean, AbstractComponent<?,?>, List<String>> build(String id, String type, String scopeKey, Map<String, Object> logicConfig) {
        try {
            Class<? extends AbstractComponent<?, ?>> clazz = COMPONENT_CLASS_MAP.get(type);
            if (clazz == null) {
                return new ThreeTuples<>(false, null, Collections.singletonList("【" + type + "】组件未注册"));
            }

            AbstractComponent<?,?> component = clazz.getDeclaredConstructor().newInstance();
            component.setId(id);
            component.setType(type);
            component.setScopeKey(scopeKey);
            List<String> errors = component.initConfig(logicConfig);
            if (CheckEmptyUtil.isEmpty(errors)) {
                return new ThreeTuples<>(true, component, null);
            } else {
                return new ThreeTuples<>(false, null, errors);
            }
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            SystemErrorHandler.print(SystemErrorEnum.COMPONENT_BUILD_ERROR, e);
            return new ThreeTuples<>(false, null, Collections.singletonList("组件构建失败"));
        } catch (Exception e) {
            SystemErrorHandler.print(SystemErrorEnum.SYSTEM_ERROR, e);
            return new ThreeTuples<>(false, null, Collections.singletonList("组件构建失败"));
        }
    }
}
