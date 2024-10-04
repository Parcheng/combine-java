package com.parch.combine.core.common.util;

import com.parch.combine.core.common.canstant.SymbolConstant;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 数据解析工具类
 */
public class DataParseUtil {

    private DataParseUtil() {}

    public static Object parse(Object data, Class<?> typeClass) {
        if (data == null) {
            return null;
        }

        try {
            if (typeClass.isInstance(data)) {
                return data;
            } else if (String.class.equals(typeClass)) {
                return getString(data);
            } else if (Double.class.equals(typeClass)) {
                return Double.parseDouble(data.toString());
            } else if (Float.class.equals(typeClass)) {
                return Float.parseFloat(data.toString());
            } else if (Long.class.equals(typeClass)) {
                return Long.parseLong(data.toString());
            } else if (Integer.class.equals(typeClass)) {
                return Integer.parseInt(data.toString());
            } else if (Short.class.equals(typeClass)) {
                return Short.parseShort(data.toString());
            } else if (Date.class.equals(typeClass)) {
                return parseDate(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getString(Object data) {
        return getString(data, null);
    }

    public static String getString(Object data, String defaultValue) {
        if (data == null) {
            return defaultValue;
        }

        if (data instanceof Map || data instanceof Collection) {
            return JsonUtil.serialize(data);
        } else {
            return data.toString();
        }
    }

    public static Integer getInteger(Object data, Integer defaultValue) {
        if (!DataTypeIsUtil.isInteger(data)) {
            return defaultValue;
        }

        try {
            return Integer.parseInt(data.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Long getLong(Object data, Long defaultValue) {
        if (!DataTypeIsUtil.isLong(data)) {
            return defaultValue;
        }

        try {
            return Long.parseLong(data.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static Double getDouble(Object data, Double defaultValue) {
        if (!DataTypeIsUtil.isDouble(data)) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(data.toString());
        }  catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 解析Date数据
     *
     * @param data 数据
     * @return 数据
     */
    public static Date parseDate(Object data) {
        try {
            if (data == null) {
                return null;
            } else if (data instanceof Data) {
                return (Date) data;
            } else if (data instanceof Long) {
                return new Date((Long) data);
            } else if (data instanceof String) {
                String dateString = (String) data;
                if (dateString.matches("^\\d+$") && dateString.length() == 13) {
                    return new Date(Long.parseLong(dateString));
                } else {
                    dateString = dateString.replaceAll("[/\\-\\s:T]","");
                    if (dateString.matches("^\\d+$")) {
                        int length = dateString.length();
                        if (length == 17) {
                            return new SimpleDateFormat("yyyyMMddHHmmssSSS").parse(dateString);
                        } else if (length == 14) {
                            return new SimpleDateFormat("yyyyMMddHHmmss").parse(dateString);
                        } else if (length == 12) {
                            return new SimpleDateFormat("yyyyMMddHHmm").parse(dateString);
                        } else if (length == 10) {
                            return new SimpleDateFormat("yyyyMMddHH").parse(dateString);
                        } else if (length == 8) {
                            return new SimpleDateFormat("yyyyMMdd").parse(dateString);
                        } else if (length == 6) {
                            return new SimpleDateFormat("yyyyMM").parse(dateString);
                        } else if (length == 4) {
                            return new SimpleDateFormat("yyyy").parse(dateString);
                        }
                    }
                }
            }
        } catch (Exception e) {
            PrintLogUtil.printError("日期类型转换错误: " + e.getMessage());
            return null;
        }

        return null;
    }


    /**
     * 解析数字
     *
     * @param data 数据
     * @return 数据
     */
    public static Object parseNumber(Object data, Class<?> numClass) {
        if (data == null) {
            return null;
        }

        Number numberData = null;
        if (Number.class.isAssignableFrom(data.getClass())) {
            numberData = (Number) data;
        } else {
            String dataStr = data.toString();
            if (DataTypeIsUtil.isNumber(dataStr)) {
                if (dataStr.contains(SymbolConstant.DOT)) {
                    numberData = Double.parseDouble(dataStr);
                } else {
                    numberData = Long.parseLong(dataStr);
                }
            }
        }

        if (numberData != null && numClass != null && Number.class.isAssignableFrom(numClass)) {
            if (numClass == Double.class) {
                return numberData.doubleValue();
            } else if (numClass == Long.class) {
                return numberData.longValue();
            } else if (numClass == Float.class) {
                return numberData.floatValue();
            } else if (numClass == Integer.class) {
                return numberData.intValue();
            } else if (numClass == Short.class) {
                return numberData.shortValue();
            }
        }

        return numberData;
    }

    /**
     * 解析布尔
     *
     * @param data 数据
     * @return 数据
     */
    public static Boolean parseBoolean(Object data) {
        if (data == null) {
            return null;
        }

        if (data instanceof Boolean) {
            return (Boolean) data;
        }

        if (data instanceof Double || data instanceof Float ||
                data instanceof Long || data instanceof Integer || data instanceof Short) {
            return ((Number) data).doubleValue() > 0;
        }

        return data.toString().equalsIgnoreCase("true");
    }

    public static <T> T parseJava(Object data, Class<T> clazz) {
        String dataString = JsonUtil.serialize(data);
        return JsonUtil.deserialize(dataString, clazz);
    }
}
