package com.parch.combine.core.component.tools;


import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.Stack;

/**
 * 表达式计算帮助类
 */
public class ExpressionCalcTool {

    /**
     * 计算
     *
     * @param expression 表达式
     * @return
     */
    public static Double calc(String expression) throws Exception {
        if (CheckEmptyUtil.isEmpty(expression)) {
            return null;
        }

        // 过滤掉空格
        expression = expression.replaceAll("\\s*", "");
        return calc(expression.toCharArray());
    }

    /**
     * 计算
     *
     * @param chars 字符集合
     * @return 结果
     * @throws Exception 异常
     */
    private static Double calc(char[] chars) throws Exception {
        // 计算使用的栈定义
        Stack<Double> numStack = new Stack<>();
        Stack<Character> flagStack = new Stack<>();

        for (int i = 0; i < chars.length;) {
            // 当前符号是数字
            String numStr = getNumber(chars, i);
            if (CheckEmptyUtil.isNotEmpty(numStr)) {
                numStack.push(Double.parseDouble(numStr));
                i += numStr.length();
                continue;
            }

            // 当前符号是括号
            if (chars[i] == '(') {
                char[] parenthesesChars = getParenthesesExpression(chars, i);
                numStack.push(calc(parenthesesChars));
                i += (parenthesesChars.length + 2);
                continue;
            }

            // 当前符号是运算符
            int flagLevel = getFlagLevel(chars[i]);
            if (flagLevel != -1) {
                // 判断是否需要计算前一个表达式
                if (!flagStack.empty()) {
                    Character lastFlag = flagStack.peek();
                    int lastFlagLevel = getFlagLevel(lastFlag);
                    if (flagLevel <= lastFlagLevel) {
                        // 一直计算到底
                        while (!flagStack.empty()) {
                            numStack.push(calculateAsStack(numStack, flagStack));
                        }
                    }
                }

                flagStack.push(chars[i]);
                i++;
                continue;
            }

            // 当前符号非法
            throw new Exception("表达式不支持该符号：" + chars[i]);
        }

        // 完成剩余的计算
        while (!flagStack.empty()) {
            numStack.push(calculateAsStack(numStack, flagStack));
        }
        return numStack.pop();
    }

    /**
     * 获取数字字符串
     *
     * @param chars 字符集合
     * @param index 当前下标
     * @return 数字字符串
     */
    private static String getNumber(char[] chars, int index) {
        String numStr = CheckEmptyUtil.EMPTY;
        for (int i = index; i < chars.length; i++) {
            char currChar = chars[i];
            if ((currChar >= 48 && currChar <= 57) || currChar == '.') {
                numStr += currChar;
            } else {
                return numStr;
            }
        }

        return numStr;
    }

    /**
     * 获取括号中的字符
     *
     * @param chars 字符集和
     * @param index 索引
     * @return 括号中字符
     */
    private static char[] getParenthesesExpression(char[] chars, int index) {
        int grade = 1;
        String result = CheckEmptyUtil.EMPTY;
        for (int i = index + 1; i < chars.length; i++) {
            if (chars[i] == '(') {
                grade++;
            } else if (chars[i] == ')') {
                grade--;
            }
            if (grade == 0) {
                return result.toCharArray();
            }

            result += chars[i];
        }

        return result.toCharArray();
    }

    /**
     * 获取符号优先级
     *
     * @param flag 符号
     * @return 优先级
     */
    private static int getFlagLevel(char flag) {
        switch (flag) {
            case '*':
            case '/':
            case '%':
                return 2;
            case '+':
            case '-':
                return 1;
            default:
                return -1;
        }
    }

    /**
     * 计算栈数据
     *
     * @param numStack 数字栈
     * @param flagStack 符号栈
     * @return 结果
     * @throws Exception
     */
    private static Double calculateAsStack(Stack<Double> numStack, Stack<Character> flagStack) throws Exception {
        Double right = numStack.pop();
        Double left = numStack.pop();
        Character flag = flagStack.pop();

        switch (flag) {
            case '*':
                return left * right;
            case '/':
                return left / right;
            case '%':
                return left % right;
            case '+':
                return left + right;
            case '-':
                return left - right;
            default:
                throw new Exception("未知的计算符号：" + flag);
        }
    }
}

