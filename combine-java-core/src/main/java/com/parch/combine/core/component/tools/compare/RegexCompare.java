package com.parch.combine.core.component.tools.compare;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配处理器
 */
public class RegexCompare {

    /**
     * 匹配
     *
     * @param config 测试配置
     */
    public static CompareResult compare(CompareConfig config) {
        Object source = config.parseSourceValue();
        Object target = config.parseSourceValue();

        boolean success = false;
        if (source != null && target != null) {
            Pattern pattern = Pattern.compile(target.toString());
            Matcher matcher = pattern.matcher(source.toString());
            success = matcher.matches();
            if (config.getCompareType() == CompareTypeEnum.NO_REGEX) {
                success = !success;
            }
        }

        return success ? CompareResult.success() : CompareResult.fail();
    }
}
