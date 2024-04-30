package com.parch.combine.components.data.general.format.func;

import com.parch.combine.common.util.JsonUtil;
import com.parch.combine.core.error.ComponentErrorHandler;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

/**
 * MD5加密
 */
public class MD5Format implements ICustomFormat {

    @Override
    public List<String> check(List<String> params) {
        return new ArrayList<>();
    }

    @Override
    public Object format(Object sourceValue, List<String> params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        // 数据转为JSON格式
        String data = JsonUtil.serialize(sourceValue);

        // 验证数据类型
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5 = md.digest(data.getBytes(StandardCharsets.UTF_8));

        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : md5) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e){
            ComponentErrorHandler.print(FormatFuncError.MD5_ERROR, e);
            throw new Exception(FormatFuncError.MD5_ERROR);
        }
    }
}
