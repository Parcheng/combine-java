package com.parch.combine.web.service;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.service.ICombineWebService;
import com.parch.combine.web.dto.JsonConfigInitDTO;
import com.parch.combine.web.util.ResourceFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlowConfigService {

    @Autowired
    private ICombineWebService service;

    /**
     * 初始化
     *
     * @return 是否成功
     */
    public List<JsonConfigInitDTO> init(String path) {
        List<JsonConfigInitDTO> result = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(path)) {
            return result;
        }

        try {
            // 读取配置文件
            String jsonDataStr = ResourceFileUtil.readFile(path);
            service.registerFlow(jsonDataStr, vo -> {
                // 保存每个接口的初始化结果
                JsonConfigInitDTO initDTO = new JsonConfigInitDTO();
                initDTO.setKey(vo.getFlowKey());
                initDTO.setSuccess(vo.isSuccess());
                initDTO.setErrorList(vo.getErrorList());
                initDTO.setComponentIds(vo.getComponentIds());
                initDTO.setStaticComponentIds(vo.getStaticComponentIds());
                result.add(initDTO);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 保存流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 是否成功
     */
    public boolean save(String domain, String function, List<String> configs) {
        return service.save(domain, function, configs);
    }

    /**
     * 获取流程配置集合
     *
     * @param domain 域
     * @param function 功能
     * @return 流程配置集合
     */
    public List<String> list(String domain, String function) {
        return service.getComponentIds(domain, function);
    }
}
