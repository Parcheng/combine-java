package com.parch.combine.core.ui.service;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.ui.base.HtmlConfig;
import com.parch.combine.core.ui.builder.HtmlBuilder;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.tools.ConfigErrorMsgTool;
import com.parch.combine.core.ui.vo.CombineConfigVO;
import com.parch.combine.core.ui.vo.CombineLoadVO;
import com.parch.combine.core.ui.vo.CombineRegisterVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombineJavaPageService implements ICombineJavaPageService {

    private ConfigLoadingContext context;

    private CombineManager combineManager = new CombineManager();

    private Map<String, String> pageMap = new HashMap<>();

    public CombineJavaPageService(ConfigLoadingContext context) {
        this.context = context;
        this.context.setManager(combineManager);
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
