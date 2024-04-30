package com.parch.combine.core.tools.compare;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.core.tools.ConfigGroupHelper;
import com.parch.combine.core.tools.variable.DataVariableHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较配置
 */
public class CompareConfig {

    /**
     * 比较来源
     */
    private String source;
    /**
     * 比较类型
     */
    private CompareTypeEnum compareType;

    /**
     * 比较目标
     */
    private String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public CompareTypeEnum getCompareType() {
        return compareType;
    }

    public void setCompareType(CompareTypeEnum compareType) {
        this.compareType = compareType;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Object parseSourceValue() {
        if (CheckEmptyUtil.isEmpty(source)) {
            return source;
        }
        return DataVariableHelper.parseValue(source, false);
    }

    public Object parseTargetValue() {
        if (CheckEmptyUtil.isEmpty(target)) {
            return target;
        }
        return DataVariableHelper.parseValue(target, false);
    }

    /**
     * 构建
     *
     * @param compareConfig 数组
     * @return 对象
     */
    public static CompareConfig build(String[] compareConfig) {
        CompareConfig item = new CompareConfig();
        item.setSource(ConfigGroupHelper.getConfigByIndex(compareConfig,0));
        item.setCompareType(CompareTypeEnum.get(ConfigGroupHelper.getConfigByIndex(compareConfig,1)));
        item.setTarget(ConfigGroupHelper.getConfigByIndex(compareConfig,2));
        return item;
    }

    /**
     * 构建
     *
     * @param compareConfigList 配置集合
     * @return 对象
     */
    public static List<CompareConfig> buildList(List<String> compareConfigList) {
        if (CheckEmptyUtil.isEmpty(compareConfigList)) {
            return new ArrayList<>();
        }

        return ConfigGroupHelper.buildItemList(compareConfigList, conditionStr -> {
            CompareConfig item = new CompareConfig();
            item.setSource(ConfigGroupHelper.getConfigByIndex(conditionStr,0));
            item.setCompareType(CompareTypeEnum.get(ConfigGroupHelper.getConfigByIndex(conditionStr,1)));
            item.setTarget(ConfigGroupHelper.getConfigByIndex(conditionStr,2));
            return item;
        });
    }

    /**
     * 属性检测
     *
     * @return 异常信息
     */
    public List<String> check() {
        List<String> errorMsg = new ArrayList<>();
        if (this.compareType == CompareTypeEnum.NONE) {
            errorMsg.add("比较类型不合法");
        }

        // 不需要target的类型
        List<CompareTypeEnum> noTargetType = CompareTypeEnum.getNoTargetTypes();
        if (this.target == null && !noTargetType.contains(this.compareType)) {
            errorMsg.add("右比较值不能为空");
        }

        return errorMsg;
    }
}
