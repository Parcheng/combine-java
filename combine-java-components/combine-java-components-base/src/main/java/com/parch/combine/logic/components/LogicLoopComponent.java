package com.parch.combine.logic.components;

import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.logic.base.loop.LogicLoopErrorEnum;
import com.parch.combine.logic.base.loop.LogicLoopInitConfig;
import com.parch.combine.logic.base.loop.LogicLoopLogicConfig;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component(key = "loop", name = "逻辑循环组件", logicConfigClass = LogicLoopLogicConfig.class, initConfigClass = LogicLoopInitConfig.class)
@ComponentDesc({
    "循环中变量:",
    "$v.$组件ID.$index: 当前迭代项的索引（从0开始）",
    "$v.$组件ID.$item: 当前迭代项对象",
    "其中 $index 和 $item 属性名可以在初始化配置中自己指定"
})
@ComponentResult(name = "逻辑循环执行")
public class LogicLoopComponent extends AbstractComponent<LogicLoopInitConfig, LogicLoopLogicConfig> {

    public LogicLoopComponent() {
        super(LogicLoopInitConfig.class, LogicLoopLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        LogicLoopLogicConfig logicConfig = getLogicConfig();
        LogicLoopInitConfig initConfig = getInitConfig();

        Collection<?> list = null;
        Object data = logicConfig.source();
        Integer count = logicConfig.count();

        if (data != null) {
            // 验证数据类型
            if (!(data instanceof Collection)) {
                return ComponentDataResult.fail(LogicLoopErrorEnum.DATA_ERROR);
            }

            // 数据转换
            list = (Collection<?>) data;

            // 重新设置遍历次数，防止下标越界
            int dataSize = list.size();
            if (count == null || count > dataSize) {
                count = dataSize;
            }
        }

        // 设置循环中变量
        String variableKey = SymbolConstant.DOLLAR_SIGN + logicConfig.id();
        Map<String, Object> variable = new HashMap<>();
        ComponentContextHandler.getVariables().put(variableKey, variable);

        int loopedCont = 0;
        Iterator<?> iterator = list == null ? null : list.iterator();
        for (int i = 0; i < count; i++) {
            // 设置循环中数据
            variable.put(initConfig.indexPropertyName(), i);
            if (iterator != null) {
                variable.put(initConfig.itemPropertyName(), iterator.next());
            }
            // 记录遍历次数
            loopedCont++;

            // 条件判断
            LogicLoopLogicConfig.LoopConditionConfig loopCondition = logicConfig.condition();
            if (loopCondition != null) {
                // 判断是否需要跳过当前循环
                if (CompareTool.isPass(loopCondition.skip(), false)) {
                    continue;
                }

                // 判断是否停止循环
                if (CompareTool.isPass(loopCondition.finish(), false)) {
                    break;
                }
            }

            // 执行组件逻辑
            ComponentDataResult result = SubComponentTool.execute(manager, logicConfig.components());
            if (logicConfig.failStop() && !result.getSuccess()) {
                return ComponentDataResult.fail(result.getErrMsg(), result.getShowMsg());
            }
        }

        // 执行完成清理流程中变量
        ComponentContextHandler.getVariables().remove(variableKey);

        return ComponentDataResult.success(loopedCont);
    }
}
