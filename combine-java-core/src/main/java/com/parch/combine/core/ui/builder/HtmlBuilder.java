package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.*;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.tools.PrintTool;
import com.parch.combine.core.ui.base.HtmlConfig;
import com.parch.combine.core.ui.tools.HtmlBuildTool;
import com.parch.combine.core.ui.tools.ScriptBuildTool;
import com.parch.combine.core.ui.tools.UrlPathHelper;

import java.util.*;

public class HtmlBuilder {

    private final static Map<String, HtmlConfig> TEMP_MAP = new HashMap<>();

    private String key;

    private HtmlConfig config;

    private ElementGroupBuilder groupBuilder;

    private HtmlConfig templateConfig;
    public HtmlBuilder(String key, HtmlConfig config) {
        this.key = key;
        this.config = config;
        this.groupBuilder = new ElementGroupBuilder(config.getGroupIds());
        loadTemplate();
    }

    public List<String> check() {
        config.check();
        groupBuilder.check();
        if (templateConfig == null) {

        }

        return null;
    }

    public String build() {
//        List<ElementResultHelper.ElementResult> elementResults = new ArrayList<>();
//        Map<String, String> initElements = new HashMap<>(16);

        String head = buildHead();
        String body = buildBody();
        String script = buildScript(null, null);

        // 生成页面
        return buildPage(head, null, script);
    }

    private String buildHead() {
        HtmlHeaderLinkBuilder linkBuilder = new HtmlHeaderLinkBuilder(templateConfig.getLinks(), config.getLinks());
        HtmlHeaderMetaBuilder metaBuilder = new HtmlHeaderMetaBuilder(templateConfig.getMetas(), config.getMetas());

        String headBody = CheckEmptyUtil.EMPTY;
        headBody += StringUtil.join(metaBuilder.build(), "");
        headBody += StringUtil.join(linkBuilder.build(), "");
        return HtmlBuildTool.build("head", headBody, null, false);
    }

    private String buildPage(String head, String body, String script) {
        Map<String, String> htmlProperties = new HashMap<>();
        htmlProperties.put("lang", CheckEmptyUtil.isEmpty(config.getLang()) ? templateConfig.getLang() : config.getLang());

        String htmlCode = HtmlBuildTool.build("html", head + body + script, htmlProperties, false);
        return CharacterUtil.replaceChinese(htmlCode).replaceAll("\\\\{2}", "\\\\\\\\\\\\");
    }

    private String buildBody() {
        List<String> body = new ArrayList<>();
        ElementGroupBuilder.ElementGroupResult groupResult = groupBuilder.build();



//        // 解析出元素组结果
//        List<String> groupIds = config.getGroupIds();
//        for (String componentGroupId : groupIds) {
//            DataResult componentResult = ComponentContextHandler.getResultData(componentGroupId);
//            if (componentResult == null) {
//                errorMsg.add("【" + componentGroupId + "】不存在");
//                continue;
//            }
//
//            List<ElementGroupBuilder.ElementResult> results = new ArrayList<>();
//            buildElementResult(componentResult.getData(), results, body);
//            for (ElementGroupBuilder.ElementResult result : results) {
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
//            body.add(HtmlBuildTool.build(htmlElement, tempDomConfig, false));
//            if (htmlElement != null) {
//                if (CheckEmptyUtil.isNotEmpty(htmlElement.getShowElement()) && !groupIds.contains(htmlElement.getShowElement())) {
//                    errorMsg.add("【" + htmlElement.getShowElement() + "】不存在");
//                } else {
//                    initElement.put(htmlElement.getId(), htmlElement.getShowElement());
//                }
//            }
//        }

        // 添加弹窗使用的DIV元素
        Map<String, String> properties = new HashMap<>();
        properties.put("id", "$combine-web-triggers");
        body.add(HtmlBuildTool.build("div", null, properties, false));

        // 构建页面
        return HtmlBuildTool.build("body", StringUtil.join(body, ""), null, false);
    }

    @SuppressWarnings("unchecked")
    protected void buildElementResult(Object data, List<ElementGroupBuilder.ElementGroupResult> results, List<String> body) {
//        if (data instanceof Collection) {
//            for (Object itemData : (Collection<Object>) data) {
//                if (itemData instanceof Map && ElementGroupBuilder.isElementResult((Map<?,?>) itemData)) {
//                    ElementGroupBuilder.ElementResult result = new ElementGroupBuilder.ElementResult();
//                    ElementGroupBuilder.convert(result, (Map<?,?>) itemData);
//                    results.add(result);
//                } else {
//                    body.add(JsonUtil.serialize(itemData));
//                }
//            }
//        } else if (data instanceof Map && ElementGroupBuilder.isElementResult((Map<?,?>) data)) {
//            ElementGroupBuilder.ElementResult result = new ElementGroupBuilder.ElementResult();
//            ElementGroupBuilder.convert(result, (Map<?,?>) data);
//            results.add(result);
//        } else {
//            body.add(JsonUtil.serialize(data));
//        }
    }

    protected String buildScript(List<String> elementScripts, Map<String, String> initElements) {
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();

        List<String> scripts = new ArrayList<>();

        // 添加模板脚本
        if (CheckEmptyUtil.isNotEmpty(templateConfig.getScripts())) {
            for (String scriptSrc : templateConfig.getScripts()) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(scriptSrc)));
            }
        }

        // 添加用户配置脚本
        if (CheckEmptyUtil.isNotEmpty(config.getScripts())) {
            for (String scriptSrc : config.getScripts()) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag((scriptSrc))));
            }
        }

        // 添加框架核心功能引用脚本
        scripts.add(ScriptBuildTool.build(context.getSystemUrl() + "/lib/base.js"));
        for (String elementScript : elementScripts) {
            scripts.add(ScriptBuildTool.build(elementScript));
        }

        // 添加框架组件实例注册代码
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

        return StringUtil.join(scripts, "");
    }

    private void loadTemplate() {
        if (!TEMP_MAP.containsKey(config.getTempPath())) {
            try {
                String testConfigJson = ResourceFileUtil.read(config.getTempPath());
                if (CheckEmptyUtil.isNotEmpty(testConfigJson)) {
                    templateConfig = JsonUtil.deserialize(testConfigJson, HtmlConfig.class);
                    TEMP_MAP.put(config.getTempPath(), templateConfig);
                } else {
                    PrintTool.printInit("【PAGE-TEMPLATE】【" + config.getTempPath() + "】【加载模板数据为空】");
                }
            } catch (Exception e) {
                PrintTool.printInit("【PAGE-TEMPLATE】【" + config.getTempPath() + "】【加载模板失败】");
            }
        }
    }
}
