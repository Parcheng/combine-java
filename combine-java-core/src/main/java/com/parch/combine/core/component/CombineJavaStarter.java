package com.parch.combine.core.component;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.context.GlobalContext;
import com.parch.combine.core.component.context.GlobalContextHandler;
import com.parch.combine.core.component.service.CombineJavaService;
import com.parch.combine.core.component.handler.ComponentClassHandler;
import com.parch.combine.core.component.service.ICombineJavaService;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.core.component.vo.ComponentClassInitVO;
import java.util.HashMap;
import java.util.List;

public class CombineJavaStarter {

    static {
        PrintHelper.printInit("=======================================================================================================================================================");
        PrintHelper.printInit("初始化组件 >>>");
        List<ComponentClassInitVO> components = ComponentClassHandler.init();
        for (ComponentClassInitVO vo : components) {
            PrintHelper.printInit("组件【" + vo.getKey() + "】初始化完成");
        }
        PrintHelper.printInit("=======================================================================================================================================================");
    }

    /**
     * 初始化
     *
     * @param path 初始化文件相对路径
     */
    public static ICombineJavaService init(String path) {
        CombineJavaService combineWebService = new CombineJavaService();

        String scopeKey = combineWebService.getScopeKey();
        GlobalContextHandler.init(scopeKey, path);
        GlobalContext context = GlobalContextHandler.get(scopeKey);
        PrintHelper.printInit("=======================================================================================================================================================");
        PrintHelper.printInit("初始化全局设置 [" + path + "] >>>");
        PrintHelper.printInit("加载配置文件设置   -> " + StringUtil.join(context.getInitConfigs(), ","));
        PrintHelper.printInit("初始化流程设置     -> " + StringUtil.join(context.getInitFlows(), ","));
        PrintHelper.printInit("是否开放配置注册   -> " + context.getOpenRegisterConfig());
        PrintHelper.printInit("流程链路请求ID字段 -> " + context.getRequestIdKey());
        PrintHelper.printInit("打印组件执行结果   -> " + context.getPrintComponentResult());
        PrintHelper.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");


        PrintHelper.printInit("初始化流程 >>>");
        for (String initConfigPath : context.getInitConfigs()) {
            combineWebService.registerFlowAsPath(initConfigPath, vo -> {
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
            List<String> componentIds = combineWebService.getComponentIds(keyArr[0], keyArr[1]);
            if (componentIds == null) {
                PrintUtil.printError(initFlowKey + " Error：流程未注册");
            } else {
                PrintHelper.printInit("执行流程：" + initFlowKey);
                combineWebService.executeAny(keyArr[0], keyArr[1], new HashMap<>(0), new HashMap<>(0));
            }
        }
        PrintHelper.printInit("=======================================================================================================================================================");


        combineWebService.setOpenRegister(context.getOpenRegisterConfig());

        return combineWebService;
    }
}
