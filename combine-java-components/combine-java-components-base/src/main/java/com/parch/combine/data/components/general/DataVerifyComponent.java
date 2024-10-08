package com.parch.combine.data.components.general;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.general.verify.DataVerifyErrorEnum;
import com.parch.combine.data.base.general.verify.DataVerifyHandler;
import com.parch.combine.data.base.general.verify.DataVerifyInitConfig;
import com.parch.combine.data.base.general.verify.DataVerifyLogicConfig;
import com.parch.combine.data.base.general.verify.VerifyModeEnum;

import java.util.ArrayList;
import java.util.List;

@Component(key = "verify", name = "数据校验组件", logicConfigClass = DataVerifyLogicConfig.class, initConfigClass = DataVerifyInitConfig.class)
@ComponentResult(name = "校验结果的错误提示信息（或错误提示信息集合）")
public class DataVerifyComponent extends AbstractComponent<DataVerifyInitConfig, DataVerifyLogicConfig> {

    public DataVerifyComponent() {
        super(DataVerifyInitConfig.class, DataVerifyLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        List<String> result = new ArrayList<>();

        // 数据过滤
        DataVerifyLogicConfig logicConfig = getLogicConfig();

        String defaultMsg = logicConfig.defaultMsg();
        DataVerifyLogicConfig.DataVerifyItem[] items = logicConfig.items();
        if (items != null) {
            for (DataVerifyLogicConfig.DataVerifyItem item : items) {
                DataVerifyErrorEnum msg = DataVerifyHandler.verify(item, defaultMsg, result);
                if (msg != null) {
                    return ComponentDataResult.fail(msg);
                }
            }
        }

        // 验证通过
        if (result.size() == 0) {
            return ComponentDataResult.success(true);
        }

        // 验证不通过，根据模式处理返回的错误信息
        VerifyModeEnum mode = VerifyModeEnum.get(logicConfig.mode());
        switch (mode) {
            case FIRST:
                return ComponentDataResult.fail(result.get(0), DataVerifyErrorEnum.VERIFY_NO_PASS, result.get(0));
            case ALL:
                return ComponentDataResult.fail(result, DataVerifyErrorEnum.VERIFY_NO_PASS, "验证不通过");
            default:
                return ComponentDataResult.fail(DataVerifyErrorEnum.MODE_ERROR);
        }
    }
}
