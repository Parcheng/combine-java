package com.parch.combine.components.web;

import java.util.HashMap;
import java.util.Map;

/**
 * 元素结果字段KEY
 */
public class ElementResultHandler {

    private final static String ID = "id";
    private final static String TYPE = "TYPE";
    private final static String CONFIG_JSON = "configJson";
    private final static String LIB_SCRIPT = "libScript";
    private final static String FLAG = "elementGroupFlag";
    private final static String FLAG_VALUE = "$element";

    /**
     * 构建结构对象
     *
     * @param id ID
     * @param type 类型
     * @param configJson JSON配置
     * @return 结果
     */
    public static Map<String, String> build(String id, String type, String configJson) {
        Map<String, String> data = new HashMap<>();
        data.put(ID, id);
        data.put(TYPE, type);
        data.put(CONFIG_JSON, configJson);
        data.put(FLAG, FLAG_VALUE);
        return data;
    }

    /**
     * 是否为元素组结果对象
     *
     * @param data 数据对象
     * @return 是否
     */
    public static boolean isElementResult(Map<?, ?> data) {
        Object flag = data.get(FLAG);
        if (flag == null || !FLAG_VALUE.equals(flag.toString())) {
            return false;
        }

        return true;
    }

    /**
     * 是否为元素组结果对象
     *
     * @param data 数据对象
     * @return 是否
     */
    public static ElementResult convert(ElementResult result, Map<?, ?> data) {
        if (result == null || data == null) {
            return null;
        }

        result.setId((String) data.get(ID));
        result.setType((String) data.get(TYPE));
        result.setConfigJson((String) data.get(CONFIG_JSON));
        return result;
    }

    public static class ElementResult {

        public String id;

        public String type;

        public String configJson;

        public String libScript;

        public String groupId;

        public String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getConfigJson() {
            return configJson;
        }

        public void setConfigJson(String configJson) {
            this.configJson = configJson;
        }

        public String getLibScript() {
            return libScript;
        }

        public void setLibScript(String libScript) {
            this.libScript = libScript;
        }

        public String getGroupId() {
            return groupId;
        }

        public void setGroupId(String groupId) {
            this.groupId = groupId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
