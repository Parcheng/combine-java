package com.parch.combine.components.file.build.text;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.components.file.build.FileBuildComponent;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;

import java.util.*;

/**
 * 文件读取组件
 */
@Component(order = 100, key = "build.text", name = "构建文本文件数据组件", logicConfigClass = FileBuildTextLogicConfig.class, initConfigClass = FileBuildTextInitConfig.class)
@ComponentResult(name = "文件的字节数据，可以下载/保存成 xlsx 文件（其他格式不行）")
public class FileBuildTextComponent extends FileBuildComponent<FileBuildTextInitConfig, FileBuildTextLogicConfig> {

    /**
     * 文件类型（后缀）
     */
    private static final String FILE_TYPE = "txt";

    /**
     * 构造器
     */
    public FileBuildTextComponent() {
        super(FileBuildTextInitConfig.class, FileBuildTextLogicConfig.class);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    protected FileBuildInfo buildFromObject(Map<String, Object> data) {
        return build(Collections.singletonList(data));
    }

    @Override
    protected FileBuildInfo buildFromObjectList(List<Map<String, Object>> dataList) {
        return build(dataList);
    }

    @Override
    protected FileBuildInfo buildFromByte(byte[] data) {
        return new FileBuildInfo(FILE_TYPE, data);
    }

    @Override
    protected FileBuildInfo buildFromText(String data) {
        return build(Collections.singletonList(data));
    }

    @Override
    protected FileBuildInfo buildFromTextList(List<String> dataList) {
        return build(dataList);
    }

    /**
     * 根据集合构建
     *
     * @param list 集合数据
     * @return 文件构建信息
     */
    private FileBuildInfo build(List<?> list) {
        List<String> textData = new ArrayList<>();
        for (Object item : list) {
            textData.add(item == null ? CheckEmptyUtil.EMPTY : item.toString());
        }

        StringBuilder data = new StringBuilder();
        for (String item : textData) {
            data.append(item).append("\n");
        }
        return new FileBuildInfo(FILE_TYPE, data.toString().getBytes());
    }
}
