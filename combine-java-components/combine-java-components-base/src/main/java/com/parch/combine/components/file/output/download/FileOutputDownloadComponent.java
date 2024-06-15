package com.parch.combine.components.file.output.download;

import com.parch.combine.components.file.output.FileOutputComponent;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.FileNameUtil;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.vo.DataResult;

@Component(order = 400, key = "output.download", name = "文件数据下载组件", logicConfigClass = FileOutputDownloadLogicConfig.class, initConfigClass = FileOutputDownloadInitConfig.class)
@ComponentResult(name = "文件写入磁盘是否成功", isDownload = true)
public class FileOutputDownloadComponent extends FileOutputComponent<FileOutputDownloadInitConfig, FileOutputDownloadLogicConfig> {

    public FileOutputDownloadComponent() {
        super(FileOutputDownloadInitConfig.class, FileOutputDownloadLogicConfig.class);
    }

    @Override
    public DataResult execute(FileInfo fileInfo) {
        FileOutputDownloadLogicConfig logicConfig = getLogicConfig();

        // 获取文件名
        String fileName = logicConfig.name();
        if (fileName == null) {
            return DataResult.fail(FileOutputDownloadErrorEnum.FILE_NAME_IS_NULL);
        }

        // 拼接文件名和后缀
        String postfix = logicConfig.postfix();
        if (CheckEmptyUtil.isNotEmpty(postfix)) {
            fileName = fileName + "." + postfix;
        }

        // 设置要下载的文件信息
        fileInfo.setName(fileName);
        fileInfo.setType(FileNameUtil.getPostfix(fileName));
        return DataResult.download(fileInfo);
    }
}
