package com.parch.combine.html.base.dataload;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.ILogicConfig;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.html.base.ConfigErrorEnum;
import com.parch.combine.html.core.canstant.ConfigFiledConstant;
import com.parch.combine.html.core.tool.ConfigParseTool;

import java.util.Map;

public abstract class AbstractDataLoadComponent<L extends ILogicConfig> extends AbstractComponent<DataLoadInitConfig, L> {

    protected DataLoadTypeEnum type;

    /**
     * 构造器
     */
    public AbstractDataLoadComponent(Class<L> logicClass, DataLoadTypeEnum type) {
        super(DataLoadInitConfig.class, logicClass);
        this.type = type;
    }

    @Override
    protected ComponentDataResult execute() {
        DataLoadConfig config = getConfig();
        if (config == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.CONFIG_IS_NULL);
        }

        Map<String, Object> configMap =  ConfigParseTool.parseInterfaceToMap(config);
        configMap.put("type", type.name());

        Object id = configMap.get(ConfigFiledConstant.ID);
        if(id == null) {
            return ComponentDataResult.fail(ConfigErrorEnum.ID_IS_NULL);
        }

        DataloadHandler.register(id.toString(), JsonUtil.serialize(configMap));
        return ComponentDataResult.success(true);
    }

    protected abstract DataLoadConfig getConfig();
}
