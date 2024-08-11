package com.parch.combine.data.components.general;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataFindHandler;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.general.format.DataFormatErrorEnum;
import com.parch.combine.data.base.general.format.DataFormatFunctionEnum;
import com.parch.combine.data.base.general.format.DataFormatHandler;
import com.parch.combine.data.base.general.format.DataFormatInitConfig;
import com.parch.combine.data.base.general.format.DataFormatLogicConfig;

import java.util.ArrayList;
import java.util.List;

@Component(key = "format", name = "数据格式化组件", logicConfigClass = DataFormatLogicConfig.class, initConfigClass = DataFormatInitConfig.class)
@ComponentResult(name = "所有格式化后的新值集合")
public class DataFormatComponent extends AbstractComponent<DataFormatInitConfig, DataFormatLogicConfig> {

    public DataFormatComponent() {
        super(DataFormatInitConfig.class, DataFormatLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<Object> result = new ArrayList<>();

        // 执行格式化
        DataFormatLogicConfig logicConfig = getLogicConfig();
        DataFormatLogicConfig.DataFormatItem[] items = logicConfig.items();
        for (DataFormatLogicConfig.DataFormatItem item : items) {
            String source = item.source();

            DataFormatFunctionEnum functionType = DataFormatFunctionEnum.get(item.function());
            if (functionType == DataFormatFunctionEnum.NONE) {
                return ComponentDataResult.fail(DataFormatErrorEnum.FUNCTION_IS_NULL);
            }

            String[] params = item.params();
            List<String> errors = DataFormatHandler.checkParams(functionType, params);
            if (CheckEmptyUtil.isNotEmpty(errors)) {
                return ComponentDataResult.fail(errors, DataFormatErrorEnum.PARAMS_ERROR);
            }

            // 获取可执行格式化函数
            DataFindHandler.GetDataFunction<Object> function = DataFormatHandler.getFunction(functionType, params);
            if (function == null) {
                return ComponentDataResult.fail(DataFormatErrorEnum.FAIL);
            }

            // 执行替换操作
            if (logicConfig.replace()) {
                if (!DataFindHandler.replaceAsFunc(source, function)) {
                    return ComponentDataResult.fail(DataFormatErrorEnum.FAIL);
                }
                result.add(DataFindHandler.find(source));
            } else {
                try {
                    result.add(function.get(DataFindHandler.find(source)));
                } catch (Exception e) {
                    result.add(null);
                }
            }
        }

        return ComponentDataResult.success(result);
    }
}
