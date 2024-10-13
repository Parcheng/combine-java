package com.parch.combine.file.components.build;

import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentDesc;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.build.BaseFileBuildComponent;
import com.parch.combine.file.base.build.pdf.FileBuildPdfErrorEnum;
import com.parch.combine.file.base.build.pdf.FileBuildPdfInitConfig;
import com.parch.combine.file.base.build.pdf.FileBuildPdfLogicConfig;
import com.parch.combine.file.base.build.pdf.PdfPageTypeEnum;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//@Component(order = 100, key = "build.pdf", name = "构建PDF文件数据组件", logicConfigClass = FileBuildPdfLogicConfig.class, initConfigClass = FileBuildPdfInitConfig.class)
//@ComponentDesc("依赖 pdfbox")
//@ComponentResult(name = "文件的字节数据，可以下载/保存成 pdf 文件（其他格式不行）")
public class FileBuildPdfComponent extends BaseFileBuildComponent<FileBuildPdfInitConfig, FileBuildPdfLogicConfig> {

    /**
     * 文件类型（后缀）
     */
    private static final String FILE_TYPE = "pdf";

    public FileBuildPdfComponent() {
        super(FileBuildPdfInitConfig.class, FileBuildPdfLogicConfig.class);
    }

    /**
     * 构建
     *
     * @return 文件构建信息
     */
    protected FileBuildInfo build() {
        FileBuildPdfLogicConfig logicConfig = getLogicConfig();

        byte[] byteData = null;
        try (PDDocument document = new PDDocument()) {

            Map<String, PDFont> fontMap = new HashMap<>();

            FileBuildPdfLogicConfig.PageConfig[] pageConfigs = logicConfig.pages();
            for (FileBuildPdfLogicConfig.PageConfig pageConfig : pageConfigs) {
                // 创建一个新的页面
                PDPage page = null;

                PdfPageTypeEnum typeEnum = PdfPageTypeEnum.get(pageConfig.type());
                switch (typeEnum) {
                    case A1:
                        page = new PDPage(PDRectangle.A1);
                        break;
                    case A2:
                        page = new PDPage(PDRectangle.A2);
                        break;
                    case A3:
                        page = new PDPage(PDRectangle.A3);
                        break;
                    case A4:
                        page = new PDPage(PDRectangle.A4);
                        break;
                    case A5:
                        page = new PDPage(PDRectangle.A5);
                        break;
                    case A6:
                        page = new PDPage(PDRectangle.A6);
                        break;
                    default:
                        continue;
                }

                FileBuildPdfLogicConfig.ContentConfig[] contentConfigs = pageConfig.contents();
                for (FileBuildPdfLogicConfig.ContentConfig contentConfig : contentConfigs) {
                    // 创建内容流
                    try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                        // 设置字体
                        String fontPath = contentConfig.fontPath();
                        PDFont font = fontMap.get(fontPath);
                        if (font == null) {
                            font = PDType0Font.load(document, new File(fontPath));
                            fontMap.put(fontPath, font);
                        }
                        contentStream.setFont(font, contentConfig.fontSize());

                        // 写入文本
                        contentStream.beginText();
                        contentStream.newLineAtOffset(contentConfig.x(), contentConfig.y());
                        contentStream.showText(StringUtil.join(contentConfig.texts(), "\n"));
                        contentStream.endText();

                        // 添加图像
//                      File imageFile = new File("path/to/your/image.png");
//                      PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(imageFile.getAbsolutePath(), document);
//                      contentStream.drawImage(pdImage, 100, 500, 200, 200); // 设置图像位置和大小
                    }
                }
            }

            // 设置文档的元数据
            FileBuildPdfLogicConfig.HeaderConfig headerConfig = logicConfig.header();
            if (headerConfig != null) {
                PDDocumentInformation info = document.getDocumentInformation();
                info.setTitle(headerConfig.title());
                info.setAuthor(headerConfig.author());
                info.setSubject(headerConfig.subject());
                info.setKeywords(headerConfig.keywords());
            }

            // 保存文档
            byteData = convertPDDocumentToByteArray(document);
        } catch (IOException e) {
            e.printStackTrace();
            new FileBuildInfo(FileBuildPdfErrorEnum.BUILD_ERROR);
        }

        return new FileBuildInfo(FILE_TYPE, byteData);
    }

    private static byte[] convertPDDocumentToByteArray(PDDocument document) throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            document.save(baos);
            return baos.toByteArray();
        }
    }
}
