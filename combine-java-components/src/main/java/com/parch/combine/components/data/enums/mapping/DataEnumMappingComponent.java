package com.parch.combine.components.data.enums.mapping;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.components.data.enums.EnumCacheHandler;
import com.parch.combine.core.component.base.AbsComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.error.ComponentErrorHandler;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.variable.DataVariableHelper;
import com.parch.combine.core.component.vo.DataResult;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 运算组件
 */
@Component(order = 1, key = "enum.mapping", name = "枚举映射组件", logicConfigClass = DataEnumMappingLogicConfig.class, initConfigClass = DataEnumMappingInitConfig.class)
@ComponentDesc("将数据中的枚举编码映射为枚举名称")
@ComponentResult(name = "映射后的数据（集）")
public class DataEnumMappingComponent extends AbsComponent<DataEnumMappingInitConfig, DataEnumMappingLogicConfig> {

    /**
     * 构造器
     */
    public DataEnumMappingComponent() {
        super(DataEnumMappingInitConfig.class, DataEnumMappingLogicConfig.class);
    }

    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        DataEnumMappingLogicConfig logicConfig = getLogicConfig();
        List<DataEnumMappingLogicConfig.MappingItem> items = logicConfig.getItems();
        if (items != null) {
            for (int i = 0; i < items.size(); i++) {
                DataEnumMappingLogicConfig.MappingItem item = items.get(i);
                String baseMsg = "第<" + (i+1) + ">条-";
                if (CheckEmptyUtil.isEmpty(item.getEnumKey())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "枚举KEY为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getSourceField())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "数据来源为空"));
                }
            }
        }

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public DataResult execute() {
        DataEnumMappingLogicConfig logicConfig = getLogicConfig();

        Collection<Map<String, Object>> data;
        Object sourceData = DataVariableHelper.parseValue(logicConfig.getSource(), true);
        if (sourceData instanceof Map) {
            data = new ArrayList<>();
            data.add((Map<String, Object>) sourceData);
        } else if (sourceData instanceof Collection) {
            data = (Collection<Map<String, Object>>) sourceData;
        } else {
            return DataResult.fail(DataEnumMappingErrorEnum.DATA_TYPE_ERROR);
        }

        try {
            Map<String, Map<String, EnumCacheHandler.EnumItem>> enumMap = new HashMap<>();
            for (DataEnumMappingLogicConfig.MappingItem item : logicConfig.getItems()) {
                if (enumMap.containsKey(item.getEnumKey())) {
                    continue;
                }

                List<EnumCacheHandler.EnumItem> enumItems = EnumCacheHandler.get(item.getEnumKey());
                if (enumItems == null) {
                    continue;
                }

                enumMap.put(item.getEnumKey(), enumItems.stream().collect(Collectors.toMap(EnumCacheHandler.EnumItem::getCode, Function.identity())));
            }

            for (Map<String, Object> dataItem : data) {
                for (DataEnumMappingLogicConfig.MappingItem item : logicConfig.getItems()) {
                    Object currSourceData = dataItem.get(item.getSourceField());
                    if (currSourceData == null) {
                        continue;
                    }

                    Map<String, EnumCacheHandler.EnumItem> currEnumMap = enumMap.get(item.getEnumKey());
                    if (currEnumMap == null) {
                        continue;
                    }

                    EnumCacheHandler.EnumItem currEnumItem = currEnumMap.get(currSourceData.toString());
                    if (currEnumItem == null) {
                        continue;
                    }

                    String target = CheckEmptyUtil.isEmpty(item.getTargetField()) ? item.getSourceField() : item.getTargetField();
                    dataItem.put(target, currEnumItem.getName());
                }
            }

        } catch (Exception e) {
            ComponentErrorHandler.print(DataEnumMappingErrorEnum.FAIL, e);
            return DataResult.fail(DataEnumMappingErrorEnum.FAIL);
        }

        Object result = sourceData;
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getResultId())) {
            DataResult componentResult = ComponentContextHandler.getResultData(logicConfig.getResultId());
            if (componentResult != null) {
                result = componentResult.getData();
            }
        }

        return DataResult.success(result);
    }
}
