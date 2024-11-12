package com.parch.combine.data.base.general.format.func;

import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.component.tools.PrintErrorHelper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

/**
 * MD5加密
 */
public class MD5Format implements ICustomFormat {

    @Override
    public List<String> check(String[] params) {
        return null;
    }

    @Override
    public Object format(Object sourceValue, String[] params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        // 数据转为JSON格式
        String data = JsonUtil.serialize(sourceValue);
        try {
            // 验证数据类型
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e){
            PrintErrorHelper.print(FormatFuncError.MD5_ERROR, e);
            throw new Exception(FormatFuncError.MD5_ERROR);
        }
    }
}
