package com.parch.combine.component.data.format.func;

/**
 * 格式化函数异常信息
 */
public interface FormatFuncError {

    String PARAMS_IS_NULL = "函数参数为空";

    String PARAMS_COUNT_ERROR = "函数参数数量不合规";

    String DATA_TYPE_ERROR = "数据类型错误";

    String DATA_TYPE_NOT_STRING = "数据非字符串类型";

    String DATA_TYPE_NOT_STRING_LIST = "数据非字符串集合";

    String DATA_TYPE_NOT_OBJECT_LIST = "数据非对象集合";

    String DATA_TYPE_NOT_OBJECT = "数据非对象类型";

    String DATA_TYPE_NOT_LIST = "数据非集合类型";

    String UNKNOWN_JSON_TYPE = "未知的JSON函数类型";

    String JSON_TYPE_IS_NULL = "JSON函数类型为空";

    String TEXT_CONVERT_TYPE_ERROR = "文本转换函数类型不合规";

    String TEXT_CONVERT_KEYS_IS_NULL = "文本转换函数字段KEY为空";

    String TEXT_CONVERT_COL_SEPARATOR_IS_NULL = "文本转换函数列分隔符为空";

    String GROUP_KEY_IS_NULL = "分组函数KEY为空";

    String RAND_START_INDEX_IS_NOT_NUMBER = "区间取值函数起始下标非数字";

    String RAND_END_INDEX_IS_NOT_NUMBER = "区间取值函数解说下标非数字";

    String MD5_ERROR = "MD5加密失败";
}
