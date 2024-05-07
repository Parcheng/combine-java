package com.parch.combine.components.web.helper;

import com.parch.combine.core.common.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 脚本构建
 */
public class ScriptBuildHelper {

    /**
     * 构建script
     *
     * @param src 引用路径
     * @return 脚本代码
     */
    public static String build(String src) {
        Map<String, String> properties = new HashMap<>();
        properties.put("src", src);
        return HtmlBuileHelper.build("script", null, properties, false);
    }

    /**
     * 构建script
     *
     * @param body 代码
     * @return 脚本代码
     */
    public static String build(List<String> body) {
        return HtmlBuileHelper.build("script", StringUtil.join(body, "\n"), null, false);
    }
}
