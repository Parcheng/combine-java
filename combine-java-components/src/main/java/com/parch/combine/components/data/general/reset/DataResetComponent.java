package com.parch.combine.components.data.general.reset;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.compare.CompareHelper;
import com.parch.combine.core.vo.DataResult;
import java.util.ArrayList;
import java.util.List;

@Component(key = "reset", name = "数据重置组件", logicConfigClass = DataResetLogicConfig.class, initConfigClass = DataResetInitConfig.class)
@ComponentResult(name = "是否全部赋值成功 true | false")
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
        List<DataResetLogicConfig.DataResetCompare> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataResetLogicConfig.DataResetCompare item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (item.getResets() != null) {
                    for (DataResetLogicConfig.DataResetConfig reset : item.getResets()) {
                        if (CheckEmptyUtil.isEmpty(reset.getFieldName())) {
                            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "字段名为空"));
                        }
                        if(CheckEmptyUtil.isEmpty(reset.getValue())) {
                            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "值为空"));
                        }
                        String msg = DataResetHandler.checkDataType(reset.getType(), reset.getValue());
                        if (msg != null) {
                            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + msg));
                        }
                    }
                }
            }
        }

        return result;
    }

    @Override
    public DataResult execute() {
        DataResetLogicConfig logicConfig = getLogicConfig();
        List<DataResetLogicConfig.DataResetCompare> items = logicConfig.getItems();
        if (items != null) {
            for (DataResetLogicConfig.DataResetCompare item : items) {
                if (CheckEmptyUtil.isEmpty(item.getResets())) {
                    continue;
                }

                boolean isTrue = CompareHelper.isPass(item, true);
                if (!isTrue) {
                    continue;
                }

                for (DataResetLogicConfig.DataResetConfig reset : item.getResets()) {
                    DataResetErrorEnum msg = DataResetHandler.reset(reset, logicConfig.getNullValue());
                    if (msg != null) {
                        return DataResult.fail(msg);
                    }
                }

            }
        }

        return DataResult.success(true);
    }
}
