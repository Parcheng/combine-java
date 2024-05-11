package com.parch.combine.core.ui.settings.config;

import com.parch.combine.core.common.settings.config.CommonObjectSetting;
import com.parch.combine.core.ui.base.element.ElementConfig;
import java.util.List;

public class PageElementSetting {

    private Class<? extends ElementConfig<?>> elementClass;

    private int order;

    private String key;

    private String name;

    private List<String> desc;

//    private List<String> important;
//
//    private List<PropertySetting> initConfig;
//
//    private List<PropertySetting> logicConfig;

    private List<CommonObjectSetting> commonObjects;
//
//    private ComponentResultSetting result;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDesc() {
        return desc;
    }

    public void setDesc(List<String> desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Class<? extends ElementConfig<?>> thisElementClass() {
        return elementClass;
    }

    public void setElementClass(Class<? extends ElementConfig<?>> elementClass) {
        this.elementClass = elementClass;
    }

    public List<CommonObjectSetting> getCommonObjects() {
        return commonObjects;
    }

    public void setCommonObjects(List<CommonObjectSetting> commonObjects) {
        this.commonObjects = commonObjects;
    }
}