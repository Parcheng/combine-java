package com.parch.combine.core.ui;

import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.tools.PrintTool;
import com.parch.combine.core.ui.handler.ElementClassHandler;
import com.parch.combine.core.ui.service.CombineJavaUIService;
import com.parch.combine.core.ui.service.ICombineJavaUIService;
import com.parch.combine.core.ui.vo.GlobalConfigVO;
import com.parch.combine.core.ui.vo.PageElementClassInitVO;
import java.util.List;

public class CombineJavaUIStarter {

    static {
        PrintTool.printInit("=======================================================================================================================================================");
        PrintTool.printInit("初始化组件 >>>");
        List<PageElementClassInitVO> components = ElementClassHandler.init();
        for (PageElementClassInitVO vo : components) {
            PrintTool.printInit("组件【" + vo.getKey() + "】初始化完成");
        }
        PrintTool.printInit("=======================================================================================================================================================");
    }

    /**
     * 初始化
     *
     * @param path 初始化文件相对路径
     */
    public static ICombineJavaUIService init(String path) {
        CombineJavaUIService service = new CombineJavaUIService(path);

        CombineManager combineManager = new CombineManager();
        ConfigLoadingContextHandler.init(combineManager, null, null);

        GlobalConfigVO globalConfig = service.getGlobalConfig();
//        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");
//        PrintHelper.printInit("初始化全局设置 >>>");
//        PrintHelper.printInit("加载配置文件设置   -> " + StringUtil.join(context.getInitConfigs(), ","));
//        PrintHelper.printInit("初始化流程设置     -> " + StringUtil.join(context.getInitFlows(), ","));
//        PrintHelper.printInit("是否开放配置注册   -> " + context.getOpenRegisterConfig());
//        PrintHelper.printInit("流程链路请求ID字段 -> " + context.getRequestIdKey());
//        PrintHelper.printInit("打印组件执行结果   -> " + context.getPrintComponentResult());
//        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");


        PrintTool.printInit("初始化页面 >>>");
        for (String initConfigPath : globalConfig.getInitConfigs()) {
            service.registerAsPath(initConfigPath, vo -> {
//                PrintHelper.printInit(vo.getFlowKey() + " | " + StringUtil.join(vo.getComponentIds(), ", "));
//                if (CheckEmptyUtil.isNotEmpty(vo.getStaticComponentIds())) {
//                    PrintHelper.printInit(vo.getFlowKey() + " STATIC | " + StringUtil.join(vo.getStaticComponentIds(), ", "));
//                }
//                if (CheckEmptyUtil.isNotEmpty(vo.getErrorList())) {
//                    for (String errorMsg : vo.getErrorList()) {
//                        PrintUtil.printError(vo.getFlowKey() + " Error：" + errorMsg);
//                    }
//                }
            });
        }
        PrintTool.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");


//        PrintHelper.printInit("执行流程逻辑 >>>");
//        for (String initFlowKey : context.getInitFlows()) {
//            // 获取功能的组件配置集合
//            String[] keyArr = FlowKeyUtil.parseKey(initFlowKey);
//            List<String> componentIds = combineWebService.getComponentIds(keyArr[0], keyArr[1]);
//            if (componentIds == null) {
//                PrintUtil.printError(initFlowKey + " Error：流程未注册");
//            } else {
//                PrintHelper.printInit("执行流程：" + initFlowKey);
//                combineWebService.executeAny(keyArr[0], keyArr[1], new HashMap<>(0), new HashMap<>(0));
//            }
//        }
//        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");


//        combineWebService.setOpenRegister(context.getOpenRegisterConfig());

        ConfigLoadingContextHandler.clear();

        return service;
    }
}
