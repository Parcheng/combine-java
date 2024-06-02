package com.parch.combine.components.data.enums.register;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;

import com.parch.combine.core.component.vo.DataResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 运算组件
 */
@Component(order = 1, key = "enum.register", name = "枚举注册组件", logicConfigClass = DataEnumRegisterLogicConfig.class, initConfigClass = DataEnumRegisterInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class DataEnumRegisterComponent extends AbsComponent<DataEnumRegisterInitConfig, DataEnumRegisterLogicConfig> {

    /**
     * 构造器
     */
    public DataEnumRegisterComponent() {
        super(DataEnumRegisterInitConfig.class, DataEnumRegisterLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();
        DataEnumRegisterLogicConfig logicConfig = getLogicConfig();

        String key = logicConfig.key();
        if (key == null) {
            return DataResult.fail(DataEnumRegisterErrorEnum.KEY_IS_NULL);
        }

        List<EnumCacheHandler.EnumItem> enums = new ArrayList<>();
        for (DataEnumRegisterLogicConfig.EnumItem configItem : logicConfig.items()) {
            EnumCacheHandler.EnumItem item = new EnumCacheHandler.EnumItem();
            item.setCode(configItem.code());
            item.setName(configItem.name());
            item.setDesc(configItem.desc());
            enums.add(item);
        }

        try {
            EnumCacheHandler.register(key, enums);
        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumRegisterErrorEnum.FAIL, e);
            return DataResult.fail(DataEnumRegisterErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
