package com.parch.combine.ui.old.elements;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.components.web.ElementResultHandler;
import com.parch.combine.components.web.elements.entity.ElementEntity;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 页面组件
 */
@Component(key = "elements", name = "页面元素（组）配置组件", logicConfigClass = WebElementGroupLogicConfig.class, initConfigClass = WebElementGroupInitConfig.class)
@ComponentResult(name = "页面元素（组）配置")
public class WebElementGroupComponent extends AbsComponent<WebElementGroupInitConfig, WebElementGroupLogicConfig> {

    /**
     * 构造器
     */
    public WebElementGroupComponent() {
        super(WebElementGroupInitConfig.class, WebElementGroupLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        WebElementGroupLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getElements())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "未配置任何页面元素"));
        }
        return result;
    }

    @Override
    public DataResult execute() {
        return DataResult.success(buildElements());
    }

    /**
     * 构建HTML体组
     *
     * @return 结果
     */
    public List<Map<String, String>> buildElements() {
        WebElementGroupLogicConfig logicConfig = getLogicConfig();

        List<Map<String, String>> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(logicConfig.getElements())) {
            return result;
        }

        for (ElementEntity<?> item : logicConfig.getElements()) {
            result.add(ElementResultHandler.build(item.getId(), item.getType().name(), JsonUtil.serialize(item).replace("\"", "\\\"")));
        }

        return result;
    }
}
