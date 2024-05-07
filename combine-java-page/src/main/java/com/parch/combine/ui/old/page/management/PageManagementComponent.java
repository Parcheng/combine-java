package com.parch.combine.ui.old.page.management;

import com.parch.combine.components.web.page.AbsWebPageComponent;
import com.parch.combine.components.web.page.WebPageLogicConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面组件
 */
@Component(key = "page.management", name = "管理页面组件", logicConfigClass = PageManagementLogicConfig.class, initConfigClass = PageManagementInitConfig.class)
@ComponentResult(name = "页面的HTML代码")
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
        return result;
    }

    @Override
    public DataResult execute() {
        PageManagementLogicConfig logicConfig = getLogicConfig();
        List<String> errorMsg = new ArrayList<>();

        List<WebPageLogicConfig.HtmlElementConfig> configs = new ArrayList<>(5);
        configs.add(new WebPageLogicConfig.HtmlElementConfig("header", logicConfig.getHeader()));
        configs.add(new WebPageLogicConfig.HtmlElementConfig("footer", logicConfig.getFooter()));
        configs.add(new WebPageLogicConfig.HtmlElementConfig("left", logicConfig.getLeft()));
        configs.add(new WebPageLogicConfig.HtmlElementConfig("right", logicConfig.getRight()));
        configs.add(new WebPageLogicConfig.HtmlElementConfig("content", logicConfig.getContent()));

        String pageCode = super.build(configs, errorMsg);
        if (CheckEmptyUtil.isNotEmpty(errorMsg)) {
            return DataResult.fail(PageManagementErrorEnum.FAIL, StringUtil.join(errorMsg, ","));
        }

        return DataResult.success(pageCode);
    }
}
