package com.parch.combine.ui.old.page.custom;

import com.parch.combine.components.web.page.AbsWebPageComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面组件
 */
@Component(key = "page.custom", name = "自定义页面组件", logicConfigClass = PageCustomLogicConfig.class, initConfigClass = PageCustomInitConfig.class)
@ComponentResult(name = "页面的HTML代码")
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
