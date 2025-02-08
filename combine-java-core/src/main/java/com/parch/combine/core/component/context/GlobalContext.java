package com.parch.combine.core.component.context;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.annotations.FieldObject;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;

import java.util.List;

/**
 * 全局配置
 */
public class GlobalContext {

    @Field(key = "loadConfigs", name = "组件加载配置", type = FieldTypeEnum.OBJECT)
    @FieldObject(LoadConfigs.class)
    private LoadConfigs loadConfigs = new LoadConfigs();

    public static class LoadConfigs {

        @Field(key = "includes", name = "包含（可使用*）", type = FieldTypeEnum.TEXT, isArray = true)
        private List<String> includes;

        @Field(key = "excludes", name = "排除（可使用*）", type = FieldTypeEnum.TEXT, isArray = true)
        private List<String> excludes;

        public List<String> getIncludes() {
            return includes;
        }

        public void setIncludes(List<String> includes) {
            this.includes = includes;
        }

        public List<String> getExcludes() {
            return excludes;
        }

        public void setExcludes(List<String> excludes) {
            this.excludes = excludes;
        }
    }

    public LoadConfigs getLoadConfigs() {
        return loadConfigs;
    }

    public void setLoadConfigs(LoadConfigs loadConfigs) {
        this.loadConfigs = loadConfigs;
    }
}
