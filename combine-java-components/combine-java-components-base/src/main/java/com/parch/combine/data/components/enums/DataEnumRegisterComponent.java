package com.parch.combine.data.components.enums;

import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.data.base.enums.register.DataEnumRegisterErrorEnum;
import com.parch.combine.data.base.enums.register.DataEnumRegisterLogicConfig;

import java.util.ArrayList;
import java.util.List;

@Component(order = 1, key = "enum.register", name = "枚举注册组件", logicConfigClass = DataEnumRegisterLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "true 或异常信息")
public class DataEnumRegisterComponent extends AbstractComponent<IInvalidInitConfig, DataEnumRegisterLogicConfig> {

    public DataEnumRegisterComponent() {
        super(IInvalidInitConfig.class, DataEnumRegisterLogicConfig.class);
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
            PrintErrorHelper.print(DataEnumRegisterErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataEnumRegisterErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
