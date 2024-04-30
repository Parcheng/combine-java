package com.parch.combine.component.data.calc;

import com.parch.combine.common.constant.CommonConstant;
import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.tools.ValueHelper;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.common.util.MatcherUtil;
import com.parch.combine.core.tools.ExpressionCalcHelper;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import java.util.*;

/**
 * 运算组件
 */
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
                    calcResult = ExpressionCalcHelper.calc(expression[0]);
                } catch (Exception e) {
                    ComponentErrorHandler.print(DataCalcErrorEnum.CALC_ERROR, e);
                    return DataResult.fail(DataCalcErrorEnum.CALC_ERROR);
                }
                break;
            case MAX:
            case MIN:
                List<Object> values = new ArrayList<>();
                for (String param : item.getParams()) {
                    ValueHelper.parseValueToList(DataVariableHelper.parseValue(param, false), values);
                }
                calcResult = mode == DataCalcModeEnum.MAX ? ValueHelper.max(values, null) : ValueHelper.min(values, null);
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
                calcResult = UUID.randomUUID();
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
