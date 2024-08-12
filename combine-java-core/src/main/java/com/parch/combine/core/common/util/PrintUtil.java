package com.parch.combine.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印工具类
 */
public class PrintUtil {

    private static final Logger logger = LoggerFactory.getLogger("[combine-java]");

    private PrintUtil() {}

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void print(String msg) {
        logger.info(msg);
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printError(String msg) {
        System.out.print("\u001B[31m");
        logger.error(msg);
        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printWarn(String msg) {
//        System.out.print("\u001B[33m");
        logger.warn(msg);
//        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printInfo(String msg) {
        // System.out.print("\u001B[36m");
        logger.info(msg);
        // System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printMark(String msg) {
//        System.out.print("\u001B[32m");
        logger.info(msg);
//        System.out.print("\u001B[0m");
    }
}
