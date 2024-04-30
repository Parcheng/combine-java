package com.parch.combine.components.file.parse.pdf;

import com.parch.combine.core.base.FileInfo;
import com.parch.combine.components.file.FilePostfixEnum;
import com.parch.combine.components.file.parse.FileParseComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.settings.annotations.Component;
import com.parch.combine.core.settings.annotations.ComponentResult;
import com.parch.combine.core.vo.DataResult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.util.List;

/**
 * 文件读取组件
 */
@Component(order = 500, key = "parse.pdf", name = "解析PDF文件数据组件", logicConfigClass = FileParsePdfLogicConfig.class, initConfigClass = FileParsePdfInitConfig.class)
@ComponentResult(name = "文本")
public class FileParsePdfComponent extends FileParseComponent<FileParsePdfInitConfig, FileParsePdfLogicConfig> {

    /**
     * 构造器
     */
    public FileParsePdfComponent() {
        super(FileParsePdfInitConfig.class, FileParsePdfLogicConfig.class, FilePostfixEnum.PDF);
    }

    @Override
    public List<String> init() {
        return null;
    }

    @Override
    protected DataResult execute(FileInfo fileInfo) {
        String result;
        try (PDDocument document = PDDocument.load(fileInfo.getData())){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            result = pdfTextStripper.getText(document);
        } catch (IOException e) {
            ComponentErrorHandler.print(FileParsePdfErrorEnum.FAIL, e);
            return DataResult.fail(FileParsePdfErrorEnum.FAIL);
        }

        return DataResult.success(result);
    }
}
