package com.parch.combine.core.component.base;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.annotations.FieldDesc;
import com.parch.combine.core.common.settings.annotations.FieldEg;
import com.parch.combine.core.common.settings.annotations.FieldSelect;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import java.util.ArrayList;
import java.util.List;

public abstract class LogicConfig {

    @Field(key = "id", name = "逻辑配置ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("组件ID，默认为随机字符串")
    @FieldEg(eg = "component_logic_config_001")
    private String id;

    @Field(key = "type", name = "组件类型", type = FieldTypeEnum.TEXT, isRequired = true)
    private String type;

    @Field(key = "ref", name = "关联初始化配置的ID", type = FieldTypeEnum.TEXT)
    @FieldDesc("如果同类型组件未配置多项初始化配置，无需填写此参数，默认：使用该组件类的第一项初始化配置")
    @FieldEg(eg = "component_init_config_001", desc = "引用ID为component_init_config_001的初始化配置")
    private String ref;

    @Field(key = "flag", name = "标识", type = FieldTypeEnum.SELECT, isArray = true)
    @FieldSelect(enumClass = ComponentFlagEnum.class)
    private List<ComponentFlagEnum> flags = new ArrayList<>();

    @Field(key = "showMsg", name = "自定义错误信息", type = FieldTypeEnum.TEXT)
    @FieldDesc("当组件执行异常时返回该自定义错误信息，默认：返回系统内置的错误提示信息")
    @FieldEg(eg = "XX组件执行错误")
    private String showMsg;

    @Field(key = "printResult", name = "是否组件打印执行结果", type = FieldTypeEnum.BOOLEAN)
    @FieldDesc("不设置时，以全局配置为准")
    private Boolean printResult;

    /**
     * 用于设置默认值
     */
    public void init() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public List<ComponentFlagEnum> getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        if (CheckEmptyUtil.isEmpty(flags)) {
            return;
        }
        String[] flagArr = flags.split(CheckEmptyUtil.SPACE);
        for (String flag : flagArr) {
            this.flags.add(ComponentFlagEnum.get(flag));
        }
    }

    public void setFlags(List<ComponentFlagEnum> flags) {
        this.flags = flags;
    }

    public String getShowMsg() {
        return showMsg;
    }

    public void setShowMsg(String showMsg) {
        this.showMsg = showMsg;
    }

    public Boolean getPrintResult() {
        return printResult;
    }

    public void setPrintResult(Boolean printResult) {
        this.printResult = printResult;
    }
}
