package com.parch.combine.data.base.general;

import com.parch.combine.core.common.canstant.SymbolConstant;

public class DataStructureHelper {

    public static boolean isStructure(String value) {
        return value.startsWith(SymbolConstant.LEFT_PARENTHESIS)
                && value.endsWith(SymbolConstant.RIGHT_PARENTHESIS)
                && value.indexOf(SymbolConstant.COLON) > 0;
    }

    public static String[] parseStructure(String value) {
        return value.substring(1, value.length() -1).split(SymbolConstant.COLON);
    }
}
