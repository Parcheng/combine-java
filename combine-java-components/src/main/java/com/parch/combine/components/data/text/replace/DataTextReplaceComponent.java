package com.parch.combine.components.data.text.replace;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.*;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.*;

/**
 * 运算组件
 */
@Component(order = 2, key = "text.replace", name = "文本替换组件", logicConfigClass = DataTextReplaceLogicConfig.class, initConfigClass = DataTextReplaceInitConfig.class)
@ComponentResult(name = "替换后的文本")
public class DataTextReplaceComponent extends AbsComponent<DataTextReplaceInitConfig, DataTextReplaceLogicConfig> {

    /**
     * 构造器
     */
    public DataTextReplaceComponent() {
        super(DataTextReplaceInitConfig.class, DataTextReplaceLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataTextReplaceLogicConfig logicConfig = getLogicConfig();

        DataTextReplaceModeEnum mode = DataTextReplaceModeEnum.get(logicConfig.getMode());
        if (mode == DataTextReplaceModeEnum.NONE) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "非法的替换方式"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getSource())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "数据源为空为空"));
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getOldText())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "旧文本为空"));
        }
        if (logicConfig.getNewText() == null) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "新文本为空"));
        }

        return result;
    }

    @Override
    public DataResult execute() {
        DataTextReplaceLogicConfig logicConfig = getLogicConfig();
        Object result = null;

        try {
            Object data = DataVariableHelper.parseValue(logicConfig.getSource(), true);
            if (data != null) {
                DataTextReplaceModeEnum mode = DataTextReplaceModeEnum.get(logicConfig.getMode());
                result = replace(mode, logicConfig.getOldText(), logicConfig.getNewText(), data);
            }
            if (logicConfig.getIsReplace()) {
                Object finalResult = result;
                DataVariableHelper.replaceValue(logicConfig.getSource(), old -> finalResult);
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(DataTextReplaceErrorEnum.FAIL, e);
            return DataResult.fail(DataTextReplaceErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }

    @SuppressWarnings("unchecked")
    private Object replace(DataTextReplaceModeEnum mode, String oldText, String newText, Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof Map) {
            Map<String, Object> mapData = (Map<String, Object>) data;
            Map<String, Object> newMapData = new HashMap<>(mapData.size());
            mapData.forEach((k, v) -> {
                newMapData.put(k, replace(mode, oldText, newText, v));
            });
            return newMapData;
        } else if (data instanceof Collection) {
            Collection<Object> listData = (Collection<Object>) data;
            List<Object> newListData = new ArrayList<>();
            listData.forEach(v -> newListData.add(replace(mode, oldText, newText, v)));
            return newListData;
        } else {
            switch (mode) {
                case FIRST:
                    return data.toString().replace(oldText, newText);
                case ALL:
                    return data.toString().replaceAll(oldText, newText);
                default:
                    return null;
            }
        }
    }
}
