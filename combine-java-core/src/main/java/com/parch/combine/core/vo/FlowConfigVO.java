package com.parch.combine.core.vo;

import java.util.List;
import java.util.Map;

/**
 * 配置JSON实体类
 */
public class FlowConfigVO {

    private Map<String, Object> constant;

    private List<Map<String, Object>> init;

    private List<Map<String, Object>> blocks;

    private List<FlowAspectVO> before;

    private List<FlowAspectVO> after;

    private Map<String, List<Map<String, Object>>> templates;

    private Map<String, List<Map<String, Object>>> flows;

    public List<Map<String, Object>> getInit() {
        return init;
    }

    public void setInit(List<Map<String, Object>> init) {
        this.init = init;
    }

    public Map<String, List<Map<String, Object>>> getFlows() {
        return flows;
    }

    public void setFlows(Map<String, List<Map<String, Object>>> flows) {
        this.flows = flows;
    }

    public Map<String, Object> getConstant() {
        return constant;
    }

    public void setConstant(Map<String, Object> constant) {
        this.constant = constant;
    }

    public List<Map<String, Object>> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Map<String, Object>> blocks) {
        this.blocks = blocks;
    }

    public List<FlowAspectVO> getBefore() {
        return before;
    }

    public void setBefore(List<FlowAspectVO> before) {
        this.before = before;
    }

    public List<FlowAspectVO> getAfter() {
        return after;
    }

    public void setAfter(List<FlowAspectVO> after) {
        this.after = after;
    }

    public Map<String, List<Map<String, Object>>> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, List<Map<String, Object>>> templates) {
        this.templates = templates;
    }
}
