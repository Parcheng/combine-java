package com.parch.combine.component.file.read;


/**
 * 文件内容读取逻辑配置类
 */
public abstract class FileDataReadLogicConfig extends FileReadLogicConfig {

    /**
     * 从第几行开始读取
     */
    private Integer startRow;

    /**
     * 从第几项开始读取
     */
    private Integer startIndex;

    /**
     * 行开头跳过数量
     */
    private Integer startSkipCount;

    /**
     * 行末尾丢弃数量
     */
    private Integer endDiscardCount;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getStartSkipCount() {
        return startSkipCount;
    }

    public void setStartSkipCount(Integer startSkipCount) {
        this.startSkipCount = startSkipCount;
    }

    public Integer getEndDiscardCount() {
        return endDiscardCount;
    }

    public void setEndDiscardCount(Integer endDiscardCount) {
        this.endDiscardCount = endDiscardCount;
    }
}
