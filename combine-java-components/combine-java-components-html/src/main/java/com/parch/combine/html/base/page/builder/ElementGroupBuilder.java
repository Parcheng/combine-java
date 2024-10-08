package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.html.common.cache.ElementConfigCache;
import com.parch.combine.html.common.cache.ElementGroupConfigCache;
import com.parch.combine.html.common.cache.TriggerConfigCache;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ElementGroupBuilder {

    private String scopeKey;
    private CombineManager manager;
    private Map<String, String[]> groupMap = new HashMap<>();
    private Map<String, ElementConfigCache.ElementCacheModel> elementMap = new HashMap<>();
    private Map<String, ElementTemplateConfig> templateMap = new HashMap<>();
    private Map<String, DataLoadConfig> dataLoadMap = new HashMap<>();
    private Map<String, Set<String>> dataLoadToElementIdMap = new HashMap<>();
    private Map<String, TriggerConfigCache.TriggerCacheModel> triggerMap = new HashMap<>();

    public ElementGroupBuilder(String[] groupIds, CombineManager manager) {
        this.manager = manager;
        initGroups(groupIds);
    }

    private void initGroups(String[] groupIds) {
        for (String groupId : groupIds) {
            ElementGroupConfigCache.GroupCacheModel model = ElementGroupConfigCache.INSTANCE.get(groupId);
            if (model == null) {
                SubComponentTool.execute(manager, groupId);
            }

            model = ElementGroupConfigCache.INSTANCE.get(groupId);
            if (model == null) {
                // TODO ERROR
                continue;
            }

            groupMap.put(groupId, model.elementIds);
            if (CheckEmptyUtil.isEmpty(model.elementIds)) {
                continue;
            }

            initElements(model.elementIds);
        }
    }

    private void initElements(String[] elementIds) {
        if (CheckEmptyUtil.isEmpty(elementIds)) {
            return;
        }

        for (String elementId : elementIds) {
            ElementConfigCache.ElementCacheModel model = ElementConfigCache.INSTANCE.get(elementId);
            if (model == null) {
                SubComponentTool.execute(manager, elementId);
            }

            model = ElementConfigCache.INSTANCE.get(elementId);
            if (model == null) {
                // TODO ERROR
                continue;
            }

            elementMap.put(elementId, model);
            List<BaseCacheModel.SubCacheModel> subs = model.subCaches;
            if (CheckEmptyUtil.isEmpty(subs)) {
                continue;
            }

            initElements(subs.stream().filter(s -> s.type == ConfigTypeEnum.ELEMENT).map(s -> s.componentId).toArray(String[]::new));
            initDataLoad(model.loadId, elementId);
            initTemplate(model.templateId, model.type, elementId);
            initTriggers(subs.stream().filter(s -> s.type == ConfigTypeEnum.TRIGGER).map(s -> s.componentId).toArray(String[]::new));
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

    private void initTriggers(String[] triggerIds) {
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
    
//    public List<String> check() {
//        List<String> result = new ArrayList<>();
//        groupMap.forEach((k, v) -> {
//            if (v == null) {
//                result.add("groupId:" + k + "不存在");
//            }
//        });
//
//        elementMap.forEach((k, v) -> {
//            if (v == null) {
//                result.add("elementId:" + k + "不存在");
//            } else {
//                List<String> errors = v.check();
//                if (CheckEmptyUtil.isNotEmpty(errors)) {
//                    for (String error : errors) {
//                        result.add("elementId:" + k + " > " + error);
//                    }
//                }
//            }
//        });
//
//        templateMap.forEach((k, v) -> {
//            if (v == null) {
//                result.add("template:" + k + "不存在");
//            }
//        });
//
//        dataLoadMap.forEach((k, v) -> {
//            if (v == null) {
//                result.add("dataloadId:" + k + "不存在");
//            }
//        });
//
//        triggerMap.forEach((k, v) -> {
//            if (v == null) {
//                result.add("triggerId:" + k + "不存在");
//            }
//        });
//
//        return result;
//    }

    public ElementGroupResult build() {
        ElementGroupResult result = new ElementGroupResult();

        result.groupMap = new HashMap<>();
        groupMap.forEach((k, v) -> result.groupMap.put(k, JsonUtil.serialize(v)));

        int elementSize = elementMap.size();
        result.elementScripts = new HashSet<>(elementSize);
        result.elementStyles = new HashSet<>(elementSize);
        result.elementMap = new HashMap<>(elementSize);
        elementMap.forEach((k, v) -> {
            result.elementScripts.add(v.thisElementJSPath());
            if (CheckEmptyUtil.isNotEmpty(v.thisElementCssPath())) {
                result.elementStyles.add(v.thisElementCssPath());
            }
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

        public Set<String> elementStyles;

        public Map<String, String> groupMap;

        public Map<String, String> dataLoadMap;

        public Map<String, String> dataLoadToElementIdMap;

        public Map<String, String> triggerMap;

        public Map<String, String> templateMap;

        public Map<String, String> elementMap;
    }
}
