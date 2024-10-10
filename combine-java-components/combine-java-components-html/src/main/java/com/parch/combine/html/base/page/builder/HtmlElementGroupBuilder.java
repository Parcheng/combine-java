package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.core.component.tools.SubComponentTool;
import com.parch.combine.html.common.cache.DataloadConfigCache;
import com.parch.combine.html.common.cache.ElementConfigCache;
import com.parch.combine.html.common.cache.ElementGroupConfigCache;
import com.parch.combine.html.common.cache.ElementTemplateConfigCache;
import com.parch.combine.html.common.cache.TriggerConfigCache;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.IConfigGet;
import com.parch.combine.html.common.cache.base.IRegisterComponent;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HtmlElementGroupBuilder {

    private final String[] groupIds;
    private final CombineManager manager;

    private boolean success = true;

    private final Map<String, String> groupMap = new HashMap<>();
    private final Map<String, ElementConfigCache.ElementCacheModel> elementMap = new HashMap<>();
    private final Map<String, ElementTemplateConfigCache.TemplateCacheModel> templateMap = new HashMap<>();
    private final Map<String, DataloadConfigCache.DataloadCacheModel> dataLoadMap = new HashMap<>();
    private final Map<String, Set<String>> dataLoadToElementIdMap = new HashMap<>();
    private final Map<String, TriggerConfigCache.TriggerCacheModel> triggerMap = new HashMap<>();

    public HtmlElementGroupBuilder(String[] groupIds, CombineManager manager) {
        this.manager = manager;
        this.groupIds = groupIds;
    }

    public boolean build() {
        initGroups(this.groupIds);
        return success;
    }

    private void initGroups(String[] groupIds) {
        for (String groupId : groupIds) {
            ElementGroupConfigCache.GroupCacheModel model = ElementGroupConfigCache.INSTANCE.get(groupId);
            if (model == null) {
                SubComponentTool.execute(manager, groupId);
            }

            model = ElementGroupConfigCache.INSTANCE.get(groupId);
            if (model == null) {
                success = false;
                PrintHelper.printComponentError("【HTML-ELEMENT_GROUP】【" + groupId + "】【加载页面元素组数据为空】");
                continue;
            }

            groupMap.put(groupId, JsonUtil.serialize(model.elementIds));
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
            ElementConfigCache.ElementCacheModel model = this.getCacheModel(ElementConfigCache.INSTANCE, elementId, ConfigTypeEnum.ELEMENT);
            if (model == null) {
                continue;
            }

            elementMap.put(elementId, model);
            List<BaseCacheModel.SubCacheModel> subs = model.subCaches;
            if (CheckEmptyUtil.isEmpty(subs)) {
                continue;
            }

            initDataLoad(model.loadId, elementId);
            initTemplate(model.templateId);
            initElements(subs.stream().filter(s -> s.type == ConfigTypeEnum.ELEMENT).map(s -> s.componentId).toArray(String[]::new));
            initTriggers(subs.stream().filter(s -> s.type == ConfigTypeEnum.TRIGGER).map(s -> s.componentId).toArray(String[]::new));
        }
    }

    private void initTemplate(String templateId) {
        if (CheckEmptyUtil.isEmpty(templateId)) {
            return;
        }

        ElementTemplateConfigCache.TemplateCacheModel model = this.getCacheModel(ElementTemplateConfigCache.INSTANCE, templateId, ConfigTypeEnum.ELEMENT_TEMPLATE);
        if (model == null) {
            return;
        }

        templateMap.put(templateId, model);
    }

    private void initDataLoad(String dataLoadId, String elementId) {
        if (CheckEmptyUtil.isEmpty(dataLoadId)) {
            return;
        }

        DataloadConfigCache.DataloadCacheModel model = this.getCacheModel(DataloadConfigCache.INSTANCE, dataLoadId, ConfigTypeEnum.DATA_LOAD);
        if (model == null) {
            return;
        }

        dataLoadMap.put(dataLoadId, model);
        Set<String> elementIds = dataLoadToElementIdMap.computeIfAbsent(dataLoadId, k -> new HashSet<>());
        elementIds.add(elementId);
    }

    private void initTriggers(String[] triggerIds) {
        if (CheckEmptyUtil.isEmpty(triggerIds)) {
            return;
        }

        for (String triggerId : triggerIds) {
            TriggerConfigCache.TriggerCacheModel model = this.getCacheModel(TriggerConfigCache.INSTANCE, triggerId, ConfigTypeEnum.TRIGGER);
            if (model == null) {
                continue;
            }

            triggerMap.put(triggerId, model);
            if (CheckEmptyUtil.isEmpty(model.subCaches)) {
                continue;
            }

            initElements(model.subCaches.stream().filter(s -> s.type == ConfigTypeEnum.ELEMENT).map(s -> s.componentId).toArray(String[]::new));
            initTriggers(model.subCaches.stream().filter(s -> s.type == ConfigTypeEnum.TRIGGER).map(s -> s.componentId).toArray(String[]::new));
        }
    }

    private <T extends BaseCacheModel> T getCacheModel(IConfigGet<T> configGet, String key, ConfigTypeEnum type) {
        T model = configGet.get(key);
        if (model == null) {
            AbstractComponent<?,?> component = manager.getComponent().getComponent(key);
            if (!(component instanceof IRegisterComponent)) {
                success = false;
                PrintHelper.printComponentError("【HTML-" + type.name() + "】【" + key + "】【不是注册" + type.getName() + "数据的组件】");
                return null;
            }

            ConfigTypeEnum currType = ((IRegisterComponent) component).getConfigType();
            if (currType != type) {
                success = false;
                PrintHelper.printComponentError("【HTML-" + type.name() + "】【" + key + "】【不是注册" + type.getName() + "数据的组件，而是注册" + currType.getName() + "数据的组件】");
                return null;
            }

            AbstractComponent<?, ?> currComponent = ComponentContextHandler.getCurrComponent();
            manager.getComponent().executeComponent(component);
            ComponentContextHandler.setCurrComponent(currComponent);

            model = configGet.get(key);
            if (model == null) {
                success = false;
                PrintHelper.printComponentError("【HTML-" + type.name() + "】【" + key + "】【加载" + type.getName() + "数据为空】");
                return null;
            }
        }

        return model;
    }

    public Map<String, String> getGroupMap() {
        return groupMap;
    }

    public Map<String, ElementConfigCache.ElementCacheModel> getElementMap() {
        return elementMap;
    }

    public Map<String, ElementTemplateConfigCache.TemplateCacheModel> getTemplateMap() {
        return templateMap;
    }

    public Map<String, DataloadConfigCache.DataloadCacheModel> getDataLoadMap() {
        return dataLoadMap;
    }

    public Map<String, Set<String>> getDataLoadToElementIdMap() {
        return dataLoadToElementIdMap;
    }

    public Map<String, TriggerConfigCache.TriggerCacheModel> getTriggerMap() {
        return triggerMap;
    }

    //    public ElementGroupResult build() {
//        ElementGroupResult result = new ElementGroupResult();
//
//        result.groupMap = new HashMap<>();
//        groupMap.forEach((k, v) -> result.groupMap.put(k, JsonUtil.serialize(v)));
//
//        int elementSize = elementMap.size();
//        result.elementScripts = new HashSet<>(elementSize);
//        result.elementStyles = new HashSet<>(elementSize);
//        result.elementMap = new HashMap<>(elementSize);
//        elementMap.forEach((k, v) -> {
//            result.elementScripts.add(v.thisElementJSPath());
//            if (CheckEmptyUtil.isNotEmpty(v.thisElementCssPath())) {
//                result.elementStyles.add(v.thisElementCssPath());
//            }
//            v.setElementTemplatePath(UrlPathHelper.replaceUrlFlag(v.getElementTemplatePath()));
//            result.elementMap.put(k, JsonUtil.serialize(v));
//        });
//
//        result.templateMap = new HashMap<>(templateMap.size());
//        templateMap.forEach((k, v) -> result.templateMap.put(k, JsonUtil.serialize(v)));
//
//        result.dataLoadMap = new HashMap<>(dataLoadMap.size());
//        dataLoadMap.forEach((k, v) -> result.dataLoadMap.put(k, JsonUtil.serialize(v)));
//
//        result.dataLoadToElementIdMap = new HashMap<>(dataLoadToElementIdMap.size());
//        dataLoadToElementIdMap.forEach((k, v) -> result.dataLoadToElementIdMap.put(k, JsonUtil.serialize(v)));
//
//        result.triggerMap = new HashMap<>(triggerMap.size());
//        triggerMap.forEach((k, v) -> result.triggerMap.put(k, JsonUtil.serialize(v)));
//
//        return result;
//    }
//
//    public static class ElementGroupResult {
//
//        public Set<String> elementScripts;
//
//        public Set<String> elementStyles;
//
//        public Map<String, String> groupMap;
//
//        public Map<String, String> dataLoadMap;
//
//        public Map<String, String> dataLoadToElementIdMap;
//
//        public Map<String, String> triggerMap;
//
//        public Map<String, String> templateMap;
//
//        public Map<String, String> elementMap;
//    }
}
