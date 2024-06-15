package com.parch.combine.ui.core.service;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.ui.core.base.HtmlConfig;
import com.parch.combine.ui.core.builder.HtmlBuilder;
import com.parch.combine.ui.core.context.ConfigLoadingContext;
import com.parch.combine.ui.core.context.ConfigLoadingContextHandler;
import com.parch.combine.ui.core.manager.CombineManager;
import com.parch.combine.ui.core.tools.ConfigErrorMsgTool;
import com.parch.combine.ui.core.vo.CombineConfigVO;
import com.parch.combine.ui.core.vo.CombineLoadVO;
import com.parch.combine.ui.core.vo.CombineRegisterVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombineJavaUIService implements ICombineJavaUIService {

    private ConfigLoadingContext context;

    private CombineManager combineManager;

    private Map<String, String> pageMap;

    public CombineJavaUIService(ConfigLoadingContext context) {
        this.context = context;
        this.combineManager = new CombineManager();
        this.context.setScopeKey(combineManager.getScopeKey());
        this.pageMap = new HashMap<>();
    }

    public CombineLoadVO batchLoad(List<String> configPaths) {
        CombineLoadVO result = new CombineLoadVO();
        result.setDataLoadIds(new ArrayList<>());
        result.setElementTemplateIds(new ArrayList<>());
        result.setElementIds(new ArrayList<>());
        result.setGroupElementMap(new HashMap<>());
        result.setPageKeys(new ArrayList<>());

        for (String configPath : configPaths) {
            CombineLoadVO currVO = load(configPath);
            if (currVO != null) {
                result.getDataLoadIds().addAll(currVO.getDataLoadIds());
                result.getElementTemplateIds().addAll(currVO.getElementTemplateIds());
                result.getElementIds().addAll(currVO.getElementIds());
                result.getGroupElementMap().putAll(currVO.getGroupElementMap());
                result.getPageKeys().addAll(currVO.getPageKeys());
            }
        }

        return result;
    }

    public CombineLoadVO load(String configPath) {
        CombineConfigVO config = JsonUtil.deserialize(ResourceFileUtil.read(configPath), CombineConfigVO.class);
        if (config != null) {
            return combineManager.load(config);
        }

        return null;
    }

    public List<String> buildPages(List<String> keys) {
        List<String> msg = new ArrayList<>();
        ConfigLoadingContextHandler.set(context);

        for (String pageKey : keys) {
            HtmlConfig config = combineManager.getPage().get(pageKey);

            HtmlBuilder htmlBuilder = new HtmlBuilder(config);
            List<String> checkMsg = htmlBuilder.check();
            if (CheckEmptyUtil.isEmpty(msg)) {
                pageMap.put(pageKey, htmlBuilder.build());
            } else {
                for (String msgItem : checkMsg) {
                    msg.add(ConfigErrorMsgTool.error("BUILD-PAGE", pageKey, msgItem));
                }
            }

        }
        ConfigLoadingContextHandler.clear();

        return msg;
    }

    @Override
    public CombineRegisterVO register(CombineConfigVO config) {
        CombineRegisterVO vo = new CombineRegisterVO();
        if (config == null) {
            vo.setSuccess(false);
            return vo;
        }

        CombineLoadVO initVO = combineManager.load(config);
        vo.setLoadResult(initVO);

        List<String> buildErrors = buildPages(initVO.getPageKeys());
        vo.setSuccess(CheckEmptyUtil.isEmpty(buildErrors));
        vo.setBuildErrorMsg(buildErrors);

        return vo;
    }

    @Override
    public String getPage(String key) {
        return pageMap.get(key);
    }
}
