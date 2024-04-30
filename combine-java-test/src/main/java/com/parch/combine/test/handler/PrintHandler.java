package com.parch.combine.test.handler;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.common.util.PrintUtil;
import com.parch.combine.common.util.StringUtil;
import com.parch.combine.core.vo.FlowInitVO;
import com.parch.combine.test.vo.TestConfigItemVO;

/**
 * 打印处理器
 */
public class PrintHandler {

    /**
     * 结束
     */
    public static void start() {
        PrintUtil.printMark("自动化测试开始：");
        PrintUtil.printMark("-------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * 结束
     */
    public static void hr() {
        PrintUtil.printMark("-------------------------------------------------------------------------------------------------------------------------");
    }

    /**
     * 空行
     */
    public static void blank() {
        PrintUtil.printMark("");
    }

    /**
     * 初始化
     *
     * @param path 路径
     */
    public static void init(String path) {
        PrintUtil.printMark("初始化【" + path + "】");
    }

    /**
     * 流程初始化打印
     *
     * @param vo 初始化VO
     */
    public static void flowInit(FlowInitVO vo) {
        PrintUtil.printInfo(("> 初始化流程【" + vo.getFlowKey() + "】 -> " + StringUtil.join(vo.getComponentIds(), ",")));
        if (CheckEmptyUtil.isNotEmpty(vo.getStaticComponentIds())) {
            PrintUtil.printInfo("> 初始化流程【" + vo.getFlowKey() + "】中静态逻辑块 -> " + StringUtil.join(vo.getStaticComponentIds(), ","));
        }
        if (CheckEmptyUtil.isNotEmpty(vo.getErrorList())) {
            for (String errorMsg : vo.getErrorList()) {
                PrintUtil.printError("> 流程【" + vo.getFlowKey() + "】初始化异常 -> " + errorMsg);
            }
        }
    }

    /**
     * 流程控件打印
     *
     * @param domain 域
     * @param function 函数
     */
    public static void flowComponent(String domain, String function) {
        PrintUtil.printMark("流程【" + domain + "】【" + function + "】运行：");
    }

    /**
     * 比较结果
     *
     * @param config 配置
     * @param success 是否成功
     */
    public static void compareResult(TestConfigItemVO config, boolean success) {
        String title = "> 检测项目【" + config.getId() + "】 -> " + (success ? "通过" : "未通过");
        String content = " >> 比较表达式 -> [" + config.getSource() + " -> " + JsonUtil.serialize(config.getSourceValue()) + "] " + config.getCompareType()
                + (config.getTarget() == null ? CheckEmptyUtil.EMPTY : (" [" + config.getTarget() + " -> " + JsonUtil.serialize(config.getTargetValue()) + "]"));

        if (success) {
            PrintUtil.print(title);
            PrintUtil.print(content);
        } else {
            PrintUtil.printError(title);
            PrintUtil.printError(content);
        }
    }

    /**
     * 引用的检测项失败
     *
     * @param id 检测项ID
     * @param refId 引用的检测项ID
     */
    public static void compareRefFail(String id, String refId) {
        PrintUtil.printError("检测项目【" + id + "】 -> 依赖项【" + refId + "】校验未通过");
    }

    /**
     * 比较时发生异常
     *
     * @param id 检测项ID
     * @param msg 错误信息
     */
    public static void compareError(String id, String msg) {
        PrintUtil.printError("检测项目【" + id + "】 -> " + msg);
    }

    /**
     * 错误打印
     *
     * @param msg 错误信息
     */
    public static void error(String msg) {
        PrintUtil.printError("错误【" + msg + "】");
    }
}
