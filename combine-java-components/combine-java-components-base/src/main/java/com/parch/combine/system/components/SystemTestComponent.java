package com.parch.combine.system.components;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.system.base.test.LogLevelEnum;
import com.parch.combine.system.base.test.SystemTestErrorEnum;
import com.parch.combine.system.base.test.SystemTestInitConfig;
import com.parch.combine.system.base.test.SystemTestLogicConfig;
import com.parch.combine.system.base.test.handler.FlowHandler;
import com.parch.combine.system.base.test.handler.FlowLoadResult;
import com.parch.combine.system.base.test.handler.LogHandler;
import com.parch.combine.system.base.test.handler.TestHandler;
import com.parch.combine.system.base.test.handler.TestResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(key = "test", name = "流程测试", logicConfigClass = SystemTestLogicConfig.class, initConfigClass = SystemTestInitConfig.class)
@ComponentResult(name = "测试结果，返回数据格式：{ success: true, logs:[\"XXXXX\", \"XXXXX\"] }")
public class SystemTestComponent extends AbstractComponent<SystemTestInitConfig, SystemTestLogicConfig> {

    public SystemTestComponent() {
        super(SystemTestInitConfig.class, SystemTestLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SystemTestLogicConfig logicConfig = getLogicConfig();
        LogLevelEnum logLevel = LogLevelEnum.get(logicConfig.level());
        if (logLevel == LogLevelEnum.NONE) {
            return ComponentDataResult.fail(SystemTestErrorEnum.LEVEL_ERROR);
        }

        String name = logicConfig.name();
        Boolean isPrint = logicConfig.hasPrint();
        Boolean isOutput = logicConfig.hasOutput();

        boolean success = true;
        List<String> msgList = new ArrayList<>();
        LogHandler.start(isPrint, isOutput, CheckEmptyUtil.isEmpty(name) ? logicConfig.id() : name, msgList);

        List<FlowLoadResult> initResults = FlowHandler.load(logicConfig.configPath(), manager);
        for (FlowLoadResult item : initResults) {
            success = success && item.isSuccess();
            LogHandler.mark(isPrint, isOutput, "初始化流程[" + item.getFlowKey() + "] -> " + (item.isSuccess() ? "成功" : "失败"), msgList);
            List<FlowLoadResult.InitLogInfo> logs = item.getLogs().stream().filter(l -> l.getLevel().getLevel() >= logLevel.getLevel()).collect(Collectors.toList());
            for (FlowLoadResult.InitLogInfo log : logs) {
                LogHandler.log(isPrint, isOutput, log.getLevel(), log.getMsg(), msgList);
            }
        }

        List<TestResult> testResults = TestHandler.test(logicConfig.flowTests(), manager);
        for (TestResult item : testResults) {
            success = success && item.isSuccess();
            LogHandler.mark(isPrint, isOutput, "检测流程[" + item.getDomain() + "][" + item.getFunction() + "] -> " + (item.isSuccess() ? "成功" : "失败"), msgList);
            List<TestResult.TestLogInfo> logs = item.getLogs().stream().filter(l -> l.getLevel().getLevel() >= logLevel.getLevel()).collect(Collectors.toList());
            for (TestResult.TestLogInfo log : logs) {
                LogHandler.log(isPrint, isOutput, log.getLevel(), "检测项目[" + log.getItemId() + "] -> " + log.getMsg(), msgList);
            }
        }

        LogHandler.hr(isPrint, isOutput, msgList);

        Map<String, Object> result = new HashMap<>();
        result.put("success", success);
        result.put("logs", msgList);
        return ComponentDataResult.success(result);
    }
}
