package com.parch.combine.html.common.cache.base;

public interface IConfigGet<T extends BaseCacheModel> {

    T get(String key);
}
