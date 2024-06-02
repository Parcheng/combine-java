package com.parch.combine.components.data.general.calc;

import com.parch.combine.core.common.canstant.CommonConstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.ValueTool;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.common.util.MatcherUtil;
import com.parch.combine.core.component.tools.ExpressionCalcTool;

import com.parch.combine.core.component.vo.DataResult;
import java.util.*;

/**
 * 运算组件
 */
@Component(key = "calc", name = "数据计算组件", logicConfigClass = DataCalcLogicConfig.class, initConfigClass = DataCalcInitConfig.class)
@ComponentResult(name = "调用API返回的数据")
public class DataCalcComponent extends AbsComponent<DataCalcInitConfig, DataCalcLogicConfig> {

    /**
     * 构造器
     */
    public DataCalcComponent() {
        super(DataCalcInitConfig.class, DataCalcLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataCalcLogicConfig logicConfig = getLogicConfig();
        List<DataCalcLogicConfig.DataCalcItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataCalcLogicConfig.DataCalcItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getTarget())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "运算结果的目标变量为空"));
                }
                DataCalcModeEnum mode = DataCalcModeEnum.get(item.getMode());
                if (mode == DataCalcModeEnum.NONE) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "运算方式不合规"));
                }
                if (item.getParams().size() < mode.getMinParamCount()) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "参数数量不合规"));
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        List<Object> result = new ArrayList<>();

        // 数据过滤
        DataCalcLogicConfig logicConfig = getLogicConfig();
        List<DataCalcLogicConfig.DataCalcItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataCalcLogicConfig.DataCalcItem item : items) {
                Object calcResult = calc(item);
                result.add(calcResult);
                if (!CommonConstant.PLACEHOLDER.equals(item.getTarget())) {
                    DataVariableHelper.replaceValue(item.getTarget(), calcResult, false);
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
        DataCalcModeEnum mode = DataCalcModeEnum.get(item.getMode());
        switch (mode) {
            case CALC:
                try {
                    // 替换表达式中的变量
                    String[] expression = new String[]{item.getParams().get(0)};
                    MatcherUtil.matcher(expression[0], "\\#\\{(.*?)}", matcherStr -> {
                        Object newValue = DataVariableHelper.parseValue(matcherStr, true);
                        expression[0] = expression[0].replace(matcherStr, newValue.toString());
                    });
                    // 运算表达式
                    calcResult = ExpressionCalcTool.calc(expression[0]);
                } catch (Exception e) {
                    ComponentErrorHandler.print(DataCalcErrorEnum.CALC_ERROR, e);
                    return DataResult.fail(DataCalcErrorEnum.CALC_ERROR);
                }
                break;
            case MAX:
            case MIN:
                List<Object> values = new ArrayList<>();
                for (String param : item.getParams()) {
                    ValueTool.parseValueToList(DataVariableHelper.parseValue(param, false), values);
                }
                calcResult = mode == DataCalcModeEnum.MAX ? ValueTool.max(values, null) : ValueTool.min(values, null);
                break;
            case RANDOM:
                String start, end;
                if (item.getParams().size() > 1) {
                    start = item.getParams().get(0);
                    end = item.getParams().get(1);
                } else {
                    start = "1";
                    end = item.getParams().get(0);
                }

                if (!DataTypeIsUtil.isInteger(start) || !DataTypeIsUtil.isInteger(end)) {
                    ComponentErrorHandler.print(DataCalcErrorEnum.RANDOM_RANG_ERROR);
                    return DataResult.fail(DataCalcErrorEnum.RANDOM_RANG_ERROR);
                }

                int startNum = Math.abs(Integer.parseInt(start));
                int endNum = Math.abs(Integer.parseInt(end));
                calcResult = new Random().nextInt(Math.min(startNum, endNum), Math.max(startNum, endNum));
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
