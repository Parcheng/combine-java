package com.parch.combine.file.components.parse;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.FilePostfixEnum;
import com.parch.combine.file.base.parse.FileParseComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.parse.pdf.FileParsePdfErrorEnum;
import com.parch.combine.file.base.parse.pdf.FileParsePdfInitConfig;
import com.parch.combine.file.base.parse.pdf.FileParsePdfLogicConfig;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;

@Component(order = 500, key = "parse.pdf", name = "解析PDF文件数据组件", logicConfigClass = FileParsePdfLogicConfig.class, initConfigClass = FileParsePdfInitConfig.class)
@ComponentDesc("依赖 pdfbox，推荐版本 2.0.24")
@ComponentResult(name = "文本")
public class FileParsePdfComponent extends FileParseComponent<FileParsePdfInitConfig, FileParsePdfLogicConfig> {

    /**
     * 构造器
     */
    public FileParsePdfComponent() {
        super(FileParsePdfInitConfig.class, FileParsePdfLogicConfig.class, FilePostfixEnum.PDF);
    }

    @Override
    protected ComponentDataResult execute(FileInfo fileInfo) {
        String result;
        try (PDDocument document = PDDocument.load(fileInfo.getData())){
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            result = pdfTextStripper.getText(document);
        } catch (IOException e) {
            PrintErrorHelper.print(FileParsePdfErrorEnum.FAIL, e);
            return ComponentDataResult.fail(FileParsePdfErrorEnum.FAIL);
        }

        return ComponentDataResult.success(result);
    }
}
