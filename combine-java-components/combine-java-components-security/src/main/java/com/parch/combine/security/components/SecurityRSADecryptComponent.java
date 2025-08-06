package com.parch.combine.security.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.rsa.SecurityRSADecryptInitConfig;
import com.parch.combine.security.base.rsa.SecurityRSADecryptLogicConfig;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Component(key = "rsa.decrypt", order = 100, name = "RSA解密", logicConfigClass = SecurityRSADecryptLogicConfig.class, initConfigClass = SecurityRSADecryptInitConfig.class)
@ComponentResult(name = "解密后字符串")
public class SecurityRSADecryptComponent extends AbstractComponent<SecurityRSADecryptInitConfig, SecurityRSADecryptLogicConfig> {

    public SecurityRSADecryptComponent() {
        super(SecurityRSADecryptInitConfig.class, SecurityRSADecryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityRSADecryptInitConfig initConfig = getInitConfig();
        SecurityRSADecryptLogicConfig logicConfig = getLogicConfig();

        try {
            // 生成私钥对象
            byte[] privateKeyBytes = SecurityUtil.base64ToBytes(initConfig.privateKey());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            // 创建 Cipher 对象
            Cipher cipher = Cipher.getInstance("RSA");
            // 初始化 Cipher 为解密模式
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            // 解密数据
            byte[] data = cipher.doFinal(SecurityUtil.base64ToBytes(logicConfig.ciphertext()));
            return ComponentDataResult.success(new String(data));
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
