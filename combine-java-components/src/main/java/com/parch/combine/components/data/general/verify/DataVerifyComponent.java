package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.compare.CompareConfig;
import com.parch.combine.core.component.tools.compare.CompareTypeEnum;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

@Component(key = "verify", name = "数据校验组件", logicConfigClass = DataVerifyLogicConfig.class, initConfigClass = DataVerifyInitConfig.class)
@ComponentResult(name = "校验结果的错误提示信息（或错误提示信息集合）")
public class DataVerifyComponent extends AbsComponent<DataVerifyInitConfig, DataVerifyLogicConfig> {

    /**
     * 构造器
     */
    public DataVerifyComponent() {
        super(DataVerifyInitConfig.class, DataVerifyLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataVerifyLogicConfig logicConfig = getLogicConfig();
        VerifyModeEnum mode = VerifyModeEnum.get(logicConfig.getMode());
        if (mode == VerifyModeEnum.NONE) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "验证模式不合规"));
        }

        List<DataVerifyLogicConfig.DataVerifyItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataVerifyLogicConfig.DataVerifyItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";

                if (CheckEmptyUtil.isEmpty(item.getMsg())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "错误提示信息为空"));
                }

                for (int j = 0; j < item.getConditions().size(); j++) {
                    CompareConfig condition = item.getConditions().get(j);
                    String baseConditionMsg = "第<" + (j+1) + ">个条件-";

                    if (CheckEmptyUtil.isEmpty(condition.getSource())) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + baseConditionMsg + "字段名为空"));
                    }
                    if (condition.getCompareType() == CompareTypeEnum.NONE) {
                        result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + baseConditionMsg + "条件类型不合规"));
                    }
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        List<String> result = new ArrayList<>();

        // 数据过滤
        DataVerifyLogicConfig logicConfig = getLogicConfig();
        List<DataVerifyLogicConfig.DataVerifyItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataVerifyLogicConfig.DataVerifyItem item : items) {
                DataVerifyErrorEnum msg = DataVerifyHandler.verify(item, logicConfig.getDefaultMsg(), result);
                if (msg != null) {
                    return DataResult.fail(msg);
                }
            }
        }

        // 验证通过
        if (result.size() == 0) {
            return DataResult.success(true);
        }

        // 验证不通过，根据模式处理返回的错误信息
        VerifyModeEnum mode = VerifyModeEnum.get(logicConfig.getMode());
        switch (mode) {
            case FIRST:
                return DataResult.fail(result.get(0), DataVerifyErrorEnum.VERIFY_NO_PASS, result.get(0));
            case ALL:
                return DataResult.fail(result, DataVerifyErrorEnum.VERIFY_NO_PASS, "验证不通过");
            default:
                return DataResult.fail(DataVerifyErrorEnum.MODE_ERROR);
        }
    }
}
