package com.parch.combine.core.component.tools.variable;


public class TextExpressionHelper {

    public static String getText(String expression) {
        return getText(expression, null);
    }

    public static String getText(String expression, String defaultText) {
        return getText(expression, defaultText, false);
    }

    public static String getText(String expression, String defaultText, boolean isForce) {
        Object data = DataVariableHelper.parseValue(expression, false);
        if (data == null) {
            return defaultText;
        }

        return data.toString();
    }

    public static Object getObject(String expression) {
        return DataVariableHelper.parseValue(expression, true);
    }
}
