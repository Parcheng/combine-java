package com.parch.combine.file.base.build;

import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.FileInfo;
import com.parch.combine.core.component.base.IInitConfig;
import com.parch.combine.core.component.error.IComponentError;
import com.parch.combine.core.component.vo.ComponentDataResult;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件输入组件基类
 *
 * @param <T> 初始化配置
 * @param <R> 逻辑配置
 */
public abstract class DefaultFileBuildComponent<T extends IInitConfig, R extends DefaultFileBuildLogicConfig> extends BaseFileBuildComponent<T, R> {

    public DefaultFileBuildComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final FileBuildInfo build() {
        DefaultFileBuildLogicConfig logicConfig = getLogicConfig();
        Object data = logicConfig.source();
        if (data == null) {
            return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
        }

        // 根据数据类型，调用不通的处理方法
        FileBuildInfo buildInfo;
        if (data instanceof List) {
            List<?> list = (List<?>) data;
            if (CheckEmptyUtil.isEmpty(list)) {
                return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
            }

            Object firstItem = list.get(0);
            if (firstItem instanceof Map) {
                buildInfo = buildFromObjectList((List<Map<String, Object>>) data);
            } else if (firstItem instanceof String) {
                buildInfo = buildFromTextList((List<String>) data);
            } else {
                return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
            }
        } else if (data instanceof Map) {
            buildInfo = buildFromObject((Map<String, Object>) data);
        } else if (data instanceof byte[]) {
            buildInfo = buildFromByte((byte[]) data);
        } else if (data instanceof String) {
            buildInfo = buildFromText(data.toString());
        } else {
            return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
        }

        return buildInfo;
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromObject(Map<String, Object> data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param dataList 数据集合
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromObjectList(List<Map<String, Object>> dataList) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromByte(byte[] data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param data 数据
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromText(String data) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }

    /**
     * 根据对象构建文件（需要子类覆盖）
     *
     * @param dataList 数据集合
     * @return 文件构建信息
     */
    protected FileBuildInfo buildFromTextList(List<String> dataList) {
        return new FileBuildInfo(FileBuildErrorEnum.DATA_TYPE_ERROR);
    }
}
