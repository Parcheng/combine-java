package com.parch.combine.components.logic.error;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.compare.CompareHelper;
import com.parch.combine.core.tools.compare.CompareConfig;
import com.parch.combine.core.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

/**
 * 异常判断组件
 */
@Component(key = "error", name = "错误抛出组件", logicConfigClass = LogicErrorLogicConfig.class, initConfigClass = LogicErrorInitConfig.class)
@ComponentResult(name = "配置的错误信息或 true")
public class LogicErrorComponent extends AbsComponent<LogicErrorInitConfig, LogicErrorLogicConfig> {

    /**
     * 构造器
     */
    public LogicErrorComponent() {
        super(LogicErrorInitConfig.class, LogicErrorLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        LogicErrorLogicConfig logicConfig = getLogicConfig();
        List<LogicErrorLogicConfig.LogicErrorItem> items = logicConfig.getItems();
        if (items != null) {
            // 遍历检测配置项
            for (int i = 0; i < items.size(); i++) {
                LogicErrorLogicConfig.LogicErrorItem item = items.get(i);
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
                if (CheckEmptyUtil.isEmpty(item.getShowMsg())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "显示错误提示信息不能为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getErrorMsg())) {
                    item.setErrorMsg(item.getShowMsg());
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        if (getLogicConfig().getItems() != null) {
            for (LogicErrorLogicConfig.LogicErrorItem item : getLogicConfig().getItems()) {

                // 逻辑判断是否通过
                if (!isPass(item)) {
                    continue;
                }

                // 输出对应的错误信息
                return DataResult.fail(item.getErrorMsg(), item.getShowMsg());
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
        if (CheckEmptyUtil.isEmpty(item.getConditions())) {
            return true;
        }

        return CompareHelper.isPass(item, false);
    }
}
