package com.parch.combine.components.data.enums.register;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

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
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataEnumRegisterLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "枚举KEY为空"));
        }

        List<EnumCacheHandler.EnumItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                EnumCacheHandler.EnumItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getCode())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "枚举编码为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getCode())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "枚举名称为空"));
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();
        DataEnumRegisterLogicConfig logicConfig = getLogicConfig();
        try {
            EnumCacheHandler.register(logicConfig.getKey(), logicConfig.getItems());
        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumRegisterErrorEnum.FAIL, e);
            return DataResult.fail(DataEnumRegisterErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
