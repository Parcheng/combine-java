package com.parch.combine.web.service;

import com.parch.combine.core.base.FileInfo;
import com.parch.combine.core.service.ICombineWebService;
import com.parch.combine.core.vo.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FlowExecuteService {

    @Autowired
    private ICombineWebService service;

    @Autowired
    private FlowConfigService flowConfigService;

    /**
     * 执行
     *
     * @param params 入参
     * @return 结果集
     */
    public DataResult execute(Map<String, Object> params, Map<String, String> headers, FileInfo fileInfo, String domain, String function) {
        // 获取功能的组件配置集合
        List<String> componentIds = flowConfigService.list(domain, function);
        if (componentIds == null) {
            return DataResult.fail("接口未注册", "接口不存在");
        }

        // 执行配置逻辑
        return service.execute(domain, function, params, headers, fileInfo);
    }
}
