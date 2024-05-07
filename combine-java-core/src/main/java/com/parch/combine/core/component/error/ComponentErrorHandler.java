package com.parch.combine.core.component.error;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.component.base.InitConfig;
import com.parch.combine.core.component.base.LogicConfig;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.base.AbsComponent;

/**
 * 组件异常处理器
 */
public class ComponentErrorHandler {

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     */
    public static void print(String msg) {
        print(msg, null);
    }

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     */
    public static void print(IComponentError msg) {
        print(msg, null);
    }

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(IComponentError msg, Exception e) {
        print(msg.getMsg(), e);
    }

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(String msg, Exception e) {
        AbsComponent<?, ?> component = ComponentContextHandler.getCurrComponent();
        print(component, msg, e);
    }

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(AbsComponent<?, ?> component, String msg, Exception e) {
        // 拼接错误信息字符串
        if (component == null) {
            PrintUtil.printError("【未知组件】" + msg);
        } else {
            PrintUtil.printError("【" + component.getId() + "】【" + component.getType() + "】" + msg);
        }

        // 打印异常信息
        if (e != null) {
            e.printStackTrace();
        }
    }

    /**
     * 构建检测初始化配置错误信息
     *
     * @param config 初始化配置
     * @param msg 错误信息
     */
    public static String buildCheckInitMsg(InitConfig config, String msg) {
        return "【" + config.getType() + "】【初始化配置】【" + config.getId() + "】【" + msg + "】";
    }

    /**
     * 构建检测逻辑配置错误信息
     *
     * @param config 逻辑配置
     * @param msg 错误信息
     */
    public static String buildCheckLogicMsg(LogicConfig config, String msg) {
        return "【" + config.getType() + "】【逻辑配置】【" + config.getId() + "】【" + msg + "】";
    }
}