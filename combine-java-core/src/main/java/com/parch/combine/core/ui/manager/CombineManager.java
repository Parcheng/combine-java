package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.manager.ConstantManager;
import com.parch.combine.core.ui.builder.config.HtmlConfig;
import com.parch.combine.core.ui.handler.CombineManagerHandler;
import com.parch.combine.core.ui.vo.CombineConfigVO;
import com.parch.combine.core.ui.vo.CombineInitVO;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * 核心处理器
 */
public class CombineManager {

    private String scopeKey;

    private DataLoadManager dataLoad;

    private PageElementLogicManager pageElementLogic;

    private PageElementGroupManager pageGroup;

    private PageManager page;

    private PageTemplateManager pageTemplate;

    private ConstantManager constant;

    public CombineManager() {
        scopeKey = UUID.randomUUID().toString();
        constant = new ConstantManager();
        dataLoad = new DataLoadManager();
        pageElementLogic = new PageElementLogicManager();
        pageTemplate = new PageTemplateManager();
        pageGroup = new PageElementGroupManager(pageElementLogic);
        page = new PageManager();
        CombineManagerHandler.register(scopeKey, this);
    }

    public void init(CombineConfigVO config, Consumer<CombineInitVO> func) {
        // 保存常量到常量池
        constant.save(config.getConstant());

        // 加载数据加载配置
        dataLoad.load(config.getDataLoads());

        // 加载DOM模板配置
        pageElementLogic.load(config.getTemplates());

        // 加载页面元素配置
        pageElementLogic.load(config.getElements());

        // 初始化页面元素组配置
        pageGroup.init(config.getGroups());

        // 初始化每个接口的逻辑
        page.load(config.getPages());
    }

    public String getPage(String key) {
        HtmlConfig pageConfig = page.get(key);



        return null;
    }

//    /**
//     * 执行业务逻辑集合
//     *
//     * @param params 参数
//     * @param componentIds 业务逻辑中-需要执行的组件ID集合
//     * @param func 自定义函数
//     * @return 结果集
//     */
//    public DataResult execute(String key, Map<String, Object> params, Map<String, String> headers, List<String> componentIds, PageElementLogicManager.Function func) {
//        return execute(key, params, headers, null, componentIds, func);
//    }
//
//    /**
//     * 执行业务逻辑集合
//     *
//     * @param params 参数
//     * @param componentIds 业务逻辑中-需要执行的组件ID集合
//     * @param func 自定义函数
//     * @return 结果集
//     */
//    public DataResult execute(String key, Map<String, Object> params, Map<String, String> headers, FileInfo file, List<String> componentIds, PageElementLogicManager.Function func) {
//        // 初始化流程上下文
//        ComponentContextHandler.init(scopeKey, key, params, headers, file);
//        // 打印请求头和参数信息
//        PrintHelper.printComponentHeader();
//        PrintHelper.printComponentParam();
//
//        // 前置函数
//        if (func != null) {
//            func.before();
//        }
//
//        // 执行前置逻辑
//        DataResult result = flowAspect.executeBefore(key);
//
//        // 执行流程逻辑
//        if (result.getSuccess() && !result.isStop() && CheckEmptyUtil.isNotEmpty(componentIds)) {
//            result = component.executeComponents(componentIds);
//        }
//
//        // 执行后置逻辑
//        flowAspect.executeAfter(key);
//
//        // 后置函数
//        if (func != null) {
//            func.after();
//        }
//
//        // 清除缓存
//        ComponentContextHandler.clear();
//
//        return result;
//    }

    public String getScopeKey() {
        return scopeKey;
    }


}
