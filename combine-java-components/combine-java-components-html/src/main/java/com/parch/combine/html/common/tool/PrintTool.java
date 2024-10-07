package com.parch.combine.html.common.tool;

import com.parch.combine.core.common.util.PrintLogUtil;

/**
 * 组件异常处理器
 */
public class PrintTool {

    public static void printInit(String text) {
        PrintLogUtil.printMark("INIT UI -> " + text);
    }
}
