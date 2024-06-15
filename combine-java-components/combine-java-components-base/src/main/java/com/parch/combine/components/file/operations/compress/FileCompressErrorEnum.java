package com.parch.combine.components.file.operations.compress;

import com.parch.combine.core.component.error.IComponentError;

/**
 * 文件保存异常信息
 */
public enum FileCompressErrorEnum implements IComponentError {

    FILE_IS_NULL("文件或目录不存在", "文件或目录不存在"),
    FILE_NOT_EXIST("文件不存在", "文件不存在"),
    SOURCE_PATH_IS_NULL("源文件路径为空", "源文件路径为空"),
    TARGET_PATH_IS_NULL("目标文件路径为空", "目标文件路径为空"),
    NO_COMPRESS_FILE("未指定压缩文件", "未指定压缩文件"),
    TYPE_ERROR("不支持的压缩/解压类型", "不支持的压缩/解压类型"),
    FAIL("压缩或解压失败", "压缩或解压失败"),
    ;

    private String msg;

    private String showMsg;

    FileCompressErrorEnum(String msg, String showMsg) {
        this.msg = msg;
        this.showMsg = showMsg;

    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getShowMsg() {
        return this.showMsg;
    }
}
