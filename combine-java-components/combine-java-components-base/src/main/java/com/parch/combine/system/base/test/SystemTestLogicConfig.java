package com.parch.combine.system.base.test;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldObject;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.tools.compare.CompareConfig;
import java.util.Map;

public interface SystemTestLogicConfig extends ILogicConfig {

    @Field(key = "name", name = "名称", type = FieldTypeEnum.TEXT)
    String name();

    @Field(key = "configPath", name = "流程配置JSON文件地址", type = FieldTypeEnum.TEXT)
    String configPath();

    @Field(key = "level", name = "日志级别", type = FieldTypeEnum.SELECT, defaultValue = "INFO")
    @FieldSelect(enumClass = LogLevelEnum.class)
    String level();

    @Field(key = "hasPrint", name = "控制台是否打印测试日志", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean hasPrint();

    @Field(key = "hasOutput", name = "是否将日志输出到结果中", type = FieldTypeEnum.BOOLEAN, defaultValue = "true")
    Boolean hasOutput();

    @Field(key = "flowTests", name = "流程测试配置集合", type = FieldTypeEnum.CONFIG, isArray = true)
    @FieldObject(FlowTestConfig.class)
    FlowTestConfig[] flowTests();

    interface FlowTestConfig {

        @Field(key = "domain", name = "领域", type = FieldTypeEnum.TEXT, isRequired = true)
        String domain();

        @Field(key = "function", name = "函数", type = FieldTypeEnum.TEXT)
        String function();

        @Field(key = "params", name = "参数", type = FieldTypeEnum.MAP)
        Map<String, Object> params();

        @Field(key = "headers", name = "请求头", type = FieldTypeEnum.MAP)
        Map<String, String> headers();

        @Field(key = "items", name = "检测项集合", type = FieldTypeEnum.OBJECT, isArray = true)
        @FieldObject(CheckItemConfig.class)
        CheckItemConfig[] items();
    }

    class CheckItemConfig extends CompareConfig {

        @Field(key = "id", name = "比较项目ID", type = FieldTypeEnum.TEXT)
        private String id;

        @Field(key = "refId", name = "关联比较项目ID", type = FieldTypeEnum.TEXT)
        private String refId;

        private Object sourceValue;

        private Object targetValue;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRefId() {
            return refId;
        }

        public void setRefId(String refId) {
            this.refId = refId;
        }

        public Object getSourceValue() {
            if (sourceValue == null) {
                sourceValue = parseSourceValue();
            }
            return sourceValue;
        }

        public Object getTargetValue() {
            if (targetValue == null) {
                targetValue = parseTargetValue();
            }
            return targetValue;
        }
    }
}
