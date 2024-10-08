package com.parch.combine.html.common.cache.base;

import com.parch.combine.html.common.enums.ConfigTypeEnum;

import java.util.List;

public abstract class BaseCacheModel {
    public String id;
    public String json;
    public List<SubCacheModel> subCaches;

    public static class SubCacheModel {
        public String componentId;
        public ConfigTypeEnum type;
    }
}


