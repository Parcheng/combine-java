package com.parch.combine.core.tools;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.PrintUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.vo.DataResult;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * 组件异常处理器
 */
public class PrintHelper {

    /**
     * 获取时间字符串
     *
     * @return 时间字符串
     */
    private static String getTimeStr() {
        // 获取当前时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        LocalDateTime dateTime = LocalDateTime.now();
        return dateTime.format(formatter);
    }


    /**
     * 打印结果信息
     */
    public static void printSql(String sql) {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        PrintUtil.printInfo("[" + getTimeStr() + "][" + requestId + "][" + flowKey + "] SQL -> " + sql);
    }

    /**
     * 打印结果信息
     */
    public static void printComponentParam() {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        Map<String, Object> params = ComponentContextHandler.getParams();
        PrintUtil.printInfo("[" + getTimeStr() + "][" + requestId + "][" + flowKey + "] PARAMS -> " + JsonUtil.serialize(params));
    }

    /**
     * 打印结果信息
     */
    public static void printComponentResult(AbsComponent<?,?> component, DataResult result) {
        String formattedDateTime = getTimeStr();
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        // 拼接错误信息字符串
        if (component == null) {
            PrintUtil.printInfo("[" + formattedDateTime + "][" + requestId + "][" + flowKey + "][未知组件]");
            return;
        }

        PrintUtil.printInfo("[" + formattedDateTime + "][" + requestId + "][" + flowKey + "][" + component.getType() + "] RESULT -> " + JsonUtil.serialize(result));
    }

    /**
     * 打印初始化信息
     */
    public static void printInit(String text) {
        String formattedDateTime = getTimeStr();
        PrintUtil.printMark("[" + formattedDateTime + "] INIT -> " + text);
    }
}
