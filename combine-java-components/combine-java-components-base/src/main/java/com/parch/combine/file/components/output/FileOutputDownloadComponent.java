package com.parch.combine.file.components.output;

import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.file.base.output.FileOutputComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.file.base.output.download.FileOutputDownloadErrorEnum;
import com.parch.combine.file.base.output.download.FileOutputDownloadInitConfig;
import com.parch.combine.file.base.output.download.FileOutputDownloadLogicConfig;

@Component(order = 400, key = "output.download", name = "文件数据下载组件", logicConfigClass = FileOutputDownloadLogicConfig.class, initConfigClass = FileOutputDownloadInitConfig.class)
@ComponentResult(name = "文件数据", isDownload = true)
public class FileOutputDownloadComponent extends FileOutputComponent<FileOutputDownloadInitConfig, FileOutputDownloadLogicConfig> {

    public FileOutputDownloadComponent() {
        super(FileOutputDownloadInitConfig.class, FileOutputDownloadLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute(FileInfo fileInfo) {
        FileOutputDownloadLogicConfig logicConfig = getLogicConfig();

        // 获取文件名
        String fileName = logicConfig.name();
        if (fileName == null) {
            return ComponentDataResult.fail(FileOutputDownloadErrorEnum.FILE_NAME_IS_NULL);
        }

        // 拼接文件名和后缀
        String postfix = logicConfig.postfix();
        if (CheckEmptyUtil.isNotEmpty(postfix)) {
            fileName = fileName + "." + postfix;
        }

        // 设置要下载的文件信息
        fileInfo.setName(fileName);
        fileInfo.setType(FileNameUtil.getPostfix(fileName));
        return ComponentDataResult.download(fileInfo);
    }
}
