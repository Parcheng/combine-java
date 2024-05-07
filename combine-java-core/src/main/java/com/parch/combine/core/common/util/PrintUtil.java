package com.parch.combine.core.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打印工具类
 */
public class PrintUtil {

    private static final Logger logger = LoggerFactory.getLogger("[combine-java]");

//    public static void init(String filePath) {
//        try {
//            System.setOut(new PrintStream(new FileOutputStream(filePath, true)));
//        } catch (Exception e) {
//            printError(e.getMessage());
//        }
//    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void print(String msg) {
        logger.info(msg);
//        System.out.println(msg);
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printError(String msg) {
        logger.error(msg);
//        System.out.print("\u001B[31m");
//        System.out.println(msg);
//        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printWarn(String msg) {
        logger.warn(msg);
//        System.out.print("\u001B[33m");
//        System.out.println(msg);
//        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printInfo(String msg) {
        logger.info(msg);
//        System.out.print("\u001B[36m");
//        System.out.println(msg);
//        System.out.print("\u001B[0m");
    }

    /**
     * 打印
     *
     * @param msg 信息
     */
    public static void printMark(String msg) {
        logger.info(msg);
//        System.out.print("\u001B[32m");
//        System.out.println(msg);
//        System.out.print("\u001B[0m");
    }
}
