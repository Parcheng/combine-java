package com.parch.combine.core.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.PrintUtil;
import com.parch.combine.common.util.StringUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.base.FileParamKey;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.vo.DataResult;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        PrintUtil.printInfo("[" + requestId + "][" + flowKey + "] SQL -> " + sql);
        if (CheckEmptyUtil.isNotEmpty(sqlParams)) {
            PrintUtil.printInfo("[" + requestId + "][" + flowKey + "] SQL-PARAMS -> " + StringUtil.join(sqlParams, ","));
        }
    }

    /**
     * 打印请求头信息
     */
    public static void printComponentHeader() {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        Map<String, String> header = ComponentContextHandler.getHeader();
        PrintUtil.printInfo("[" + requestId + "][" + flowKey + "] HEADER -> " + JsonUtil.serialize(header));
    }

    /**
     * 打印参数信息
     */
    public static void printComponentParam() {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        Map<String, Object> params = ComponentContextHandler.getParams();

        // 过滤掉上传的文件信息
        String paramJson = JsonUtil.serialize(params, Map.class, new JsonSerializer<>() {
            @Override
            public void serialize(Map map, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeStartObject();
                for (Object key : map.keySet()) {
                    if (!key.equals(FileParamKey.FILE_OBJ_KEY)) {
                        gen.writeObjectField(key.toString(), map.get(key));
                    }
                }
                gen.writeEndObject();
            }
        });

        PrintUtil.printInfo("[" + requestId + "][" + flowKey + "] PARAMS -> " + paramJson);
    }

    /**
     * 打印结果信息
     */
    public static void printComponentResult(AbsComponent<?,?> component, DataResult result) {
        String requestId = ComponentContextHandler.getRequestId();
        String flowKey = ComponentContextHandler.getFlowKey();
        // 拼接错误信息字符串
        if (component == null) {
            PrintUtil.printInfo("[" + requestId + "][" + flowKey + "][未知组件]");
            return;
        }

        PrintUtil.printInfo("[" + requestId + "][" + flowKey + "][" + component.getType() + "] RESULT -> " + JsonUtil.serialize(result));
    }

    /**
     * 打印初始化信息
     */
    public static void printInit(String text) {
        PrintUtil.printMark("INIT -> " + text);
    }
}
