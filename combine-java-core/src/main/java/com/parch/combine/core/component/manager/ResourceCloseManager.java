package com.parch.combine.core.component.manager;

import com.parch.combine.core.common.util.PrintUtil;
import com.parch.combine.core.component.tools.thread.ThreadPoolTool;

import java.util.ArrayList;
import java.util.List;

public class ResourceCloseManager {

    private final String scopeKey;
    private final List<IClear> clearFunctions = new ArrayList<>();

    public ResourceCloseManager(String scopeKey) {
        this.scopeKey = scopeKey;
    }

    public synchronized void register(IClear func) {
        this.clearFunctions.add(func);
    }

    public synchronized void closeAll() {
        for (IClear clear : this.clearFunctions) {
            try {
                boolean success = clear.clear();
                PrintUtil.printInfo("[" + clear.getName() + "] 清理完成, 结果为: " + success);
            } catch (Exception e) {
                PrintUtil.printError("[" + clear.getName() + "] 清理异常");
                e.printStackTrace();
            }
        }

        this.clearFunctions.clear();
        this.clearCommonResource();
    }

    private void clearCommonResource () {
        ThreadPoolTool.getHandler(scopeKey).clear();
    }

    public interface IClear {
        String getName();
        boolean clear();
    }
}
