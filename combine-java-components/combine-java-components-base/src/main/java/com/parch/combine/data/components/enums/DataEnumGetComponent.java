package com.parch.combine.data.components.enums;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.data.base.enums.list.DataEnumGetErrorEnum;
import com.parch.combine.data.base.enums.list.DataEnumGetInitConfig;
import com.parch.combine.data.base.enums.list.DataEnumGetLogicConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(order = 1, key = "enum.get", name = "枚举获取组件", logicConfigClass = DataEnumGetLogicConfig.class, initConfigClass = DataEnumGetInitConfig.class)
@ComponentResult(name = "枚举项集合")
public class DataEnumGetComponent extends AbstractComponent<DataEnumGetInitConfig, DataEnumGetLogicConfig> {

    public DataEnumGetComponent() {
        super(DataEnumGetInitConfig.class, DataEnumGetLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataEnumGetLogicConfig logicConfig = getLogicConfig();
        String key = logicConfig.key();
        if (key == null) {
            return ComponentDataResult.fail(DataEnumGetErrorEnum.KEY_IS_NULL);
        }

        List<EnumCacheHandler.EnumItem> items;
        try {
            items = EnumCacheHandler.get(key);
        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumGetErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataEnumGetErrorEnum.FAIL);
        }

        if (items == null) {
            return ComponentDataResult.fail(DataEnumGetErrorEnum.ENUM_NO_REGISTER);
        }

        List<Map<String, String>> result = new ArrayList<>();
        for (EnumCacheHandler.EnumItem item : items) {
            Map<String, String> resultItem = new HashMap<>(8);
            resultItem.put("code", item.getCode());
            resultItem.put("name", item.getName());
            resultItem.put("desc", item.getDesc());
            result.add(resultItem);
        }

        return ComponentDataResult.success(result);
    }
}
