package com.parch.combine.data.base.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnumCacheHandler {

    public final static Map<String, List<EnumItem>> CACHE = new HashMap<>();

    public static synchronized void register(String key, List<EnumItem> items) {
        CACHE.put(key, items);
    }

    public static synchronized List<EnumItem> get(String key) {
        return CACHE.get(key);
    }

    public static class EnumItem {

        private String code;

        private String name;

        private String desc;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
