package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.manager.ConstantManager;
import com.parch.combine.core.ui.base.HtmlConfig;
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

    private PageElementManager pageElement;

    private PageElementGroupManager pageGroup;

    private PageManager page;

    private PageTemplateManager pageTemplate;

    private ConstantManager constant;

    private TriggerManager trigger;

    public CombineManager() {
        scopeKey = UUID.randomUUID().toString();
        constant = new ConstantManager();
        dataLoad = new DataLoadManager();
        trigger = new TriggerManager();
        pageElement = new PageElementManager();
        pageTemplate = new PageTemplateManager();
        pageGroup = new PageElementGroupManager(pageElement);
        page = new PageManager();
        CombineManagerHandler.register(scopeKey, this);
    }

    public void init(CombineConfigVO config, Consumer<CombineInitVO> func) {

        // TODO func

        // 保存常量到常量池
        constant.save(config.getConstant());

        // 加载数据加载配置
        dataLoad.load(config.getDataLoads());

        // 加载DOM模板配置
        pageElement.load(config.getTemplates());

        // 加载页面元素配置
        pageElement.load(config.getElements());

        // 初始化页面元素组配置
        pageGroup.init(config.getGroups());

        // 初始化每个接口的逻辑
        page.load(config.getPages());
    }

    public String getPage(String key) {
        HtmlConfig pageConfig = page.get(key);



        return null;
    }

    public DataLoadManager getDataLoad() {
        return dataLoad;
    }

    public PageElementManager getPageElement() {
        return pageElement;
    }

    public PageElementGroupManager getPageGroup() {
        return pageGroup;
    }

    public PageManager getPage() {
        return page;
    }

    public PageTemplateManager getPageTemplate() {
        return pageTemplate;
    }

    public ConstantManager getConstant() {
        return constant;
    }

    public TriggerManager getTrigger() {
        return trigger;
    }

    public String getScopeKey() {
        return scopeKey;
    }


}
