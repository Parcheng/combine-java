package com.parch.combine.data.components.text;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.text.regex.DataTextRegexErrorEnum;
import com.parch.combine.data.base.text.regex.DataTextRegexInitConfig;
import com.parch.combine.data.base.text.regex.DataTextRegexLogicConfig;
import com.parch.combine.data.base.text.regex.DataTextRegexResultModeEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component(order = 2, key = "text.regex", name = "文本正则处理组件", logicConfigClass = DataTextRegexLogicConfig.class, initConfigClass = DataTextRegexInitConfig.class)
@ComponentResult(name = "处理后的文本（集合）")
public class DataTextRegexComponent extends AbstractComponent<DataTextRegexInitConfig, DataTextRegexLogicConfig> {

    public DataTextRegexComponent() {
        super(DataTextRegexInitConfig.class, DataTextRegexLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataTextRegexLogicConfig logicConfig = getLogicConfig();
        String text = getSourceText();
        String regex = logicConfig.regex();
        if (regex == null) {
            return ComponentDataResult.fail(DataTextRegexErrorEnum.REGEX_IS_NULL);
        }

        return ComponentDataResult.success(match(text, DataTextRegexResultModeEnum.get(logicConfig.resultMode()), regex));
    }

    private String getSourceText() {
        Object data = getLogicConfig().source();
        if (data == null) {
            return CheckEmptyUtil.EMPTY;
        }

        if (data instanceof Map || data instanceof Collection) {
            return JsonUtil.serialize(data);
        } else {
            return data.toString();
        }
    }

    private List<Object> match(String text, DataTextRegexResultModeEnum mode, String regex) {
        List<Object> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
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
