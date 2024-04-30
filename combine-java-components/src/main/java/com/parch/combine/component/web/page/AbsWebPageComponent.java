package com.parch.combine.component.web.page;

import com.parch.combine.common.util.*;
import com.parch.combine.component.web.helper.DomConfig;
import com.parch.combine.component.web.helper.HtmlBuileHelper;
import com.parch.combine.component.web.helper.ScriptBuildHelper;
import com.parch.combine.core.base.AbsComponent;
import com.parch.combine.component.web.ElementResultHandler;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.core.context.ComponentContextHandler;
import com.parch.combine.core.error.ComponentErrorHandler;
import com.parch.combine.core.vo.DataResult;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 页面组件
 */
public abstract class AbsWebPageComponent<T extends WebPageInitConfig, R extends WebLogicConfig> extends AbsComponent<T, R> {

    /**
     * 模板缓存
     */
    public final static Map<String, WebPageTempConfig> TEMP_MAP = new HashMap<>();

    /**
     * 构造器
     *
     * @param initConfigClass  初始化配置类Class对象
     * @param logicConfigClass 业务配置类Class对象
     */
    public AbsWebPageComponent(Class<T> initConfigClass, Class<R> logicConfigClass) {
        super(initConfigClass, logicConfigClass);
    }


    @Override
    public List<String> init() {
        List<String> result = new ArrayList<>();
        WebPageInitConfig initConfig = getInitConfig();
        WebLogicConfig logicConfig = getLogicConfig();
        this.initConfig();

        // 初始化根路径配置
        if (CheckEmptyUtil.isEmpty(initConfig.getBaseUrl())) {
            initConfig.setBaseUrl(".");
        }

        // 检测meta配置
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getMetas())) {
            for (int i = 0; i < logicConfig.getMetas().size(); i++) {
                WebPageTempConfig.HtmlMetaItem item = logicConfig.getMetas().get(i);
                String baseMsg = "第<" + (i + 1) + ">条-[META]-";
                if (CheckEmptyUtil.isEmpty(item.getName())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "name属性不能为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getContent())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "content属性不能为空"));
                }
            }
        }

        // 检测link配置
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getLinks())) {
            for (int i = 0; i < logicConfig.getLinks().size(); i++) {
                WebPageTempConfig.HtmlLinkItem item = logicConfig.getLinks().get(i);
                String baseMsg = "第<" + (i + 1) + ">条-[LINK]-";
                if (CheckEmptyUtil.isEmpty(item.getRel())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "rel属性不能为空"));
                }
                if (CheckEmptyUtil.isEmpty(item.getHref())) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, baseMsg + "href属性不能为空"));
                }
            }
        }

        // 加载加载首页模板配置
        if (!TEMP_MAP.containsKey(logicConfig.getTempPath())) {
            try {
                String testConfigJson = SystemFileUtil.read(logicConfig.getTempPath());
                if (CheckEmptyUtil.isEmpty(testConfigJson)) {
                    result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "加载模板数据为空: " + logicConfig.getTempPath()));
                } else {
                    WebPageTempConfig tempConfig = JsonUtil.deserialize(testConfigJson, WebPageTempConfig.class);
                    TEMP_MAP.put(logicConfig.getTempPath(), tempConfig);
                }
            } catch (Exception e) {
                result.add(ComponentErrorHandler.buildCheckLogicMsg(logicConfig, "加载首页模板失败: " + e.getMessage()));
            }
        }

        return result;
    }

    public abstract List<String> initConfig();

    /**
     * 构建
     *
     * @param configs 页面元素集合
     * @param errorMsg 异常信息
     * @return 页面代码
     */
    protected String build(List<WebPageTempConfig.HtmlElementConfig<WebLogicConfig.HtmlElement>> configs, List<String> errorMsg) {
        List<ElementResultHandler.ElementResult> elementResults = new ArrayList<>();
        Map<String, String> initElements = new HashMap<String, String>(16);

        // 构建页面头和页面内容
        String head = buildHead();
        String body = buildBody(elementResults, configs, initElements, errorMsg);

        // 构建用户定义脚本
        String customScript = buildCustomScript();

        // 构建元素组件脚本代码
        String elementScript = buildElementScript(elementResults, initElements);

        if (CheckEmptyUtil.isNotEmpty(errorMsg)) {
            return null;
        }

        // 生成页面
        return buildPage(head, body, customScript, elementScript);
    }

    /**
     * 构建页面
     *
     * @param head 头
     * @param body 内容体
     * @param importScript JS导入
     * @param codeScript JS编码
     * @return 页面代码
     */
    private String buildPage(String head, String body, String importScript, String codeScript) {
        WebLogicConfig logicConfig = getLogicConfig();
        WebPageTempConfig tempConfig = TEMP_MAP.get(logicConfig.getTempPath());

        Map<String, String> htmlProperties = new HashMap<>();
        htmlProperties.put("lang", CheckEmptyUtil.isEmpty(logicConfig.getLang()) ? tempConfig.getLang() : logicConfig.getLang());

        String htmlCode = HtmlBuileHelper.build("html", head + body + importScript + codeScript, htmlProperties, false);
        return CharacterUtil.replaceChinese(htmlCode).replaceAll("\\\\{2}", "\\\\\\\\\\\\");
    }

    /**
     * 构建页面头
     *
     * @return 页面头内容
     */
    private String buildHead() {
        WebLogicConfig logicConfig = getLogicConfig();
        WebPageTempConfig tempConfig = TEMP_MAP.get(logicConfig.getTempPath());
        List<String> headBody = new ArrayList<>();

        // 构建metes
        List<WebPageTempConfig.HtmlMetaItem> metes = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getMetas())) {
            metes.addAll(logicConfig.getMetas());
        }
        if (CheckEmptyUtil.isNotEmpty(tempConfig.getMetas())) {
            metes.addAll(tempConfig.getMetas());
        }
        for (WebPageTempConfig.HtmlMetaItem item : metes) {
            Map<String, String> metaProperties = new HashMap<>();
            metaProperties.put("name", item.getName());
            metaProperties.put("content", item.getContent());
            headBody.add(HtmlBuileHelper.build("meta", null, metaProperties, true));
        }

        // 构建links
        List<WebPageTempConfig.HtmlLinkItem> links = new ArrayList<>();
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getLinks())) {
            links.addAll(logicConfig.getLinks());
        }
        if (CheckEmptyUtil.isNotEmpty(tempConfig.getLinks())) {
            links.addAll(tempConfig.getLinks());
        }
        for (WebPageTempConfig.HtmlLinkItem item : links) {
            Map<String, String> linkProperties = new HashMap<>();
            linkProperties.put("rel", item.getRel());
            linkProperties.put("href", item.getHref());
            linkProperties.put("crossorigin", item.getCrossorigin());
            linkProperties.put("integrity", item.getIntegrity());
            linkProperties.put("media", item.getMedia());
            linkProperties.put("preload", item.getPreload());
            linkProperties.put("sizes", item.getSizes());
            headBody.add(HtmlBuileHelper.build("link", null, linkProperties, true));
        }

        return HtmlBuileHelper.build("head", StringUtil.join(headBody, ""), null, false);
    }

    /**
     * 构建页面体
     *
     * @return 页面体内容
     */
    private String buildBody(List<ElementResultHandler.ElementResult> elementResults,
                             List<WebPageTempConfig.HtmlElementConfig<WebLogicConfig.HtmlElement>> configs,
                             Map<String, String> initElement, List<String> errorMsg) {
        WebLogicConfig logicConfig = getLogicConfig();
        List<String> body = new ArrayList<>();

        // 解析出元素组结果
        List<String> groupIds = logicConfig.getElements();
        for (String componentGroupId : groupIds) {
            DataResult componentResult = ComponentContextHandler.getResultData(componentGroupId);
            if (componentResult == null) {
                errorMsg.add("【" + componentGroupId + "】不存在");
                continue;
            }

            List<ElementResultHandler.ElementResult> results = new ArrayList<>();
            buildElementResult(componentResult.getData(), results, body);
            for (ElementResultHandler.ElementResult result : results) {
                result.setGroupId(componentGroupId);
            }
            elementResults.addAll(results);
        }

        // 获取模板中元素配置
        WebPageTempConfig tempConfig = TEMP_MAP.get(logicConfig.getTempPath());
        Map<String, DomConfig> tempDomConfigMap;
        if (tempConfig.getConfigs() != null) {
            tempDomConfigMap = tempConfig.getConfigs().stream().collect(Collectors.toMap(WebPageTempConfig.HtmlElementConfig::getKey, WebPageTempConfig.HtmlElementConfig::getConfig));
        } else {
            tempDomConfigMap = new HashMap<>(0);
        }

        // 构建HTML，并设置初始化元素
        for (WebPageTempConfig.HtmlElementConfig<WebLogicConfig.HtmlElement> config : configs) {
            if (config == null) {
                continue;
            }

            DomConfig tempDomConfig = tempDomConfigMap.get(config.getKey());
            WebLogicConfig.HtmlElement htmlElement = config.getConfig();
            if (tempDomConfig == null && htmlElement == null) {
                continue;
            }

            body.add(HtmlBuileHelper.build(htmlElement, tempDomConfig, false));
            if (htmlElement != null) {
                if (CheckEmptyUtil.isNotEmpty(htmlElement.getShowElement()) && !groupIds.contains(htmlElement.getShowElement())) {
                    errorMsg.add("【" + htmlElement.getShowElement() + "】不存在");
                } else {
                    initElement.put(htmlElement.getId(), htmlElement.getShowElement());
                }
            }
        }

        // 添加弹窗使用的DIV元素
        Map<String, String> properties = new HashMap<>();
        properties.put("id", "$combine-web-triggers");
        body.add(HtmlBuileHelper.build("div", null, properties, false));

        // 构建页面
        return HtmlBuileHelper.build("body", StringUtil.join(body, ""), null, false);
    }

    /**
     * 构建元素结果
     *
     * @param data 数据
     * @param results 结构集
     * @param body 方法体
     */
    @SuppressWarnings("unchecked")
    protected void buildElementResult(Object data, List<ElementResultHandler.ElementResult> results, List<String> body) {
        if (data instanceof Collection) {
            for (Object itemData : (Collection<Object>) data) {
                if (itemData instanceof Map && ElementResultHandler.isElementResult((Map<?,?>) itemData)) {
                    ElementResultHandler.ElementResult result = new ElementResultHandler.ElementResult();
                    ElementResultHandler.convert(result, (Map<?,?>) itemData);
                    results.add(result);
                } else {
                    body.add(JsonUtil.serialize(itemData));
                }
            }
        } else if (data instanceof Map && ElementResultHandler.isElementResult((Map<?,?>) data)) {
            ElementResultHandler.ElementResult result = new ElementResultHandler.ElementResult();
            ElementResultHandler.convert(result, (Map<?,?>) data);
            results.add(result);
        } else {
            body.add(JsonUtil.serialize(data));
        }
    }

    /**
     * 构建script脚本
     *
     * @return script脚本内容
     */
    protected String buildCustomScript() {
        WebPageInitConfig initConfig = getInitConfig();
        WebLogicConfig logicConfig = getLogicConfig();
        WebPageTempConfig tempConfig = TEMP_MAP.get(logicConfig.getTempPath());

        List<String> scripts = new ArrayList<>();

        // 添加模板脚本
        if (CheckEmptyUtil.isNotEmpty(tempConfig.getScripts())) {
            for (String scriptSrc : tempConfig.getScripts()) {
                scripts.add(ScriptBuildHelper.build(scriptSrc));
            }
        }

        // 添加用户配置脚本
        if (CheckEmptyUtil.isNotEmpty(logicConfig.getScripts())) {
            for (String scriptSrc : logicConfig.getScripts()) {
                scripts.add(ScriptBuildHelper.build(this.buildUrl(initConfig.getBaseUrl(), scriptSrc)));
            }
        }

        return StringUtil.join(scripts, "");
    }

    /**
     * 构建脚本编码
     *
     * @param elementResults 元素结果集合
     * @return 脚本编码集合
     */
    protected String buildElementScript(List<ElementResultHandler.ElementResult> elementResults, Map<String, String> initElements) {
        if (CheckEmptyUtil.isEmpty(elementResults)) {
            return CheckEmptyUtil.EMPTY;
        }

        WebPageInitConfig initConfig = getInitConfig();
        List<String> scripts = new ArrayList<>();

        // 添加框架核心功能引用脚本
        scripts.add(ScriptBuildHelper.build("./lib/base.js"));
        for (ElementTypeEnum typeEnum : ElementTypeEnum.values()) {
            scripts.add(ScriptBuildHelper.build("./lib/" + typeEnum.name().toLowerCase() + "_element.js"));
        }

        // 添加框架组件实例注册代码
        List<String> scriptCodeList = new ArrayList<>();
        scriptCodeList.add("\n$combineWebUI.init(\"" + initConfig.getBaseUrl() + "\");");
        for (ElementResultHandler.ElementResult item : elementResults) {
            if (CheckEmptyUtil.isNotEmpty(item.getConfigJson())) {
                scriptCodeList.add("$combineWebUI.instance.register(\"" + item.getConfigJson() + "\",\"" + item.getGroupId() + "\");");
            }
        }
        initElements.forEach((k, v) -> {
            if (v != null && k != null) {
                scriptCodeList.add("$combineWebUI.instance.load(\"" + v + "\",\"" + k + "\");");
            }
        });
        scripts.add(ScriptBuildHelper.build(scriptCodeList));

        return StringUtil.join(scripts, "");
    }

    /**
     * 构建地址
     *
     * @param baseUrl 基础地址
     * @param url 地址
     * @return 结果
     */
    private String buildUrl(String baseUrl, String url) {
        if (CheckEmptyUtil.isEmpty(url)) {
            return url;
        }

        if (url.indexOf("http") == 0) {
            return url;
        } else {
            return baseUrl + url;
        }
    }
}
