package com.parch.combine.components.logic.loop;

import com.parch.combine.core.common.canstant.SymbolConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentHelper;
import com.parch.combine.core.component.tools.compare.CompareHelper;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;

/**
 * 逻辑判断组件
 */
@Component(key = "loop", name = "逻辑循环组件", logicConfigClass = LogicLoopLogicConfig.class, initConfigClass = LogicLoopInitConfig.class)
@ComponentResult(name = "逻辑循环执行")
public class LogicLoopComponent extends AbsComponent<LogicLoopInitConfig, LogicLoopLogicConfig> {

    private final static String INDEX_FILED = "$index";
    private final static String ITEM_FILED = "$item";

    /**
     * 构造器
     */
    public LogicLoopComponent() {
        super(LogicLoopInitConfig.class, LogicLoopLogicConfig.class);
    }


    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        LogicLoopLogicConfig logicConfig = getLogicConfig();

        // 循环次数判断
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource()) && logicConfig.getCount() == null) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "循环次数未定义"));
        } else if (logicConfig.getCount() != null && logicConfig.getCount() < 0) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "循环次数不能小于0"));
        }

        // 初始化循环中使用的组件
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getComponents())) {
            List<String> initErrorMsgs = SubComponentHelper.init(manager, logicConfig.getComponents());
            for (String initErrorMsg : initErrorMsgs) {
                result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        LogicLoopLogicConfig logicConfig = getLogicConfig();

        Collection<?> list = null;
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getSource())) {
            // 验证数据类型
            Object data = DataVariableHelper.parseValue(logicConfig.getSource(), true);
            if (!(data instanceof Collection)) {
                return DataResult.fail(LogicLoopErrorEnum.DATA_ERROR);
            }

            // 数据转换
            list = (Collection<?>) data;

            // 重新设置遍历次数，防止下标越界
            int dataSize = list.size();
            if (logicConfig.getCount() == null || logicConfig.getCount() > dataSize) {
                logicConfig.setCount(dataSize);
            }
        }

        // 设置循环中变量
        String variableKey = SymbolConstant.DOLLAR_SIGN + logicConfig.getId();
        Map<String, Object> variable = new HashMap<>();
        ComponentContextHandler.getVariables().put(variableKey, variable);

        int loopedCont = 0;
        Iterator<?> iterator = list == null ? null : list.iterator();
        for (int i = 0; i < logicConfig.getCount(); i++) {
            // 设置循环中数据
            variable.put(INDEX_FILED, i);
            if (iterator != null) {
                variable.put(ITEM_FILED, iterator.next());
            }
            // 记录遍历次数
            loopedCont++;

            // 条件判断
            if (logicConfig.getCondition() != null) {
                LogicLoopLogicConfig.LoopConditionConfig loopCondition = logicConfig.getCondition();
                // 判断是否需要跳过当前循环
                if (CompareHelper.isPass(loopCondition.getSkip(), false)) {
                    continue;
                }

                // 判断是否停止循环
                if (CompareHelper.isPass(loopCondition.getFinish(), false)) {
                    break;
                }
            }

            // 执行组件逻辑
            DataResult result = SubComponentHelper.execute(manager, logicConfig.getComponents());
            if (logicConfig.getFailStop() && !result.getSuccess()) {
                return DataResult.fail(result.getErrMsg(), result.getShowMsg());
            }
        }

        // 执行完成清理流程中变量
        ComponentContextHandler.getVariables().remove(variableKey);

        return DataResult.success(loopedCont);
    }
}
