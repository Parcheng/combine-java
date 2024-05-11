package com.parch.combine.core.ui;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.tools.PrintTool;
import com.parch.combine.core.ui.handler.ElementClassHandler;
import com.parch.combine.core.ui.service.CombineJavaPageService;
import com.parch.combine.core.ui.service.ICombineJavaPageService;
import com.parch.combine.core.ui.vo.CombineConfigVO;
import com.parch.combine.core.ui.vo.GlobalConfigVO;
import com.parch.combine.core.ui.vo.PageElementClassInitVO;
import java.util.List;

public class CombineJavaUIStarter {

    static {
        PrintTool.printInit("=======================================================================================================================================================");
        PrintTool.printInit("初始化UI元素 >>>");
        List<PageElementClassInitVO> components = ElementClassHandler.init();
        for (PageElementClassInitVO vo : components) {
            PrintTool.printInit("UI元素【" + vo.getKey() + "】初始化完成");
        }
        PrintTool.printInit("=======================================================================================================================================================");
    }

    /**
     * 初始化
     *
     * @param path 初始化文件相对路径
     */
    public static ICombineJavaPageService init(String path) {
        CombineJavaPageService service = new CombineJavaPageService();

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
            CombineConfigVO config = JsonUtil.deserialize(ResourceFileUtil.read(path), CombineConfigVO.class);
            if (config == null) {
                PrintTool.printInit("初始化页面 >>>"); // TODO
                continue;
            }

            combineManager.init(config, vo -> {
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

            combineManager.getPage().get().forEach((k, v) -> {
                service.register(k, v, null);
            });
        }
        PrintTool.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");

//        combineWebService.setOpenRegister(context.getOpenRegisterConfig());

        ConfigLoadingContextHandler.clear();

        return service;
    }
}
