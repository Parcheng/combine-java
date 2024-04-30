package com.parch.combine.component.web;

import com.parch.combine.component.web.elements.enums.DataLoadTypeEnum;
import com.parch.combine.component.web.elements.enums.ElementTypeEnum;
import com.parch.combine.component.web.elements.enums.TriggerTypeEnum;
import com.parch.combine.component.web.elements.settings.InputSettings;
import com.parch.combine.component.web.page.WebPageSettingHandler;
import com.parch.combine.core.settings.builder.ComponentSettingBuilder;
import com.parch.combine.core.settings.config.FieldTypeEnum;
import com.parch.combine.core.settings.config.PropertyTypeEnum;

import java.util.Arrays;

/**
 * 设置处理器
 */
public class WebSettingHandler {

    public final static String DOM_KEY = "dom";
    public final static String DOM_NAME = "页面DOM元素配置对象";

    public final static String TRIGGER_KEY = "trigger";
    public final static String TRIGGER_NAME = "事件触发配置";

    public final static String ELEMENT_ENTITY_KEY = "elementEntity";
    public final static String ELEMENT_ENTITY_NAME = "页面元素配置";

    public final static String DATA_LOAD_KEY = "load";
    public final static String DATA_LOAD_NAME = "页面元素数据加载配置";
    
    /**
     * 构建
     *
     * @param builder 构建对象
     */
    public static void buildDomConfig(ComponentSettingBuilder builder) {
        String key = DOM_KEY;
        builder.addCommonObject(key, DOM_NAME, null);

        builder.addProperty(PropertyTypeEnum.COMMON, key + ".id", FieldTypeEnum.TEXT, "DOM的ID",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".name", FieldTypeEnum.TEXT, "DOM的name属性",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".text", FieldTypeEnum.TEXT, "DOM的文本内容",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".classes", FieldTypeEnum.TEXT, "DOM的class属性",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".style", FieldTypeEnum.TEXT, "DOM的style属性",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".properties", FieldTypeEnum.OBJECT, "DOM的其他（或自定义）属性",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.COMMON, key + ".properties", "格式为“属性名:属性值”的键值对");

        builder.addProperty(PropertyTypeEnum.COMMON, key + ".events", FieldTypeEnum.OBJECT, "DOM的事件集合",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".events.type", FieldTypeEnum.TEXT, "事件类型",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".events.functionName", FieldTypeEnum.TEXT, "事件触发调用的函数名称",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".events.functionParams", FieldTypeEnum.TEXT, "函数参数",  false, true);

        builder.addProperty(PropertyTypeEnum.COMMON, key + ".trigger", FieldTypeEnum.OBJECT, "DOM的触发配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".trigger", TRIGGER_KEY, TRIGGER_NAME);
    }

    /**
     * 构建
     *
     * @param builder 构建对象
     */
    public static void buildTrigger(ComponentSettingBuilder builder) {
        String key = TRIGGER_KEY;
        builder.addCommonObject(key, TRIGGER_NAME, null);

        builder.addProperty(PropertyTypeEnum.COMMON, key + ".type", FieldTypeEnum.SELECT, "事件类型",  true, false);
        builder.setPropertyOption(PropertyTypeEnum.COMMON, key + ".type", Arrays.asList(TriggerTypeEnum.values()));
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".event", FieldTypeEnum.TEXT, "触发要监听的事件",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".success", FieldTypeEnum.OBJECT, "触发执行成功后要渲染的元素配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".success", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".fail", FieldTypeEnum.OBJECT, "触发执行失败后要渲染的元素配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".fail", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".error", FieldTypeEnum.OBJECT, "触发执行异常后要渲染的元素配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".error", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);

        String callDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CALL", "调用流程事件");
        builder.addProperty(PropertyTypeEnum.COMMON, callDynamicKey + ".flow", FieldTypeEnum.TEXT, "流程KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callDynamicKey + ".params", FieldTypeEnum.OBJECT, "流程参数",  false, false);

        String callUrlDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CALL_URL", "调用URL事件");
        builder.addProperty(PropertyTypeEnum.COMMON, callUrlDynamicKey + ".url", FieldTypeEnum.TEXT, "URL地址",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callUrlDynamicKey + ".mode", FieldTypeEnum.TEXT, "请求方式 POST | GET",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callUrlDynamicKey + ".headers", FieldTypeEnum.OBJECT, "请求头",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callUrlDynamicKey + ".params", FieldTypeEnum.OBJECT, "请求参数",  false, false);

        String callFuncDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CALL_FUNC", "调用页面元素函数事件");
        builder.addProperty(PropertyTypeEnum.COMMON, callFuncDynamicKey + ".id", FieldTypeEnum.TEXT, "页面元素ID",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callFuncDynamicKey + ".name", FieldTypeEnum.TEXT, "函数名称",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, callFuncDynamicKey + ".params", FieldTypeEnum.OBJECT, "函数参数",  true, false);

        String loadDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "LOAD", "加载页面元素组事件");
        builder.addProperty(PropertyTypeEnum.COMMON, loadDynamicKey + ".groupId", FieldTypeEnum.TEXT, "元素组ID",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, loadDynamicKey + ".parentId", FieldTypeEnum.TEXT, "要写入到的父元素DOM ID",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, loadDynamicKey + ".params", FieldTypeEnum.OBJECT, "加载元素组的数据",  false, false);

        String loadDataDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "LOAD_DATA", "触发数据LOAD事件");
        builder.addProperty(PropertyTypeEnum.COMMON, loadDataDynamicKey + "Ids", FieldTypeEnum.TEXT, "要加载的LOAD ID集合",  true, false);

        String skipDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "SKIP", "页面跳转事件");
        builder.addProperty(PropertyTypeEnum.COMMON, skipDynamicKey + ".url", FieldTypeEnum.TEXT, "要跳转的URL",  true, false);

        String customDynamicKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CUSTOM", "调用自定义函数事件");
        builder.addProperty(PropertyTypeEnum.COMMON, customDynamicKey + ".functionName", FieldTypeEnum.TEXT, "自定义函数名",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, customDynamicKey + ".functionParams", FieldTypeEnum.OBJECT, "自定义函数参数",  false, true);
    }
    
    public static void buildEntityDataLoad(ComponentSettingBuilder builder) {
        String key = DATA_LOAD_KEY;
        builder.addCommonObject(key, DATA_LOAD_NAME, null);

        builder.addProperty(PropertyTypeEnum.COMMON, key + ".id", FieldTypeEnum.TEXT, "加载配置 ID",  false, false, "随机生成");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".type", FieldTypeEnum.SELECT, "加载类型",  true, false);
        builder.setPropertyOption(PropertyTypeEnum.COMMON, key + ".type", Arrays.asList(DataLoadTypeEnum.values()));
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".cache", FieldTypeEnum.TEXT, "缓存时间，-1 表示永久",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".toGlobal", FieldTypeEnum.TEXT, "是否保存到全局",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".data", FieldTypeEnum.TEXT, "加载后的数据二次取值表达式",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".dataMapping", FieldTypeEnum.OBJECT, "数据映射配置集合",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".dataMapping.source", FieldTypeEnum.TEXT, "取值表达式",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".dataMapping.mappings", FieldTypeEnum.OBJECT, "数据映射，格式为“旧值:新值”的键值对集合",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".dataMapping.defaultValue", FieldTypeEnum.TEXT, "未命中任何映射时的默认值",  false, false);

        String flowKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key + "", "type", "FLOW", "加载流程返回数据");
        builder.addProperty(PropertyTypeEnum.COMMON, flowKey + ".flow", FieldTypeEnum.TEXT, "流程KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, flowKey + ".params", FieldTypeEnum.OBJECT, "流程参数",  false, false);

        String apiKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key + "", "type", "API", "加载外部API返回数据");
        builder.addProperty(PropertyTypeEnum.COMMON, apiKey + ".url", FieldTypeEnum.TEXT, "请求地址",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, apiKey + ".mode", FieldTypeEnum.TEXT, "请求方式 GET | POST",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, apiKey + ".params", FieldTypeEnum.OBJECT, "请求参数",  false, false);

        String fileKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key + "", "type", "FILE", "加载文件数据");
        builder.addProperty(PropertyTypeEnum.COMMON, fileKey + ".path", FieldTypeEnum.TEXT, "文件路径",  true, false);

        builder.addDynamicProperty(PropertyTypeEnum.COMMON, key + "", "type", "REF", "引用其他加载配置（只配置ID属性即可）");
    }

    public static void buildEntity(ComponentSettingBuilder builder) {
        String key = ELEMENT_ENTITY_KEY;
        builder.addCommonObject(key, ELEMENT_ENTITY_NAME, null);
        
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".id", FieldTypeEnum.TEXT, "元素ID",  false, false, "随机字符粗");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".type", FieldTypeEnum.SELECT, "元素类型",  true, false);
        builder.setPropertyOption(PropertyTypeEnum.COMMON, key + ".type", Arrays.asList(ElementTypeEnum.values()));
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".tempPath", FieldTypeEnum.TEXT, "模板文件路径",  false, false, "系统内置模板");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".data", FieldTypeEnum.OBJECT, "初始数据",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".defaultData", FieldTypeEnum.OBJECT, "默认数据",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".refresh", FieldTypeEnum.BOOLEAN, "是否支持刷新",  false, false, "true");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".load", FieldTypeEnum.OBJECT, "数据加载配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".load", DATA_LOAD_KEY, DATA_LOAD_NAME);

        
        String externalKey = "external";
        String externalName = "外部DOM配置";
        
        String textKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TEXT", ElementTypeEnum.TEXT.getName());
        buildEntityDomProperty(builder, textKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, textKey + ".line", "行通用DOM配置");
        buildEntityDomProperty(builder, textKey + ".children", "子文本DOM配置");
        buildEntityDomProperty(builder, textKey + ".levels", "每个层级的通用DOM配置集合", true);
        buildEntityDomProperty(builder, textKey + ".lines", "每行的DOM配置集合", true);
        buildEntitySettings(builder, textKey);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.retract", FieldTypeEnum.NUMBER, "缩进数",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.lines", FieldTypeEnum.OBJECT, "行数据配置",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.lines.title", FieldTypeEnum.TEXT, "行标题配置",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.lines.data", FieldTypeEnum.TEXT, "行数据二次取值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.lines.text", FieldTypeEnum.TEXT, "行文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.lines.separator", FieldTypeEnum.TEXT, "行数据为多条文本时的分隔符",  false, false, "<br/>");
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.children", FieldTypeEnum.TEXT, "子文本数据配置",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, textKey + ".settings.defaultText", FieldTypeEnum.TEXT, "所有文本为空时默认显示文本",  false, false);


        String tableKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TABLE", ElementTypeEnum.TABLE.getName());
        buildEntityDomProperty(builder, tableKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, tableKey + ".table", "表格DOM配置");
        buildEntityDomProperty(builder, tableKey + ".head", "表格头DOM配置");
        buildEntityDomProperty(builder, tableKey + ".headRow", "表格头行DOM配置");
        buildEntityDomProperty(builder, tableKey + ".headCol", "表格头单元格DOM配置");
        buildEntityDomProperty(builder, tableKey + ".body", "表格内容DOM配置");
        buildEntityDomProperty(builder, tableKey + ".row", "行DOM配置");
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".row.alternateClass", FieldTypeEnum.TEXT, "交替样式集合",  false, true);
        buildEntityDomProperty(builder, tableKey + ".col", "单元格DOM配置");
        buildEntityDomProperty(builder, tableKey + ".checkbox", "多选DOM配置");
        buildEntitySettings(builder, tableKey);
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.fieldNames", FieldTypeEnum.TEXT, "每列对应的字段名配置集合",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.headNames", FieldTypeEnum.TEXT, "表头文本集合",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.minLength", FieldTypeEnum.NUMBER, "最小行数（数据不够空行补全）",  false, false, "10");
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.hasChecked", FieldTypeEnum.BOOLEAN, "是否允许选择行",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.hasIndex", FieldTypeEnum.BOOLEAN, "是否显示行号",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, tableKey + ".settings.rowOpts", FieldTypeEnum.OBJECT, "操作栏的操作元素配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, tableKey + ".settings.rowOpts", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);


        String buttonKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "BUTTON", ElementTypeEnum.BUTTON.getName());
        buildEntityDomProperty(builder, buttonKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, buttonKey + ".button", "按钮DOM配置");
        buildEntitySettings(builder, buttonKey);
        builder.addProperty(PropertyTypeEnum.COMMON, buttonKey + ".settings.items", FieldTypeEnum.OBJECT, "",  true, true);
        buildEntityButtonItemSettings(builder, buttonKey + ".settings.items");


        String windowKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "WINDOW", ElementTypeEnum.WINDOW.getName());
        buildEntityDomProperty(builder, windowKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, windowKey + ".window", "窗口DOM配置");
        buildEntityDomProperty(builder, windowKey + ".head", "窗口标题DOM配置");
        buildEntityDomProperty(builder, windowKey + ".headTitle", "窗口标题文本DOM配置");
        buildEntityDomProperty(builder, windowKey + ".headClose", "窗口标题关闭标识DOM配置");
        buildEntitySettings(builder, windowKey);
        builder.addProperty(PropertyTypeEnum.COMMON, windowKey + ".settings.title", FieldTypeEnum.TEXT, "标题",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, windowKey + ".settings.content", FieldTypeEnum.OBJECT, "内容配置",  true, false);
        buildEntitySettingsSubElement(builder, windowKey + ".settings.content");


        String paginationKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "PAGINATION", ElementTypeEnum.PAGINATION.getName());
        buildEntityDomProperty(builder, paginationKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, paginationKey + ".pagination", "分页DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".item", "分页项DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemActive", "分页项选中时DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemDisabled", "分页项不可选时DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemContentFirst", "首页项DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemContentEnd", "尾页项DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemContentNum", "选页项DOM配置");
        buildEntityDomProperty(builder, paginationKey + ".itemContentSkip", "跳转指定页DOM配置（暂不支持）");
        buildEntityDomProperty(builder, paginationKey + ".itemContentSkipInput", "跳转指定页输入框DOM配置（暂不支持）");
        buildEntitySettings(builder, paginationKey);
        builder.addProperty(PropertyTypeEnum.COMMON, paginationKey + ".settings.currPage", FieldTypeEnum.TEXT, "当前页",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, paginationKey + ".settings.maxPage", FieldTypeEnum.TEXT, "最大页",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, paginationKey + ".settings.maxLength", FieldTypeEnum.NUMBER, "可选页最大长度",  false, false, "10");
        builder.addProperty(PropertyTypeEnum.COMMON, paginationKey + ".settings.trigger", FieldTypeEnum.OBJECT, "点击选页触发配置",  false, false);
        buildEntityButtonItemSettings(builder, paginationKey + ".settings.trigger");


        String pageTurningKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "PAGE_TURNING", ElementTypeEnum.PAGE_TURNING.getName());
        buildEntityDomProperty(builder, pageTurningKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, pageTurningKey + ".pageTurning", "DOM配置");
        buildEntityDomProperty(builder, pageTurningKey + ".last", "向前翻页元素DOM配置");
        buildEntityDomProperty(builder, pageTurningKey + ".next", "向后翻页元素DOM配置");
        buildEntityDomProperty(builder, pageTurningKey + ".lastContent", "向前翻页文本内容DOM配置");
        buildEntityDomProperty(builder, pageTurningKey + ".nextContent", "向后翻页文本内容DOM配置");
        buildEntitySettings(builder, pageTurningKey);
        builder.addProperty(PropertyTypeEnum.COMMON, pageTurningKey + ".settings.currPage", FieldTypeEnum.TEXT, "当前页",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, pageTurningKey + ".settings.maxPage", FieldTypeEnum.TEXT, "最大页",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, pageTurningKey + ".settings.trigger", FieldTypeEnum.OBJECT, "点击翻页触发配置",  false, false);
        buildEntityButtonItemSettings(builder, pageTurningKey + ".settings.trigger");


        String listKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "LIST", ElementTypeEnum.LIST.getName());
        buildEntityDomProperty(builder, listKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, listKey + ".list", "列表DOM配置");
        buildEntityDomProperty(builder, listKey + ".item", "列表项DOM配置");
        buildEntityDomProperty(builder, listKey + ".defaultText", "列表为空时显示文本的DOM配置");
        buildEntitySettings(builder, listKey);
        builder.addProperty(PropertyTypeEnum.COMMON, listKey + ".settings.defaultText", FieldTypeEnum.TEXT, "列表为空时默认显示文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, listKey + ".settings.content", FieldTypeEnum.OBJECT, "列表项内容配置",  true, false);
        buildEntitySettingsSubElement(builder, listKey + ".settings.content");


        String tagKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TAG", ElementTypeEnum.TAG.getName());
        buildEntityDomProperty(builder, tagKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, tagKey + ".tag", "标签DOM配置");
        buildEntityDomProperty(builder, tagKey + ".close", "标签关闭标识DOM配置");
        buildEntitySettings(builder, tagKey);
        builder.addProperty(PropertyTypeEnum.COMMON, tagKey + ".settings.type", FieldTypeEnum.TEXT, "样式类型（可选值取决于模板配置）",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.COMMON, tagKey + ".settings.type", "系统内置模板支持的类型：normal | success | info | primary | warn | error");
        builder.addProperty(PropertyTypeEnum.COMMON, tagKey + ".settings.text", FieldTypeEnum.TEXT, "文本",  true, false);


        String popKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "POP", ElementTypeEnum.POP.getName());
        buildEntityDomProperty(builder, popKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, popKey + ".pop", "泡泡窗DOM配置");
        buildEntityDomProperty(builder, popKey + ".content", "泡泡窗内容DOM配置");
        buildEntityDomProperty(builder, popKey + ".close", "泡泡窗关闭标识DOM配置");
        buildEntitySettings(builder, popKey);
        builder.addProperty(PropertyTypeEnum.COMMON, popKey + ".settings.type", FieldTypeEnum.TEXT, "样式类型（可选值取决于模板配置）",  true, false);
        builder.addPropertyDesc(PropertyTypeEnum.COMMON, popKey + ".settings.type", "系统内置模板支持的类型：normal | success | info | warn | error");
        builder.addProperty(PropertyTypeEnum.COMMON, popKey + ".settings.text", FieldTypeEnum.TEXT, "内容文本",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, popKey + ".settings.hasClose", FieldTypeEnum.BOOLEAN, "是否支持关闭",  false, false, "false");


        String tabKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TAB", ElementTypeEnum.TAB.getName());
        buildEntityDomProperty(builder, tabKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, tabKey + ".tab", "页签DOM配置");
        buildEntityDomProperty(builder, tabKey + ".item", "页签项DOM配置");
        buildEntityDomProperty(builder, tabKey + ".itemActive", "页签项选中时DOM配置");
        buildEntityDomProperty(builder, tabKey + ".title", "页签项标题DOM配置");
        buildEntityDomProperty(builder, tabKey + ".titleText", "页签项标题文本DOM配置");
        buildEntityDomProperty(builder, tabKey + ".titleClose", "页签项标题关闭标识DOM配置");
        buildEntityDomProperty(builder, tabKey + ".content", "页签内容DOM配置");
        buildEntitySettings(builder, tabKey);
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items", FieldTypeEnum.OBJECT, "页签项配置集合",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.id", FieldTypeEnum.TEXT, "页签ID",  false, false, "随机生成");
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.title", FieldTypeEnum.TEXT, "页签标题",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.show", FieldTypeEnum.BOOLEAN, "页签是否默认显示",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.checked", FieldTypeEnum.BOOLEAN, "页签是否默认选中",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.hasClose", FieldTypeEnum.BOOLEAN, "页签是否允许关闭",  false, false, "true");
        builder.addProperty(PropertyTypeEnum.COMMON, tabKey + ".settings.items.body", FieldTypeEnum.OBJECT, "页签内容配置",  true, false);
        buildEntitySettingsSubElement(builder, tabKey + ".settings.items.body");


        String breadcrumbKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "BREADCRUMB", ElementTypeEnum.BREADCRUMB.getName());
        buildEntityDomProperty(builder, breadcrumbKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, breadcrumbKey + ".breadcrumb", "面包削DOM配置");
        buildEntityDomProperty(builder, breadcrumbKey + ".itemActive", "选中元素项DOM配置");
        buildEntityDomProperty(builder, breadcrumbKey + ".item", "元素项DOM配置");
        buildEntityDomProperty(builder, breadcrumbKey + ".content", "元素项内容DOM配置");


        String contentKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CONTENT", ElementTypeEnum.CONTENT.getName());
        buildEntityDomProperty(builder, contentKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, contentKey + ".left", "内容左元素DOM配置");
        buildEntityDomProperty(builder, contentKey + ".leftImg", "内容左元素中图片DOM配置");
        buildEntityDomProperty(builder, contentKey + ".right", "内容右元素DOM配置");
        buildEntityDomProperty(builder, contentKey + ".rightImg", "内容右边元素中图片DOM配置");
        buildEntityDomProperty(builder, contentKey + ".body", "内容元素DOM配置");
        buildEntityDomProperty(builder, contentKey + ".bodyTitle", "内容标题DOM配置");
        buildEntityDomProperty(builder, contentKey + ".bodyContent", "内容文本DOM配置");
        buildEntityDomProperty(builder, contentKey + ".bodyChildren", "子内容外部DOM配置");
        buildEntitySettings(builder, contentKey);
        builder.addProperty(PropertyTypeEnum.COMMON,contentKey + ".settings.leftImg", FieldTypeEnum.TEXT, "左图片地址",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON,contentKey + ".settings.rightImg", FieldTypeEnum.TEXT, "右图片地址",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON,contentKey + ".settings.title", FieldTypeEnum.TEXT, "标题",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON,contentKey + ".settings.text", FieldTypeEnum.TEXT, "每一行内容文本",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON,contentKey + ".settings.children", FieldTypeEnum.TEXT, "子本文",  false, false);


        String fromKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "FROM", ElementTypeEnum.FROM.getName());
        buildEntityDomProperty(builder, fromKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, fromKey + ".from", "表单DOM配置");
        buildEntityDomProperty(builder, fromKey + ".item", "表单项DOM配置");
        buildEntityDomProperty(builder, fromKey + ".label", "标签DOM配置");
        buildEntityDomProperty(builder, fromKey + ".content", "内容DOM配置");
        buildEntitySettings(builder, fromKey);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.layout", FieldTypeEnum.TEXT, "布局 VERTICAL | INLINE | HORIZONTAL",  false, false, "HORIZONTAL");
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items", FieldTypeEnum.OBJECT, "表单项配置",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items.id", FieldTypeEnum.TEXT, "配置项ID",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items.fieldKey", FieldTypeEnum.TEXT, "表单字段KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items.fieldName", FieldTypeEnum.TEXT, "表单字段名称",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items.text", FieldTypeEnum.TEXT, "（text 和 element 配置任意一项即可）",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, fromKey + ".settings.items.element", FieldTypeEnum.OBJECT, "表单字段元素",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, fromKey + ".settings.items.element", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);


        String navBarKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "NAV_BAR", ElementTypeEnum.NAV_BAR.getName());
        buildEntityDomProperty(builder, navBarKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, navBarKey + ".navBar", "导航条DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".brand", "导航条商标DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".brandContent", "导航条商标内容DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".brandImgContent", "导航条商标图片内容DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".brandTextContent", "导航条商标文本内容DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".body", "导航条内容DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyNav", "导航DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyNavItem", "导航项DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyNavItemText", "导航项文本DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyNavItemTextActive", "导航项文本选中时DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyNavItemChildren", "导航项子项DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyRight", "导航右栏DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyRightItem", "导航右栏项DOM配置");
        buildEntityDomProperty(builder, navBarKey + ".bodyRightItemButton", "导航右栏项中按钮DOM配置");
        buildEntitySettings(builder, navBarKey);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.brandText", FieldTypeEnum.TEXT, "商标文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.brandImg", FieldTypeEnum.TEXT, "商标LOGO图片地址",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultChecked", FieldTypeEnum.NUMBER, "默认选择项索引（从0开始）",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.nav", FieldTypeEnum.OBJECT, "导航项配置",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.nav.text", FieldTypeEnum.TEXT, "导航项文本",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.nav.children", FieldTypeEnum.TEXT, "导航项子项",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.nav.trigger", FieldTypeEnum.OBJECT, "导航项触发配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, navBarKey + ".settings.nav.trigger", TRIGGER_KEY, TRIGGER_NAME);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs", FieldTypeEnum.OBJECT, "导航默认项配置集合",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs.text", FieldTypeEnum.TEXT, "默认项文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs.index", FieldTypeEnum.NUMBER, "默认项位置索引（从0开始）",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs.children", FieldTypeEnum.OBJECT, "默认项子项（子项配置项与“settings.defaultNavs”相同）",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs.trigger", FieldTypeEnum.OBJECT, "默认项触发配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, navBarKey + ".settings.defaultNavs.trigger", TRIGGER_KEY, TRIGGER_NAME);
        builder.addProperty(PropertyTypeEnum.COMMON, navBarKey + ".settings.buttons", FieldTypeEnum.OBJECT, "导航右栏按钮配置集合",  false, true);
        buildEntityButtonItemSettings(builder, navBarKey + ".settings.buttons");


        String treeKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TREE", ElementTypeEnum.TREE.getName());
        buildEntityDomProperty(builder, treeKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, treeKey + ".tree", "树DOM配置");
        buildEntityDomProperty(builder, treeKey + ".item", "树项DOM配置");
        buildEntityDomProperty(builder, treeKey + ".levelItems", "每个层级的树项DOM配置", true);
        buildEntityDomProperty(builder, treeKey + ".itemActive", "树项选中时DOM配置");
        buildEntityDomProperty(builder, treeKey + ".itemText", "树项文本DOM配置");
        buildEntityDomProperty(builder, treeKey + ".itemTree", "树项子树DOM配置");
        buildEntitySettings(builder, treeKey);
        builder.addProperty(PropertyTypeEnum.COMMON, treeKey + ".settings.checkedField", FieldTypeEnum.TEXT, "是否被选择",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, treeKey + ".settings.textField", FieldTypeEnum.TEXT, "文本内容",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, treeKey + ".settings.childrenField", FieldTypeEnum.TEXT, "子树",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, treeKey + ".settings.checkFirst", FieldTypeEnum.BOOLEAN, "是否默认选择第一项",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, treeKey + ".settings.trigger", FieldTypeEnum.OBJECT, "树项触发配置",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, treeKey + ".settings.trigger", TRIGGER_KEY, TRIGGER_NAME);


        String thumbnailKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "THUMBNAIL", ElementTypeEnum.THUMBNAIL.getName());
        buildEntityDomProperty(builder, thumbnailKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, thumbnailKey + ".img", "图片DOM配置");
        buildEntityDomProperty(builder, thumbnailKey + ".content", "内容DOM配置");
        buildEntityDomProperty(builder, thumbnailKey + ".contentTitle", "内容标题DOM配置");
        buildEntityDomProperty(builder, thumbnailKey + ".contentText", "内容段落行DOM配置");
        buildEntityDomProperty(builder, thumbnailKey + ".contentButton", "内容操作按钮栏DOM配置");
        buildEntitySettings(builder, thumbnailKey);
        builder.addProperty(PropertyTypeEnum.COMMON, thumbnailKey + ".settings.path", FieldTypeEnum.TEXT, "图片路径",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, thumbnailKey + ".settings.title", FieldTypeEnum.TEXT, "内容标题",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, thumbnailKey + ".settings.text", FieldTypeEnum.TEXT, "内容的每行文本",  false, true);
        builder.addProperty(PropertyTypeEnum.COMMON, thumbnailKey + ".settings.buttons", FieldTypeEnum.OBJECT, "操作按钮配置集合",  false, true);
        buildEntityButtonItemSettings(builder, thumbnailKey + ".settings.buttons");


        String inputKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "INPUT", ElementTypeEnum.INPUT.getName());
        buildEntityDomProperty(builder, inputKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, inputKey + ".addon", "文本插件DOM配置");
        buildEntityDomProperty(builder, inputKey + ".input", "文本输入框DOM配置");
        buildEntitySettings(builder, inputKey);
        builder.addProperty(PropertyTypeEnum.COMMON, inputKey + ".settings.key", FieldTypeEnum.TEXT, "输入框字段KEY",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, inputKey + ".settings.type", FieldTypeEnum.SELECT, "输入框类型",  true, false);
        builder.setPropertyOption(PropertyTypeEnum.COMMON, inputKey + ".settings.type", Arrays.asList(InputSettings.ControlItemType.values()));
        builder.addProperty(PropertyTypeEnum.COMMON, inputKey + ".settings.value", FieldTypeEnum.TEXT, "输入框默认值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, inputKey + ".settings.afterText", FieldTypeEnum.TEXT, "前置插件文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, inputKey + ".settings.beforeText", FieldTypeEnum.TEXT, "后置插件文本",  false, false);


        String selectKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "SELECT", ElementTypeEnum.SELECT.getName());
        buildEntityDomProperty(builder, selectKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, selectKey + ".select", "下拉框DOM配置");
        buildEntityDomProperty(builder, selectKey + ".selectValue", "下拉框值DOM配置");
        buildEntityDomProperty(builder, selectKey + ".selectOptions", "下拉框选项集合DOM配置");
        buildEntityDomProperty(builder, selectKey + ".selectOptionFlag", "下拉标识DOM配置");
        buildEntityDomProperty(builder, selectKey + ".selectOptionItem", "下拉选项DOM配置");
        buildEntitySettings(builder, selectKey);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.key", FieldTypeEnum.TEXT, "下拉框KEY属性，在获取数据时作为字段名",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.value", FieldTypeEnum.TEXT, "当前选中的值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.defaultText", FieldTypeEnum.BOOLEAN, "默认显示文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.defaultValue", FieldTypeEnum.BOOLEAN, "默认选择的值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.options", FieldTypeEnum.OBJECT, "下拉项配置",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.options.value", FieldTypeEnum.TEXT, "下拉选项值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.options.text", FieldTypeEnum.TEXT, "下拉选项显示文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, selectKey + ".settings.trigger", FieldTypeEnum.OBJECT, "下拉选择触发配置（暂不支持）",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, selectKey + ".settings.trigger", TRIGGER_KEY, TRIGGER_NAME);


        String radioKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "RADIO", ElementTypeEnum.RADIO.getName());
        buildEntityDomProperty(builder, radioKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, radioKey + ".inline", "内联显示时DOM配置");
        buildEntityDomProperty(builder, radioKey + ".multiline", "多行显示时DOM配置");
        buildEntityDomProperty(builder, radioKey + ".disabled", "禁用时DOM配置");
        buildEntityDomProperty(builder, radioKey + ".option", "选项DOM配置");
        buildEntitySettings(builder, radioKey);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.layout", FieldTypeEnum.TEXT, "布局 INLINE | MULTILINE",  false, false, "MULTILINE");
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.key", FieldTypeEnum.TEXT, "单选框的KEY属性，在获取数据时作为字段名",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.value", FieldTypeEnum.TEXT, "当前选中的值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.disabled", FieldTypeEnum.BOOLEAN, "是否禁选",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.options", FieldTypeEnum.OBJECT, "单选项配置",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.options.value", FieldTypeEnum.TEXT, "单选项值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.options.text", FieldTypeEnum.TEXT, "单选项显示文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, radioKey + ".settings.trigger", FieldTypeEnum.OBJECT, "单选框触发配置（暂不支持）",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, radioKey + ".settings.trigger", TRIGGER_KEY, TRIGGER_NAME);


        String checkboxKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "CHECKBOX", ElementTypeEnum.CHECKBOX.getName());
        buildEntityDomProperty(builder, checkboxKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, checkboxKey + ".inline", "内联显示时DOM配置");
        buildEntityDomProperty(builder, checkboxKey + ".multiline", "多行显示时DOM配置");
        buildEntityDomProperty(builder, checkboxKey + ".disabled", "禁用时DOM配置");
        buildEntityDomProperty(builder, checkboxKey + ".option", "选项DOM配置");
        buildEntitySettings(builder, checkboxKey);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.layout", FieldTypeEnum.TEXT, "布局 INLINE | MULTILINE",  false, false, "MULTILINE");
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.key", FieldTypeEnum.TEXT, "多选框的KEY属性，在获取数据时作为字段名",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.value", FieldTypeEnum.TEXT, "当前选中的值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.disabled", FieldTypeEnum.BOOLEAN, "是否禁选",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.options", FieldTypeEnum.OBJECT, "多选项配置",  true, true);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.options.value", FieldTypeEnum.TEXT, "多选项值",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.options.text", FieldTypeEnum.TEXT, "多选项显示文本",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, checkboxKey + ".settings.trigger", FieldTypeEnum.OBJECT, "多选框触发配置（暂不支持）",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, checkboxKey + ".settings.trigger", TRIGGER_KEY, TRIGGER_NAME);


        String textareaKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TEXTAREA", ElementTypeEnum.TEXTAREA.getName());
        buildEntityDomProperty(builder, textareaKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, textareaKey + ".textarea", "多行文本框DOM配置");
        buildEntitySettings(builder, textareaKey);
        builder.addProperty(PropertyTypeEnum.COMMON, textareaKey + ".settings.key", FieldTypeEnum.TEXT, "多行文本KEY属性，在获取数据时作为字段名",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, textareaKey + ".settings.value", FieldTypeEnum.TEXT, "多行文本内容",  true, false);


        String audioKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "AUDIO", ElementTypeEnum.AUDIO.getName());
        buildEntityDomProperty(builder, audioKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, audioKey + ".audio", "音频DOM配置");
        buildEntityDomProperty(builder, audioKey + ".mp3", "mp3格式音频DOM配置");
        buildEntityDomProperty(builder, audioKey + ".ogg", "ogg格式音频DOM配置");
        buildEntityDomProperty(builder, audioKey + ".content", "不兼容展示的内容DOM配置");
        buildEntitySettings(builder, audioKey);
        builder.addProperty(PropertyTypeEnum.COMMON, audioKey + ".settings.src", FieldTypeEnum.OBJECT, "音频来源",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, audioKey + ".settings.text", FieldTypeEnum.OBJECT, "不兼容时显示的文本提示信息",  false, false);


        String videoKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "VIDEO", ElementTypeEnum.VIDEO.getName());
        buildEntityDomProperty(builder, videoKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, videoKey + ".video", "视频DOM配置");
        buildEntityDomProperty(builder, videoKey + ".mp4", "mp4格式视频DOM配置");
        buildEntityDomProperty(builder, videoKey + ".ogg", "ogg格式视频DOM配置");
        buildEntityDomProperty(builder, videoKey + ".webm", "webm格式视频DOM配置");
        buildEntityDomProperty(builder, videoKey + ".content", "不兼容展示的内容DOM配置");
        buildEntitySettings(builder, videoKey);
        builder.addProperty(PropertyTypeEnum.COMMON, videoKey + ".settings.src", FieldTypeEnum.TEXT, "视频地址",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, videoKey + ".settings.text", FieldTypeEnum.TEXT, "不兼容时显示的文本提示信息",  false, false);


        String lineKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "LINE", ElementTypeEnum.LINE.getName());
        buildEntityDomProperty(builder, lineKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, lineKey + ".line", "线DOM配置");
        buildEntitySettings(builder, lineKey);
        builder.addProperty(PropertyTypeEnum.COMMON, lineKey + ".settings.text", FieldTypeEnum.TEXT, "线中间的文本",  false, false);


        String titleKey = builder.addDynamicProperty(PropertyTypeEnum.COMMON, key, "type", "TITLE", ElementTypeEnum.TITLE.getName());
        buildEntityDomProperty(builder, titleKey + "." + externalKey, externalName);
        buildEntityDomProperty(builder, titleKey + ".h1", "级别一标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".h2", "级别二标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".h3", "级别三标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".h4", "级别四标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".h5", "级别五标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".h6", "级别六标题DOM配置");
        buildEntityDomProperty(builder, titleKey + ".top", "上分割线DOM配置");
        buildEntityDomProperty(builder, titleKey + ".bottom", "下分割线DOM配置");
        buildEntitySettings(builder, titleKey);
        builder.addProperty(PropertyTypeEnum.COMMON, titleKey + ".settings.text", FieldTypeEnum.TEXT, "文本",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, titleKey + ".settings.level", FieldTypeEnum.NUMBER, "级别（1-6）",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, titleKey + ".settings.top", FieldTypeEnum.BOOLEAN, "是否需要上面添加空行",  false, false, "false");
        builder.addProperty(PropertyTypeEnum.COMMON, titleKey + ".settings.bottom", FieldTypeEnum.BOOLEAN, "是否需要下分割线",  false, false, "false");
    }

    private static void buildEntitySettings(ComponentSettingBuilder builder, String key) {
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".settings", FieldTypeEnum.OBJECT, "配置信息",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".settings.dataField", FieldTypeEnum.OBJECT, "元素ID",  false, false, "随机字符粗");
    }

    private static void buildEntityDomProperty(ComponentSettingBuilder builder, String key, String name) {
        buildEntityDomProperty(builder, key, name, false);
    }

    private static void buildEntityDomProperty(ComponentSettingBuilder builder, String key, String name, boolean isArray) {
        builder.addProperty(PropertyTypeEnum.COMMON, key, FieldTypeEnum.OBJECT, name,  false, isArray);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key, ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);
    }

    private static void buildEntitySettingsSubElement(ComponentSettingBuilder builder, String key) {
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".text", FieldTypeEnum.TEXT, "文本内容",  false, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".elements", FieldTypeEnum.OBJECT, "元素组配置集合",  false, true);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".elements", ELEMENT_ENTITY_KEY, ELEMENT_ENTITY_NAME);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".elementsId", FieldTypeEnum.TEXT, "引用元素组ID",  false, false);
    }

    private static void buildEntityButtonItemSettings(ComponentSettingBuilder builder, String key) {
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".type", FieldTypeEnum.TEXT, "类型",  false, false);
        builder.addPropertyDesc(PropertyTypeEnum.COMMON, key + ".type", "系统内置模板支持的类型：normal | primary | success | info | warn | error");
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".text", FieldTypeEnum.TEXT, "按钮显示文本",  true, false);
        builder.addProperty(PropertyTypeEnum.COMMON, key + ".trigger", FieldTypeEnum.OBJECT, "按钮触发事件",  false, false);
        builder.addPropertyRef(PropertyTypeEnum.COMMON, key + ".trigger", TRIGGER_KEY, TRIGGER_NAME);
    }
}
