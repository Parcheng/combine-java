package com.parch.combine.security.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.md5.SecurityMD5EncryptLogicConfig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component(key = "md5.encrypt", order = 200, name = "MD5加密", logicConfigClass = SecurityMD5EncryptLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "加密后的密文")
public class SecurityMD5EncryptComponent extends AbstractComponent<IInvalidInitConfig, SecurityMD5EncryptLogicConfig> {

    public SecurityMD5EncryptComponent() {
        super(IInvalidInitConfig.class, SecurityMD5EncryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecurityMD5EncryptLogicConfig logicConfig = getLogicConfig();

        try {
            String text = JsonUtil.obj2String(logicConfig.data());
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return ComponentDataResult.success(hexString.toString());
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
