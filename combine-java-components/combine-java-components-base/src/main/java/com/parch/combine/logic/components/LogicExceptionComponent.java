package com.parch.combine.logic.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.exception.LogicExceptionErrorEnum;
import com.parch.combine.logic.base.exception.LogicExceptionInitConfig;
import com.parch.combine.logic.base.exception.LogicExceptionLogicConfig;

import java.util.HashMap;
import java.util.Map;

@Component(key = "exception", name = "异常捕获组件", logicConfigClass = LogicExceptionLogicConfig.class, initConfigClass = LogicExceptionInitConfig.class)
@ComponentResult(name = "执行结果结构: { success: false, errorMsg:\"......\", showMsg:\"......\" }")
public class LogicExceptionComponent extends AbstractComponent<LogicExceptionInitConfig, LogicExceptionLogicConfig> {

    public LogicExceptionComponent() {
        super(LogicExceptionInitConfig.class, LogicExceptionLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        ComponentDataResult result = null;
        ComponentDataResult finallyResult = null;

        try {
            result = SubComponentTool.execute(manager, getLogicConfig().components());
        } catch (Exception e) {
            PrintErrorHelper.print(LogicExceptionErrorEnum.SYSTEM_ERROR, e);
        } finally {
            try {
                finallyResult = SubComponentTool.execute(manager, getLogicConfig().finallyComponents());
            } catch (Exception e) {
                PrintErrorHelper.print(LogicExceptionErrorEnum.SYSTEM_ERROR, e);
            }
        }

        if (finallyResult == null) {
            return ComponentDataResult.success(LogicExceptionErrorEnum.SYSTEM_ERROR);
        } else if (!finallyResult.getSuccess()) {
            return ComponentDataResult.fail(finallyResult.getErrMsg(), finallyResult.getShowMsg());
        }

        Map<String, Object> data;
        if (result == null) {
            data = buildResult(false, LogicExceptionErrorEnum.SYSTEM_ERROR.getMsg(), LogicExceptionErrorEnum.SYSTEM_ERROR.getShowMsg());
        } else if (!result.getSuccess()) {
            data = buildResult(false, result.getErrMsg(), result.getShowMsg());
        } else {
            data = buildResult(true, null, null);
        }

        return ComponentDataResult.success(data);
    }

    private Map<String, Object> buildResult(boolean success, String errorMsg, String showMsg) {
        Map<String, Object> data = new HashMap<>();
        data.put("success", success);
        data.put("errorMsg", errorMsg);
        data.put("showMsg", showMsg);
        return data;
    }
}
