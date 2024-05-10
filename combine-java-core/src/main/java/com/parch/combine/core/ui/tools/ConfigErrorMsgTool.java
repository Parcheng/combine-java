package com.parch.combine.core.ui.tools;

public class ConfigErrorMsgTool {

    public static String fieldCheckError(String filedName, String msg) {
        return "【" + filedName + "】" + msg;
    }

    public static String fieldCheckError(String filedName, int index, String msg) {
        return "【" + filedName + "】【第<" + index + ">项】" + msg;
    }
}
