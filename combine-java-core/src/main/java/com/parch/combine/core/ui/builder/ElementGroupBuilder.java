package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.ui.base.dataload.DataLoadConfig;
import com.parch.combine.core.ui.base.element.ElementConfig;
import com.parch.combine.core.ui.base.element.ElementTemplateConfig;
import com.parch.combine.core.ui.base.trigger.TriggerConfig;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.handler.CombineManagerHandler;
import com.parch.combine.core.ui.manager.CombineManager;
import com.parch.combine.core.ui.tools.UrlPathHelper;

import java.util.*;

public class ElementGroupBuilder {

    private String scopeKey;
    private CombineManager manager;
    private Map<String, List<String>> groupMap = new HashMap<>();
    private Map<String, ElementConfig<?>> elementMap = new HashMap<>();
    private Map<String, ElementTemplateConfig> templateMap = new HashMap<>();
    private Map<String, DataLoadConfig> dataLoadMap = new HashMap<>();
    private Map<String, Set<String>> dataLoadToElementIdMap = new HashMap<>();
    private Map<String, TriggerConfig> triggerMap = new HashMap<>();

    public ElementGroupBuilder(List<String> groupIds) {
        scopeKey = ConfigLoadingContextHandler.getContext().getScopeKey();
        manager = CombineManagerHandler.get(scopeKey);
        initGroups(groupIds);
    }

    private void initGroups(List<String> groupIds) {
        for (String groupId : groupIds) {
            List<String> elementIds = manager.getPageGroup().get(groupId);
            groupMap.put(groupId, elementIds);
            if (CheckEmptyUtil.isEmpty(elementIds)) {
                continue;
            }

            initElements(elementIds);
        }
    }

    private void initElements(List<String> elementIds) {
        if (CheckEmptyUtil.isEmpty(elementIds)) {
            return;
        }

        for (String elementId : elementIds) {
            ElementConfig<?> element = manager.getPageElement().get(elementId);
            elementMap.put(elementId, element);
            if (element == null) {
                continue;
            }

            initTemplate(element.getTemplateId(), element.getType(), element.thisTemplateConfigClass());
            initDataLoad(element.getDataLoadId(), element.getId());
            initElements(manager.getPageElement().getSubElements(element.getId()));
            initTriggers(manager.getPageElement().getSubTriggers(element.getId()));
        }
    }

    private void initTemplate(String templateId, String type, Class<? extends ElementTemplateConfig> templateClass) {
        if (CheckEmptyUtil.isEmpty(templateId)) {
            return;
        }

        templateMap.put(templateId, manager.getPageElementTemplate().get(templateId, type, templateClass));
    }

    private void initDataLoad(String dataLoadId, String elementId) {
        if (CheckEmptyUtil.isEmpty(dataLoadId)) {
            return;
        }

        dataLoadMap.put(dataLoadId, manager.getDataLoad().get(dataLoadId));

        Set<String> elementIds = dataLoadToElementIdMap.computeIfAbsent(dataLoadId, k -> new HashSet<>());
        elementIds.add(elementId);
    }

    private void initTriggers(List<String> triggerIds) {
        if (CheckEmptyUtil.isEmpty(triggerIds)) {
            return;
        }

        for (String triggerId : triggerIds) {
            TriggerConfig trigger = manager.getTrigger().get(triggerId);
            triggerMap.put(triggerId, trigger);
            if (trigger == null) {
                continue;
            }

            initElements(manager.getTrigger().getSubElements(trigger.getId()));
            initTriggers(manager.getTrigger().getSubTriggers(trigger.getId()));
        }
    }



    public List<String> check() {
        List<String> result = new ArrayList<>();
        groupMap.forEach((k, v) -> {
            if (v == null) {
                result.add("groupId:" + k + "不存在");
            }
        });

        elementMap.forEach((k, v) -> {
            if (v == null) {
                result.add("elementId:" + k + "不存在");
            }
        });

        templateMap.forEach((k, v) -> {
            if (v == null) {
                result.add("template:" + k + "不存在");
            }
        });

        dataLoadMap.forEach((k, v) -> {
            if (v == null) {
                result.add("dataloadId:" + k + "不存在");
            }
        });

        triggerMap.forEach((k, v) -> {
            if (v == null) {
                result.add("triggerId:" + k + "不存在");
            }
        });

        return result;
    }

    public ElementGroupResult build() {
        ElementGroupResult result = new ElementGroupResult();

        result.groupMap = new HashMap<>();
        groupMap.forEach((k, v) -> result.groupMap.put(k, JsonUtil.serialize(v)));

        result.elementScripts = new HashSet<>(elementMap.size());
        result.elementMap = new HashMap<>(elementMap.size());
        elementMap.forEach((k, v) -> {
            result.elementScripts.add(v.thisElementJSPath());
            v.setElementTemplatePath(UrlPathHelper.replaceUrlFlag(v.getElementTemplatePath()));
            result.elementMap.put(k, JsonUtil.serialize(v));
        });

        result.templateMap = new HashMap<>(templateMap.size());
        templateMap.forEach((k, v) -> result.templateMap.put(k, JsonUtil.serialize(v)));

        result.dataLoadMap = new HashMap<>(dataLoadMap.size());
        dataLoadMap.forEach((k, v) -> result.dataLoadMap.put(k, JsonUtil.serialize(v)));

        result.dataLoadToElementIdMap = new HashMap<>(dataLoadToElementIdMap.size());
        dataLoadToElementIdMap.forEach((k, v) -> result.dataLoadToElementIdMap.put(k, JsonUtil.serialize(v)));

        result.triggerMap = new HashMap<>(triggerMap.size());
        triggerMap.forEach((k, v) -> result.triggerMap.put(k, JsonUtil.serialize(v)));

        return result;
    }

    public static class ElementGroupResult {

        public Set<String> elementScripts;

        public Map<String, String> groupMap;

        public Map<String, String> dataLoadMap;

        public Map<String, String> dataLoadToElementIdMap;

        public Map<String, String> triggerMap;

        public Map<String, String> templateMap;

        public Map<String, String> elementMap;
    }
}
