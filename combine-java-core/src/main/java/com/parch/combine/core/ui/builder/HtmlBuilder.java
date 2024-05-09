package com.parch.combine.core.ui.builder;

import com.parch.combine.core.ui.builder.config.HtmlConfig;
import java.util.*;

public class HtmlBuilder {

    private final static Map<String, HtmlConfig> TEMP_MAP = new HashMap<>();

    private HtmlConfig configs;

    public HtmlBuilder(HtmlConfig configs) {
        this.configs = configs;
    }

    public String build() {

//        List<ElementResultHelper.ElementResult> elementResults = new ArrayList<>();
//        Map<String, String> initElements = new HashMap<>(16);
//
//        // 构建页面头和页面内容
//        String head = buildHead();
//        String body = buildBody(elementResults, configs, initElements, errorMsg);
//
//        // 构建用户定义脚本
//        String customScript = buildCustomScript();
//
//        // 构建元素组件脚本代码
//        String elementScript = buildElementScript(elementResults, initElements);
//
//        if (CheckEmptyUtil.isNotEmpty(errorMsg)) {
//            return null;
//        }
//
//        // 生成页面
//        return buildPage(head, body, customScript, elementScript);

        return null;
    }

//    /**
//     * 构建页面
//     *
//     * @param head 头
//     * @param body 内容体
//     * @param importScript JS导入
//     * @param codeScript JS编码
//     * @return 页面代码
//     */
//    private String buildPage(String head, String body, String importScript, String codeScript) {
//        HtmlConfig pageConfig = getpageConfig();
//        HtmlConfig tempConfig = TEMP_MAP.get(pageConfig.getTempPath());
//
//        Map<String, String> htmlProperties = new HashMap<>();
//        htmlProperties.put("lang", CheckEmptyUtil.isEmpty(pageConfig.getLang()) ? tempConfig.getLang() : pageConfig.getLang());
//
//        String htmlCode = HtmlBuileHelper.build("html", head + body + importScript + codeScript, htmlProperties, false);
//        return CharacterUtil.replaceChinese(htmlCode).replaceAll("\\\\{2}", "\\\\\\\\\\\\");
//    }
//
//    /**
//     * 构建页面体
//     *
//     * @return 页面体内容
//     */
//    private String buildBody(List<ElementResultHelper.ElementResult> elementResults,
//                             List<HtmlConfig.HtmlElementConfig> configs,
//                             Map<String, String> initElement, List<String> errorMsg) {
//        HtmlConfig pageConfig = getpageConfig();
//        List<String> body = new ArrayList<>();
//
//        // 解析出元素组结果
//        List<String> groupIds = pageConfig.getElements();
//        for (String componentGroupId : groupIds) {
//            DataResult componentResult = ComponentContextHandler.getResultData(componentGroupId);
//            if (componentResult == null) {
//                errorMsg.add("【" + componentGroupId + "】不存在");
//                continue;
//            }
//
//            List<ElementResultHelper.ElementResult> results = new ArrayList<>();
//            buildElementResult(componentResult.getData(), results, body);
//            for (ElementResultHelper.ElementResult result : results) {
//                result.setGroupId(componentGroupId);
//            }
//            elementResults.addAll(results);
//        }
//
//        // 获取模板中元素配置
//        HtmlConfig tempConfig = TEMP_MAP.get(pageConfig.getTempPath());
//        Map<String, DomConfig> tempDomConfigMap;
//        if (tempConfig.getConfigs() != null) {
//            tempDomConfigMap = tempConfig.getConfigs().stream().collect(Collectors.toMap(HtmlConfig.HtmlElementConfig::getKey, HtmlConfig.HtmlElementConfig::getConfig));
//        } else {
//            tempDomConfigMap = new HashMap<>(0);
//        }
//
//        // 构建HTML，并设置初始化元素
//        for (HtmlConfig.HtmlElementConfig config : configs) {
//            if (config == null) {
//                continue;
//            }
//
//            DomConfig tempDomConfig = tempDomConfigMap.get(config.getKey());
//            HtmlConfig.HtmlElement htmlElement = config.getConfig();
//            if (tempDomConfig == null && htmlElement == null) {
//                continue;
//            }
//
//            body.add(HtmlBuileHelper.build(htmlElement, tempDomConfig, false));
//            if (htmlElement != null) {
//                if (CheckEmptyUtil.isNotEmpty(htmlElement.getShowElement()) && !groupIds.contains(htmlElement.getShowElement())) {
//                    errorMsg.add("【" + htmlElement.getShowElement() + "】不存在");
//                } else {
//                    initElement.put(htmlElement.getId(), htmlElement.getShowElement());
//                }
//            }
//        }
//
//        // 添加弹窗使用的DIV元素
//        Map<String, String> properties = new HashMap<>();
//        properties.put("id", "$combine-web-triggers");
//        body.add(HtmlBuileHelper.build("div", null, properties, false));
//
//        // 构建页面
//        return HtmlBuileHelper.build("body", StringUtil.join(body, ""), null, false);
//    }
//
//    /**
//     * 构建元素结果
//     *
//     * @param data 数据
//     * @param results 结构集
//     * @param body 方法体
//     */
//    @SuppressWarnings("unchecked")
//    protected void buildElementResult(Object data, List<ElementResultHelper.ElementResult> results, List<String> body) {
//        if (data instanceof Collection) {
//            for (Object itemData : (Collection<Object>) data) {
//                if (itemData instanceof Map && ElementResultHelper.isElementResult((Map<?,?>) itemData)) {
//                    ElementResultHelper.ElementResult result = new ElementResultHelper.ElementResult();
//                    ElementResultHelper.convert(result, (Map<?,?>) itemData);
//                    results.add(result);
//                } else {
//                    body.add(JsonUtil.serialize(itemData));
//                }
//            }
//        } else if (data instanceof Map && ElementResultHelper.isElementResult((Map<?,?>) data)) {
//            ElementResultHelper.ElementResult result = new ElementResultHelper.ElementResult();
//            ElementResultHelper.convert(result, (Map<?,?>) data);
//            results.add(result);
//        } else {
//            body.add(JsonUtil.serialize(data));
//        }
//    }
//
//    /**
//     * 构建script脚本
//     *
//     * @return script脚本内容
//     */
//    protected String buildCustomScript() {
//        WebPageInitConfig initConfig = getInitConfig();
//        HtmlConfig pageConfig = getpageConfig();
//        HtmlConfig tempConfig = TEMP_MAP.get(pageConfig.getTempPath());
//
//        List<String> scripts = new ArrayList<>();
//
//        // 添加模板脚本
//        if (CheckEmptyUtil.isNotEmpty(tempConfig.getScripts())) {
//            for (String scriptSrc : tempConfig.getScripts()) {
//                scripts.add(ScriptBuildHelper.build(replaceUrlFlag(scriptSrc)));
//            }
//        }
//
//        // 添加用户配置脚本
//        if (CheckEmptyUtil.isNotEmpty(pageConfig.getScripts())) {
//            for (String scriptSrc : pageConfig.getScripts()) {
//                scripts.add(ScriptBuildHelper.build(this.replaceUrlFlag((scriptSrc))));
//            }
//        }
//
//        return StringUtil.join(scripts, "");
//    }
//
//    /**
//     * 构建脚本编码
//     *
//     * @param elementResults 元素结果集合
//     * @return 脚本编码集合
//     */
//    protected String buildElementScript(List<ElementResultHelper.ElementResult> elementResults, Map<String, String> initElements) {
//        if (CheckEmptyUtil.isEmpty(elementResults)) {
//            return CheckEmptyUtil.EMPTY;
//        }
//
//        WebPageInitConfig initConfig = getInitConfig();
//        List<String> scripts = new ArrayList<>();
//
//        // 添加框架核心功能引用脚本
//        scripts.add(ScriptBuildHelper.build(initConfig.getSystemUrl() + "/lib/base.js"));
//        for (ElementTypeEnum typeEnum : ElementTypeEnum.values()) {
//            scripts.add(ScriptBuildHelper.build(initConfig.getSystemUrl() + "/lib/" + typeEnum.name().toLowerCase() + "_element.js"));
//        }
//
//        // 添加框架组件实例注册代码
//        List<String> scriptCodeList = new ArrayList<>();
//        scriptCodeList.add("\n$combineWebUI.init(\"" + initConfig.getBaseUrl() + "\");");
//        for (ElementResultHelper.ElementResult item : elementResults) {
//            if (CheckEmptyUtil.isNotEmpty(item.getConfigJson())) {
//                scriptCodeList.add("$combineWebUI.instance.register(\"" + replaceUrlFlag(item.getConfigJson()) + "\",\"" + item.getGroupId() + "\");");
//            }
//        }
//        initElements.forEach((k, v) -> {
//            if (v != null && k != null) {
//                scriptCodeList.add("$combineWebUI.instance.load(\"" + v + "\",\"" + k + "\");");
//            }
//        });
//        scripts.add(ScriptBuildHelper.build(scriptCodeList));
//
//        return StringUtil.join(scripts, "");
//    }

    public List<String> check() {

        // mate
        // link

//        // 加载模板配置
//        if (!TEMP_MAP.containsKey(pageConfig.getTempPath())) {
//            try {
//                String testConfigJson = ResourceFileUtil.read(pageConfig.getTempPath());
//                if (CheckEmptyUtil.isEmpty(testConfigJson)) {
//                    result.add(PageErrorHandler.buildCheckMsg(key, "加载模板数据为空: " + pageConfig.getTempPath()));
//                } else {
//                    HtmlConfig tempConfig = JsonUtil.deserialize(testConfigJson, HtmlConfig.class);
//                    TEMP_MAP.put(pageConfig.getTempPath(), tempConfig);
//                }
//            } catch (Exception e) {
//                result.add(PageErrorHandler.buildCheckMsg(key, "加载模板失败: " + e.getMessage()));
//            }
//        }

        return null;
    }


}
