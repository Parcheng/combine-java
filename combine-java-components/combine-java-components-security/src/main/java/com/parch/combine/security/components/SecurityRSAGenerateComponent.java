package com.parch.combine.security.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.settings.annotations.ComponentResultDesc;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.rsa.SecurityRSAGenerateLogicConfig;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.HashMap;
import java.util.Map;

@Component(key = "rsa.generate", order = 100, name = "RSA生成公私钥", logicConfigClass = SecurityRSAGenerateLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "生成的公司钥")
@ComponentResultDesc("消息数据格式：{ privateKey: ‘xxxx’, publicKey: ‘xxxxx’ }")
public class SecurityRSAGenerateComponent extends AbstractComponent<IInvalidInitConfig, SecurityRSAGenerateLogicConfig> {

    public SecurityRSAGenerateComponent() {
        super(IInvalidInitConfig.class, SecurityRSAGenerateLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityRSAGenerateLogicConfig logicConfig = getLogicConfig();

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(logicConfig.keySize());
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            Map<String, String> result = new HashMap<>();
            result.put("privateKey", SecurityUtil.bytesToBase64(keyPair.getPrivate().getEncoded()));
            result.put("publicKey", SecurityUtil.bytesToBase64(keyPair.getPublic().getEncoded()));
            return ComponentDataResult.success(result);
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
