package com.parch.combine.data.components.enums;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.data.base.enums.register.DataEnumRegisterErrorEnum;
import com.parch.combine.data.base.enums.register.DataEnumRegisterInitConfig;
import com.parch.combine.data.base.enums.register.DataEnumRegisterLogicConfig;

import java.util.ArrayList;
import java.util.List;

@Component(order = 1, key = "enum.register", name = "枚举注册组件", logicConfigClass = DataEnumRegisterLogicConfig.class, initConfigClass = DataEnumRegisterInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class DataEnumRegisterComponent extends AbstractComponent<DataEnumRegisterInitConfig, DataEnumRegisterLogicConfig> {

    public DataEnumRegisterComponent() {
        super(DataEnumRegisterInitConfig.class, DataEnumRegisterLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<Object> result = new ArrayList<>();
        DataEnumRegisterLogicConfig logicConfig = getLogicConfig();

        String key = logicConfig.key();
        if (key == null) {
            return ComponentDataResult.fail(DataEnumRegisterErrorEnum.KEY_IS_NULL);
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
            return ComponentDataResult.fail(DataEnumRegisterErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
