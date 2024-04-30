package com.parch.combine.core.base;

import com.parch.combine.common.util.CheckEmptyUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class LogicConfig {

    /**
     * 组件ID
     */
    private String id;

    /**
     * 组件类型
     */
    private String type;

    /**
     * 管理Init的ID
     */
    private String ref;

    /**
     * 标识（多个用空格分割）
     */
    private List<ComponentFlagEnum> flags = new ArrayList<>();

    /**
     * 自定义异常显示信息
     */
    private String showMsg;

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
}
