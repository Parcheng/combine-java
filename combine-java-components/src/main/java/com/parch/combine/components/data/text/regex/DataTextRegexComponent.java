package com.parch.combine.components.data.text.regex;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 运算组件
 */
@Component(order = 2, key = "text.regex", name = "文本正则处理组件", logicConfigClass = DataTextRegexLogicConfig.class, initConfigClass = DataTextRegexInitConfig.class)
@ComponentResult(name = "处理后的文本（集合）")
public class DataTextRegexComponent extends AbsComponent<DataTextRegexInitConfig, DataTextRegexLogicConfig> {

    /**
     * 构造器
     */
    public DataTextRegexComponent() {
        super(DataTextRegexInitConfig.class, DataTextRegexLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataTextRegexLogicConfig logicConfig = getLogicConfig();
        DataTextRegexResultModeEnum mode = DataTextRegexResultModeEnum.get(logicConfig.getResultMode());
        if (mode == DataTextRegexResultModeEnum.NONE) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "非法的返回结果方式"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "数据源为空为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getRegex())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "正则表达式为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        String text = getSourceText();
        return DataResult.success(match(text));
    }

    private String getSourceText() {
        Object data = DataVariableHelper.parseValue(getLogicConfig().getSource(), false);
        if (data instanceof Map || data instanceof Collection) {
            return JsonUtil.serialize(data);
        } else {
            return data.toString();
        }
    }

    private List<Object> match(String text) {
        List<Object> result = new ArrayList<>();
        DataTextRegexLogicConfig logicConfig = getLogicConfig();
        DataTextRegexResultModeEnum mode = DataTextRegexResultModeEnum.get(logicConfig.getResultMode());

        Pattern pattern = Pattern.compile(logicConfig.getRegex(), Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            if (mode == DataTextRegexResultModeEnum.FULL) {
                result.add(matcher.group());
            } else {
                int groupCount = matcher.groupCount();
                List<String> groups = new ArrayList<>(groupCount);
                for (int i = 1; i <= groupCount; i++) {
                    groups.add(matcher.group(i));
                }
                result.add(groups);
            }
        }

        return result;
    }
}
