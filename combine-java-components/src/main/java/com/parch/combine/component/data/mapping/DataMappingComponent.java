package com.parch.combine.component.data.mapping;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
