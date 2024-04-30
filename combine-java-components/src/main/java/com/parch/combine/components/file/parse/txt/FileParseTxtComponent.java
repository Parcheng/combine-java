package com.parch.combine.components.file.parse.txt;

import com.parch.combine.core.base.FileInfo;
import com.parch.combine.components.file.FilePostfixEnum;
import com.parch.combine.components.file.parse.FileParseComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读取组件
 */
@Component(order = 500, key = "parse.txt", name = "解析文本文件数据组件", logicConfigClass = FileParseTxtLogicConfig.class, initConfigClass = FileParseTxtInitConfig.class)
@ComponentResult(name = "文本行集合")
public class FileParseTxtComponent extends FileParseComponent<FileParseTxtInitConfig, FileParseTxtLogicConfig> {

    /**
     * 构造器
     */
    public FileParseTxtComponent() {
        super(FileParseTxtInitConfig.class, FileParseTxtLogicConfig.class, FilePostfixEnum.TXT);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    protected DataResult execute(FileInfo fileInfo) {
        List<String> result = new ArrayList<>();
        ByteArrayInputStream is = new ByteArrayInputStream(fileInfo.getData());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            ComponentErrorHandler.print(FileParseTxtErrorEnum.FAIL, e);
            return DataResult.fail(FileParseTxtErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
