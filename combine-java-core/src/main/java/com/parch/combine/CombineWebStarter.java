package com.parch.combine;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.FlowKeyUtil;
import com.parch.combine.common.util.PrintUtil;
import com.parch.combine.common.util.StringUtil;
import com.parch.combine.core.context.GlobalContext;
import com.parch.combine.core.context.GlobalContextHandler;
import com.parch.combine.core.handler.CombineWebService;
import com.parch.combine.core.service.ICombineWebService;
import com.parch.combine.core.tools.PrintHelper;
import com.parch.combine.core.vo.ComponentInitVO;
import java.util.HashMap;
import java.util.List;

public class CombineWebStarter {

    private static CombineWebService SERVICE;

    /**
     * 初始化
     *
     * @param path 初始化文件相对路径
     */
    public static void init(String path) {
        SERVICE = new CombineWebService();

        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");
        PrintHelper.printInit("初始化全局设置 >>>");
        GlobalContextHandler.init(path);
        GlobalContext context = GlobalContextHandler.get();
        PrintHelper.printInit("加载配置文件设置   -> " + StringUtil.join(context.getInitConfigs(), ","));
        PrintHelper.printInit("初始化流程设置     -> " + StringUtil.join(context.getInitFlows(), ","));
        PrintHelper.printInit("是否开放配置注册   -> " + context.getOpenRegisterConfig());
        PrintHelper.printInit("流程链路请求ID字段 -> " + context.getRequestIdKey());
        PrintHelper.printInit("打印组件执行结果   -> " + context.getPrintComponentResult());
        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");



        PrintHelper.printInit("初始化组件 >>>");
        List<ComponentInitVO> components = SERVICE.init();
        for (ComponentInitVO vo : components) {
            PrintHelper.printInit("组件【" + vo.getKey() + "】初始化完成");
        }
        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");



        PrintHelper.printInit("初始化流程 >>>");
        for (String initConfigPath : context.getInitConfigs()) {
            SERVICE.registerFlowAsPath(initConfigPath, vo -> {
                PrintHelper.printInit(vo.getFlowKey() + " | " + StringUtil.join(vo.getComponentIds(), ", "));
                if (CheckEmptyUtil.isNotEmpty(vo.getStaticComponentIds())) {
                    PrintHelper.printInit(vo.getFlowKey() + " STATIC | " + StringUtil.join(vo.getStaticComponentIds(), ", "));
                }
                if (CheckEmptyUtil.isNotEmpty(vo.getErrorList())) {
                    for (String errorMsg : vo.getErrorList()) {
                        PrintUtil.printError(vo.getFlowKey() + " Error：" + errorMsg);
                    }
                }
            });
        }
        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");



        PrintHelper.printInit("执行流程逻辑 >>>");
        for (String initFlowKey : context.getInitFlows()) {
            // 获取功能的组件配置集合
            String[] keyArr = FlowKeyUtil.parseKey(initFlowKey);
            List<String> componentIds = SERVICE.getComponentIds(keyArr[0], keyArr[1]);
            if (componentIds == null) {
                PrintUtil.printError(initFlowKey + " Error：流程未注册");
            } else {
                PrintHelper.printInit("执行流程：" + initFlowKey);
                SERVICE.executeAny(keyArr[0], keyArr[1], new HashMap<>(0), new HashMap<>(0));
            }
        }
        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");


        SERVICE.setOpenRegister(context.getOpenRegisterConfig());
    }

    /**
     * 获取 Service 服务类（必须先初始化）
     *
     * @return Service 服务类
     */
    public static ICombineWebService getService() {
        return SERVICE;
    }
}
