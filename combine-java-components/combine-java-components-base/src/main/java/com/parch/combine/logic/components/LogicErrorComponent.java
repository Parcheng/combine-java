package com.parch.combine.logic.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.logic.base.error.LogicErrorInitConfig;
import com.parch.combine.logic.base.error.LogicErrorLogicConfig;

@Component(key = "error", name = "错误抛出组件", logicConfigClass = LogicErrorLogicConfig.class, initConfigClass = LogicErrorInitConfig.class)
@ComponentResult(name = "配置的错误信息或 true")
public class LogicErrorComponent extends AbsComponent<LogicErrorInitConfig, LogicErrorLogicConfig> {

    public LogicErrorComponent() {
        super(LogicErrorInitConfig.class, LogicErrorLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        LogicErrorLogicConfig.LogicErrorItem[] items = getLogicConfig().items();
        if (items != null) {
            for (LogicErrorLogicConfig.LogicErrorItem item : items) {

                // 逻辑判断是否通过
                if (!isPass(item)) {
                    continue;
                }

                // 输出对应的错误信息
                return DataResult.fail(item.errorMsg(), item.showMsg());
            }
        }

        return DataResult.success(true);
    }

    /**
     * 判断条件是否通过
     *
     * @param item 条件配置
     * @return 是否通过
     */
    private boolean isPass(LogicErrorLogicConfig.LogicErrorItem item) {
        // 无条件配置，直接通过
        if (item.compare() == null || CheckEmptyUtil.isEmpty(item.compare().getConditions())) {
            return true;
        }

        return CompareTool.isPass(item.compare(), false);
    }
}
