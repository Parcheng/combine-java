package com.parch.combine.components.tool.crontab;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;
import java.util.concurrent.*;

/**
 * 组件设置信息组件
 */
@Component(key = "crontab", name = "定时任务", logicConfigClass = ToolCrontabLogicConfig.class, initConfigClass = ToolCrontabInitConfig.class)
@ComponentResult(name = "异常信息或 true")
public class ToolCrontabComponent extends AbsComponent<ToolCrontabInitConfig, ToolCrontabLogicConfig> {

    private static final Map<String, ScheduledExecutorService> SERVICE_CACHE = new HashMap<>(1);

    /**
     * 构造器
     */
    public ToolCrontabComponent() {
        super(ToolCrontabInitConfig.class, ToolCrontabLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        ToolCrontabLogicConfig logicConfig = getLogicConfig();
        if (logicConfig.getStartTime() != null && !DataTypeIsUtil.isDate(logicConfig.getStartTime())) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "开始事件日期格式错误"));
        }
        if (logicConfig.getPeriod() == null || logicConfig.getPeriod() <= 0) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "执行周期为空或不合法"));
        }
        if (logicConfig.getMaxPeriod() != null && logicConfig.getMaxPeriod() <= logicConfig.getPeriod()) {
            errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "最大周期必须大于最小周期"));
        }

        // 初始化逻辑中使用的组件
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getComponents())) {
            List<String> initErrorMsgs = SubComponentHelper.init(manager, logicConfig.getComponents());
            for (String initErrorMsg : initErrorMsgs) {
                errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            ToolCrontabLogicConfig logicConfig = getLogicConfig();
            ScheduledExecutorService service = getService();

            Date currDate = new Date();
            Date startTime = logicConfig.getStartTime() == null ? currDate : DataParseUtil.parseDate(logicConfig.getStartTime());
            long initialDelay = currDate.getTime() - startTime.getTime() + logicConfig.getInitialDelay();
            initialDelay = initialDelay < 0 ? 0 : initialDelay;

            if (logicConfig.getMaxPeriod() == null) {
                service.scheduleAtFixedRate(this::executeSubComponents, initialDelay, logicConfig.getPeriod(), TimeUnit.MILLISECONDS);
            } else {
                service.schedule(new CrontabScheduledTask(this), initialDelay, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolCrontabErrorEnum.FAIL, e);
            return DataResult.fail(ToolCrontabErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    protected void executeSubComponents() {
        ToolCrontabLogicConfig logicConfig = getLogicConfig();
        SubComponentHelper.execute(manager, logicConfig.getJobFlowKey(), new HashMap<>(0), logicConfig.getComponents());
    }

    protected ScheduledExecutorService getService() {
        ToolCrontabInitConfig initConfig = getInitConfig();
        ScheduledExecutorService service = SERVICE_CACHE.get(initConfig.getId());
        if (service != null) {
            return service;
        }

        synchronized (SERVICE_CACHE) {
            service = SERVICE_CACHE.get(initConfig.getId());
            if (service == null) {
                ThreadFactory threadFactory = new CustomThreadFactory(initConfig.getType() + "-" + initConfig.getId());
                service = new ScheduledThreadPoolExecutor(initConfig.getPoolSize(), threadFactory);
                SERVICE_CACHE.put(initConfig.getId(), service);
            }
        }

        return service;
    }

    private static class CustomThreadFactory implements ThreadFactory {
        private String name;

        public CustomThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName(name);
            return thread;
        }
    }

    protected static class CrontabScheduledTask implements Runnable {
        private Random random = new Random();
        private ToolCrontabComponent component;

        public CrontabScheduledTask(ToolCrontabComponent component) {
            this.component = component;
        }

        @Override
        public void run() {
            ToolCrontabLogicConfig logicConfig = component.getLogicConfig();

            // 计算周期
            int period = logicConfig.getPeriod();
            if (logicConfig.getMaxPeriod() != null) {
                period = random.nextInt(logicConfig.getMaxPeriod() - logicConfig.getPeriod()) + logicConfig.getPeriod();
            }

            // 执行逻辑
            component.executeSubComponents();

            // 注册下一次执行任务
            component.getService().schedule(new CrontabScheduledTask(component), period, TimeUnit.MILLISECONDS);
        }
    }
}
