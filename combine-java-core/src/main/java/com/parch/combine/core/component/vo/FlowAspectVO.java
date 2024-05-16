package com.parch.combine.core.component.vo;

import com.parch.combine.core.common.util.FlowKeyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 流程切面配置
 */
public class FlowAspectVO {

    private String id;

    private Integer order;

    private Boolean failStop;

    private List<Map<String, Object>> flow;

    private List<String[]> includes;

    private List<String[]> excludes;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<Map<String, Object>> getFlow() {
        return flow;
    }

    public void setFlow(List<Map<String, Object>> flow) {
        this.flow = flow;
    }

    public List<String[]> getIncludes() {
        return includes;
    }

    public void setIncludes(List<String> includes) {
        if (includes == null) {
            return;
        }

        this.includes = new ArrayList<>();
        for (String include : includes) {
            this.includes.add(FlowKeyUtil.parseKey(include));
        }
    }

    public List<String[]> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<String> excludes) {
        if (excludes == null) {
            return;
        }

        this.excludes = new ArrayList<>();
        for (String exclude : excludes) {
            this.excludes.add(FlowKeyUtil.parseKey(exclude));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getFailStop() {
        return failStop;
    }

    public void setFailStop(Boolean failStop) {
        this.failStop = failStop;
    }
}
