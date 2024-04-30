package com.parch.combine.common.util;

/**
 * 打印工具类
 */
public class PrintUtil {

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void print(String msg) {
        System.out.println(msg);
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printError(String msg) {
        System.out.print("\u001B[31m");
        System.out.println(msg);
        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printWarn(String msg) {
        System.out.print("\u001B[33m");
        System.out.println(msg);
        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printInfo(String msg) {
        System.out.print("\u001B[36m");
        System.out.println(msg);
        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printMark(String msg) {
        System.out.print("\u001B[32m");
        System.out.println(msg);
        System.out.print("\u001B[0m");
    }
}
