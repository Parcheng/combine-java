package com.parch.combine.system.base.test.handler;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.vo.CombineConfigVO;
import com.parch.combine.core.component.vo.CombineInitVO;
import com.parch.combine.system.base.test.LogLevelEnum;

import java.util.ArrayList;
import java.util.List;

public class FlowHandler {

    public static List<FlowLoadResult> load(String path, CombineManager manager) {
        List<FlowLoadResult> initResults = new ArrayList<>();

        String jsonDataStr = ResourceFileUtil.read(path, FlowHandler.class.getClassLoader());
        CombineConfigVO config = JsonUtil.string2Obj(jsonDataStr, CombineConfigVO.class);
        if (config != null) {
            manager.init(config, vo -> FlowHandler.build(vo, initResults));
        }

        return initResults;
    }

    private static void build(CombineInitVO item, List<FlowLoadResult> initResults) {
        List<FlowLoadResult.InitLogInfo> logs = new ArrayList<>();

        boolean success = true;
        if (CheckEmptyUtil.isNotEmpty(item.getErrorList())) {
            success = false;
            for (String errorMsg : item.getErrorList()) {
                FlowLoadResult.InitLogInfo error = new FlowLoadResult.InitLogInfo();
                error.setLevel(LogLevelEnum.ERROR);
                error.setMsg("初始化异常 -> " + errorMsg);
                logs.add(error);
            }
        } else {
            FlowLoadResult.InitLogInfo info1 = new FlowLoadResult.InitLogInfo();
            info1.setLevel(LogLevelEnum.INFO);
            info1.setMsg("包含组件 -> " + StringUtil.join(item.getComponentIds(), ","));
            logs.add(info1);

            if (CheckEmptyUtil.isNotEmpty(item.getStaticComponentIds())) {
                FlowLoadResult.InitLogInfo info2 = new FlowLoadResult.InitLogInfo();
                info2.setLevel(LogLevelEnum.INFO);
                info2.setMsg("包含静态组件 -> " + StringUtil.join(item.getStaticComponentIds(), ","));
                logs.add(info2);
            }
        }

        FlowLoadResult result = new FlowLoadResult();
        result.setFlowKey(item.getFlowKey());
        result.setSuccess(success);
        result.setLogs(logs);
        initResults.add(result);
    }
}
