package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.component.settings.annotations.Field;
import com.parch.combine.core.component.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CharacterUtil;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.common.util.json.JsonUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.html.base.page.HtmlPageInitConfig;
import com.parch.combine.html.base.page.HtmlPageLogicConfig;
import com.parch.combine.html.base.page.config.HtmlElementConfig;
import com.parch.combine.html.base.page.config.HtmlHeaderLinkConfig;
import com.parch.combine.html.base.page.config.HtmlHeaderMetaConfig;
import com.parch.combine.html.base.page.config.HtmlPageTemplateConfig;
import com.parch.combine.html.common.canstant.UrlPathCanstant;
import com.parch.combine.html.common.tool.HtmlBuildTool;
import com.parch.combine.html.common.tool.ScriptBuildTool;
import com.parch.combine.html.common.tool.SystemElementPathTool;
import com.parch.combine.html.common.tool.UrlPathHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HtmlBuilder {

    private final static String TRIGGERS_DOM_ID = "$combine-triggers";

    private final String baseUrl;
    private final String flagConfigJson;

    private final HtmlPageLogicConfig config;
    private final HtmlPageTemplateConfig templateConfig;

    private final HtmlElementGroupBuilder groupBuilder;

    private String html;

    public HtmlBuilder(HtmlPageLogicConfig logicConfig, HtmlPageInitConfig initConfig, CombineManager manager) {
        this.baseUrl = initConfig.baseUrl();
        this.config = logicConfig;
        this.templateConfig = initConfig.templateConfig();
        this.groupBuilder = new HtmlElementGroupBuilder(config.groupIds(), manager);

        Map<String, Object> configMap = parseConfig(initConfig.flagConfig());
        this.flagConfigJson = configMap == null ? null : JsonUtil.obj2String(configMap);
    }

    public boolean build() {
        if (flagConfigJson == null) {
            return false;
        }

        boolean success = this.groupBuilder.build();
        if (!success) {
            return success;
        }

        String head = buildHead();
        String body = buildBody();
        String script = buildScript();
        String elementScript = buildElementScript();
        this.html = buildPage(head, body, script + elementScript);
        return true;
    }

    public String getHtml() {
        return html;
    }

    private String buildHead() {
        HtmlHeaderLinkConfig[] templateLinks = null;
        HtmlHeaderMetaConfig[] templateMetas = null;
        if (templateConfig != null) {
            templateLinks = templateConfig.links();
            templateMetas = templateConfig.metas();
        }
        HtmlHeaderLinkBuilder linkBuilder = new HtmlHeaderLinkBuilder(baseUrl, templateLinks, config.links());
        HtmlHeaderMetaBuilder metaBuilder = new HtmlHeaderMetaBuilder(templateMetas, config.metas());

        // 添加框架核心
        Map<String, String> coreCssProperties = new HashMap<>();
        coreCssProperties.put("rel", "stylesheet");
        coreCssProperties.put("href", baseUrl + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_BASE_CSS_NAME);
        String coreCssTag = HtmlBuildTool.build("link", null, coreCssProperties, true);

        // 添加框架中使用的页面元素JS
        List<String> elementCssTag = new ArrayList<>();
        groupBuilder.getElementMap().values().forEach(m -> {
            Map<String, String> elementCssProperties = new HashMap<>();
            elementCssProperties.put("rel", "stylesheet");
            elementCssProperties.put("href", SystemElementPathTool.buildCssPath(baseUrl, m.cssLibName));
            elementCssTag.add(HtmlBuildTool.build("link", null, elementCssProperties, true));
        });

        String headBody = CheckEmptyUtil.EMPTY;
        headBody += StringUtil.join(metaBuilder.build(), CheckEmptyUtil.EMPTY);
        headBody += coreCssTag;
        headBody += StringUtil.join(linkBuilder.build(), CheckEmptyUtil.EMPTY);
        headBody += StringUtil.join(elementCssTag, CheckEmptyUtil.EMPTY);
        return HtmlBuildTool.build("head", headBody, null, false);
    }

    private String buildPage(String head, String body, String script) {
        Map<String, String> htmlProperties = new HashMap<>();
        String lang = config.lang();
        if (CheckEmptyUtil.isNotEmpty(lang)) {
            htmlProperties.put("lang", lang);
        }

        String htmlCode = HtmlBuildTool.build("html", head + body + script, htmlProperties, false);
        return CharacterUtil.replaceChinese(htmlCode).replaceAll("\\\\{2}", "\\\\\\\\\\\\");
    }

    private String buildBody() {
        List<String> body = new ArrayList<>();

        // 模板
        HtmlElementConfig[] templateModels = templateConfig == null ? null : templateConfig.modules();
        Map<String, HtmlElementConfig> templateModelMap = new HashMap<>();
        if (CheckEmptyUtil.isNotEmpty(templateModels)) {
            templateModelMap = Arrays.stream(templateModels).collect(Collectors.toMap(HtmlElementConfig::key, Function.identity()));
        }

        // 页面模块
        HtmlElementConfig[] models = config.modules();
        if (CheckEmptyUtil.isNotEmpty(models)) {
            for (HtmlElementConfig model : models) {
                String key = model.key();
                if (CheckEmptyUtil.isEmpty(key)) {
                    continue;
                }
                HtmlElementConfig tempDomConfig = templateModelMap.get(key);
                body.add(HtmlBuildTool.build(key, model.config(), tempDomConfig == null ? null : tempDomConfig.config(), false));
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
        String[] scriptArr = templateConfig == null ? null : templateConfig.scripts();
        if (CheckEmptyUtil.isNotEmpty(scriptArr)) {
            for (String scriptSrc : scriptArr) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(scriptSrc, baseUrl)));
            }
        }

        // 添加用户配置脚本
        String[] configScriptArr = config.scripts();
        if (CheckEmptyUtil.isNotEmpty(configScriptArr)) {
            for (String scriptSrc : configScriptArr) {
                scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(scriptSrc, baseUrl)));
            }
        }

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    protected String buildElementScript() {
        List<String> scripts = new ArrayList<>();

        // 添加框架核心
        String baseJsPath = baseUrl + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_BASE_JS_NAME;
        scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(baseJsPath, baseUrl)));
        String baseToolsJsPath = baseUrl + UrlPathCanstant.BASE_PATH + UrlPathCanstant.DEFAULT_TOOLS_JS_NAME;
        scripts.add(ScriptBuildTool.build(UrlPathHelper.replaceUrlFlag(baseToolsJsPath, baseUrl)));

        // 添加框架中使用的页面元素JS
        groupBuilder.getElementMap().values().forEach(m -> {
            scripts.add(ScriptBuildTool.build(SystemElementPathTool.buildJsPath(baseUrl, m.jsLibName)));
        });

        // 添加框架组件实例注册代码
        List<String> scriptCodeList = new ArrayList<>();
        scriptCodeList.add("\n$combine.init(\"" + baseUrl + "\", " + flagConfigJson + ");");

        // 常量注册
        Map<String, Object> constantMap = new HashMap<>();
        if (templateConfig != null && templateConfig.constant() != null) {
            constantMap.putAll(templateConfig.constant());
        }
        if (config.constant() != null) {
            constantMap.putAll(config.constant());
        }
        scriptCodeList.add("\n$combine.constant.register(" + JsonUtil.obj2String(constantMap) + ");");

        // 元素模板注册
        groupBuilder.getTemplateMap().values().forEach(m -> scriptCodeList.add("\n$combine.instanceTemp.register(\"" + m.id + "\"," + m.json + ");"));

        // 数据加载配置注册
        groupBuilder.getDataLoadMap().values().forEach(m -> scriptCodeList.add("\n$combine.loadData.register(\"" + m.id + "\"," + m.json + ", " + JsonUtil.obj2String(groupBuilder.getDataLoadToElementIdMap().get(m.id)) + ");"));

        // trigger事件注册
        if (!groupBuilder.getTriggerMap().isEmpty()) {
            scriptCodeList.add("\n$combine.trigger.setDomId(\"" + TRIGGERS_DOM_ID + "\");");
            groupBuilder.getTriggerMap().values().forEach(m -> scriptCodeList.add("\n$combine.trigger.register(\"" + m.id + "\"," + m.json + ");"));
        }

        // 页面元素注册
        groupBuilder.getElementMap().values().forEach(m -> scriptCodeList.add("\n$combine.instance.register(\"" + m.id + "\"," + m.json + ");"));

        // 页面元素组注册
        groupBuilder.getGroupMap().forEach((k, v) -> scriptCodeList.add("\n$combine.group.register(\"" + k + "\"," + v + ");"));

        // 页面模块初始化
        HtmlElementConfig[] models = config.modules();
        if (CheckEmptyUtil.isNotEmpty(models)) {
            for (HtmlElementConfig model : models) {
                String key = model.key();
                String showGroupId = model.defaultShowGroupId();
                if (CheckEmptyUtil.isNotEmpty(showGroupId)) {
                    String domId = model.config().id();
                    scriptCodeList.add("\n$combine.group.load(\"" + showGroupId + "\",\"" + (CheckEmptyUtil.isEmpty(domId) ? key : domId) + "\");");
                }
            }
        }

        // 构建
        scripts.add(ScriptBuildTool.build(scriptCodeList));

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    private Map<String, Object> parseConfig(Object interfaceObj) {
        if (interfaceObj == null) {
            return null;
        }

        Map<String, Object> config = new HashMap<>();
        Method[] methods = interfaceObj.getClass().getMethods();
        for (Method method : methods) {
            Field field = method.getAnnotation(Field.class);
            if (field == null) {
                continue;
            }

            Object value;
            try {
                value = method.invoke(interfaceObj);
            } catch (IllegalAccessException | InvocationTargetException e) {
                PrintHelper.printComponentError(e);
                return null;
            }

            if (value == null) {
                continue;
            }

            if (field.type() == FieldTypeEnum.CONFIG) {
                value = this.parseConfig(value);
            }

            if (value != null) {
                config.put(method.getName(), value);
            }
        }

        return config;
    }
}
