package com.parch.combine.components.system.template;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.manager.ComponentManager;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.core.vo.FlowInitVO;

import java.util.*;

/**
 * 组件设置信息组件
 */
@Component(key = "template", name = "模板组件", logicConfigClass = SystemTemplateLogicConfig.class, initConfigClass = SystemTemplateInitConfig.class)
@ComponentResult(name = "模板中组件报错信息或 true")
public class SystemTemplateComponent extends AbsComponent<SystemTemplateInitConfig, SystemTemplateLogicConfig> {

    /**
     * 构造器
     */
    public SystemTemplateComponent() {
        super(SystemTemplateInitConfig.class, SystemTemplateLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        SystemTemplateInitConfig initConfig = getInitConfig();
        SystemTemplateLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getKey())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板KEY为空"));
        }

        List<Map<String, Object>> configs = initConfig.getTemplates() == null ? null : initConfig.getTemplates().get(logicConfig.getKey());
        if (configs == null) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板不存在, KEY: " + logicConfig.getKey()));
        } else {
            // 初始化ID
            for (Map<String, Object> config : configs) {
                if (config.get(ComponentManager.ID_FIELD) == null) {
                    config.put(ComponentManager.ID_FIELD, UUID.randomUUID().toString());
                }
            }

            // 初始化模板使用的组件
            FlowInitVO initVO = manager.getComponent().init(getScopeKey(), configs);
            if (!initVO.isSuccess()) {
                for (String initError : initVO.getErrorList()) {
                    errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板中组件初始化失败: " + initError));
                }
            }
            logicConfig.setComponentIds(initVO.getComponentIds());
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        SystemTemplateInitConfig initConfig = getInitConfig();
        SystemTemplateLogicConfig logicConfig = getLogicConfig();

        int mapInitialCapacity = logicConfig.getConfigs() == null ? 0 : logicConfig.getConfigs().size();
        Map<String, Object> configs = new HashMap<>(mapInitialCapacity);
        if (mapInitialCapacity > 0) {
            logicConfig.getConfigs().forEach((k, v) -> configs.put(k, DataVariableHelper.parse(v)));
        }

        ComponentContextHandler.getVariables().put(initConfig.getVariableKey(), configs);
        DataResult result = manager.getComponent().executeComponents(logicConfig.getComponentIds());
        if (!result.getSuccess()) {
            return DataResult.fail(result.getErrMsg(), result.getShowMsg());
        }

        return DataResult.success(true);
    }
}
