package com.parch.combine.components.data.general.calc;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.calc.ValueOptTool;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.tools.calc.ExpressionCalcTool;

import com.parch.combine.core.component.tools.variable.DataVariableFlagHelper;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

@Component(key = "calc", name = "数据计算组件", logicConfigClass = DataCalcLogicConfig.class, initConfigClass = DataCalcInitConfig.class)
@ComponentResult(name = "调用API返回的数据")
public class DataCalcComponent extends AbsComponent<DataCalcInitConfig, DataCalcLogicConfig> {

    public DataCalcComponent() {
        super(DataCalcInitConfig.class, DataCalcLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();

        // 数据过滤
        DataCalcLogicConfig logicConfig = getLogicConfig();
        DataCalcLogicConfig.DataCalcItem[] items = logicConfig.items();
        if (items != null) {
            for (DataCalcLogicConfig.DataCalcItem item : items) {
                Object calcResult = calc(item);
                result.add(calcResult);

                String target = item.target();
                if (CheckEmptyUtil.isNotEmpty(target)) {
                    DataVariableHelper.replaceValue(target, calcResult, false);
                }
            }
        }

        return DataResult.success(result);
    }

    /**
     * 计算
     *
     * @param item 计算配置
     * @return 结果
     */
    private Object calc(DataCalcLogicConfig.DataCalcItem item) {
        Object calcResult = null;
        DataCalcModeEnum mode = DataCalcModeEnum.get(item.mode());
        Object[] params = item.params();
        if (params == null) {
            params = new Object[0];
        }
        if (params.length < mode.getMinParamCount()) {
            return DataResult.fail(DataCalcErrorEnum.PARAMS_COUNT_ERROR);
        }

        switch (mode) {
            case CALC:
                try {
                    // 替换表达式中的变量
                    Object[] finalParams = params;
                    StringUtil.matcher(params[0].toString(), DataVariableFlagHelper.getRegex(), matcherStr -> {
                        Object newValue = DataVariableHelper.parseValue(matcherStr, true);
                        finalParams[0] = finalParams[0].toString().replace(matcherStr, newValue.toString());
                    });
                    // 运算表达式
                    calcResult = ExpressionCalcTool.calc(params[0].toString());
                } catch (Exception e) {
                    ComponentErrorHandler.print(DataCalcErrorEnum.CALC_ERROR, e);
                    return DataResult.fail(DataCalcErrorEnum.CALC_ERROR);
                }
                break;
            case MAX:
            case MIN:
                List<Object> values = new ArrayList<>();
                for (Object param : params) {
                    ValueOptTool.parseValueToList(param, values);
                }
                calcResult = mode == DataCalcModeEnum.MAX ? ValueOptTool.max(values, null) : ValueOptTool.min(values, null);
                break;
            case RANDOM:
                String start, end;
                if (params.length > 1) {
                    start = params[0].toString();
                    end = params[1].toString();
                } else {
                    start = "0";
                    end = params[0].toString();
                }

                if (!DataTypeIsUtil.isInteger(start) || !DataTypeIsUtil.isInteger(end)) {
                    ComponentErrorHandler.print(DataCalcErrorEnum.RANDOM_RANG_ERROR);
                    return DataResult.fail(DataCalcErrorEnum.RANDOM_RANG_ERROR);
                }

                int startNum = Integer.parseInt(start);
                int endNum = Integer.parseInt(end);
                calcResult = startNum + new Random().nextInt(endNum - startNum);
                break;
            case UUID:
                calcResult = UUID.randomUUID().toString();
                break;
            default:
                break;
        }

        // 运算失败
        if (calcResult == null) {
            return DataResult.fail(DataCalcErrorEnum.FAIL);
        }

        return calcResult;
    }
}
