package com.parch.combine.core.ui;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.tools.PrintTool;
import com.parch.combine.core.ui.handler.ElementClassHandler;
import com.parch.combine.core.ui.service.CombineJavaUIService;
import com.parch.combine.core.ui.service.ICombineJavaUIService;
import com.parch.combine.core.ui.vo.CombineLoadVO;
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
    public static ICombineJavaUIService init(String path) {
        GlobalConfigVO config = GlobalConfigVO.build(path);
        PrintTool.printInit("=======================================================================================================================================================");
        PrintTool.printInit("初始化UI全局设置 [" + path + "] >>>");
        PrintTool.printInit("加载配置文件设置   -> " + StringUtil.join(config.getConfigs(), ","));
        PrintTool.printInit("根URL   -> " + config.getBaseUrl());
        PrintTool.printInit("系统根URL -> " + config.getSystemUrl());
        PrintTool.printInit("------------------------------------------------------------------------------------------------------------------------------------------------------");

        CombineJavaUIService service = new CombineJavaUIService(ConfigLoadingContextHandler.build(config));
        CombineLoadVO loadVO = service.batchLoad(config.getConfigs());

        PrintTool.printInit("初始化页面 >>>");
        if (CheckEmptyUtil.isNotEmpty(loadVO.getDataLoadIds())) {
            PrintTool.printInit("DATA-LOAD | " + StringUtil.join(loadVO.getDataLoadIds(), ", "));
        }

        if (CheckEmptyUtil.isNotEmpty(loadVO.getElementTemplateIds())) {
            PrintTool.printInit("ELEMENT-TEMPLATE | " + StringUtil.join(loadVO.getElementTemplateIds(), ", "));
        }

        if (CheckEmptyUtil.isNotEmpty(loadVO.getElementIds())) {
            PrintTool.printInit("ELEMENT | " + StringUtil.join(loadVO.getElementIds(), ", "));
        }

        loadVO.getGroupElementMap().forEach((k, v) -> {
            PrintTool.printInit("ELEMENT-GROUP | " + k + " : " + StringUtil.join(v, ", "));
        });

        if (CheckEmptyUtil.isNotEmpty(loadVO.getPageKeys())) {
            PrintTool.printInit("PAGE | " + StringUtil.join(loadVO.getPageKeys(), ", "));
            List<String> buildErrors = service.buildPages(loadVO.getPageKeys());
            if (CheckEmptyUtil.isNotEmpty(buildErrors)) {
                for (String buildError : buildErrors) {
                    PrintTool.printInit("PAGE-BUILD | ERROR : " + buildError);
                }
                PrintTool.printInit("PAGE-BUILD | FAIL");
            } else {
                PrintTool.printInit("PAGE-BUILD | SUCCESS");
            }
        }
        PrintTool.printInit("=======================================================================================================================================================");

        return service;
    }
}
