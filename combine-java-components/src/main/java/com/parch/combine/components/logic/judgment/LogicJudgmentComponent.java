package com.parch.combine.components.logic.judgment;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentHelper;
import com.parch.combine.core.component.tools.compare.CompareHelper;
import com.parch.combine.core.component.tools.compare.CompareConfig;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.vo.DataResult;
import java.util.ArrayList;
import java.util.List;

/**
 * 逻辑判断组件
 */
@Component(key = "judgment", name = "逻辑判断组件", logicConfigClass = LogicJudgmentLogicConfig.class, initConfigClass = LogicJudgmentInitConfig.class)
@ComponentResult(name = "被执行的组件 ID 集合")
public class LogicJudgmentComponent extends AbsComponent<LogicJudgmentInitConfig, LogicJudgmentLogicConfig> {

    /**
     * 构造器
     */
    public LogicJudgmentComponent() {
        super(LogicJudgmentInitConfig.class, LogicJudgmentLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        LogicJudgmentLogicConfig logicConfig = getLogicConfig();
        List<LogicJudgmentLogicConfig.LogicJudgmentItem> items = logicConfig.getItems();
        if (items != null) {
            // 遍历检测配置项
            for (int i = 0; i < items.size(); i++) {
                LogicJudgmentLogicConfig.LogicJudgmentItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";

                // 检查条件配置
                if (CheckEmptyUtil.isNotEmpty(item.getConditions())) {
                    for (CompareConfig config : item.getConditions()) {
                        for (String compareMsgItem : config.check()) {
                            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + compareMsgItem));
                        }
                    }
                }

                // 初始化逻辑中使用的组件
                if (CheckEmptyUtil.isNotEmpty(item.getComponents())) {
                    List<String> initErrorMsgs = SubComponentHelper.init(manager, item.getComponents());
                    for (String initErrorMsg : initErrorMsgs) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + initErrorMsg));
                    }
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        if (getLogicConfig().getItems() != null) {
            for (LogicJudgmentLogicConfig.LogicJudgmentItem item : getLogicConfig().getItems()) {

                // 逻辑判断是否通过
                if (!isPass(item)) {
                    continue;
                }

                // 没有可执行的组件逻辑
                if (CheckEmptyUtil.isEmpty(item.getComponents())) {
                    break;
                }

                // 逻辑判断通过，返回执行结果
                return SubComponentHelper.execute(manager, item.getComponents());
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
        return CompareHelper.isPass(item, true);
    }
}
