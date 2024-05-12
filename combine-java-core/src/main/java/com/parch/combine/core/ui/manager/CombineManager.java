package com.parch.combine.core.ui.manager;

import com.parch.combine.core.common.manager.ConstantManager;
import com.parch.combine.core.ui.vo.CombineConfigVO;
import com.parch.combine.core.ui.vo.CombineLoadVO;
import java.util.UUID;

/**
 * 核心处理器
 */
public class CombineManager {

    private String scopeKey;

    private DataLoadManager dataLoad;

    private PageElementManager pageElement;

    private PageElementGroupManager pageGroup;

    private PageManager page;

    private PageElementTemplateManager pageElementTemplate;

    private ConstantManager constant;

    private TriggerManager trigger;

    public CombineManager() {
        scopeKey = UUID.randomUUID().toString();
        constant = new ConstantManager();
        dataLoad = new DataLoadManager();
        trigger = new TriggerManager();
        pageElement = new PageElementManager();
        pageElementTemplate = new PageElementTemplateManager();
        pageGroup = new PageElementGroupManager(pageElement);
        page = new PageManager();
    }

    public CombineLoadVO load(CombineConfigVO config) {
        CombineLoadVO initVO = new CombineLoadVO();

        // 保存常量到常量池
        constant.save(config.getConstant());
        // 加载数据加载配置
        initVO.setDataLoadIds(dataLoad.load(config.getDataLoads()));
        // 加载元素模板配置
        initVO.setElementTemplateIds(pageElementTemplate.load(config.getTemplates()));
        // 加载页面元素配置
        initVO.setElementIds(pageElement.load(config.getElements()));
        // 初始化页面元素组配置
        initVO.setGroupElementMap(pageGroup.init(config.getGroups()));
        // 初始化每个接口的逻辑
        initVO.setPageKeys(page.load(config.getPages()));

        return initVO;
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

    public PageElementTemplateManager getPageElementTemplate() {
        return pageElementTemplate;
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
