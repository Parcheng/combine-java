package com.parch.combine.tool.base.cache;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CacheHandler {
    private static Map<String, Map<String, CacheData>> CACHE;

    public static synchronized void set(String domain, String key, Object data, Long expires, int domainCapacity, int keyCapacity) {
        if (CACHE == null) {
            CACHE = new HashMap<>(domainCapacity);
        }

        Map<String, CacheData> domainCache = CACHE.computeIfAbsent(domain, k -> new HashMap<>(keyCapacity));

        CacheData cacheData = new CacheData();
        cacheData.setKey(key);
        cacheData.setData(data);
        cacheData.setCreateTime(new Date().getTime());
        cacheData.setExpires(expires);
        domainCache.put(key, cacheData);
    }

    public static Map<String, CacheData> get(String domain) {
        if (CACHE == null || domain == null) {
            return null;
        }

        return CACHE.get(domain);
    }

    public static CacheData get(String domain, String key, boolean renewal) {
        if (domain == null || key == null) {
            return null;
        }

        Map<String, CacheData> domainCache = get(domain);
        if (domainCache == null) {
            return null;
        }

        CacheData cacheData = domainCache.get(key);
        if (cacheData == null) {
            return null;
        }

        if (isExpired(cacheData)) {
            remove(domain, key);
            return null;
        }

        if (renewal) {
            cacheData.setCreateTime(new Date().getTime());
        }
        return cacheData;
    }

    public static List<CacheData> get(String domain, String key, CacheKeyMatchRuleEnum matchRule, boolean renewal) {
        List<CacheData> result = new ArrayList<>();
        Map<String, CacheData> domainCache = get(domain);
        if (domainCache == null || matchRule == null) {
            return result;
        }

        switch (matchRule) {
            case EXACT:
                CacheData cacheData = get(domain, key, renewal);
                if (cacheData != null) {
                    result = new ArrayList<>();
                    result.add(cacheData);
                }
                break;
            case FUZZY:
                Pattern pattern = Pattern.compile(key);
                Set<String> keys = domainCache.keySet();
                for (String currKey : keys) {
                    Matcher m = pattern.matcher(currKey);
                    if (m.find()) {
                        result.add(get(domain, currKey, renewal));
                    }
                }
            default:
                break;
        }

        return result;
    }

    public static boolean isExpired(CacheData cacheData) {
        Long expires = cacheData.getExpires();
        if (expires == null || expires <= 0) {
            return false;
        }

        long currTime = new Date().getTime();
        return (expires + cacheData.getCreateTime()) <= currTime;
    }

    public static synchronized void remove(String domain, String key) {
        Map<String, CacheData> domainCache = get(domain);
        if (domainCache == null) {
            return;
        }

        domainCache.remove(key);
    }
}
