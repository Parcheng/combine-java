package com.parch.combine.component.tool.crontab;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.common.util.DataParseUtil;
import com.parch.combine.common.util.DataTypeIsUtil;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.tools.SubComponentHelper;
import com.parch.combine.core.vo.DataResult;

import java.util.*;
import java.util.concurrent.*;

/**
 * 组件设置信息组件
 */
public class SystemCrontabComponent extends AbsComponent<SystemCrontabInitConfig, SystemCrontabLogicConfig> {

    private static final Map<String, ScheduledExecutorService> SERVICE_CACHE = new HashMap<>(1);

    /**
     * 构造器
     */
    public SystemCrontabComponent() {
        super(SystemCrontabInitConfig.class, SystemCrontabLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> errorMsg = new ArrayList<>(1);
        SystemCrontabInitConfig initConfig = getInitConfig();
        SystemCrontabLogicConfig logicConfig = getLogicConfig();

        if (initConfig.getPoolSize() == null) {
            initConfig.setPoolSize(10);
        }
        if (logicConfig.getInitialDelay() == null) {
            logicConfig.setInitialDelay(0);
        }
        if (CheckEmptyUtil.isEmpty(logicConfig.getJobFlowKey())) {
            logicConfig.setJobFlowKey(logicConfig.getId() + "-Listen");
        }

        if (logicConfig.getFixedRate() == null) {
            logicConfig.setFixedRate(logicConfig.getMaxPeriod() == null);
        }

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
            List<String> initErrorMsgs = SubComponentHelper.init(logicConfig.getComponents());
            for (String initErrorMsg : initErrorMsgs) {
                errorMsg.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, initErrorMsg));
            }
        }

        return errorMsg;
    }

    @Override
    public DataResult execute() {
        try {
            SystemCrontabLogicConfig logicConfig = getLogicConfig();
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
            ComponentErrorHandler.print(SystemCrontabErrorEnum.FAIL, e);
            DataResult.fail(SystemCrontabErrorEnum.FAIL);
        }

        return DataResult.success(true);
    }

    protected void executeSubComponents() {
        SystemCrontabLogicConfig logicConfig = getLogicConfig();
        SubComponentHelper.execute(logicConfig.getJobFlowKey(), new HashMap<>(0), logicConfig.getComponents());
    }

    protected ScheduledExecutorService getService() {
        SystemCrontabInitConfig initConfig = getInitConfig();
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
        private SystemCrontabComponent component;

        public CrontabScheduledTask(SystemCrontabComponent component) {
            this.component = component;
        }

        @Override
        public void run() {
            SystemCrontabLogicConfig logicConfig = component.getLogicConfig();

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
