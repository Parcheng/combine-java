package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.util.CharacterUtil;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.html.base.page.config.HtmlConfig;
import com.parch.combine.html.base.page.config.HtmlElementConfig;
import com.parch.combine.html.common.canstant.UrlPathCanstant;
import com.parch.combine.html.common.tool.ConfigErrorMsgTool;
import com.parch.combine.html.common.tool.HtmlBuildTool;
import com.parch.combine.html.common.tool.PrintTool;
import com.parch.combine.html.common.tool.ScriptBuildTool;
import com.parch.combine.html.common.tool.UrlPathHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HtmlBuilder {

    private final static String TRIGGERS_DOM_ID = "$combine-triggers";

    private final static Map<String, HtmlConfig> TEMP_MAP = new HashMap<>();

    private HtmlConfig config;

    private HtmlConfig templateConfig;

    private ElementGroupBuilder groupBuilder;

    public HtmlBuilder(HtmlConfig config) {
        this.config = config;
        this.groupBuilder = new ElementGroupBuilder(config.groupIds());
        loadTemplate();
    }

    public List<String> check() {
        List<String> msg = new ArrayList<>();
        msg.addAll(groupBuilder.check());
        if (templateConfig == null) {
            msg.add(ConfigErrorMsgTool.fieldCheckError("templateConfig", "页面模板不存在"));
        }
        return msg;
    }

    public String build() {
        ElementGroupBuilder.ElementGroupResult groupResult = groupBuilder.build();
        ConfigLoadingContext context = ConfigLoadingContextHandler.getContext();

        String head = buildHead(groupResult, context);
        String body = buildBody();
        String script = buildScript();
        String elementScript = buildElementScript(groupResult, context);
        return buildPage(head, body, script + elementScript);
    }

    private String buildHead(ElementGroupBuilder.ElementGroupResult groupResult, ConfigLoadingContext context) {
        HtmlHeaderLinkBuilder linkBuilder = new HtmlHeaderLinkBuilder(templateConfig.links(), config.links());
        HtmlHeaderMetaBuilder metaBuilder = new HtmlHeaderMetaBuilder(templateConfig.metas(), config.metas());

        // 添加框架核心
        Map<String, String> coreCssProperties = new HashMap<>();
        coreCssProperties.put("rel", "stylesheet");
        coreCssProperties.put("href", context.getSystemUrl() + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_BASE_CSS_NAME);
        String coreCssTag = HtmlBuildTool.build("link", null, coreCssProperties, true);

        // 添加框架中使用的页面元素JS
        List<String> elementCssTag = new ArrayList<>();
        for (String elementStyle : groupResult.elementStyles) {
            Map<String, String> elementCssProperties = new HashMap<>();
            elementCssProperties.put("rel", "stylesheet");
            elementCssProperties.put("href", UrlPathHelper.replaceUrlFlag(elementStyle));
            elementCssTag.add(HtmlBuildTool.build("link", null, elementCssProperties, true));
        }

        String headBody = CheckEmptyUtil.EMPTY;
        headBody += StringUtil.join(metaBuilder.build(), CheckEmptyUtil.EMPTY);
        headBody += coreCssTag;
        headBody += StringUtil.join(linkBuilder.build(), CheckEmptyUtil.EMPTY);
        headBody += StringUtil.join(elementCssTag, CheckEmptyUtil.EMPTY);
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
        HtmlElementConfig[] templateModels = templateConfig.modules();
        Map<String, HtmlElementConfig> templateModelMap = new HashMap<>();
        if (CheckEmptyUtil.isNotEmpty(templateModels)) {
            templateModelMap = Arrays.stream(templateModels).collect(Collectors.toMap(HtmlElementConfig::key, Function.identity()));
        }

        // 页面模块
        HtmlElementConfig[] models = config.modules();
        if (CheckEmptyUtil.isNotEmpty(models)) {
            for (HtmlElementConfig model : models) {
                if (CheckEmptyUtil.isEmpty(model.key())) {
                    continue;
                }
                HtmlElementConfig tempDomConfig = templateModelMap.get(model.key());
                body.add(HtmlBuildTool.build(model.config(), tempDomConfig.config(), false));
            }
        }

        // 添加弹窗使用的DIV元素
        Map<String, String> properties = new HashMap<>();
        properties.put("id", TRIGGERS_DOM_ID);
        body.add(HtmlBuildTool.build("div", null, properties, false));

        // 构建页面
        return HtmlBuildTool.build("body", StringUtil.join(body, CheckEmptyUtil.EMPTY), null, false);
    }

    protected String buildScript() {
        List<String> scripts = new ArrayList<>();

        // 添加模板脚本
        String[] scriptArr = templateConfig.scripts();
        if (CheckEmptyUtil.isNotEmpty(scriptArr)) {
            for (String scriptSrc : scriptArr) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(scriptSrc)));
            }
        }

        // 添加用户配置脚本
        String[] configScriptArr = config.scripts();
        if (CheckEmptyUtil.isNotEmpty(configScriptArr)) {
            for (String scriptSrc : configScriptArr) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag((scriptSrc))));
            }
        }

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    protected String buildElementScript(ElementGroupBuilder.ElementGroupResult groupResult, ConfigLoadingContext context) {
        List<String> scripts = new ArrayList<>();

        // 添加框架核心
        String baseJsPath = context.getSystemUrl() + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_BASE_JS_NAME;
        scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(baseJsPath)));
        String baseToolsJsPath = context.getSystemUrl() + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_TOOLS_JS_NAME;
        scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(baseToolsJsPath)));

        // 添加框架中使用的页面元素JS
        for (String elementScript : groupResult.elementScripts) {
            scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(elementScript)));
        }

        // 添加框架组件实例注册代码
        List<String> scriptCodeList = new ArrayList<>();
        scriptCodeList.add("\n$combine.init(\"" + context.getBaseUrl() + "\", " + context.getFlagConfigsJson() + ");");

        // 常量注册
        String contentJson = JsonUtil.serialize(CombineManagerHandler.get(context.getScopeKey()).getConstant().get());
        scriptCodeList.add("\n$combine.constant.register(" + contentJson + ");");

        // 元素模板注册
        groupResult.templateMap.forEach((k, v) -> scriptCodeList.add("\n$combine.instanceTemp.register(\"" + k + "\"," + v + ");"));

        // 数据加载配置注册
        groupResult.dataLoadMap.forEach((k, v) -> scriptCodeList.add("\n$combine.loadData.register(\"" + k + "\"," + v + ", " + groupResult.dataLoadToElementIdMap.get(k) + ");"));

        // trigger事件注册
        if (groupResult.triggerMap.size() > 0) {
            scriptCodeList.add("\n$combine.trigger.setDomId(\"" + TRIGGERS_DOM_ID + "\");");
            groupResult.triggerMap.forEach((k, v) -> scriptCodeList.add("\n$combine.trigger.register(\"" + k + "\"," + v + ");"));
        }

        // 页面元素注册
        groupResult.elementMap.forEach((k, v) -> scriptCodeList.add("\n$combine.instance.register(\"" + k + "\"," + v + ");"));

        // 页面元素组注册
        groupResult.groupMap.forEach((k, v) -> scriptCodeList.add("\n$combine.group.register(\"" + k + "\"," + v + ");"));

        // 页面模块初始化
        HtmlElementConfig[] models = config.modules();
        if (CheckEmptyUtil.isNotEmpty(models)) {
            for (HtmlElementConfig model : models) {
                String showGroupId = model.defaultShowGroupId();
                if (CheckEmptyUtil.isNotEmpty(showGroupId)) {
                    scriptCodeList.add("\n$combine.group.load(\"" + showGroupId + "\",\"" + model.id() + "\");");
                }
            }
        }

        // 构建
        scripts.add(ScriptBuildTool.build(scriptCodeList));

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    private void loadTemplate() {
        String tempPath = config.tempPath();
        if (!TEMP_MAP.containsKey(tempPath)) {
            try {
                String testConfigJson = ResourceFileUtil.read(tempPath);
                if (CheckEmptyUtil.isNotEmpty(testConfigJson)) {
                    templateConfig = JsonUtil.deserialize(testConfigJson, HtmlConfig.class);
                    TEMP_MAP.put(tempPath, templateConfig);
                } else {
                    PrintTool.printInit("【PAGE-TEMPLATE】【" + tempPath + "】【加载模板数据为空】");
                }
            } catch (Exception e) {
                PrintTool.printInit("【PAGE-TEMPLATE】【" + tempPath + "】【加载模板失败】");
            }
        } else {
            templateConfig = TEMP_MAP.get(tempPath);
        }
    }
}
