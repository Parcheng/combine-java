package com.parch.combine.security.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.rsa.SecurityRSAEncryptInitConfig;
import com.parch.combine.security.base.rsa.SecurityRSAEncryptLogicConfig;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

@Component(key = "rsa.encrypt", order = 100, name = "RSA加密", logicConfigClass = SecurityRSAEncryptLogicConfig.class, initConfigClass = SecurityRSAEncryptInitConfig.class)
@ComponentResult(name = "加密后的密文")
public class SecurityRSAEncryptComponent extends AbstractComponent<SecurityRSAEncryptInitConfig, SecurityRSAEncryptLogicConfig> {

    public SecurityRSAEncryptComponent() {
        super(SecurityRSAEncryptInitConfig.class, SecurityRSAEncryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityRSAEncryptInitConfig initConfig = getInitConfig();
        SecurityRSAEncryptLogicConfig logicConfig = getLogicConfig();

        try {
            // 生成公钥对象
            byte[] publicKeyBytes = SecurityUtil.base64ToBytes(initConfig.publicKey());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            // 要加密的数据
            byte[] data = JsonUtil.obj2String(logicConfig.data()).getBytes();
            // 创建 Cipher 对象
            Cipher cipher = Cipher.getInstance("RSA");
            // 初始化 Cipher 为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            // 加密数据
            return ComponentDataResult.success(SecurityUtil.bytesToBase64(cipher.doFinal(data)));
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
