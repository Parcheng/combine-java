package com.parch.combine.core.component.error;

import com.parch.combine.core.common.util.PrintLogUtil;

/**
 * 组件异常处理器
 */
public class SystemErrorHandler {

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(IComponentError msg, Exception e) {
        PrintLogUtil.printError("【SYSTEM】" + msg.getMsg());
        e.printStackTrace();
    }
}
