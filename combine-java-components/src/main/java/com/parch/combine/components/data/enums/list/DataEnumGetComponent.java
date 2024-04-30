package com.parch.combine.components.data.enums.list;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

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
        List<Object> result = new ArrayList<>();
        DataEnumGetLogicConfig logicConfig = getLogicConfig();

        List<EnumCacheHandler.EnumItem> items;
        try {
            items = EnumCacheHandler.get(logicConfig.getKey());
        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumGetErrorEnum.FAIL, e);
            return DataResult.fail(DataEnumGetErrorEnum.FAIL);
        }

        if (items == null) {
            return DataResult.fail(DataEnumGetErrorEnum.ENUM_NO_REGISTER);
        }

        return DataResult.success(items);
    }
}
