package com.parch.combine.security.components;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.hash.SecurityHashLogicConfig;

import java.nio.charset.StandardCharsets;

@Component(key = "hash", order = 300, name = "HASH运算", logicConfigClass = SecurityHashLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "HASH值")
public class SecurityHashComponent extends AbstractComponent<IInvalidInitConfig, SecurityHashLogicConfig> {

    public SecurityHashComponent() {
        super(IInvalidInitConfig.class, SecurityHashLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityHashLogicConfig logicConfig = getLogicConfig();

        try {
            String text = JsonUtil.obj2String(logicConfig.data());
            HashFunction hashFunction = null;
            Object hashValue = null;
            switch (logicConfig.digits()) {
                case 32:
                    hashFunction = Hashing.murmur3_32();
                    hashValue = hashFunction.hashString(text, StandardCharsets.UTF_8).asInt();
                    break;
                case 64:
                    hashFunction = Hashing.farmHashFingerprint64();
                    hashValue = hashFunction.hashString(text, StandardCharsets.UTF_8).asLong();
                    break;
                case 128:
                    hashFunction = Hashing.murmur3_128();
                    hashValue = new String(hashFunction.hashString(text, StandardCharsets.UTF_8).asBytes());
                    break;
                default:
                    return ComponentDataResult.fail(SecurityErrorEnum.NONSUPPORT_HASH_DIGITS);
            }
            return ComponentDataResult.success(hashValue);
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }


    }
}
