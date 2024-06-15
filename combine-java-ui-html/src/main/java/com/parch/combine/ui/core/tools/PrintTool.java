package com.parch.combine.ui.core.tools;

import com.parch.combine.core.common.util.PrintUtil;

/**
 * 组件异常处理器
 */
public class PrintTool {

    public static void printInit(String text) {
        PrintUtil.printMark("INIT UI -> " + text);
    }
}
