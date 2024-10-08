package com.parch.combine.html.common.tool;

public class ConfigErrorMsgTool {

    public static String fieldCheckError(String filedName, String msg) {
        return "【" + filedName + "】" + msg;
    }

    public static String fieldCheckError(String filedName, int index, String msg) {
        return "【" + filedName + "】【第<" + index + ">项】" + msg;
    }

    public static String error(String ... msg) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < msg.length; i++) {
            if (i == msg.length - 1) {
                sb.append(msg[i]);
            } else {
                sb.append("【").append(msg[i]).append("】");
            }

        }

        return sb.toString();
    }
}
