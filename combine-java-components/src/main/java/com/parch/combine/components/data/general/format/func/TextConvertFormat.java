package com.parch.combine.components.data.general.format.func;

import com.parch.combine.core.common.util.CheckEmptyUtil;

import java.util.*;

/**
 * 文件转换
 */
public class TextConvertFormat implements ICustomFormat {

    private static final String DEFAULT_ROW_SEPARATOR = "\\s+";

    @Override
    public List<String> check(String[] params) {
        List<String> errorMsg = new ArrayList<>();
        if (CheckEmptyUtil.isEmpty(params)) {
            errorMsg.add(FormatFuncError.PARAMS_IS_NULL);
            return errorMsg;
        }

        // 函数参数验证
        ConvertType type = ConvertType.get(params[0]);
        switch (type) {
            case MODEL_TO_TEXT:
            case MODELS_TO_TEXTS:
            case TEXT_TO_MODEL:
            case TEXTS_TO_MODELS:
                if (params.length < 2 || CheckEmptyUtil.isEmpty(params[1])) {
                    errorMsg.add(FormatFuncError.TEXT_CONVERT_KEYS_IS_NULL);
                }
                if (params.length < 3 || CheckEmptyUtil.isEmpty(params[2])) {
                    errorMsg.add(FormatFuncError.TEXT_CONVERT_COL_SEPARATOR_IS_NULL);
                }
            case MODELS_TO_TEXT:
            case TEXT_TO_MODELS:
                break;
            default:
                errorMsg.add(FormatFuncError.TEXT_CONVERT_TYPE_ERROR);
                break;
        }

        return errorMsg;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object format(Object sourceValue, String[] params) throws Exception {
        if (sourceValue == null) {
            return null;
        }

        ConvertType type = ConvertType.get(params[0]);
        switch (type) {
            case MODEL_TO_TEXT:
                if (!(sourceValue instanceof Map)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_OBJECT);
                }
                return modelToText((Map<String, Object>) sourceValue, params[1], params[2]);
            case MODELS_TO_TEXT:
                if (!(sourceValue instanceof List)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
                }
                List<?> modelsToTextData = (List<?>) sourceValue;
                if (CheckEmptyUtil.isEmpty(modelsToTextData)) {
                    return CheckEmptyUtil.EMPTY;
                }
                if (!(modelsToTextData.get(0) instanceof Map)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_OBJECT_LIST);
                }
                return modelsToText((List<Map<String, Object>>) sourceValue, params[1], params[2], params[3]);
            case MODELS_TO_TEXTS:
                if (!(sourceValue instanceof List)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
                }
                List<?> modelsToTextsData = (List<?>) sourceValue;
                if (CheckEmptyUtil.isEmpty(modelsToTextsData)) {
                    return new ArrayList<String>();
                }
                if (!(modelsToTextsData.get(0) instanceof Map)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_OBJECT_LIST);
                }
                return modelsToTexts((List<Map<String, Object>>) sourceValue, params[1], params[2]);
            case TEXT_TO_MODEL:
                if (!(sourceValue instanceof String)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_STRING);
                }
                return textToModel((String) sourceValue, params[1], params[2]);
            case TEXT_TO_MODELS:
                if (!(sourceValue instanceof String)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_STRING);
                }
                String rowSeparator = params.length < 4 || CheckEmptyUtil.isEmpty(params[3]) ? DEFAULT_ROW_SEPARATOR : params[3];
                return textToModels((String) sourceValue, params[1], params[2], rowSeparator);
            case TEXTS_TO_MODELS:
                if (!(sourceValue instanceof List)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_LIST);
                }
                List<?> textsToModelsData = (List<?>) sourceValue;
                if (CheckEmptyUtil.isEmpty(textsToModelsData)) {
                    return new ArrayList<Map<String, Object>>();
                }
                if (!(textsToModelsData.get(0) instanceof String)) {
                    throw new Exception(FormatFuncError.DATA_TYPE_NOT_STRING_LIST);
                }
                return textsToModels((List<String>) sourceValue, params[1], params[2]);
            default:
                return null;
        }
    }

    /**
     * 对象转文本
     *
     * @param data 对象数据
     * @param colSeparator 列分隔符
     * @return 结果
     */
    private String modelToText(Map<String, Object> data, String keys, String colSeparator) {
        StringBuilder text = new StringBuilder();
        String[] keyArr = keys.split(KEY_SEPARATOR);
        for (int i = 0; i < keyArr.length; i++) {
            Object value = data.get(keyArr[i]);
            text.append(value == null ? CheckEmptyUtil.EMPTY : value.toString());
            if (i != keyArr.length - 1) {
                text.append(colSeparator);
            }
        }
        return text.toString();
    }

    /**
     * 对象集合转文本集合
     *
     * @param dataList 对象集合数据
     * @param colSeparator 列分隔符
     * @return 结果
     */
    private List<String> modelsToTexts(List<Map<String, Object>> dataList, String keys, String colSeparator) {
        List<String> texts = new ArrayList<>();
        for (Map<String, Object> data : dataList) {
            texts.add(modelToText(data, keys, colSeparator));
        }
        return texts;
    }

    /**
     * 对象集合转文本
     *
     * @param dataList 对象集合数据
     * @param colSeparator 列分隔符
     * @param rowSeparator 行分隔符
     * @return 结果
     */
    private String modelsToText(List<Map<String, Object>> dataList, String keys, String colSeparator, String rowSeparator) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, Object> data = dataList.get(i);
            text.append(modelToText(data, keys, colSeparator));
            if (i != dataList.size() - 1) {
                text.append(rowSeparator);
            }
        }
        return text.toString();
    }

    /**
     * 文本转对象
     *
     * @param text 文本
     * @param colSeparator 列分隔符
     * @return 结果
     */
    private Map<String, Object> textToModel(String text, String keys, String colSeparator) {
        String[] keyArr = keys.split(KEY_SEPARATOR);
        String[] colArr = text.split(colSeparator);
        int length = Math.min(keyArr.length, colArr.length);

        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (CheckEmptyUtil.isNotEmpty(keyArr[i])) {
                data.put(keyArr[i], colArr[i]);
            }
        }

        return data;
    }

    /**
     * 文本转对象集合
     *
     * @param text 文本
     * @param colSeparator 列分隔符
     * @param rowSeparator 行分隔符
     * @return 结果
     */
    private List<Map<String, Object>> textToModels(String text, String keys, String colSeparator, String rowSeparator) {
        String[] rowArr = text.split(rowSeparator);
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (String row : rowArr) {
            dataList.add(textToModel(row, keys, colSeparator));
        }
        return dataList;
    }

    /**
     * 文本集合转对象集合
     *
     * @param text 文本
     * @param colSeparator 列分隔符
     * @return 结果
     */
    private List<Map<String, Object>> textsToModels(List<String> text, String keys, String colSeparator) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (String row : text) {
            dataList.add(textToModel(row, keys, colSeparator));
        }
        return dataList;
    }

    /**
     * 格式化类型
     */
    private enum ConvertType {
        MODEL_TO_TEXT, MODELS_TO_TEXTS, MODELS_TO_TEXT, TEXT_TO_MODEL, TEXT_TO_MODELS, TEXTS_TO_MODELS, NONE;

        public static ConvertType get(String type) {
            if (CheckEmptyUtil.isEmpty(type)) {
                return NONE;
            }
            for (ConvertType value : ConvertType.values()) {
                if (value.toString().equals(type.toUpperCase())) {
                    return value;
                }
            }
            return NONE;
        }
    }
}
