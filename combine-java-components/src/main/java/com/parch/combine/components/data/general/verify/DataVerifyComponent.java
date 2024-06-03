package com.parch.combine.components.data.general.verify;

import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

import java.util.ArrayList;
import java.util.List;

@Component(key = "verify", name = "数据校验组件", logicConfigClass = DataVerifyLogicConfig.class, initConfigClass = DataVerifyInitConfig.class)
@ComponentResult(name = "校验结果的错误提示信息（或错误提示信息集合）")
public class DataVerifyComponent extends AbsComponent<DataVerifyInitConfig, DataVerifyLogicConfig> {

    public DataVerifyComponent() {
        super(DataVerifyInitConfig.class, DataVerifyLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        List<String> result = new ArrayList<>();

        // 数据过滤
        DataVerifyLogicConfig logicConfig = getLogicConfig();

        String defaultMsg = logicConfig.defaultMsg();
        DataVerifyLogicConfig.DataVerifyItem[] items = logicConfig.items();
        if (items != null) {
            for (DataVerifyLogicConfig.DataVerifyItem item : items) {
                DataVerifyErrorEnum msg = DataVerifyHandler.verify(item, defaultMsg, result);
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
        VerifyModeEnum mode = VerifyModeEnum.get(logicConfig.mode());
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
