package com.parch.combine.component.data.format;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.variable.DataFindHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据格式化组件
 */
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
        if (logicConfig.getReplace() == null) {
            logicConfig.setReplace(false);
        }

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
