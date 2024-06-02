package com.parch.combine.components.data.general.mapping;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(key = "mapping", name = "数据映射组件", logicConfigClass = DataMappingLogicConfig.class, initConfigClass = DataMappingInitConfig.class)
@ComponentResult(name = "由 items 中配置的所有 “字段名” 组成的对象")
public class DataMappingComponent extends AbsComponent<DataMappingInitConfig, DataMappingLogicConfig> {

    /**
     * 构造器
     */
    public DataMappingComponent() {
        super(DataMappingInitConfig.class, DataMappingLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataMappingLogicConfig logicConfig = getLogicConfig();
        List<DataMappingLogicConfig.DataMappingItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataMappingLogicConfig.DataMappingItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getNewFieldName())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段名为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getSource())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段值来源为空"));
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        Map<String, Object> result = new HashMap<>();

        // 数据过滤
        DataMappingLogicConfig logicConfig = getLogicConfig();
        List<DataMappingLogicConfig.DataMappingItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataMappingLogicConfig.DataMappingItem item : items) {
                result.put(item.getNewFieldName(), DataVariableHelper.parseValue(item.getSource(), false));
            }
        }

        return DataResult.success(result);
    }
}
