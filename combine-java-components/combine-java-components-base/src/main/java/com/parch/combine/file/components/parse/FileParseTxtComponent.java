package com.parch.combine.file.components.parse;

import com.parch.combine.file.base.FilePostfixEnum;
import com.parch.combine.file.base.parse.FileParseComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.parse.txt.FileParseTxtErrorEnum;
import com.parch.combine.file.base.parse.txt.FileParseTxtInitConfig;
import com.parch.combine.file.base.parse.txt.FileParseTxtLogicConfig;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
    protected ComponentDataResult execute(FileInfo fileInfo) {
        List<String> result = new ArrayList<>();
        ByteArrayInputStream is = new ByteArrayInputStream(fileInfo.getData());
        try (BufferedReader in = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = in.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            PrintErrorHelper.print(FileParseTxtErrorEnum.FAIL, e);
            return ComponentDataResult.fail(FileParseTxtErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
