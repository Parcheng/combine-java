package com.parch.combine.html.common.cache;

import com.parch.combine.html.common.cache.base.IConfigClear;
import java.util.HashMap;
import java.util.Map;

public class PageHtmlCache implements IConfigClear {

    public final static PageHtmlCache INSTANCE = new PageHtmlCache();

    private final static Map<String, PageCacheModel> CACHE = new HashMap<>();

    private PageHtmlCache() {}

    public void register(String id, String html) {
        PageCacheModel model = new PageCacheModel();
        model.id = id;
        model.html = html;
        CACHE.put(id, model);
    }

    public PageCacheModel get(String key) {
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

    public static class PageCacheModel {
        public String id;
        public String html;
    }
}
