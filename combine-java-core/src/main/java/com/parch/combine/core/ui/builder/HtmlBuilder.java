package com.parch.combine.core.ui.builder;

import com.parch.combine.core.common.util.*;
import com.parch.combine.core.ui.base.HtmlElementConfig;
import com.parch.combine.core.ui.base.UrlPathCanstant;
import com.parch.combine.core.ui.context.ConfigLoadingContext;
import com.parch.combine.core.ui.context.ConfigLoadingContextHandler;
import com.parch.combine.core.ui.tools.PrintTool;
import com.parch.combine.core.ui.base.HtmlConfig;
import com.parch.combine.core.ui.tools.HtmlBuildTool;
import com.parch.combine.core.ui.tools.ScriptBuildTool;
import com.parch.combine.core.ui.tools.UrlPathHelper;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        String script = buildScript();
        String elementScript = buildElementScript();

        // 生成页面
        return buildPage(head, body, script + elementScript);
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

        // 模板
        List<HtmlElementConfig> templateModels = templateConfig.getModules();
        Map<String, HtmlElementConfig> templateModelMap = new HashMap<>();
        if (CheckEmptyUtil.isNotEmpty(templateModels)) {
            templateModelMap = templateModels.stream().collect(Collectors.toMap(HtmlElementConfig::getKey, Function.identity()));
        }

        // 页面模块
        List<HtmlElementConfig> models = config.getModules();
        for (HtmlElementConfig model : models) {
            HtmlElementConfig tempDomConfig = templateModelMap.get(model.getKey());
            body.add(HtmlBuildTool.build(model, tempDomConfig, false));
        }

        // 添加弹窗使用的DIV元素
        Map<String, String> properties = new HashMap<>();
        properties.put("id", "$combine-web-triggers");
        body.add(HtmlBuildTool.build("div", null, properties, false));

        // 构建页面
        return HtmlBuildTool.build("body", StringUtil.join(body, CheckEmptyUtil.EMPTY), null, false);
    }

    protected String buildScript() {
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

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    protected String buildElementScript() {
        List<String> scripts = new ArrayList<>();

        ElementGroupBuilder.ElementGroupResult groupResult = groupBuilder.build();
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();

        // 添加框架核心JS和页面元素JS
        scripts.add(ScriptBuildTool.build(context.getSystemUrl() + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_BASE_JS_NAME));
        for (String elementScript : groupResult.elementScripts) {
            scripts.add(ScriptBuildTool.build(elementScript));
        }

        // 添加框架组件实例注册代码
        List<String> scriptCodeList = new ArrayList<>();
        scriptCodeList.add("\n$combineWebUI.init(\"" + context.getBaseUrl() + "\");");

        String content = JsonUtil.serialize(context.getManager().getConstant().get());
        scriptCodeList.add("\n$combineWebUI.content.register(\"" + content + "\");");

        // TODO INIT的模块

        // 元素模板注册
        groupResult.templateMap.forEach((k, v) -> scriptCodeList.add("\n$combineWebUI.template.register(\"" + k + "\",\"" + v + "\");"));
        // 数据加载配置注册
        groupResult.dataLoadMap.forEach((k, v) -> scriptCodeList.add("\n$combineWebUI.dataLoad.register(\"" + k + "\",\"" + v + "\", \"" + groupResult.dataLoadToElementIdMap.get(k) + "\");"));
        // trigger事件注册
        groupResult.triggerMap.forEach((k, v) -> scriptCodeList.add("\n$combineWebUI.trigger.register(\"" + k + "\",\"" + v + "\");"));
        // 页面元素注册
        groupResult.elementMap.forEach((k, v) -> scriptCodeList.add("\n$combineWebUI.element.register(\"" + k + "\",\"" + v + "\");"));
        // 页面元素组注册
        groupResult.groupMap.forEach((k, v) -> scriptCodeList.add("\n$combineWebUI.group.register(\"" + k + "\",\"" + v + "\");"));

        // 构建
        scripts.add(ScriptBuildTool.build(scriptCodeList));

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
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