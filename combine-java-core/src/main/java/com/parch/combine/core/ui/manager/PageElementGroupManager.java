package com.parch.combine.core.ui.manager;

import com.parch.combine.core.ui.vo.PageElementGroupVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageElementGroupManager {

    /**
     * 流程组件集合配置缓存池
     */
    private final Map<String, List<String>> ELEMENT_IDS_MAP = new HashMap<>();

    private PageElementManager pageElementLogicManager;

    public PageElementGroupManager(PageElementManager pageElementLogicManager) {
        this.pageElementLogicManager = pageElementLogicManager;
    }

    /**
     * 保存流程配置集合
     *
     * @param groups 流程配置集合
     * @return 是否成功
     */
    @SuppressWarnings("unchecked")
    protected Map<String, List<String>> init(List<PageElementGroupVO> groups) {
        Map<String, List<String>> groupElementMap = new HashMap<>();
        for (PageElementGroupVO group : groups) {
            List<String> elementIds = new ArrayList<>();
            for (Object element : group.getElements()) {
                if (element instanceof Map) {
                    elementIds.add(pageElementLogicManager.load((Map<String, Object>) element));
                } else {
                    elementIds.add(element.toString());
                }
            }

            save(group.getId(), elementIds);
            groupElementMap.put(group.getId(), elementIds);
        }

        return groupElementMap;
    }

    /**
     * 保存流程配置集合
     *
     * @param key KEY
     * @return 是否成功
     */
    private void save(String key, List<String> configs) {
        ELEMENT_IDS_MAP.put(key, configs);
    }

    /**
     * 获取流程配置集合
     *
     * @param id id
     * @return 流程配置集合
     */
    public List<String> get(String id) {
        return ELEMENT_IDS_MAP.get(id);
    }
}
