package com.parch.combine.core.ui.tools;

import com.parch.combine.core.common.util.PrintUtil;

/**
 * 组件异常处理器
 */
public class PrintHelper {

    public static void printInit(String text) {
        PrintUtil.printMark("INIT UI -> " + text);
    }
}
