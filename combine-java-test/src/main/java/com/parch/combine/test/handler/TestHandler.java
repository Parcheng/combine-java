package com.parch.combine.test.handler;

import com.parch.combine.CombineWebStarter;
import com.parch.combine.common.util.*;
import com.parch.combine.core.tools.compare.CompareHelper;
import com.parch.combine.core.tools.compare.CompareResult;
import com.parch.combine.core.handler.ComponentHandler;
import com.parch.combine.test.vo.TestConfigItemVO;
import com.parch.combine.test.vo.TestConfigVO;

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
    public static void test(String path) {
        // 获取配置
        String testConfigJson = SystemFileUtil.read(path);
        List<TestConfigVO> testConfigs = JsonUtil.parseArray(testConfigJson, TestConfigVO.class);
        if (CheckEmptyUtil.isEmpty(testConfigs)) {
            return;
        }

        // 遍历配置项并检测
        int index = 0;
        for (TestConfigVO testConfig : testConfigs) {
            PrintHandler.flowComponent(testConfig.getDomain(), testConfig.getFunction());

            // 获取组件集合
            List<String> componentIds = CombineWebStarter.getService().getComponentIds(testConfig.getDomain(), testConfig.getFunction());

            // 执行组件
            String key = FlowKeyUtil.getKey(testConfig.getDomain(), testConfig.getFunction());
            ComponentHandler.execute(key, testConfig.getParams(), new HashMap<>(0), componentIds, new ComponentHandler.Function() {
                @Override
                public void before() {}
                @Override
                public void after() {
                    // 执行检查项
                    Map<String, Boolean> checkResultMap = new HashMap<>();
                    if (CheckEmptyUtil.isNotEmpty(testConfig.getItems())) {
                        for (int i = 1; i <= testConfig.getItems().size(); i++) {
                            TestConfigItemVO testItem = testConfig.getItems().get(i-1);
                            // ID不存在则将序号作为ID
                            if (CheckEmptyUtil.isEmpty(testItem.getId())) {
                                testItem.setId("$" + i);
                            }

                            // 检查关联的检查项是否成功
                            if (!checkRefResult(testItem, checkResultMap)) {
                                putResult(testItem, false, checkResultMap);
                            } else {
                                CompareResult result = CompareHelper.compare(testItem);
                                if (CheckEmptyUtil.isNotEmpty(result.getErrorMsg())) {
                                    PrintHandler.compareError(testItem.getId(), result.getErrorMsg());
                                } else {
                                    putResult(testItem, result.isSuccess(), checkResultMap);
                                    PrintHandler.compareResult(testItem, result.isSuccess());
                                }
                            }
                        }
                    }
                }
            });

            // 打印空行控制
            if (index != testConfigs.size() - 1) {
                PrintHandler.blank();
                index++;
            }
        }

        PrintHandler.hr();
    }

    /**
     * 检查关联的结果
     *
     * @param config 配置
     * @param checkResultMap 检测结果集合
     */
    private static boolean checkRefResult(TestConfigItemVO config, Map<String, Boolean> checkResultMap) {
        // 没有关联其他结果则不检测
        if (config.getRefId() == null) {
            return true;
        }

        Boolean refResult = checkResultMap.get(config.getRefId());
        if (refResult == null) {
            PrintHandler.compareError(config.getId(), "引用ID不存在");
            return false;
        } else if (!refResult) {
            PrintHandler.compareRefFail(config.getId(), config.getRefId());
            return false;
        }

        return true;
    }

    /**
     * 将检测结果添加到集合中
     *
     * @param config 配置
     * @param result 结果
     * @param checkResultMap 检测结果集合
     */
    private static void putResult(TestConfigItemVO config, boolean result, Map<String, Boolean> checkResultMap) {
        // 将检测结果加入到Map中
        checkResultMap.put(config.getId(), result);
    }
}
