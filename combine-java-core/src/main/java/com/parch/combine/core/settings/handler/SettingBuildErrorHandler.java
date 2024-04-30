package com.parch.combine.core.settings.handler;

import com.parch.combine.common.util.PrintUtil;

/**
 * 组件异常处理器
 */
public class SettingBuildErrorHandler {

    /**
     * 打印错误信息
     *
     * @param msg 错误信息
     * @param e 异常对象
     */
    public static void print(String msg, Exception e) {
        PrintUtil.printError("【BUILD SETTING】" + msg);
        if (e != null) {
            e.printStackTrace();
        }
    }
}
