package com.parch.combine.system.base.test.handler;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.system.base.test.LogLevelEnum;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 打印处理器
 */
public class LogHandler {

    public static void start(Boolean isPrint, Boolean isOutput, String name, List<String> msgList) {
        if (isOutput != null && isOutput) {
            msgList.add("自动化测试【" + name + "】开始");
            msgList.add("--------------------------------------------------------------------------------------------------------------------------------------");
        }

        if (isPrint != null && isPrint) {
            PrintUtil.printMark("自动化测试【" + name + "】开始");
            PrintUtil.printInfo("--------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void hr(Boolean isPrint, Boolean isOutput, List<String> msgList) {
        if (isOutput != null && isOutput) {
            msgList.add("-------------------------------------------------------------------------------------------------------------------------");
        }

        if (isPrint != null && isPrint) {
            PrintUtil.printInfo("-------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void blank(Boolean isPrint, Boolean isOutput, List<String> msgList) {
        if (isOutput != null && isOutput) {
            msgList.add("");
        }

        if (isPrint != null && isPrint) {
            PrintUtil.printInfo("");
        }
    }

    public static void mark(Boolean isPrint, Boolean isOutput, String msg, List<String> msgList) {
        String finalMsg = getTime() + " [SYSTEM.TEST] [INFO] " + msg;

        if (isOutput != null && isOutput) {
            msgList.add(finalMsg);
        }

        if (isPrint != null && isPrint) {
            PrintUtil.printMark(finalMsg);
        }
    }

    public static void log(Boolean isPrint, Boolean isOutput, LogLevelEnum level, String msg, List<String> msgList) {
        String finalMsg = getTime() + " [SYSTEM.TEST] [" + level.name() + "] " + msg;

        if (isOutput != null && isOutput) {
            msgList.add(finalMsg);
        }

        if (isPrint != null && isPrint) {
            switch (level) {
                case INFO:
                    PrintUtil.printInfo(finalMsg);
                    break;
                case WARN:
                    PrintUtil.printWarn(finalMsg);
                    break;
                case FAIL:
                case ERROR:
                    PrintUtil.printError(finalMsg);
                    break;
            }
        }
    }

    private static String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return "[" + now.format(formatter) + "]";
    }
}
