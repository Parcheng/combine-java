package com.parch.combine.data.components.general;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.data.base.general.mapping.DataMappingInitConfig;
import com.parch.combine.data.base.general.mapping.DataMappingLogicConfig;

import java.util.HashMap;
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
    public DataResult execute() {
        Map<String, Object> result = new HashMap<>();

        // 数据过滤
        DataMappingLogicConfig logicConfig = getLogicConfig();
        DataMappingLogicConfig.DataMappingItem[] items = logicConfig.items();
        if (items != null) {
            for (DataMappingLogicConfig.DataMappingItem item : items) {
                result.put(item.fieldName(), item.source());
            }
        }

        return DataResult.success(result);
    }
}
