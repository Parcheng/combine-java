package com.parch.combine.security.components;

import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.aes.SecurityAESDecryptLogicConfig;
import com.parch.combine.security.base.aes.SecurityAESInitConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component(key = "aes.decrypt", order = 300, name = "AES解密", logicConfigClass = SecurityAESDecryptLogicConfig.class, initConfigClass = SecurityAESInitConfig.class)
@ComponentResult(name = "解密后的文本")
public class SecurityAESDecryptComponent extends AbstractComponent<SecurityAESInitConfig, SecurityAESDecryptLogicConfig> {

    public SecurityAESDecryptComponent() {
        super(SecurityAESInitConfig.class, SecurityAESDecryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityAESDecryptLogicConfig logicConfig = getLogicConfig();
        SecurityAESInitConfig initConfig = getInitConfig();

        try {
            // 计算秘钥合法性
            String key = initConfig.key();
            if (key.length() != 16) {
                return ComponentDataResult.fail(SecurityErrorEnum.KEY_MUST_16);
            }
            byte[] bytes = key.getBytes(StandardCharsets.UTF_8);

            SecretKeySpec keySpec = new SecretKeySpec(bytes, "AES");

            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            GCMParameterSpec paramSpec = new GCMParameterSpec(bytes.length * 8, bytes);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

            //根据待解密内容进行解密
            byte[] decodeBase64 = SecurityUtil.base64ToBytes(logicConfig.ciphertext());
            byte[] decrypted = cipher.doFinal(decodeBase64);
            //将字节数组转成字符串
            return ComponentDataResult.success(new String(decrypted, StandardCharsets.UTF_8));
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
