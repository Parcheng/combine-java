package com.parch.combine.component.web.page.management;

import com.parch.combine.common.util.*;
import com.parch.combine.component.web.page.AbsWebPageComponent;
import com.parch.combine.component.web.page.WebLogicConfig;
import com.parch.combine.component.web.page.WebPageTempConfig;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 页面组件
 */
public class PageManagementComponent extends AbsWebPageComponent<PageManagementInitConfig, PageManagementLogicConfig> {

    /**
     * 构造器
     */
    public PageManagementComponent() {
        super(PageManagementInitConfig.class, PageManagementLogicConfig.class);
    }


    @Override
    public List<String> initConfig() {
        List<String> result = new ArrayList<>();
        PageManagementLogicConfig logicConfig = getLogicConfig();

        if (CheckEmptyUtil.isEmpty(logicConfig.getTempPath())) {
            logicConfig.setTempPath("/static/template/page/management_template.json");
        }

        return result;
    }

    @Override
    public DataResult execute() {
        PageManagementLogicConfig logicConfig = getLogicConfig();
        List<String> errorMsg = new ArrayList<>();

        List<WebPageTempConfig.HtmlElementConfig<WebLogicConfig.HtmlElement>> configs = new ArrayList<>(5);
        configs.add(new WebPageTempConfig.HtmlElementConfig<>("header", logicConfig.getHeader()));
        configs.add(new WebPageTempConfig.HtmlElementConfig<>("footer", logicConfig.getFooter()));
        configs.add(new WebPageTempConfig.HtmlElementConfig<>("left", logicConfig.getLeft()));
        configs.add(new WebPageTempConfig.HtmlElementConfig<>("right", logicConfig.getRight()));
        configs.add(new WebPageTempConfig.HtmlElementConfig<>("content", logicConfig.getContent()));

        String pageCode = super.build(configs, errorMsg);
        if (CheckEmptyUtil.isNotEmpty(errorMsg)) {
            return DataResult.fail(PageManagementErrorEnum.FAIL, StringUtil.join(errorMsg, ","));
        }

        return DataResult.success(pageCode);
    }
}
