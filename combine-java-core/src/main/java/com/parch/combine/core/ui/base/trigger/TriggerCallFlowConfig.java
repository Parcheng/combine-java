package com.parch.combine.core.ui.base.trigger;

import com.parch.combine.core.common.settings.annotations.CommonObject;
import com.parch.combine.core.ui.settings.PageSettingCanstant;


@CommonObject(order = 3, key = PageSettingCanstant.TRIGGER_KEY, name = "调用流程触发配置", desc = "当 TYPE = CALL_FLOW 时的参数列表")
public class TriggerCallFlowConfig extends TriggerCallUrlConfig {
}
