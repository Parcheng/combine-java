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
    protected boolean init(List<PageElementGroupVO> groups) {
        for (PageElementGroupVO group : groups) {
            List<String> elementIds = new ArrayList<>();
            for (Object element : group.getElements()) {
                if (element instanceof Map) {
                    elementIds.add(pageElementLogicManager.load((Map<String, Object>) element));
                } else {
                    elementIds.add(element.toString());
                }
            }

            return save(group.getId(), elementIds);
        }

        return true;
    }

    /**
     * 保存流程配置集合
     *
     * @param key KEY
     * @return 是否成功
     */
    private boolean save(String key, List<String> configs) {
        ELEMENT_IDS_MAP.put(key, configs);
        return true;
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
