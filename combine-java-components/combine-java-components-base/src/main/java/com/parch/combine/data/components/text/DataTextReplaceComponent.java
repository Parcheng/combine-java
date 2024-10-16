package com.parch.combine.data.components.text;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableFlagHelper;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.text.replace.DataTextReplaceErrorEnum;
import com.parch.combine.data.base.text.replace.DataTextReplaceInitConfig;
import com.parch.combine.data.base.text.replace.DataTextReplaceLogicConfig;
import com.parch.combine.data.base.text.replace.DataTextReplaceModeEnum;

import java.util.*;

/**
 * 运算组件
 */
@Component(order = 2, key = "text.replace", name = "文本替换组件", logicConfigClass = DataTextReplaceLogicConfig.class, initConfigClass = DataTextReplaceInitConfig.class)
@ComponentResult(name = "替换后的文本")
public class DataTextReplaceComponent extends AbstractComponent<DataTextReplaceInitConfig, DataTextReplaceLogicConfig> {

    /**
     * 构造器
     */
    public DataTextReplaceComponent() {
        super(DataTextReplaceInitConfig.class, DataTextReplaceLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataTextReplaceLogicConfig logicConfig = getLogicConfig();
        Object result = null;

        try {
            String source = logicConfig.source();
            Object data = DataVariableHelper.parseValue(logicConfig.source(), true);
            if (data != null) {
                DataTextReplaceModeEnum mode = DataTextReplaceModeEnum.get(logicConfig.mode());
                result = replace(mode, logicConfig.oldText(), logicConfig.newText(), data);
            }
            if (logicConfig.isReplace() && DataVariableFlagHelper.hasParseFlag(source)) {
                Object finalResult = result;
                DataVariableHelper.replaceValue(source, old -> finalResult);
            }
        } catch (Exception e) {
            PrintErrorHelper.print(DataTextReplaceErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataTextReplaceErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }

    @SuppressWarnings("unchecked")
    private Object replace(DataTextReplaceModeEnum mode, String oldText, String newText, Object data) {
        if (data == null) {
            return null;
        }

        Object oldTextObj = DataVariableHelper.parseValue(oldText, false);
        String finalOldText = oldTextObj == null ? oldText : oldTextObj.toString();

        Object newTextObj = DataVariableHelper.parseValue(newText, false);
        String finalNewTest = newTextObj == null ?  newText : newTextObj.toString();

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            Map<String, Object> newMapData = new HashMap<>(mapData.size());
            mapData.forEach((k, v) -> {
                newMapData.put(k, replace(mode, finalOldText, finalNewTest, v));
            });
            return newMapData;
        } else if (data instanceof Collection) {
            Collection<Object> listData = (Collection<Object>) data;
            List<Object> newListData = new ArrayList<>();
            listData.forEach(v -> newListData.add(replace(mode, finalOldText, finalNewTest, v)));
            return newListData;
        } else {
            switch (mode) {
                case FIRST:
                    return data.toString().replaceFirst(finalOldText, finalNewTest);
                case ALL:
                    return data.toString().replaceAll(finalOldText, finalNewTest);
                default:
                    return null;
            }
        }
    }
}
