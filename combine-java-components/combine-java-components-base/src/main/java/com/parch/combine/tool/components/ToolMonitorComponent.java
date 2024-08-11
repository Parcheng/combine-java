package com.parch.combine.tool.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;
import com.parch.combine.tool.base.monitor.ToolMonitorErrorEnum;
import com.parch.combine.tool.base.monitor.ToolMonitorInitConfig;
import com.parch.combine.tool.base.monitor.ToolMonitorLogicConfig;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.Map;

@Component(key = "event.monitor", name = "系统监听组件", logicConfigClass = ToolMonitorLogicConfig.class, initConfigClass = ToolMonitorInitConfig.class)
@ComponentResult(name = "监听数据")
public class ToolMonitorComponent extends AbstractComponent<ToolMonitorInitConfig, ToolMonitorLogicConfig> {

    public ToolMonitorComponent() {
        super(ToolMonitorInitConfig.class, ToolMonitorLogicConfig.class);
    }

    @Override
    public DataResult execute() {
        Map<String, Object> monitorData = new HashMap<>();

        try {
            ToolMonitorLogicConfig logicConfig = getLogicConfig();
            ToolMonitorLogicConfig.KeyConfig keyConfig = logicConfig.keyConfig();

            MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
            MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

            // 堆内存
            monitorData.put(keyConfig.initHeapMemory(), heapMemoryUsage.getInit());
            monitorData.put(keyConfig.usedHeapMemory(), heapMemoryUsage.getUsed());
            monitorData.put(keyConfig.committedHeapMemory(), heapMemoryUsage.getCommitted());
            monitorData.put(keyConfig.maxHeapMemory(), heapMemoryUsage.getMax());

            // 非堆内存
            monitorData.put(keyConfig.initNonHeapMemory(), nonHeapMemoryUsage.getInit());
            monitorData.put(keyConfig.usedNonHeapMemory(), nonHeapMemoryUsage.getUsed());
            monitorData.put(keyConfig.committedNonHeapMemory(), nonHeapMemoryUsage.getCommitted());
            monitorData.put(keyConfig.maxNonHeapMemory(), nonHeapMemoryUsage.getMax());

            // 获取 CPU 使用率
            double cpuUsage = ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
            monitorData.put(keyConfig.CPUUsage(), cpuUsage);

            // 获取线程数
            int threadCount = ManagementFactory.getThreadMXBean().getThreadCount();
            monitorData.put(keyConfig.threadCount(), threadCount);
        } catch (Exception e) {
            ComponentErrorHandler.print(ToolMonitorErrorEnum.FAIL, e);
            return DataResult.fail(ToolMonitorErrorEnum.FAIL);
        }

        return DataResult.success(monitorData);
    }
}
