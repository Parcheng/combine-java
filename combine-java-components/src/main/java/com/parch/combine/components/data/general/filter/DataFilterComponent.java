package com.parch.combine.components.data.general.filter;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据过滤组件
 */
@Component(key = "filter", name = "数据过滤组件", logicConfigClass = DataFilterLogicConfig.class, initConfigClass = DataFilterInitConfig.class)
@ComponentResult(name = "参数 resultId 指定组件的结果，或上一步组件的结果")
public class DataFilterComponent extends AbsComponent<DataFilterInitConfig, DataFilterLogicConfig> {

    /**
     * 构造器
     */
    public DataFilterComponent() {
        super(DataFilterInitConfig.class, DataFilterLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataFilterLogicConfig logicConfig = getLogicConfig();
        List<DataFilterLogicConfig.DataFilterItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataFilterLogicConfig.DataFilterItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getRule())) {
                    item.setRule(DataFilterRuleEnum.CLEAR.name());
                }

                if (CheckEmptyUtil.isEmpty(item.getFieldName())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段名为空"));
                }

                DataFilterRuleEnum rule = DataFilterRuleEnum.get(item.getRule());
                if (rule == DataFilterRuleEnum.NONE) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "过滤规则不合规"));
                }
                if (rule == DataFilterRuleEnum.REPLACE && (CheckEmptyUtil.isEmpty(item.getFieldName()) || item.getRuleParams().size() < 1)) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "过滤规则参数不合规"));
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        // 数据过滤
        DataFilterLogicConfig logicConfig = getLogicConfig();
        List<DataFilterLogicConfig.DataFilterItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataFilterLogicConfig.DataFilterItem item : items) {
                DataFilterErrorEnum msg = DataFilterHandler.filter(item);
                if (msg != null) {
                    return DataResult.fail(msg);
                }
            }
        }

        // 获取其他组件的执行结果（配置了resultId就按该字段去，否则取上一步的执行结果）
        String resultId = logicConfig.getResultId();
        DataResult result = CheckEmptyUtil.isEmpty(resultId) ? ComponentContextHandler.getLastResultData() : ComponentContextHandler.getResultData(resultId);
        if (result == null) {
            return DataResult.fail(DataFilterErrorEnum.RESULT_ERROR);
        }
        result.setId(logicConfig.getId());
        return result;
    }
}
