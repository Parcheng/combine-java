package com.parch.combine.data.components.general;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.general.mapping.DataMappingInitConfig;
import com.parch.combine.data.base.general.mapping.DataMappingLogicConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(key = "mapping", name = "数据映射组件", logicConfigClass = DataMappingLogicConfig.class, initConfigClass = DataMappingInitConfig.class)
@ComponentResult(name = "由 items 中配置的所有 “字段名” 组成的对象")
public class DataMappingComponent extends AbstractComponent<DataMappingInitConfig, DataMappingLogicConfig> {

    /**
     * 构造器
     */
    public DataMappingComponent() {
        super(DataMappingInitConfig.class, DataMappingLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataMappingLogicConfig logicConfig = getLogicConfig();
        Boolean isArray = logicConfig.isArray();

        List<Map<String, Object>> result = new ArrayList<>();
        DataMappingLogicConfig.DataMappingItem[] items = logicConfig.items();
        if (items != null) {
            for (DataMappingLogicConfig.DataMappingItem item : items) {
                Integer index = item.index();
                if (index < 0) {
                    index = 0;
                }
                for (int i = index - result.size() + 1; i > 0; i--) {
                    result.add(new HashMap<>());
                }

                Map<String, Object> resultItem = result.get(index);
                resultItem.put(item.fieldName(), item.source());
            }
        }

        isArray = isArray == null ? result.size() > 1 : isArray;
        if (isArray) {
            return ComponentDataResult.success(result);
        } else {
            return ComponentDataResult.success(result.size() > 0 ? result.get(0) : null);
        }
    }
}
