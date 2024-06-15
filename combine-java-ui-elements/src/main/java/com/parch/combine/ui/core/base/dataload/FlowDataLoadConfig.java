package com.parch.combine.ui.core.base.dataload;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.ui.core.settings.PageSettingCanstant;

@CommonObject(order = 2, key = PageSettingCanstant.DATA_LOAD_KEY, name = "加载流程数据源配置", desc = "当 TYPE = FLOW 时的参数列表")
public class FlowDataLoadConfig extends ApiDataLoadConfig {
}
