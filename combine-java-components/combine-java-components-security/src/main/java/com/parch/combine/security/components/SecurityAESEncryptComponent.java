package com.parch.combine.security.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.aes.SecurityAESEncryptLogicConfig;
import com.parch.combine.security.base.aes.SecurityAESInitConfig;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Component(key = "aes.encrypt", order = 300, name = "AES加密", logicConfigClass = SecurityAESEncryptLogicConfig.class, initConfigClass = SecurityAESInitConfig.class)
@ComponentResult(name = "加密后的密文")
public class SecurityAESEncryptComponent extends AbstractComponent<SecurityAESInitConfig, SecurityAESEncryptLogicConfig> {

    public SecurityAESEncryptComponent() {
        super(SecurityAESInitConfig.class, SecurityAESEncryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityAESEncryptLogicConfig logicConfig = getLogicConfig();
        SecurityAESInitConfig initConfig = getInitConfig();

        try {
            // 计算秘钥合法性
            String key = initConfig.key();
            if (key.length() != 16) {
                return ComponentDataResult.fail(SecurityErrorEnum.KEY_MUST_16);
            }
            byte[] bytes = key.getBytes(StandardCharsets.UTF_8);

            String text = JsonUtil.obj2String(logicConfig.data());
            if(logicConfig.toLowercase()){
                text = text.toLowerCase();
            }

            //设置加密算法，生成秘钥
            SecretKeySpec skeySpec = new SecretKeySpec(bytes, "AES");

            // "算法/模式/补码方式"
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            //加密
            GCMParameterSpec paramSpec = new GCMParameterSpec(bytes.length * 8, bytes);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec);

            //根据待加密内容生成字节数组
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            return ComponentDataResult.success(SecurityUtil.bytesToBase64(encrypted));
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
