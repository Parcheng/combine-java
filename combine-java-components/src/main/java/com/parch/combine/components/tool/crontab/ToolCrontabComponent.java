package com.parch.combine.components.tool.crontab;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.DataParseUtil;
import com.parch.combine.core.common.util.DataTypeIsUtil;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.SubComponentTool;
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
    public DataResult execute() {
        try {
            ToolCrontabLogicConfig logicConfig = getLogicConfig();
            ScheduledExecutorService service = getService();

            Date currDate = new Date();
            Date startTime = logicConfig.startTime() == null ? currDate : DataParseUtil.parseDate(logicConfig.startTime());
            long initialDelay = currDate.getTime() - startTime.getTime() + logicConfig.initialDelay();
            initialDelay = initialDelay < 0 ? 0 : initialDelay;

            if (logicConfig.period() == null) {
                service.scheduleAtFixedRate(this::executeSubComponents, initialDelay, logicConfig.period(), TimeUnit.MILLISECONDS);
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
        String jobFlowKey = logicConfig.jobFlowKey();
        SubComponentTool.execute(manager, jobFlowKey == null ? (logicConfig.id() + "-Listen") : jobFlowKey, new HashMap<>(0), logicConfig.components());
    }

    protected ScheduledExecutorService getService() {
        ToolCrontabInitConfig initConfig = getInitConfig();
        ScheduledExecutorService service = SERVICE_CACHE.get(initConfig.id());
        if (service != null) {
            return service;
        }

        synchronized (SERVICE_CACHE) {
            service = SERVICE_CACHE.get(initConfig.id());
            if (service == null) {
                ThreadFactory threadFactory = new CustomThreadFactory(initConfig.type() + "-" + initConfig.id());
                service = new ScheduledThreadPoolExecutor(initConfig.poolSize(), threadFactory);
                SERVICE_CACHE.put(initConfig.id(), service);
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
            int period = logicConfig.period();
            int maxPeriod = logicConfig.maxPeriod();
            if (logicConfig.maxPeriod() != null) {
                period = random.nextInt(maxPeriod - period) + period;
            }

            // 执行逻辑
            component.executeSubComponents();

            // 注册下一次执行任务
            component.getService().schedule(new CrontabScheduledTask(component), period, TimeUnit.MILLISECONDS);
        }
    }
}
