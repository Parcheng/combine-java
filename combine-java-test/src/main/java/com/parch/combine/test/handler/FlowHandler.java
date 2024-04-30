package com.parch.combine.test.handler;

import com.parch.combine.CombineWebStarter;
import com.parch.combine.common.util.SystemFileUtil;


/**
 * 初始化处理器
 */
public class FlowHandler {

    /**
     * 初始化
     *
     * @param path 文件路径
     */
    public static void init(String path) {
        // 读取配置文件
        String jsonDataStr = SystemFileUtil.read(path);

        // 初始化流程
        PrintHandler.init(path);
        CombineWebStarter.getService().registerFlow(jsonDataStr, PrintHandler::flowInit);
        PrintHandler.hr();
    }
}
