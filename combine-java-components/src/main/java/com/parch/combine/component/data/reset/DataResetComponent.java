package com.parch.combine.component.data.reset;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;
import java.util.ArrayList;
import java.util.List;

public class DataResetComponent extends AbsComponent<DataResetInitConfig, DataResetLogicConfig> {

    /**
     * 构造器
     */
    public DataResetComponent() {
        super(DataResetInitConfig.class, DataResetLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataResetLogicConfig logicConfig = getLogicConfig();
        List<DataResetLogicConfig.DataResetItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataResetLogicConfig.DataResetItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getFieldName())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段名为空"));
                }
                if(CheckEmptyUtil.isEmpty(item.getValue())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "值为空"));
                }
                String msg = DataResetHandler.checkDataType(item.getType(), item.getValue());
                if (msg != null) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + msg));
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        DataResetLogicConfig logicConfig = getLogicConfig();
        List<DataResetLogicConfig.DataResetItem> items = logicConfig.getItems();
        if (items != null) {
            for (DataResetLogicConfig.DataResetItem item : items) {
                DataResetErrorEnum msg = DataResetHandler.reset(item);
                if (msg != null) {
                    return DataResult.fail(msg);
                }
            }
        }

        return DataResult.success(true);
    }
}
