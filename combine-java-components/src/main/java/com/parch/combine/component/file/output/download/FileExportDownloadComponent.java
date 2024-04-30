package com.parch.combine.component.file.output.download;

import com.parch.combine.common.util.CheckEmptyUtil;
import com.parch.combine.component.file.output.FileOutputComponent;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.component.file.FileInfo;
import com.parch.combine.core.tools.variable.DataVariableHelper;
import com.parch.combine.core.vo.DataResult;
import com.parch.combine.common.util.FileNameUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件下载组件
 */
public class FileExportDownloadComponent extends FileOutputComponent<FileOutputDownloadInitConfig, FileOutputDownloadLogicConfig> {

    /**
     * 构造器
     */
    public FileExportDownloadComponent() {
        super(FileOutputDownloadInitConfig.class, FileOutputDownloadLogicConfig.class);
    }

    @Override
    public List<String> initFileConfig() {
        List<String> result = new ArrayList<>();
        FileOutputDownloadLogicConfig logicConfig = getLogicConfig();
        if (CheckEmptyUtil.isEmpty(logicConfig.getName())) {
            result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig,  "文件名不能为空"));
        }
        return result;
    }

    @Override
    public DataResult execute(FileInfo fileInfo) {
        FileOutputDownloadLogicConfig logicConfig = getLogicConfig();

        // 获取文件名
        Object fileNameObj = DataVariableHelper.parseValue(logicConfig.getName(), false);
        if (fileNameObj == null) {
            return DataResult.fail(FileOutputDownloadErrorEnum.FILE_NAME_IS_NULL);
        }

        // 拼接文件名和后缀
        String fileName = fileNameObj.toString();
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getPostfix())) {
            fileName = fileName + "." + logicConfig.getPostfix();
        }

        // 设置要下载的文件信息
        fileInfo.setName(fileName);
        fileInfo.setType(FileNameUtil.getPostfix(fileName));
        return DataResult.download(fileInfo);
    }
}
