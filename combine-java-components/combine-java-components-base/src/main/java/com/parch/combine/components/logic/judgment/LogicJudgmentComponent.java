package com.parch.combine.components.logic.judgment;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;

@Component(key = "judgment", name = "逻辑判断组件", logicConfigClass = LogicJudgmentLogicConfig.class, initConfigClass = LogicJudgmentInitConfig.class)
@ComponentResult(name = "被执行的组件 ID 集合")
public class LogicJudgmentComponent extends AbsComponent<LogicJudgmentInitConfig, LogicJudgmentLogicConfig> {

    public LogicJudgmentComponent() {
        super(LogicJudgmentInitConfig.class, LogicJudgmentLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        LogicJudgmentLogicConfig.LogicJudgmentItem[] items = getLogicConfig().items();
        if (items != null) {
            for (LogicJudgmentLogicConfig.LogicJudgmentItem item : items) {

                // 逻辑判断是否通过
                if (!isPass(item)) {
                    continue;
                }

                // 没有可执行的组件逻辑
                String[] components = item.components();
                if (CheckEmptyUtil.isEmpty(components)) {
                    break;
                }

                // 逻辑判断通过，返回执行结果
                return SubComponentTool.execute(manager, components);
            }
        }

        return DataResult.success(new ArrayList<>());
    }

    /**
     * 判断条件是否通过
     *
     * @param item 条件配置
     * @return 是否通过
     */
    private boolean isPass(LogicJudgmentLogicConfig.LogicJudgmentItem item) {
        CompareGroupConfig compare = item.compare();
        if (compare == null) {
            return true;
        }
        return CompareTool.isPass(compare, true);
    }
}
