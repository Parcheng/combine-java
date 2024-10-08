package com.parch.combine.data.components.general;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.compare.CompareGroupConfig;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.general.reset.DataResetErrorEnum;
import com.parch.combine.data.base.general.reset.DataResetHandler;
import com.parch.combine.data.base.general.reset.DataResetInitConfig;
import com.parch.combine.data.base.general.reset.DataResetLogicConfig;

@Component(key = "reset", name = "数据重置组件", logicConfigClass = DataResetLogicConfig.class, initConfigClass = DataResetInitConfig.class)
@ComponentResult(name = "是否全部赋值成功 true | false")
public class DataResetComponent extends AbstractComponent<DataResetInitConfig, DataResetLogicConfig> {

    /**
     * 构造器
     */
    public DataResetComponent() {
        super(DataResetInitConfig.class, DataResetLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        DataResetLogicConfig logicConfig = getLogicConfig();

        Boolean nullValue = logicConfig.nullValue();
        DataResetLogicConfig.DataResetCompare[] items = logicConfig.items();
        if (items != null) {
            for (DataResetLogicConfig.DataResetCompare item : items) {
                DataResetLogicConfig.DataResetConfig[] resets = item.resets();
                if (CheckEmptyUtil.isEmpty(resets)) {
                    continue;
                }

                CompareGroupConfig compare = item.compare();
                boolean isTrue = CompareTool.isPass(compare, true);
                if (!isTrue) {
                    continue;
                }

                for (DataResetLogicConfig.DataResetConfig reset : resets) {
                    DataResetErrorEnum msg = DataResetHandler.reset(reset, nullValue);
                    if (msg != null) {
                        return ComponentDataResult.fail(msg);
                    }
                }

            }
        }

        return ComponentDataResult.success(true);
    }
}
