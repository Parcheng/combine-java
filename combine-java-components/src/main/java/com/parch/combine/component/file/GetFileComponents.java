package com.parch.combine.component.file;

import com.parch.combine.component.file.build.table.FileBuildTableSettingHandler;
import com.parch.combine.component.file.build.text.FileBuildTextSettingHandler;
import com.parch.combine.component.file.input.open.FileInputOpenSettingHandler;
import com.parch.combine.component.file.input.upload.FileInputUploadSettingHandler;
import com.parch.combine.component.file.output.disk.FileOutputDiskSettingHandler;
import com.parch.combine.component.file.output.download.FileOutputDownloadSettingHandler;
import com.parch.combine.component.file.read.bytes.FileReadBytesSettingHandler;
import com.parch.combine.component.file.read.table.FileReadTableSettingHandler;
import com.parch.combine.component.file.read.text.FileReadTextSettingHandler;
import com.parch.combine.core.settings.spi.AbsGetComponents;
import com.parch.combine.core.settings.config.ComponentSetting;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取文件组件加载器
 */
public class GetFileComponents extends AbsGetComponents {

    public GetFileComponents() {
        super("file", "文件");
    }

    @Override
    public List<ComponentSetting> init() {
        List<ComponentSetting> setting = new ArrayList<>();
        setting.add(FileBuildTableSettingHandler.get());
        setting.add(FileBuildTextSettingHandler.get());
        setting.add(FileInputOpenSettingHandler.get());
        setting.add(FileInputUploadSettingHandler.get());
        setting.add(FileOutputDiskSettingHandler.get());
        setting.add(FileOutputDownloadSettingHandler.get());
        setting.add(FileReadBytesSettingHandler.get());
        setting.add(FileReadTableSettingHandler.get());
        setting.add(FileReadTextSettingHandler.get());
        return setting;
    }
}
