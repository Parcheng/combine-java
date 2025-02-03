package com.parch.combine.html.common.cache;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.html.base.element.core.ElementConfig;
import com.parch.combine.html.common.cache.base.BaseCacheModel;
import com.parch.combine.html.common.cache.base.CacheModelBuilder;
import com.parch.combine.html.common.cache.base.IConfigClear;
import com.parch.combine.html.common.cache.base.IConfigGet;
import com.parch.combine.html.common.tool.SystemElementPathTool;

import java.util.HashMap;
import java.util.Map;

public class ElementConfigCache implements IConfigClear, IConfigGet<ElementConfigCache.ElementCacheModel> {

    public final static ElementConfigCache INSTANCE = new ElementConfigCache();

    private final static Map<String, ElementCacheModel> CACHE = new HashMap<>();

    private ElementConfigCache() {}

    public void register(String id, String type, String jsLibName, String cssLibName, String templateLibName, ElementConfig config, CombineManager manager) {

        // 修正资源文件信息
        if (CheckEmptyUtil.isAnyEmpty(jsLibName, cssLibName, templateLibName)) {
            String defaultLibName = type.toLowerCase().replaceAll("\\.", "/");
            if (CheckEmptyUtil.isEmpty(jsLibName)) {
                jsLibName = defaultLibName;
            }
            if (CheckEmptyUtil.isEmpty(cssLibName)) {
                cssLibName = defaultLibName;
            }
            if (CheckEmptyUtil.isEmpty(templateLibName)) {
                templateLibName = defaultLibName;
            }
        }

        CacheModelBuilder builder = new CacheModelBuilder(id, type, config, manager);
        builder.addProperty("elementTemplatePath", SystemElementPathTool.buildTemplatePath(templateLibName));
        ElementCacheModel model = builder.build(new ElementCacheModel());

        model.type = type;
        model.jsLibName = jsLibName;
        model.cssLibName = cssLibName;
        model.loadId = config.dataload();
        model.templateId = config.template();
        CACHE.put(id, model);
    }

    @Override
    public ElementCacheModel get(String key) {
        return CACHE.get(key);
    }

    @Override
    public void clear(String key) {
        CACHE.remove(key);
    }

    @Override
    public void clear() {
        CACHE.clear();
    }

    public static class ElementCacheModel extends BaseCacheModel {
        public String jsLibName;
        public String cssLibName;
        public String type;
        public String loadId;
        public String templateId;
    }
}
