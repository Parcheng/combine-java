package com.parch.combine.html.common.cache.base;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.PrintLogUtil;
import com.parch.combine.html.common.canstant.ConfigFiledConstant;
import com.parch.combine.html.common.enums.ConfigTypeEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCacheModel {
    public String id;
    public String json;
    public List<SubCacheModel> subCaches = new ArrayList<>();

    public static class SubCacheModel {
        public String componentId;
        public ConfigTypeEnum type;
    }
}


