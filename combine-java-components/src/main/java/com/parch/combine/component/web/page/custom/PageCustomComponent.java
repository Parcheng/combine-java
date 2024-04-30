package com.parch.combine.component.web.page.custom;

import com.parch.combine.common.util.*;
import com.parch.combine.component.web.page.AbsWebPageComponent;
import com.parch.combine.component.web.page.WebPageTempConfig;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 页面组件
 */
public class PageCustomComponent extends AbsWebPageComponent<PageCustomInitConfig, PageCustomLogicConfig> {

    /**
     * 构造器
     */
    public PageCustomComponent() {
        super(PageCustomInitConfig.class, PageCustomLogicConfig.class);
    }


    @Override
    public List<String> initConfig() {
        List<String> result = new ArrayList<>();
        PageCustomLogicConfig logicConfig = getLogicConfig();

        if (CheckEmptyUtil.isEmpty(logicConfig.getTempPath())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板地址为空"));
        }

        if (CheckEmptyUtil.isEmpty(logicConfig.getConfigs())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "页面未添加DOM元素"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        PageCustomLogicConfig logicConfig = getLogicConfig();
        List<String> errorMsg = new ArrayList<>();

        String pageCode = super.build(logicConfig.getConfigs(), errorMsg);
        if (CheckEmptyUtil.isNotEmpty(errorMsg)) {
            return DataResult.fail(PageCustomErrorEnum.FAIL, StringUtil.join(errorMsg, ","));
        }

        return DataResult.success(pageCode);
    }
}
