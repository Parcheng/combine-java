package com.parch.combine.core.error;

import com.parch.combine.common.util.PrintUtil;

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
        PrintUtil.printError("【SYSTEM】" + msg.getMsg());
        e.printStackTrace();
    }
}
