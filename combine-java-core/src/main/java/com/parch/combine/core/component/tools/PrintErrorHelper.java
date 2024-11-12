package com.parch.combine.core.component.tools;

import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.IComponentError;

/**
 * 组件异常处理器
 */
public class PrintErrorHelper {

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
        AbstractComponent<?, ?> component = ComponentContextHandler.getCurrComponent();
        print(component, msg, e);
    }

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(AbstractComponent<?, ?> component, String msg, Exception e) {
        // 拼接错误信息字符串
        if (component == null) {
            PrintLogUtil.printError("【未知组件】" + msg);
        } else {
            PrintLogUtil.printError("【" + component.getId() + "】【" + component.getType() + "】" + msg);
        }

        // 打印异常信息
        if (e != null) {
            e.printStackTrace();
        }
    }

    /**
     * 构建检测初始化配置错误信息
     *
     * @param msg 错误信息
     */
    public static String buildFieldMsg(String key, String msg) {
        return key + " > " + msg;
    }
}
