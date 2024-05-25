package com.parch.combine.components.system.template;

import com.parch.combine.core.common.canstant.FieldKeyCanstant;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.core.component.vo.CombineInitVO;

import java.util.*;
import java.util.stream.Collectors;

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

        List<SystemTemplateInitConfig.SystemTemplate> configs = initConfig.getTemplates().stream()
                .filter(item -> item.getKey().equals(logicConfig.getKey())).collect(Collectors.toList());
        if (CheckEmptyUtil.isEmpty(configs)) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板不存在, KEY: " + logicConfig.getKey()));
        } else if (configs.size() > 1) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "存在重复的模板 KEY: " + logicConfig.getKey()));
        } else {
            List<String> componentIds = new ArrayList<>();
            if (CheckEmptyUtil.isNotEmpty(configs.get(0).getComponents())) {
                for (Object component : configs.get(0).getComponents()) {
                    if (component == null) {
                        continue;
                    }

                    if (component instanceof Map) {
                        CombineInitVO initVO = manager.getComponent().init(Collections.singletonList((Map<String, Object>) component));
                        if (!initVO.isSuccess()) {
                            for (String initError : initVO.getErrorList()) {
                                errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "模板中组件初始化失败: " + initError));
                            }
                        }
                        componentIds.addAll(initVO.getComponentIds());
                    } else {
                        componentIds.add(component.toString());
                    }
                }

                logicConfig.setComponentIds(componentIds);
            }
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
