package com.parch.combine.components.data.enums.list;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataFindHandler;

import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 运算组件
 */
@Component(order = 1, key = "enum.get", name = "枚举获取组件", logicConfigClass = DataEnumGetLogicConfig.class, initConfigClass = DataEnumGetInitConfig.class)
@ComponentResult(name = "枚举项集合")
public class DataEnumGetComponent extends AbsComponent<DataEnumGetInitConfig, DataEnumGetLogicConfig> {

    /**
     * 构造器
     */
    public DataEnumGetComponent() {
        super(DataEnumGetInitConfig.class, DataEnumGetLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataEnumGetLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "枚举KEY为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        DataEnumGetLogicConfig logicConfig = getLogicConfig();
        Object key = DataVariableHelper.parseValue(logicConfig.getKey(), false);
        if (key == null) {
            return DataResult.fail(DataEnumGetErrorEnum.KEY_IS_NULL);
        }

        List<EnumCacheHandler.EnumItem> items;
        try {
            items = EnumCacheHandler.get(key.toString());
        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumGetErrorEnum.FAIL, e);
            return DataResult.fail(DataEnumGetErrorEnum.FAIL);
        }

        if (items == null) {
            return DataResult.fail(DataEnumGetErrorEnum.ENUM_NO_REGISTER);
        }

        List<Map<String, String>> result = new ArrayList<>();
        for (EnumCacheHandler.EnumItem item : items) {
            Map<String, String> resultItem = new HashMap<>(8);
            resultItem.put("code", item.getCode());
            resultItem.put("name", item.getName());
            resultItem.put("desc", item.getDesc());
            result.add(resultItem);
        }

        return DataResult.success(result);
    }
}
