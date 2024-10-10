package com.parch.combine.html.base.page.builder;

import com.parch.combine.core.common.settings.annotations.Field;
import com.parch.combine.core.common.settings.config.FieldTypeEnum;
import com.parch.combine.core.common.util.CharacterUtil;
import com.parch.combine.core.common.util.CheckEmptyUtil;
import com.parch.combine.core.common.util.JsonUtil;
import com.parch.combine.core.common.util.ResourceFileUtil;
import com.parch.combine.core.common.util.StringUtil;
import com.parch.combine.core.component.manager.CombineManager;
import com.parch.combine.core.component.tools.PrintHelper;
import com.parch.combine.html.base.page.config.FlagConfig;
import com.parch.combine.html.base.page.config.HtmlConfig;
import com.parch.combine.html.base.page.config.HtmlElementConfig;
import com.parch.combine.html.base.template.core.DomConfig;
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
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HtmlBuilder {

    private final static String TRIGGERS_DOM_ID = "$combine-triggers";

    private final static Map<String, HtmlConfig> TEMP_MAP = new HashMap<>();

    private final String baseUrl;
    private final String flagConfigJson;
    private final HtmlConfig config;
    private HtmlConfig templateConfig;
    private final HtmlElementGroupBuilder groupBuilder;

    private String html;

    public HtmlBuilder(HtmlConfig config, String baseUrl, FlagConfig flagConfig, CombineManager manager) {
        this.baseUrl = baseUrl;
        this.config = config;
        this.groupBuilder = new HtmlElementGroupBuilder(config.groupIds(), manager);

        Map<String, Object> configMap = parseConfig(flagConfig);
        this.flagConfigJson = configMap == null ? null : JsonUtil.serialize(configMap);
    }

    public boolean build() {
        if (flagConfigJson == null) {
            return false;
        }

        boolean success = this.loadTemplate();
        if (!success) {
            return success;
        }

        success = this.groupBuilder.build();
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
        HtmlHeaderLinkBuilder linkBuilder = new HtmlHeaderLinkBuilder(baseUrl, templateConfig.links(), config.links());
        HtmlHeaderMetaBuilder metaBuilder = new HtmlHeaderMetaBuilder(templateConfig.metas(), config.metas());

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
        if (CheckEmptyUtil.isEmpty(lang)) {
            lang = templateConfig.lang();
        }
        htmlProperties.put("lang", lang);

        String htmlCode = HtmlBuildTool.build("static/html", head + body + script, htmlProperties, false);
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
            scripts.add(SystemElementPathTool.buildJsPath(baseUrl, m.jsLibName));
        });

        // 添加框架组件实例注册代码
        List<String> scriptCodeList = new ArrayList<>();
        scriptCodeList.add("\n$combine.init(\"" + baseUrl + "\", " + flagConfigJson + ");");

        // 常量注册 TODO
//        String contentJson = JsonUtil.serialize(CombineManagerHandler.get(context.getScopeKey()).getConstant().get());
//        scriptCodeList.add("\n$combine.constant.register(" + contentJson + ");");

        // 元素模板注册
        groupBuilder.getTemplateMap().values().forEach(m -> scriptCodeList.add("\n$combine.instanceTemp.register(\"" + m.id + "\"," + m.json + ");"));

        // 数据加载配置注册
        groupBuilder.getDataLoadMap().values().forEach(m -> scriptCodeList.add("\n$combine.loadData.register(\"" + m.id + "\"," + m.json + ", " + groupBuilder.getDataLoadToElementIdMap().get(m.id) + ");"));

        // trigger事件注册
        if (!groupBuilder.getTemplateMap().isEmpty()) {
            scriptCodeList.add("\n$combine.trigger.setDomId(\"" + TRIGGERS_DOM_ID + "\");");
            groupBuilder.getTemplateMap().values().forEach(m -> scriptCodeList.add("\n$combine.trigger.register(\"" + m.id + "\"," + m.json + ");"));
        }

        // 页面元素注册
        groupBuilder.getElementMap().values().forEach(m -> scriptCodeList.add("\n$combine.instance.register(\"" + m.id + "\"," + m.json + ");"));

        // 页面元素组注册
        groupBuilder.getGroupMap().forEach((k, v) -> scriptCodeList.add("\n$combine.group.register(\"" + k + "\"," + v + ");"));

        // 页面模块初始化
        HtmlElementConfig[] models = config.modules();
        if (CheckEmptyUtil.isNotEmpty(models)) {
            for (HtmlElementConfig model : models) {
                String showGroupId = model.defaultShowGroupId();
                if (CheckEmptyUtil.isNotEmpty(showGroupId)) {
                    // TODO
                    String domId = null;
                    DomConfig domConfig = model.config();
                    if (domConfig != null) {
                        domId = domConfig.id();
                    }
                    if (CheckEmptyUtil.isEmpty(domId)) {
                        domId = UUID.randomUUID().toString();
                    }
                    scriptCodeList.add("\n$combine.group.load(\"" + showGroupId + "\",\"" + domId + "\");");
                }
            }
        }

        // 构建
        scripts.add(ScriptBuildTool.build(scriptCodeList));

        return StringUtil.join(scripts, CheckEmptyUtil.EMPTY);
    }

    private boolean loadTemplate() {
        String tempPath = config.tempPath();
        if (!TEMP_MAP.containsKey(tempPath)) {
            try {
                String testConfigJson = ResourceFileUtil.read(tempPath);
                if (CheckEmptyUtil.isNotEmpty(testConfigJson)) {
                    templateConfig = JsonUtil.deserialize(testConfigJson, HtmlConfig.class);
                    TEMP_MAP.put(tempPath, templateConfig);
                } else {
                    PrintHelper.printComponentError("【HTML-TEMPLATE】【" + tempPath + "】【加载模板数据为空】");
                }
            } catch (Exception e) {
                PrintHelper.printComponentError("【PAGE-TEMPLATE】【" + tempPath + "】【加载模板失败】");
            }
        } else {
            templateConfig = TEMP_MAP.get(tempPath);
        }

        return templateConfig != null;
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
