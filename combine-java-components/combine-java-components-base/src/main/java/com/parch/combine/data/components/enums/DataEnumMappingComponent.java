package com.parch.combine.data.components.enums;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.data.base.enums.EnumCacheHandler;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.context.ComponentContextHandler;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.data.base.enums.mapping.DataEnumMappingErrorEnum;
import com.parch.combine.data.base.enums.mapping.DataEnumMappingInitConfig;
import com.parch.combine.data.base.enums.mapping.DataEnumMappingLogicConfig;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component(order = 1, key = "enum.mapping", name = "枚举映射组件", logicConfigClass = DataEnumMappingLogicConfig.class, initConfigClass = DataEnumMappingInitConfig.class)
@ComponentDesc("将数据中的枚举编码映射为枚举名称")
@ComponentResult(name = "映射后的数据（集）")
public class DataEnumMappingComponent extends AbstractComponent<DataEnumMappingInitConfig, DataEnumMappingLogicConfig> {

    public DataEnumMappingComponent() {
        super(DataEnumMappingInitConfig.class, DataEnumMappingLogicConfig.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ComponentDataResult execute() {
        DataEnumMappingLogicConfig logicConfig = getLogicConfig();

        Collection<Map<String, Object>> data;
        Object sourceData = logicConfig.source();
        if (sourceData instanceof Map) {
            data = new ArrayList<>();
            data.add((Map<String, Object>) sourceData);
        } else if (sourceData instanceof Collection) {
            data = (Collection<Map<String, Object>>) sourceData;
        } else {
            return ComponentDataResult.fail(DataEnumMappingErrorEnum.DATA_TYPE_ERROR);
        }

        DataEnumMappingLogicConfig.MappingItem[] items = logicConfig.items();
        try {
            Map<String, Map<String, EnumCacheHandler.EnumItem>> enumMap = new HashMap<>();
            for (DataEnumMappingLogicConfig.MappingItem item : items) {
                String enumKey = item.enumKey();
                if (enumMap.containsKey(enumKey)) {
                    continue;
                }

                Map<String, EnumCacheHandler.EnumItem> itemData = null;
                if (enumKey != null) {
                    List<EnumCacheHandler.EnumItem> enumItems = EnumCacheHandler.get(enumKey);
                    if (enumItems != null) {
                        itemData = enumItems.stream().collect(Collectors.toMap(EnumCacheHandler.EnumItem::getCode, Function.identity()));
                    }
                }

                enumMap.put(enumKey, itemData);
            }

            for (Map<String, Object> dataItem : data) {
                for (DataEnumMappingLogicConfig.MappingItem item : items) {
                    String enumKey = item.enumKey();
                    String sourceField = item.sourceField();
                    String targetField = item.targetField();

                    Object currSourceData = dataItem.get(sourceField);
                    if (currSourceData == null) {
                        continue;
                    }

                    Map<String, EnumCacheHandler.EnumItem> currEnumMap = enumMap.get(enumKey);
                    if (currEnumMap == null) {
                        continue;
                    }

                    EnumCacheHandler.EnumItem currEnumItem = currEnumMap.get(currSourceData.toString());
                    if (currEnumItem == null) {
                        continue;
                    }

                    String target = CheckEmptyUtil.isEmpty(targetField) ? sourceField : targetField;
                    dataItem.put(target, currEnumItem.getName());
                }
            }

        } catch (Exception e) {
            PrintErrorHelper.print(DataEnumMappingErrorEnum.FAIL, e);
            return ComponentDataResult.fail(DataEnumMappingErrorEnum.FAIL);
        }

        Object result = sourceData;
        String resultId = logicConfig.resultId();
        if (CheckEmptyUtil.isNotEmpty(resultId)) {
            ComponentDataResult componentResult = ComponentContextHandler.getResultData(resultId);
            if (componentResult != null) {
                result = componentResult.getData();
            }
        }

        return ComponentDataResult.success(result);
    }
}
