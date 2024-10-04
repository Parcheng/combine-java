package com.parch.combine.system.base.test.handler;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FlowKeyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.manager.ComponentManager;
import com.parch.combine.core.component.tools.compare.CompareResult;
import com.parch.combine.core.component.tools.compare.CompareTool;
import com.parch.combine.system.base.test.LogLevelEnum;
import com.parch.combine.system.base.test.SystemTestLogicConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试处理器
 */
public class TestHandler {

    /**
     * 执行测试
     */
    public static List<TestResult> test(SystemTestLogicConfig.FlowTestConfig[] flowTestConfigs, CombineManager manager) {
        List<TestResult> testResults = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(flowTestConfigs)) {
            return testResults;
        }

        // 遍历配置项并检测
        for (SystemTestLogicConfig.FlowTestConfig testConfig : flowTestConfigs) {
            String domain = testConfig.domain();
            String function = testConfig.function();
            List<TestResult.TestLogInfo> logs = new ArrayList<>();

            String key = FlowKeyUtil.getKey(domain, function);
            List<String> componentIds = manager.getFlow().list(domain, function);
            manager.execute(key, testConfig.params(), testConfig.headers(), null, componentIds, new ComponentManager.Function() {
                @Override
                public void before() {}
                @Override
                public void after() {
                    // 执行检查项
                    Map<String, Boolean> checkResultMap = new HashMap<>();
                    SystemTestLogicConfig.CheckItemConfig[] items = testConfig.items();
                    if (CheckEmptyUtil.isNotEmpty(items)) {
                        for (int i = 1; i <= items.length; i++) {
                            SystemTestLogicConfig.CheckItemConfig testItem = items[i-1];
                            // ID不存在则将序号作为ID
                            if (CheckEmptyUtil.isEmpty(testItem.getId())) {
                                testItem.setId("$" + i);
                            }

                            TestResult.TestLogInfo logInfo = new TestResult.TestLogInfo();
                            logInfo.setItemId(testItem.getId());

                            // 检查关联的检查项是否成功
                            int checkRefState = checkRefResult(testItem, checkResultMap);
                            if (checkRefState < 0) {
                                if (checkRefState == -1) {
                                    logInfo.setLevel(LogLevelEnum.FAIL);
                                    logInfo.setMsg("依赖项【" + testItem.getRefId() + "】校验未通过");
                                } else {
                                    logInfo.setLevel(LogLevelEnum.ERROR);
                                    logInfo.setMsg("引用ID不存在");
                                }
                                putResult(testItem, false, checkResultMap);
                            } else {
                                CompareResult result = CompareTool.compare(testItem);
                                if (CheckEmptyUtil.isNotEmpty(result.getErrorMsg())) {
                                    logInfo.setLevel(LogLevelEnum.ERROR);
                                    logInfo.setMsg("比较时发生异常 -> [" + result.getErrorMsg() + "]");
                                } else {
                                    logInfo.setLevel(result.isSuccess() ? LogLevelEnum.INFO : LogLevelEnum.FAIL);
                                    logInfo.setMsg("比较表达式 -> [" + testItem.getSource() + " -> " + JsonUtil.serialize(testItem.getSourceValue()) + "] " + testItem.getCompareType()
                                            + (testItem.getTarget() == null ? CheckEmptyUtil.EMPTY : (" [" + testItem.getTarget() + " -> " + JsonUtil.serialize(testItem.getTargetValue()) + "]")));
                                    putResult(testItem, result.isSuccess(), checkResultMap);
                                }
                            }

                            logs.add(logInfo);
                        }
                    }
                }
            });

            TestResult testResult = new TestResult();
            testResult.setDomain(domain);
            testResult.setFunction(function);
            testResult.setSuccess(logs.stream().noneMatch(item -> item.getLevel() != LogLevelEnum.INFO));
            testResult.setLogs(logs);
            testResults.add(testResult);
        }

        return testResults;
    }

    /**
     * 检查关联的结果
     *
     * @param config 配置
     * @param checkResultMap 检测结果集合
     */
    private static int checkRefResult(SystemTestLogicConfig.CheckItemConfig config, Map<String, Boolean> checkResultMap) {
        // 没有关联其他结果则不检测
        if (config.getRefId() == null) {
            return 0;
        }

        Boolean refResult = checkResultMap.get(config.getRefId());
        if (refResult == null) {
            return -2;
        } else if (!refResult) {
            return -1;
        }

        return 1;
    }

    /**
     * 将检测结果添加到集合中
     *
     * @param config 配置
     * @param result 结果
     * @param checkResultMap 检测结果集合
     */
    private static void putResult(SystemTestLogicConfig.CheckItemConfig config, boolean result, Map<String, Boolean> checkResultMap) {
        // 将检测结果加入到Map中
        checkResultMap.put(config.getId(), result);
    }
}
