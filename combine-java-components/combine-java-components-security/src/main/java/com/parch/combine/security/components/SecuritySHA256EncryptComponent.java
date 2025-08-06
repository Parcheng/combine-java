package com.parch.combine.security.components;

import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.base.AbstractComponent;
import com.parch.combine.core.component.base.IInvalidInitConfig;
import com.parch.combine.core.component.settings.annotations.Component;
import com.parch.combine.core.component.settings.annotations.ComponentResult;
import com.parch.combine.core.component.tools.PrintErrorHelper;
import com.parch.combine.core.component.vo.ComponentDataResult;
import com.parch.combine.security.base.SecurityErrorEnum;
import com.parch.combine.security.base.SecurityUtil;
import com.parch.combine.security.base.sha256.SecuritySHA256EncryptLogicConfig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component(key = "sha256.encrypt", order = 300, name = "SHA256加密", logicConfigClass = SecuritySHA256EncryptLogicConfig.class, initConfigClass = IInvalidInitConfig.class)
@ComponentResult(name = "加密后的密文")
public class SecuritySHA256EncryptComponent extends AbstractComponent<IInvalidInitConfig, SecuritySHA256EncryptLogicConfig> {

    public SecuritySHA256EncryptComponent() {
        super(IInvalidInitConfig.class, SecuritySHA256EncryptLogicConfig.class);
    }

    @Override
    public ComponentDataResult execute() {
        SecuritySHA256EncryptLogicConfig logicConfig = getLogicConfig();

        try {
            String text = JsonUtil.obj2String(logicConfig.data());
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            return ComponentDataResult.success(SecurityUtil.bytesToHex(encodedHash));
        } catch (Exception e) {
            PrintErrorHelper.print(SecurityErrorEnum.FAIL, e);
            return ComponentDataResult.fail(SecurityErrorEnum.FAIL);
        }
    }
}
