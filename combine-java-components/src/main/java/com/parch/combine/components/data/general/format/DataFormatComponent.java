package com.parch.combine.components.data.general.format;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataFindHandler;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据格式化组件
 */
@Component(key = "format", name = "数据格式化组件", logicConfigClass = DataFormatLogicConfig.class, initConfigClass = DataFormatInitConfig.class)
@ComponentResult(name = "所有格式化后的新值集合")
public class DataFormatComponent extends AbsComponent<DataFormatInitConfig, DataFormatLogicConfig> {

    /**
     * 构造器
     */
    public DataFormatComponent() {
        super(DataFormatInitConfig.class, DataFormatLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataFormatLogicConfig logicConfig = getLogicConfig();
        List<DataFormatLogicConfig.DataFormatItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataFormatLogicConfig.DataFormatItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getFieldName())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段名为空"));
                }

                // 检测函数参数
                DataFormatFunctionEnum function = item.getFunction();
                if (function == DataFormatFunctionEnum.NONE) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "函数不存在"));
                }
                List<String> errorMsgs = DataFormatHandler.checkParams(function, item.getParams());
                if (errorMsgs != null) {
                    for (String errorMsg : errorMsgs) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + errorMsg));
                    }
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();

        // 执行格式化
        DataFormatLogicConfig logicConfig = getLogicConfig();
        List<DataFormatLogicConfig.DataFormatItem> items = logicConfig.getItems();
        for(DataFormatLogicConfig.DataFormatItem item : items) {
            String filedName = item.getFieldName();

            // 获取可执行格式化函数
            DataFindHandler.GetDataFunction<Object> function = DataFormatHandler.getFunction(item.getFunction(), item.getParams());
            if (function == null) {
                return DataResult.fail(DataFormatErrorEnum.FAIL);
            }

            // 执行替换操作
            if (logicConfig.getReplace()) {
                if (!DataFindHandler.replaceAsFunc(filedName, function)) {
                    return DataResult.fail(DataFormatErrorEnum.FAIL);
                }
                result.add(DataFindHandler.find(filedName));
            } else {
                try {
                    result.add(function.get(DataFindHandler.find(filedName)));
                } catch (Exception e) {
                    result.add(null);
                }
            }
        }

        return DataResult.success(result);
    }
}
