package com.parch.combine.core.component.tools;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.FileParamKey;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.core.component.vo.FlowResult;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
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
    public static void printSql(String sql, List<Object> sqlParams) {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "] SQL -> " + sql);
        if (CheckEmptyUtil.isNotEmpty(sqlParams)) {
            PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "] SQL-PARAMS -> " + StringUtil.join(sqlParams, ","));
        }
    }

    /**
     * 打印请求头信息
     */
    public static void printComponentHeader() {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        Map<String, String> header = ComponentContextHandler.getHeader();
        PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "] HEADER -> " + JsonUtil.obj2String(header));
    }

    /**
     * 打印参数信息
     */
    public static void printComponentParam() {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        Map<String, Object> params = ComponentContextHandler.getParams();

        // 过滤掉文件信息不打印到params中
        if (params.containsKey(FileParamKey.FILE_OBJ_KEY)) {
            params = new HashMap<>(params);
            params.remove(FileParamKey.FILE_OBJ_KEY);
        }
        String paramJson = JsonUtil.obj2String(params);

        PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "] PARAMS -> " + paramJson);
    }

    /**
     * 打印结果信息
     */
    public static void printComponentResult(ComponentDataResult result) {
        AbstractComponent<?,?> component = ComponentContextHandler.getCurrComponent();
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        if (component == null) {
            PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "][未知组件]");
            return;
        }

        PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "][" + component.getType() + "] COMPONENT-RESULT -> " + JsonUtil.obj2String(result));
    }

    /**
     * 打印组件异常信息
     */
    public static void printComponentError(String msg) {
        AbstractComponent<?,?> component = ComponentContextHandler.getCurrComponent();
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        String componentInfo = component == null ? "未知组件" : (component.getType() + " - " + component.getId());
        PrintLogUtil.printError("[" + requestId + "][" + flowKey + "][" + componentInfo + "] COMPONENT-ERROR -> " + msg);
    }

    /**
     * 打印组件异常信息
     */
    public static void printComponentError(Throwable e) {
        if (e != null) {
            printComponentError(e.getMessage());
            while (e != null){
                e.printStackTrace();
                e = e.getCause();
            }
        }
    }

    /**
     * 打印结果信息
     */
    public static void printFlowResult(FlowResult result) {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        PrintLogUtil.printInfo("[" + requestId + "][" + flowKey + "] FLOW-RESULT -> " + JsonUtil.obj2String(result));
    }

    /**
     * 打印初始化信息
     */
    public static void printInit(String text) {
        PrintLogUtil.printMark("INIT -> " + text);
    }
}
