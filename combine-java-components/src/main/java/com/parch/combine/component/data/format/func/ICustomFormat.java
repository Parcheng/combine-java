package com.parch.combine.component.data.format.func;

import java.util.List;

/**
 * 自定义格式化函数
 */
public interface ICustomFormat {

    String KEY_SEPARATOR = ",";

    /**
     * 参数检查
     *
     * @param params 参数集合
     * @return 错误信息集合
     */
    List<String> check(List<String> params);

    /**
     * 自定义格式转换
     *
     * @param sourceValue 原值
     * @param params 配置参数集合
     * @return 新值
     */
    Object format(Object sourceValue, List<String> params) throws Exception;
}
