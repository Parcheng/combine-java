
var groupMap = {};
var componentMap = {};
var commonRefMap = {}; // 组件KEY:commonKey:[...]

var firstGroup = null;
var copyComponent = {
    init: null,
    logic: null
}

// 4.
// 保存，检查按钮（基于config + instance）
// 保存时，按照组件加载顺序遍历配置并记录，如果先引用后配置，则引用的地方改为配置，配置的地方改为引用
// 未完成配置的红色角标提示（流程，组件，子组件）
// 配置检查按钮，输出未完成项目，检查引用组件是否存在（配置在后面的检查/未配置）

// 5.
// 常量可以改成多条显示（或者KEY方块），点击弹窗KEY,TYPE=ANY-确认
// REF INIT 可以改成下拉选项
// REF 支持多个KEY 及条件

var lastChecked = {
    flow: null,
    before: null,
    after: null
};
var instance = {
    init: {},
    logic: {}
};
var config = { 
    constant: null,
    inits: [], 
    blocks: [], 
    before:{}, 
    after:{}, 
    flow:{}
};
var idIndex = { 
    before:1, 
    after:1, 
    flow:1, 
    componentLogic:1, 
    componentInit:1,
    componentConfig:1,
    window:1,
    subBoard:1
};
var idPrefix = { 
    group: "g_", 
    component: "c_", 
    before: "b_", 
    after: "a_", 
    flow: "f_", 
    componentLogic: "cl_", 
    componentInit: "ci_",
    window: "w_",
    subBoard: "sb_"
}

window.onload = function() {
    initFns.loadData();
    initFns.loadGroup();
    initFns.bindAddFlowEvent();
    initFns.initComponentPop();
    initFns.initCheckBoard();
    initFns.initCheckComponent();
};

const initFns = {
    loadData() {
        requestFns.file("./test.json", 
            function(data) {
                var groupDataArr = JSON.parse(data);
                for (let i = 0; i < groupDataArr.length; i++) {
                    const groupDataItem = groupDataArr[i];
                    var currGroupData = groupMap[groupDataItem.key] = {
                        key: groupDataItem.key,
                        name: groupDataItem.name,
                        components: []
                    }

                    if (i == 0) {
                        firstGroup = currGroupData;
                    }

                    if (groupDataItem.settings && Array.isArray(groupDataItem.settings)) {
                        for (let j = 0; j < groupDataItem.settings.length; j++) {
                            const componentDataItem = groupDataItem.settings[j];
                            
                            // 配置数据调整
                            var dataAmend = function(configArr) {
                                if (!configArr || configArr.length == 0) {
                                    return configArr;
                                }

                                var configMap = {};
                                var newConfigArr = [];
                                for (let k = 0; k < configArr.length; k++) {
                                    const config = configArr[k];
                                    configMap[config.key] = config;

                                    var keyArr = config.key.split(".");
                                    if (keyArr.length == 1) {
                                        newConfigArr.push(config);
                                    } else {
                                        var parentKey = config.key.replace(("." + keyArr[keyArr.length - 1]), "");
                                        var parentConfig = configMap[parentKey];
                                        if (!parentConfig.children) {
                                            parentConfig.children = [];
                                        }
                                        parentConfig.children.push(config);
                                    }
                                }

                                return newConfigArr;
                            }
                            componentDataItem.initConfig = dataAmend(componentDataItem.initConfig);
                            componentDataItem.logicConfig = dataAmend(componentDataItem.logicConfig);

                            componentMap[componentDataItem.key] = componentDataItem;
                            currGroupData.components.push(componentDataItem.key);
                        }
                    }

                    if (groupDataItem.commons && Array.isArray(groupDataItem.commons)) {
                        for (let k = 0; k < groupDataItem.commons.length; k++) {
                            const commonObjectData = groupDataItem.commons[k];
                            commonRefMap[commonObjectData.key] = commonObjectData;
                        }
                    }
                }
                console.log(groupMap);
                console.log(componentMap);
            },
            function(data) {
                alert("加载组件数据失败！")
            }
        );
    },
    loadGroup() {
        buildFns.groups();
        if (firstGroup) {
            optFns.tool.checkGroup(firstGroup.key);
        }
    },
    bindAddFlowEvent: function() {
        var constantValueDom = document.getElementById("constant-value");
        constantValueDom.onchange = function() {
            var currValue = constantValueDom.value;
            try {
                config.constant = JSON.parse(currValue);
            } catch (e) {
                alert("常量配置的JSON格式错误");
                constantValueDom.focus();
            }
        }
        
        var beforeAddDom = document.getElementById("before-add");
        beforeAddDom.onclick = function() {
            buildFns.flowSettingsWindow(null, "before");
        }
    
        var afterAddDom = document.getElementById("after-add");
        afterAddDom.onclick = function() {
            buildFns.flowSettingsWindow(null, "after");
        }

        var flowAddDom = document.getElementById("flow-add");
        flowAddDom.onclick = function() {
            buildFns.flowPathWindow(null);
        }
    },
    initComponentPop: function() {
        var componentPopDom = document.getElementById("component-pop");
        var sourceIdDom = document.getElementById("check-component-pop-source-id");
        var sourceTypeDom = document.getElementById("check-component-pop-source-type");

        var itemDoms = componentPopDom.children;
        for (let i = 0; i < itemDoms.length; i++) {
            initComponentPopItem(itemDoms[i], sourceIdDom, sourceTypeDom, componentPopDom);
        }

        function initComponentPopItem(itemDom, sourceIdDom, sourceTypeDom, componentPopDom) {
            const itemId = itemDom.id;
            itemDom.onclick = function() {
                var sourceId = sourceIdDom.value;
                var sourceType = sourceTypeDom.value;
                if (!sourceId || !sourceType) {
                    console.log("数据异常，组件编辑无法获取到ID");
                    alert("组件数据异常");
                    return;
                }

                var sourceDom = document.getElementById(sourceId);
                if (!sourceDom) {
                    console.log("数据异常，组件图形不存在");
                    alert("组件数据异常，图形不存在");
                    return;
                }

                var isInit = sourceType == "init";
                var isFlow = sourceType == "flow";
                switch(itemId) {
                    case "pop-component-edit":
                        buildFns.editComponentWindow(sourceId, sourceType);
                        break;
                    case "pop-component-copy":
                        var copyValue = isInit ? instance.init[sourceId] : instance.logic[sourceId];
                        if (!copyValue) {
                            console.log("要拷贝的数据不存在【" + sourceId + "】");
                            alert("数据异常");
                            return;
                        }

                        if (isInit) {
                            copyComponent.init = copyValue;
                        } else {
                            copyComponent.logic = copyValue;
                        }
                        break;
                    case "pop-component-paste":
                        var pasteValue = isInit ? copyComponent.init : copyComponent.logic;
                        if (!pasteValue) {
                            console.log("粘贴时，拷贝数据不存在【" + sourceId + "】");
                            alert("数据异常，未拷贝数据");
                            return;
                        }

                        pasteValue.id = pasteValue.id + "copy";
                        buildFns.editComponentWindow(sourceId, sourceType, pasteValue);
                        break;
                    case "pop-component-left":
                        if (isFlow) {
                            const sourceAllDom = sourceDom.parentNode.children;
                            const index = Array.prototype.indexOf.call(sourceAllDom, sourceDom);
                            if (index > 1) {
                                const leftNode = sourceAllDom[index - 2];
                                const lineNode = sourceAllDom[index - 1];
                                sourceDom.after(leftNode);
                                sourceDom.after(lineNode);
                            }
                        }
                        break;
                    case "pop-component-right":
                        if (isFlow) {
                            const sourceAllDom = sourceDom.parentNode.children;
                            const index = Array.prototype.indexOf.call(sourceAllDom, sourceDom);
                            if (index > 1) {
                                const rightNode = sourceAllDom[index + 2];
                                const lineNode = sourceAllDom[index - 1];
                                rightNode.after(sourceDom);
                                rightNode.after(lineNode);
                            }
                        }
                        break;
                    case "pop-component-delete":
                        if (isFlow) {
                            const sourceAllDom = sourceDom.parentNode.children;
                            var index = Array.prototype.indexOf.call(sourceAllDom, sourceDom);
                            if (index > 0) {
                                parentDom.removeChild(sourceAllDom[index - 1]);
                            }
                        }
                        domTools.remove(sourceDom);

                        if (isInit) {
                            delete instance.init[sourceId];
                        } else {
                            delete instance.logic[sourceId];
                        }
                        break;
                    case "pop-component-cancel":
                        domTools.switchDisplay(componentPopDom, false);
                        break;
                }

                domTools.switchDisplay(componentPopDom, false);
            }
        }

        domTools.switchDisplay(componentPopDom, false);
    },
    initCheckBoard: function() {
        var flowConfig = config;
        var lastCheckedData = lastChecked;

        var boardSelectDom = document.getElementById("check-board-select");
        var boardSelectOptionDoms = buildDomFns.checkSelect.broadSelect()
        domTools.addAll(boardSelectDom, boardSelectOptionDoms);

        var flowSelectDom = document.getElementById("check-flow-select");
        boardSelectDom.onchange = function() {
            var selectValue = boardSelectDom.value;
            var flowItemDom = document.getElementById("check-flow-item");
            if (selectValue == "" || selectValue == "init" || selectValue == "block") {
                domTools.switchDisplay(flowItemDom, false);
                return;
            }
            
            var selectOptionData;
            var lastCheckedValue;
            if (selectValue == "before") {
                selectOptionData = flowConfig.before;
                lastCheckedValue = lastCheckedData.before;
            } else if (selectValue == "flow") {
                selectOptionData = flowConfig.flow;
                lastCheckedValue = lastCheckedData.flow;
            } else if (selectValue == "after") {
                selectOptionData = flowConfig.after;
                lastCheckedValue = lastCheckedData.after;
            } else {
                selectOptionData = {};
            }

            var flowSelectOptionDomsConfig = buildDomFns.checkSelect.flowSelect(selectOptionData, selectValue, lastCheckedValue);
            domTools.setAll(flowSelectDom, flowSelectOptionDomsConfig.doms);
            if (flowSelectOptionDomsConfig.checkedValue) {
                flowSelectDom.value = flowSelectOptionDomsConfig.checkedValue;
            }

            domTools.switchDisplay(flowItemDom, true);
        }

        flowSelectDom.onchange = function() {
            var flowSelectValue = flowSelectDom.value;
            if (flowSelectValue) {
                var boardSelectValue = boardSelectDom.value;
                if (boardSelectValue == "before") {
                    lastCheckedData.before = flowSelectValue;
                } else if (boardSelectValue == "flow") {
                    lastCheckedData.flow = flowSelectValue;
                } else if (boardSelectValue == "after") {
                    lastCheckedData.after = flowSelectValue;
                }
            }
        }

        var boardDom = document.getElementById("check-board-window");
        var continueDom = document.getElementById("check-board-continue");
        continueDom.onclick = function() {
            var sourceKeyDom = document.getElementById("check-board-source-key");
            if (!sourceKeyDom || !sourceKeyDom.value) {
                return;
            }

            var componentKey = sourceKeyDom.value
            var boardSelectValue = boardSelectDom.value;
            if (boardSelectValue == "init") {
                buildFns.initComponentWindow(componentKey, null);
            } else if (boardSelectValue == "block") {
                buildFns.logicComponentWindow(componentKey, null, null);
            } else {
                var flowSelectValue = flowSelectDom.value;
                if (flowSelectValue) {
                    buildFns.logicComponentWindow(componentKey, null, flowSelectValue);
                }
            }

            domTools.switchDisplay(boardDom, false);
        }
        
        var closeDom = document.getElementById("check-board-window-close");
        closeDom.onclick = function() {
            domTools.switchDisplay(boardDom, false);
        }
        domTools.switchDisplay(boardDom, false);
    },
    initCheckComponent: function() {
        var groupSelectDom = document.getElementById("check-group-select");
        var groupSelectOptionDoms = buildDomFns.checkSelect.groupSelect(groupMap);
        domTools.addAll(groupSelectDom, groupSelectOptionDoms);

        var componentSelectDom = document.getElementById("check-component-select");
        groupSelectDom.onchange = function() {
            var groupKey = groupSelectDom.value;
            if (!groupKey || groupKey == "") {
                domTools.clearAll(componentSelectDom);
                return;
            }
            var groupData = groupMap[groupKey];
            if (!groupData) {
                domTools.clearAll(componentSelectDom);
                return;
            }
            var componentOptionDoms = buildDomFns.checkSelect.componentSelect(groupData.components, componentMap);
            domTools.setAll(componentSelectDom, componentOptionDoms);
        }

        var windowDom = document.getElementById("check-component-window");
        var continueDom = document.getElementById("check-component-continue");
        continueDom.onclick = function() {
            var sourceKeyDom = document.getElementById("check-component-source-key");
            if (!sourceKeyDom || !sourceKeyDom.value) {
                return;
            }

            var componentKey = componentSelectDom.value;
            if (!componentKey || componentKey == "") {
                alert("请选择组件");
                return;
            }

            buildFns.subComponentWindow(componentKey, sourceKeyDom.value);
            domTools.switchDisplay(windowDom, false);
        }

        var closeDom = document.getElementById("check-component-window-close");
        closeDom.onclick = function() {
            domTools.switchDisplay(windowDom, false);
        }
        domTools.switchDisplay(windowDom, false);
    }
}

const buildFns = {
    groups: function() {
        var groupList = [];
        for (const key in groupMap) {
            if (Object.prototype.hasOwnProperty.call(groupMap, key)) {
                groupList.push(groupMap[key]);
                if (!firstGroup) {
                    firstGroup = groupMap[key];
                }
            }
        }

        var groupDom = document.getElementById("group");
        var doms = buildDomFns.tool.groups(groupList);
        domTools.setAll(groupDom, doms);
    },
    components: function(groupKey) {
        var group = groupMap[groupKey];
        if (group && group.components) {
            var componentKeys = group.components;
            var componentList = [];
            for (let i = 0; i < componentKeys.length; i++) {
                const componentKey = componentKeys[i];
                const component = componentMap[componentKey];
                if (component) {
                    componentList.push(component);
                }
            }
            var componentDom = document.getElementById("component");
            var doms =  buildDomFns.tool.components(componentList);
            domTools.setAll(componentDom, doms);
        }
    },
    beforeFlow: function(flowId) {
        var parentDom = document.getElementById("before");
        var beforeDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [beforeDom]);

        config.before[flowId] = { 
            id: flowId,
            components:[]
        };
        var settingsDom = buildDomFns.node.flowSettingsItem(flowId, "before");
        domTools.addAll(beforeDom, [settingsDom]);

        return flowId;
    },
    afterFlow: function(flowId) {
        var parentDom = document.getElementById("after");
        var beforeDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [beforeDom]);

        config.after[flowId] = { 
            id: flowId,
            components:[] 
        };
        var settingsDom = buildDomFns.node.flowSettingsItem(flowId, "after");
        domTools.addAll(beforeDom, [settingsDom]);

        return flowId;
    },
    flow: function(flowId, domain, functionName) {
        if (config.flow[flowId]) {
            alert(path + "已经存在！");
            return;
        }

        var parentDom = document.getElementById("flow");
        var flowDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [flowDom]);

        config.flow[flowId] = { 
            id: flowId,
            domain: domain,
            function: functionName,
            components: []
        };
        var pathDom = buildDomFns.node.flowPathItem(flowId, domain + "/" + functionName);
        domTools.addAll(flowDom, [pathDom]);

        return flowId;
    },
    flowItem: function(flowId, id, componentId, key) {
        var flowNodeData;
        if (flowId.startsWith(idPrefix.after)) {
            flowNodeData = config.after[flowId];
        } else if (flowId.startsWith(idPrefix.before)) {
            flowNodeData = config.before[flowId];
        } else if (flowId.startsWith(idPrefix.flow)) {
            flowNodeData = config.flow[flowId];
        }

        var flowDom = document.getElementById(flowId);
        if (!flowDom || !flowNodeData) {
            console.log("【" + flowId + "】流程不存在")
            alert("流程不存在");
            return;
        }

        flowNodeData.components.push({id: id, key: key, componentId: componentId});
        var flagDom = buildDomFns.node.flowLineItem();
        var componentLogicDom = buildDomFns.node.componentLogic(id, "flow", componentId, key);
        domTools.addAll(flowDom, [flagDom, componentLogicDom]);
    },
    initItem: function(id, componentId, key) {
        var initDom = document.getElementById("init");
        var componentInitDom = buildDomFns.node.componentInit(id, componentId, key);
        config.inits.push({id: id, key: key, componentId: componentId});
        domTools.addAll(initDom, [componentInitDom]);
    },
    blockItem: function(id, componentId, key) {
        var blockDom = document.getElementById("block");
        config.blocks.push({id: id, key: key, componentId: componentId});
        var componentLogicDom = buildDomFns.node.componentLogic(id, "block", componentId, key);
        domTools.addAll(blockDom, [componentLogicDom]);
    },
    initComponentWindow: function(key, value) {
        var component = componentMap[key];
        if (!component) {
            console.log("【" + key + "】组件不存在");
            alert("组件不存在");
            return;
        }

        var initConfig = component.initConfig;
        if (!initConfig || initConfig.length == 0) {
            alert("该组件无需配置此项");
            return;
        }

        value = value ? value : {};
        value.type = value.type ? value.type : component.key;
        value.$id = value.$id ? value.$id : (idPrefix.componentInit + (idIndex.componentInit++));
        value.id = value.id ? value.id : { id:value.$id, ref: false };

        var id = value.$id;
        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, "init", initConfig, value,
            function(data) { 
                console.log(data);
                data.$id = id;
                instance.init[id] = data;
                buildFns.initItem(id, data.id, component.key);
            }
        );
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    logicComponentWindow: function(key, value, flowId) {
        var component = componentMap[key];
        if (!component) {
            console.log("【" + key + "】组件不存在");
            alert("组件不存在");
            return;
        }

        var logicConfig = component.logicConfig;
        if (!logicConfig || logicConfig.length == 0) {
            console.log("【" + key + "】组件逻辑配置不存在");
            alert("该组件未定义配置项");
            return;
        }

        value = value ? value : {};
        value.type = value.type ? value.type : component.key;
        value.$id = value.$id ? value.$id : (idPrefix.componentLogic + (idIndex.componentLogic++));
        value.id = value.id ? value.id : { id: value.$id, ref: false };

        var id = value.$id
        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, "logic", logicConfig, value,
            function(data) {
                console.log(data);
                data.$id = id;
                instance.logic[id] = data;
                if (flowId) {
                    buildFns.flowItem(flowId, id, data.id.id, component.key);
                } else {
                    buildFns.blockItem(id, data.id, component.key);
                }
            }
        );
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    subComponentWindow: function(componentKey, subBoardId) {
        var component = componentMap[componentKey];
        if (component == null) {
            console.log("数据异常【" + componentKey + "】组件不存在");
            alert("组件不存在");
            return;
        }

        var logicConfig = component.logicConfig;
        if (!logicConfig || logicConfig.length == 0) {
            console.log("【" + key + "】组件逻辑配置不存在");
            alert("该组件未定义配置项");
            return;
        }

        var id = idPrefix.componentLogic + (idIndex.componentLogic++)
        var value = { id: { id: id, ref: false } , type: component.key };

        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, "logic", logicConfig, value,
            function(data) { 
                console.log(data);
                data.$id = id;
                instance.logic[id] = data;

                var subBoardDom = document.getElementById(subBoardId);
                if (subBoardDom && subBoardDom.children.length > 0) {
                    var componentMaxIndex = subBoardDom.children.length - 1;
                    var itemComponentDoms = buildDomFns.settings.control.component(data, componentMaxIndex);
                    var flagDom = subBoardDom.children[componentMaxIndex];
                    if (itemComponentDoms && itemComponentDoms.length > 0) {
                        for (let c = 0; c < itemComponentDoms.length; c++) {
                            subBoardDom.insertBefore(itemComponentDoms[c], flagDom);
                        }
                    }
                }
            }
        );
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    editComponentWindow: function(id, type, value) {
        var isInit = type == "init";
        if (value == null || value == undefined) {
            value = isInit ? instance.init[id] : instance.logic[id];
        }
        if (!value || !value.type) {
            console.log("【" + currId + "】组件数据不存在");
            alert("组件数据不存在");
            return;
        }

        var key = value.type;
        var component = componentMap[key];
        if (!component) {
            console.log("【" + key + "】组件不存在");
            alert("组件不存在");
            return;
        }

        var currConfig = isInit ? component.initConfig : component.logicConfig;
        if (!currConfig || currConfig.length == 0) {
            console.log("【" + key + "】【" + type + "】组件配置不存在");
            alert("组件配置不存在");
            return;
        }

        var configType = isInit ? "init" : "logic";
        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, configType, currConfig, value,
            function(data) {
                console.log(data);
                var currDom = document.getElementById(id);
                if (currDom) {
                    data.id = data.id ? data.id : { id: id, ref: false };
                    currDom.innerHTML = data.id.id + "<br>" + data.type;
                    if (isInit) {
                        instance.init[id] = data;
                    } else {
                        instance.logic[id] = data;
                    }
                }
            }
        );
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    flowSettingsWindow: function(flowId, type) {
        var isBfore = type == "before";
        var title = isBfore ? "前置流程配置" : "后置流程配置"
        var addFlowConfig = [
            { key: "id", name: "ID", type: "TEXT", isRequired: false, isArray: false },
            { key: "order", name: "执行顺序", type: "NUMBER", isRequired: false, isArray: false },
            { key: "failStop", name: "执行失败是否继续向下执行", type: "BOOLEAN", isRequired: false, isArray: false, defaultValue:"true" },
            { key: "includes", name: "包含的流程（支持“*”通配符）", type: "TEXT", isRequired: false, isArray: true, egs: [
                { value: "user/add", desc: "表示域为 user, 函数为 add 的流程" },
                { value: "user/*", desc: "表示域为 user 的所有流程" }
            ]},
            { key: "excludes", name: "排除的流程（支持“*”通配符）", type: "TEXT", isRequired: false, isArray: true, egs: [
                { value: "user/add", desc: "表示域为 user, 函数为 add 的流程" },
                { value: "user/*", desc: "表示域为 user 的所有流程" }
            ]}
        ];

        var createFlowFn, flowConfig;
        if (isBfore) {
            createFlowFn = buildFns.beforeFlow;
            flowConfig = config.before;
        } else {
            createFlowFn = buildFns.afterFlow;
            flowConfig = config.after;
        }

        var value = flowId ? flowConfig[flowId].data : {};
        var fromWindowDom = buildDomFns.window.continueFrom(title, null, addFlowConfig, value,
            function(data) { 
                console.log(data);
                if (!flowId) {
                    flowId = isBfore ? idPrefix.before + (idIndex.before++) : idPrefix.after + (idIndex.after++);
                    createFlowFn(flowId);
                }
                flowConfig[flowId].data = data;
            }
        );

        var windowsDom = document.getElementById("window");
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    flowPathWindow: function(flowId) {
        var addFlowConfig = [
            { key: "domain", name: "域名称", type: "TEXT", isRequired: true, isArray: false },
            { key: "function", name: "函数名称", type: "TEXT", isRequired: true, isArray: false }
        ];
        var value = flowId ? config.flow[flowId] : {};
        var fromWindowDom = buildDomFns.window.continueFrom("执行流程配置", null, addFlowConfig, value,
            function(data) { 
                console.log(data);
                if (flowId) {
                    var flowDom = document.getElementById(flowId);
                    if (flowDom && flowDom.children.length > 0) {
                        var pathDom = flowDom.children[0];
                        pathDom.textContent = data.domain + "/" + data.function;

                        var currFlowConfig = config.flow[flowId];
                        currFlowConfig.domain = data.domain;
                        currFlowConfig.function = data.function;
                    }
                } else {
                    flowId = idPrefix.flow + (idIndex.flow++);
                    buildFns.flow(flowId, data.domain, data.function);
                }
            }
        );

        var windowsDom = document.getElementById("window");
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    }
}

const buildDomFns = {
    tool: {
        groups: function(data) {
            var doms = [];
            for (let i = 0; i < data.length; i++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = idPrefix.group + key;
                itemDom.className = "item";
                itemDom.textContent = itemData.name;
                itemDom.onclick = (function(key) {
                    var currKey = key;
                    return function() {
                        optFns.tool.checkGroup(currKey);
                    }
                })(key);
                doms.push(itemDom);
            }
    
            return doms;
        },
        components: function(data) {
            var doms = [];
            for (let i = 0; i < data.length; i++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = idPrefix.component + key;
                itemDom.className = "item";
                itemDom.textContent = itemData.name;
                itemDom.onclick = (function(key) {
                    var currKey = key;
                    return function() {
                        optFns.tool.checkComponent(currKey);
                    }
                })(key);
                doms.push(itemDom);
            }
    
            return doms;
        },
    },
    node: {
        componentInit: function(initId, componentId, key) {
            var dom = document.createElement("div");
            dom.id = initId;
            dom.className = "component-item";
            dom.innerHTML = componentId.id + "<br>" + key;
            dom.oncontextmenu = function(event) {
                optFns.tool.checkComponentPop(initId, "init", event);
            };
            return dom;
        },
        componentLogic: function(logicId, logicType, componentId, key) {
            var dom = document.createElement("div");
            dom.id = logicId;
            dom.className = "component-item";
            dom.innerHTML = componentId.id + "<br>" + key;
            dom.oncontextmenu = function(event) {
                optFns.tool.checkComponentPop(logicId, logicType, event);
            };
            return dom;
        },
        flow: function(flowId) {
            var dom = document.createElement("div");
            dom.id = flowId;
            dom.className = "flow-item";
            return dom;
        },
        flowSettingsItem: function(flowId, type) {
            var dom = document.createElement("div");
            dom.className = "settings-item";
            dom.textContent = "设置";
            dom.onclick = function() {
                buildFns.flowSettingsWindow(flowId, type);
            };
            return dom;
        },
        flowLineItem: function() {
            var dom = document.createElement("div");
            dom.className = "line-item";
            dom.textContent = "→";
            return dom;
        },
        flowPathItem: function(flowId, flowPath) {
            var dom = document.createElement("div");
            dom.className = "path-item";
            dom.textContent = flowPath;
            dom.onclick = function() {
                buildFns.flowPathWindow(flowId);
            }
            return dom;
        }
    },
    window: {
        continueFrom: function(title, configType, dataList, value, continueFunc) {
            var windowId = idPrefix.window + (idIndex.window++);
            var windowDom = document.createElement("div");
            windowDom.id = windowId;
            windowDom.className = "from-window";

            var titleDom = document.createElement("div");
            titleDom.className = "title";
            titleDom.textContent = title;
            windowDom.appendChild(titleDom);

            var titleCloseDom = document.createElement("span");
            titleCloseDom.className = "close";
            titleCloseDom.textContent = "X";
            titleCloseDom.onclick = function() {
                domTools.remove(windowDom);
            }
            titleDom.appendChild(titleCloseDom);

            var bodyDom = document.createElement("div");
            bodyDom.className = "body";
            windowDom.appendChild(bodyDom);

            var itemDomsConfig = buildDomFns.settings.items(dataList, value);
            if (configType && itemDomsConfig.idDom) {
                bodyDom.appendChild(itemDomsConfig.idDom);
                optFns.tool.bindCheckIdRef(itemDomsConfig, value.type, configType);
            }
            domTools.addAll(bodyDom, itemDomsConfig.doms);
            var itemsGetValueFn = itemDomsConfig.getValueFn;

            var buttonDom = document.createElement("div");
            buttonDom.className = "continue";
            buttonDom.textContent = "确定";
            buttonDom.onclick = function() {
                var valueData = itemsGetValueFn();
                continueFunc(valueData ? valueData : {});
                titleCloseDom.dispatchEvent(new Event("click"));
            };
            bodyDom.appendChild(buttonDom);

            return windowDom;
        }
    },
    checkSelect: {
        broadSelect: function() {
            var options = [];

            var defaultOptionDom = document.createElement("option");
            defaultOptionDom.setAttribute("value", "");
            defaultOptionDom.textContent = "请选择面板";
            options.push(defaultOptionDom);

            var initOptionDom = document.createElement("option");
            initOptionDom.setAttribute("value", "init");
            initOptionDom.textContent = "组件全局配置面板（INIT）";
            options.push(initOptionDom);

            var blockOptionDom = document.createElement("option");
            blockOptionDom.setAttribute("value", "block");
            blockOptionDom.textContent = "公共组件定义面板（BLOCK）";
            options.push(blockOptionDom);

            var beforeOptionDom = document.createElement("option");
            beforeOptionDom.setAttribute("value", "before");
            beforeOptionDom.textContent = "前置流程面板（BEFORE）";
            options.push(beforeOptionDom);

            var flowOptionDom = document.createElement("option");
            flowOptionDom.setAttribute("value", "flow");
            flowOptionDom.textContent = "执行流程面板（FLOW）";
            options.push(flowOptionDom);

            var afterOptionDom = document.createElement("option");
            afterOptionDom.setAttribute("value", "after");
            afterOptionDom.textContent = "后置流程面板（AFTER）";
            options.push(afterOptionDom);

            return options;
        },
        flowSelect: function(selectOptionData, type, checkedValue) {
            var options = [];

            var defaultFlowOptionDom = document.createElement("option");
            defaultFlowOptionDom.setAttribute("value", "");
            defaultFlowOptionDom.textContent = "请选择流程";
            options.push(defaultFlowOptionDom);

            var defaultCheckValue;
            for (const key in selectOptionData) {
                if (Object.prototype.hasOwnProperty.call(selectOptionData, key)) {
                    const currOptionData = selectOptionData[key];
                    const currOptionDom = document.createElement("option");
                    currOptionDom.setAttribute("value", currOptionData.id);
                    if (type == "flow") {
                        currOptionDom.textContent = currOptionData.domain + "/" + currOptionData.function;
                    } else {
                        currOptionDom.textContent = currOptionData.data && currOptionData.data.id ? currOptionData.data.id : currOptionData.id;
                    }
                    options.push(currOptionDom);

                    if (checkedValue && checkedValue == currOptionData.id) {
                        defaultCheckValue = currOptionData.id;
                    }
                }
            }

            return {
                doms: options,
                checkedValue: defaultCheckValue
            }
        },
        groupSelect: function(groupData) {
            var options = [];

            var defaultOptionDom = document.createElement("option");
            defaultOptionDom.setAttribute("value", "");
            defaultOptionDom.textContent = "请选择组件分组";
            options.push(defaultOptionDom);

            for (const key in groupData) {
                if (Object.prototype.hasOwnProperty.call(groupData, key)) {
                    const currOptionData = groupData[key];
                    const currOptionDom = document.createElement("option");
                    currOptionDom.setAttribute("value", currOptionData.key);
                    currOptionDom.textContent = currOptionData.name;
                    options.push(currOptionDom);
                }
            }

            return options;
        },
        componentSelect: function(componentKeys, componentData) {
            var options = [];

            var defaultOptionDom = document.createElement("option");
            defaultOptionDom.setAttribute("value", "");
            defaultOptionDom.textContent = "请选择组件";
            options.push(defaultOptionDom);

            for (let i = 0; i < componentKeys.length; i++) {
                const componentKey = componentKeys[i];
                const currComponentData = componentData[componentKey];
                if (!currComponentData) {
                    continue;
                }
                
                const currOptionDom = document.createElement("option");
                currOptionDom.setAttribute("value", currComponentData.key);
                currOptionDom.textContent = currComponentData.name;
                options.push(currOptionDom);
            }

            return options;
        }
    },
    settings: {
        items : function(dataList, value) {
            var body = [];
            if (!dataList || dataList.length == 0) {
                return body;
            }

            var idDom = null;
            var getValueConfigs = [];
            for (let i = 0; i < dataList.length; i++) {
                var itemKey = dataList[i].key;
                var itemValue = value ? value[itemKey] : null;
                var itemDomConfig = buildDomFns.settings.item(dataList[i], itemValue);

                if (dataList[i].type == "ID") {
                    idDom = itemDomConfig.dom;
                } else {
                    body.push(itemDomConfig.dom);
                }
                
                getValueConfigs.push({
                    key: itemKey,
                    fn: itemDomConfig.getValueFn
                });
            }

            return {
                idDom: idDom,
                doms: body,
                getValueFn: function() {
                    var data = {};
                    for (let i = 0; i < getValueConfigs.length; i++) {
                        const getValueConfig = getValueConfigs[i];
                        data[getValueConfig.key] = getValueConfig.fn();
                    }
                    return data;
                }
            };
        },
        item: function(data, value) {
            var itemDom = document.createElement("div");
            itemDom.name = data.key;
            itemDom.className = "item";

            // -------------------- title --------------------------
            var titleDom = document.createElement("div");
            titleDom.className = "label";
            itemDom.appendChild(titleDom);

            var titleKeyDom = document.createElement("span");
            titleKeyDom.className = "key";
            titleKeyDom.textContent = data.key + "：";
            titleDom.appendChild(titleKeyDom);

            var titleNameDom = document.createElement("span");
            titleNameDom.className = "name";
            titleNameDom.textContent = data.name;
            titleDom.appendChild(titleNameDom);

            var requiredDom = document.createElement("span");
            requiredDom.className = "flag";
            requiredDom.textContent = data.isRequired ? "[必填]" : "[非必填]";
            titleDom.appendChild(requiredDom);

            var titleDescDom = null, titleDescFlagDom = null;
            if (data.desc && data.desc.length > 0) {
                // 详细信息
                titleDescDom = document.createElement("span");
                titleDescDom.className = "desc";

                var descText = "详细信息：";
                if (data.desc.length > 1) {
                    for (let i = 0; i < data.desc.length; i++) {
                        descText += ("<br>" + data.desc[i]);
                    }
                } else {
                    descText += data.desc[0];
                }
                titleDescDom.innerHTML =  descText;
                domTools.switchDisplay(titleDescDom);

                // 详细信息标识
                titleDescFlagDom = document.createElement("span");
                titleDescFlagDom.className = "flag";
                titleDescFlagDom.textContent = "[详细信息]";
                titleDescFlagDom.onclick = function() {
                    domTools.switchDisplay(titleDescDom);
                };
            }
           
            var titleEgDom = null, titleEgFlagDom = null;
            if (data.egs && data.egs.length > 0) {
                // 示例DOM
                titleEgDom = document.createElement("span");
                titleEgDom.className = "desc";

                var egText = "示例：";
                for (let i = 0; i < data.egs.length; i++) {
                    if (data.egs.length > 1) {
                        egText += "<br>";
                    }
                    egText += data.egs[i].value;
                    if (data.egs[i].desc) {
                        egText += ("  【" + data.egs[i].desc + "】");
                    }
                }
                titleEgDom.innerHTML = egText;
                domTools.switchDisplay(titleEgDom);
                
                // 示例标识DOM
                titleEgFlagDom = document.createElement("span");
                titleEgFlagDom.className = "flag";
                titleEgFlagDom.textContent = "[示例]";
                titleEgFlagDom.onclick = function() {
                    domTools.switchDisplay(titleEgDom);
                };
            }
            
            if (titleDescFlagDom) {
                titleDom.appendChild(titleDescFlagDom);
            }
            if (titleEgFlagDom) {
                titleDom.appendChild(titleEgFlagDom);
            }
            if (titleDescDom) {
                titleDom.appendChild(titleDescDom);
            }
            if (titleEgDom) {
                titleDom.appendChild(titleEgDom);
            }

            // -------------------- control --------------------------
            var controlDom = document.createElement("div");
            controlDom.className = "control";
            itemDom.appendChild(controlDom);

            var getLineValueFns = [];
            var isArray = data.isArray;
            if (!isArray || (isArray && !Array.isArray(value))) {
                value = [value];
            }
            if (data.type == "COMPONENT") {
                var componentLineDom = buildDomFns.settings.componentLine(getLineValueFns, data, value);
                controlDom.appendChild(componentLineDom);
            } else {
                for (let index = 0; index < value.length; index++) {
                    var lineDom = buildDomFns.settings.line(getLineValueFns, data, value[index], index);
                    controlDom.appendChild(lineDom);
                }
            }

            itemDom.appendChild(document.createElement("hr"));

            return {
                dom: itemDom,
                getValueFn: function() {
                    var currFn;
                    if (!getLineValueFns || getLineValueFns == 0) {
                        return null;
                    } 
                    
                    var currValue = [];
                    for (let i = 0; i < getLineValueFns.length; i++) {
                        currFn = getLineValueFns[i];
                        currValue.push(currFn());
                    }

                    return isArray ? currValue : currValue[0];
                }
            };
        },
        line: function(getValueFns, data, value, index) {
            if (index == 0 && (value == null || value == undefined)) {
                value = data.defaultValue;
            }

            var lineDom = document.createElement("div");
            lineDom.className = "line";

            var controlItemDoms;
            switch (data.type) {
                case "ID":
                    controlItemDoms = buildDomFns.settings.control.id(getValueFns, data, value, index);
                    break;
                case "TEXT":
                case "EXPRESSION":
                    controlItemDoms = buildDomFns.settings.control.text(getValueFns, data, value, index);
                    break;
                case "NUMBER":
                    controlItemDoms = buildDomFns.settings.control.number(getValueFns, data, value, index);
                    break;
                case "BOOLEAN":
                    controlItemDoms = buildDomFns.settings.control.boolean(getValueFns, data, value, index);
                    break;
                case "SELECT":
                    controlItemDoms = buildDomFns.settings.control.select(getValueFns, data, value, index);
                    break;
                case "MAP":
                    controlItemDoms = buildDomFns.settings.control.map(getValueFns, data, value, index);
                    break;
                case "OBJECT":
                case "CONFIG":
                    controlItemDoms = buildDomFns.settings.control.object(getValueFns, data, value, index);
                    break;
                case "ANY":
                    controlItemDoms = buildDomFns.settings.control.any(getValueFns, data, value, index);
                    break;
                default:
                    controlItemDoms = buildDomFns.settings.control.none(data);
                    break;
            }

            domTools.addAll(lineDom, controlItemDoms);
            return lineDom;
        },
        componentLine: function(getValueFns, data, valueArr) {
            var subBoardId = idPrefix.subBoard + (idIndex.subBoard++);

            var lineDom = document.createElement("div");
            lineDom.id = subBoardId;
            lineDom.className = "line";

            var isArray = data.isArray;
            if (valueArr && valueArr.length > 0) {
                for (let i = 0; i < valueArr.length; i++) {
                    var componentDoms = buildDomFns.settings.control.component(valueArr[i], i);
                    domTools.addAll(lineDom, componentDoms);
                }
            }
            
            var flagDom = buildDomFns.settings.flag.addComponent(subBoardId, data);
            lineDom.appendChild(flagDom);

            // TODO 点击事件，右键菜单
            getValueFns.push(function() {
                var currValue = [];
                var currComponentDoms = lineDom.children;
                if (currComponentDoms || currComponentDoms.length > 0) {
                    for (let c = 0; c < currComponentDoms.length; c++) {
                        const currComponentDom = currComponentDoms[c];
                        if (currComponentDom.className == "line-component") {
                            currValue.push(instance[currComponentDom.id]);
                        }
                    }
                }
                
                if (currValue.length == 0) {
                    return null;
                }
                return isArray ? currValue : currValue[0];
            });
            return lineDom;
        },
        flag : {
            all : function(getValueFns, data, index) {
                if (!data.isArray) {
                    return null;
                }

                if (index == 0) {
                    return buildDomFns.settings.flag.addLine(getValueFns, data, index);
                } else {
                    return buildDomFns.settings.flag.delLine(getValueFns);
                }
            },
            addLine: function(getValueFns, data, index) {
                var arrayFlagDom = document.createElement("span");
                arrayFlagDom.className = "flag";
                arrayFlagDom.textContent = "+";

                arrayFlagDom.onclick = (function(flagDom, data) {
                    var thisDom = flagDom;
                    var currData = data;
                    return function() {
                        var parentDom = thisDom.parentNode.parentNode;
                        if (parentDom) {
                            var itemLine = buildDomFns.settings.line(getValueFns, currData, null, index+1);
                            parentDom.appendChild(itemLine);
                        }
                    }
                })(arrayFlagDom, data);
    
                return arrayFlagDom;
            },
            delLine: function(getValueFns) {
                var arrayFlagDom = document.createElement("span");
                arrayFlagDom.className = "flag";
                arrayFlagDom.textContent = "-";
                arrayFlagDom.onclick = (function(flagDom) {
                    var thisDom = flagDom;
                    return function() {
                        var lineDom = thisDom.parentNode;
                        if (lineDom) {
                            var parentDom = lineDom.parentNode;
                            if (parentDom) {
                                var index = Array.prototype.indexOf.call(parentDom.children, lineDom);
                                getValueFns.splice(index, 1);
                                parentDom.removeChild(lineDom);
                            }
                        }
                    }
                })(arrayFlagDom);
    
                return arrayFlagDom;
            },
            addComponent: function(subBoardId, data) {
                var arrayFlagDom = document.createElement("span");
                arrayFlagDom.className = "flag";
                arrayFlagDom.textContent = "+";

                var isArray = data.isArray;
                arrayFlagDom.onclick = function() {
                    var subBoardDom = document.getElementById(subBoardId);
                    if (!subBoardDom) {
                        console.log("数据异常，【" + subBoardId + "】子画布不存在");
                        return;
                    }

                    if (!isArray && subBoardDom.children.length > 0) {
                        alert("该配置项目只支持添加一个组件，无法继续添加");
                        return;
                    }

                    var sourceKeyDom = document.getElementById("check-component-source-key");
                    sourceKeyDom.value = subBoardId;

                    var windowDom = document.getElementById("check-component-window");
                    domTools.switchDisplay(windowDom, true);
                };

                return arrayFlagDom;
            }
        },
        control: {
            id: function(getValueFns, data, value, index) {
                var inputDom = document.createElement("input");
                inputDom.className = "input";
                inputDom.setAttribute("type", "text");
                
                var selectDom = document.createElement("select");
                domTools.switchDisplay(selectDom, false);

                var checkTextDom = document.createElement("span");
                checkTextDom.className = "id-ref";
                checkTextDom.textContent = "引用"

                var checkDom = document.createElement("input");
                checkDom.setAttribute("type", "checkbox");
                checkDom.onchange = function() {
                    if (checkDom.checked) {
                        domTools.switchDisplay(selectDom, true);
                        domTools.switchDisplay(inputDom, false);
                    } else {
                        domTools.switchDisplay(selectDom, false);
                        domTools.switchDisplay(inputDom, true);
                    }
                }

                if (value != null && value != undefined) {
                    if (value.ref == true) {
                        checkDom.checked = true;
                    } 
                    inputDom.value = value.id;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() { 
                    var isChecked = checkDom.checked ? true : false;
                    return {
                        id: isChecked ? selectDom.value : inputDom.value,
                        ref: isChecked
                    };
                });
                return [inputDom, selectDom, checkDom, checkTextDom, arrayFlagDom];
            },
            text: function(getValueFns, data, value, index) {
                var dom = document.createElement("input");
                dom.className = "input";
                dom.setAttribute("type", "text");
                if (value != null && value != undefined) {
                    dom.value = value;
                }

                if (data.key == "type") {
                    dom.setAttribute("readonly", true);
                    dom.style.pointerEvents = "none";
                    dom.style.backgroundColor = "#f0f0f0";
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() { return dom.value; });
                return [dom, arrayFlagDom];
            },
            number: function(getValueFns, data, value, index) {
                var dom = document.createElement("input");
                dom.className = "input";
                dom.setAttribute("type", "number");
                if (value != null && value != undefined) {
                    dom.value = value;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() { 
                    var currNumber = dom.value;
                    var isNumber = /^[+-]?(\d+\.?\d*|\.\d+)$/.test(currNumber);
                    if (!isNumber) {
                        alert(data.key + "的值不是一个数字");
                        return null;
                    }
                    return currNumber; 
                });
                return [dom, arrayFlagDom];
            },
            boolean: function(getValueFns, data, value, index) {
                var name = data.key + "-" + new Date().getTime()

                var trueDom = document.createElement("input");
                trueDom.name = name;
                trueDom.setAttribute("type", "radio");
                
                var trueTextDom = document.createElement("span");
                trueTextDom.textContent = "True";

                var falseDom = document.createElement("input");
                falseDom.name = name;
                falseDom.setAttribute("type", "radio");

                var falseTextDom = document.createElement("span");
                falseTextDom.textContent = "False";

                if (value != null && value != undefined) {
                    if (value == "true" || value == true) {
                        trueDom.checked  = true;
                    } else {
                        falseDom.checked  = true;
                    }
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() {
                    return trueDom.checked == true ? true : (falseDom.checked == true ? false : null);
                });
                return [trueDom, trueTextDom, falseDom, falseTextDom, arrayFlagDom]; 
            },
            select: function(getValueFns, data, value, index) {
                var options = data.options;

                var selectDom = document.createElement("select");
                selectDom.className = "select";

                var optionDom = document.createElement("option");
                optionDom.setAttribute("value", "");
                optionDom.textContent = "请选择";
                selectDom.appendChild(optionDom);

                if (options && options.length > 0) {
                    for (let i = 0; i < options.length; i++) {
                        var optionDom = document.createElement("option");
                        optionDom.setAttribute("value", options[i].key);
                        optionDom.textContent = options[i].name;
                        selectDom.appendChild(optionDom);
                    }
                }

                if (value != null && value != undefined) {
                    selectDom.value = value;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() { return selectDom.value; });
                return [selectDom, arrayFlagDom];
            },
            component: function(value, index) {
                var body = [];

                if (value && value.id && value.type) {
                    if (index > 0) {
                        var nextDom = document.createElement("div");
                        nextDom.className = "line-next-flag";
                        nextDom.textContent = "→";
                        body.push(nextDom);
                    }

                    var id = value.$id;
                    if (!id) {
                        // 子元素一定是flow，就一定是logic
                        id = idPrefix.componentLogic + (idIndex.componentLogic++);
                        value.$id = id;
                    }

                    var componenttDom = document.createElement("div");
                    componenttDom.id = id;
                    componenttDom.className = "line-component";
                    componenttDom.innerHTML = value.id.id + "<br>" + value.type;
                    body.push(componenttDom);

                    componenttDom.oncontextmenu = function(event) {
                        optFns.tool.checkComponentPop(id, "flow", event);
                    };
                }

                return body;
            },
            map: function(getValueFns, data, value, index) {
                var textareaDom = document.createElement("textarea");
                if (value != null && value != undefined) {
                    textareaDom.value = value;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() { 
                    var currValue = textareaDom.value;
                    if (currValue == "") {
                        return null;
                    }
                    try {
                        return JSON.parse(currValue);
                    } catch (e) {
                        alert(data.key + "的JSON格式错误");
                        return null;
                    }
                });
                return [textareaDom, arrayFlagDom];
            },
            object: function(getValueFns, data, value, index) {
                var spanDom = document.createElement("span");
                spanDom.className = "fold";
                spanDom.textContent = "配置子属性";
     
                var subItemsDom = document.createElement("div");
                subItemsDom.className = "sub-items";
                domTools.switchDisplay(subItemsDom);

                var subDoms;
                var subDomsGetValueFn;
                if (buildDomFns.refCommonKeys && buildDomFns.refCommonKeys.length > 0) {
                    // TODO 暂不知道多 refCommonKey 及条件配置
                    var refCommonKey = buildDomFns.refCommonKeys[0].key;
                    var refCommon = commonRefMap[refCommonKey];
                    var subDomsConfig = buildDomFns.settings.items(refCommon.properties, value);
                    subDoms = subDomsConfig.doms;
                    subDomsGetValueFn = subDomsConfig.getValueFn;
                } else {
                    var subDomsConfig = buildDomFns.settings.items(data.children, value);
                    subDoms = subDomsConfig.doms;
                    subDomsGetValueFn = subDomsConfig.getValueFn;
                }
                
                spanDom.onclick = (function(subItemsDom, subDoms) {
                    var currSubItemsDom = subItemsDom;
                    var currSubDoms = subDoms;
                    return function() {
                        var initState = currSubItemsDom.getAttribute("init-state");
                        if (initState && initState == "1") {
                            domTools.switchDisplay(currSubItemsDom);
                            return;
                        }

                        currSubItemsDom.setAttribute("init-state", "1");
                        currSubItemsDom.appendChild(document.createElement("hr"));

                        domTools.addAll(currSubItemsDom, currSubDoms);
                        domTools.switchDisplay(currSubItemsDom);
                    }
                })(subItemsDom, subDoms); 

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() {
                    var initState = subItemsDom.getAttribute("init-state");
                    if (initState && initState == "1") {
                        return subDomsGetValueFn();
                    }
                    return null;
                });
                return [spanDom, arrayFlagDom, subItemsDom];
            },
            any: function(getValueFns, data, value, index) {
                var selectDom = document.createElement("select");
                selectDom.className = "any-type-select"
                
                var textOptionDom = document.createElement("option");
                textOptionDom.setAttribute("value", "TEXT");
                textOptionDom.textContent = "文本";
                selectDom.appendChild(textOptionDom);

                var numOptionDom = document.createElement("option");
                numOptionDom.setAttribute("value", "NUMBER");
                numOptionDom.textContent = "数字";
                selectDom.appendChild(numOptionDom);

                var boolOptionDom = document.createElement("option");
                boolOptionDom.setAttribute("value", "BOOLEAN");
                boolOptionDom.textContent = "布尔";
                selectDom.appendChild(boolOptionDom);

                var mapOptionDom = document.createElement("option");
                mapOptionDom.setAttribute("value", "MAP");
                mapOptionDom.textContent = "MAP";
                selectDom.appendChild(mapOptionDom);

                value = value ? value : {};
                var currType;
                if (value.value != null && value.value != undefined) {
                    if (typeof value.value === "string") {
                        currType = "TEXT";
                    } else if (typeof value.value === "number") {
                        currType = "NUMBER";
                    } else if (typeof value.value === "boolean") {
                        currType = "BOOLEAN";
                    } else if (typeof value.value === "object") {
                        currType = "MAP";
                    }
                }
                if (currType) {
                    selectDom.value = currType;
                }
                   
                var brDom = document.createElement("br")

                var textDom = document.createElement("input");
                textDom.className = "input";
                textDom.setAttribute("type", "text");
                if (currType && currType != "TEXT") {
                    domTools.switchDisplay(textDom, false);
                }

                var numberDom = document.createElement("input");
                numberDom.className = "input";
                numberDom.setAttribute("type", "number");
                if (currType != "NUMBER") {
                    domTools.switchDisplay(numberDom, false);
                }
                
                var booleanSpan = document.createElement("span");
                var name = data.key + "-" + new Date().getTime()
                var trueDom = document.createElement("input");
                trueDom.name = name;
                trueDom.setAttribute("type", "radio");
                booleanSpan.appendChild(trueDom);
                var trueTextDom = document.createElement("span");
                trueTextDom.textContent = "True";
                booleanSpan.appendChild(trueTextDom);
                var falseDom = document.createElement("input");
                falseDom.name = name;
                falseDom.setAttribute("type", "radio");
                booleanSpan.appendChild(falseDom);
                var falseTextDom = document.createElement("span");
                falseTextDom.textContent = "False";
                booleanSpan.appendChild(falseTextDom);
                if (currType != "BOOLEAN") {
                    domTools.switchDisplay(booleanSpan, false);
                }

                var textareaDom = document.createElement("textarea");
                if (currType != "MAP") {
                    domTools.switchDisplay(textareaDom, false);
                }

                selectDom.onchange = function() {
                    domTools.switchDisplay(textDom, false);
                    domTools.switchDisplay(numberDom, false);
                    domTools.switchDisplay(booleanSpan, false);
                    domTools.switchDisplay(textareaDom, false);
                    switch(selectDom.value) {
                        case "NUMBER":
                            domTools.switchDisplay(numberDom, true);
                            break;
                        case "BOOLEAN":
                            domTools.switchDisplay(booleanSpan, true);
                            break;
                        case "MAP":
                            domTools.switchDisplay(textareaDom, true);
                            break;
                        default:
                            domTools.switchDisplay(textDom, true);
                            break;
                    }
                }
                
                if (currType && value.value != null && value.value != undefined) {
                    switch(currType) {
                        case "TEXT":
                                textDom.value = value.value;
                            break;
                        case "NUMBER":
                            numberDom.value = value.value;
                            break;
                        case "BOOLEAN":
                            if (value === true) {
                                trueDom.checked  = true;
                            } else {
                                falseDom.checked  = true;
                            }
                            break;
                        case "MAP":
                            textareaDom.value = value.value;
                            break;
                    }
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(getValueFns, data, index);

                getValueFns.push(function() {
                    var currType = selectDom.value;
                    if (!currType || currType == "") {
                        return null;
                    }

                    switch(currType) {
                        case "MAP":
                            var mapValue = null;
                            try {
                                if (textareaDom.value != "") {
                                    mapValue = JSON.parse(textareaDom.value)
                                }
                            } catch (e) {
                                alert(data.key + "的对象JSON格式错误");
                            }
                            return { type: currType, value: mapValue };
                        case "BOOLEAN":
                            var booleanValue = null;
                            if (trueDom.checked) {
                                booleanValue = true;
                            } else if (falseDom.checked) {
                                booleanValue = false;
                            }
                            return { type: currType, value: booleanValue };
                        case "NUMBER":
                            var currNumber = numberDom.value;
                            var isNumber = /^[+-]?(\d+\.?\d*|\.\d+)$/.test(currNumber);
                            if (!isNumber) {
                                alert(data.key + "的值不是一个数字");
                                currNumber = null;;
                            }
                            return { type: currType, value: currNumber };
                        case "TEXT":
                            return { type: currType, value: textDom.value };
                        default:
                            return null;
                    }
                });
                return [selectDom, arrayFlagDom, brDom, textDom, numberDom, booleanSpan, textareaDom];
            },
            none: function(data) {
                var spanDom = document.createElement("span");
                spanDom.textContent = "未知类型"
                return {
                    doms: [spanDom],
                    getValueFn: function() {
                        return null;
                    }
                };
            }
        }
    }
};

const optFns = {
    tool: {
        checkGroup: function(key) {
            var currGroupDom = document.getElementById(idPrefix.group + key);
            if (!currGroupDom || currGroupDom.className == "item-checked") {
                return;
            }

            var groupDom = document.getElementById("group");
            for (var i = 0; i < groupDom.children.length; i++) {
                const groupItemDom = groupDom.children[i];
                groupItemDom.className = "item";
            }
            currGroupDom.className = "item-checked";
            buildFns.components(key);
        },
        checkComponent: function(key) {
            var checkKeyDom = document.getElementById("check-board-source-key");
            checkKeyDom.value = key;

            var boardSelectDom = document.getElementById("check-board-select");
            boardSelectDom.dispatchEvent(new Event("change"));

            var boardDom = document.getElementById("check-board-window");
            domTools.switchDisplay(boardDom, true);
            window.scrollTo(0, 0);
        },
        checkSubComponent: function(subBoardId) {
            var checkKeyDom = document.getElementById("check-component-source-key");
            checkKeyDom.value = subBoardId;

            var windowDom = document.getElementById("check-component-window");
            domTools.switchDisplay(windowDom, true);
            window.scrollTo(0, 0);
        },
        checkComponentPop: function(id, type, event) {
            event.preventDefault();
            var componentPopDom = document.getElementById("component-pop");
            componentPopDom.style.top = event.pageY + "px";
            componentPopDom.style.left = event.pageX + "px";

            var sourceIdDom = document.getElementById("check-component-pop-source-id");
            sourceIdDom.value = id;

            var sourceTypeDom = document.getElementById("check-component-pop-source-type");
            sourceTypeDom.value = type;

            var isFlow = type == "flow";
            var leftDom = document.getElementById("pop-component-left");
            domTools.switchDisplay(leftDom, false);
            var rightDom = document.getElementById("pop-component-right");
            domTools.switchDisplay(rightDom, false);
            if (isFlow) {
                var checkDom = document.getElementById(id);
                if (checkDom && checkDom.parentNode.children.length > 1) {
                    const allDoms = checkDom.parentNode.children;
                    var index = Array.prototype.indexOf.call(allDoms, checkDom);
                    var isSubFlow = allDoms[0].className == "line-component";
                    if (index > 2 || (isSubFlow && index >= 0)) {
                        domTools.switchDisplay(leftDom, true);
                    }
                    if (index < allDoms.length - 1) {
                        domTools.switchDisplay(rightDom, true);
                    }
                }
                domTools.switchDisplay(componentPopDom, true);
            }

            var pasteDom = document.getElementById("pop-component-paste");
            var copyValue = type == "init" ? copyComponent.init : copyComponent.logic;
            if (copyValue == null || copyValue == undefined) {
                domTools.switchDisplay(pasteDom, false);
            } else {
                domTools.switchDisplay(pasteDom, true);
            }
            
            domTools.switchDisplay(componentPopDom, true);
        },
        bindCheckIdRef: function(itemDomsConfig, typeValue, configType) {
            var idItemDom = itemDomsConfig.idDom;
            var idLineDoms = idItemDom.children[1].children[0].children;

            var selectDom = idLineDoms[1];
            var checkDom = idLineDoms[2];
            if (configType == "init") {
                // INIT 不支持引用
                domTools.remove(idLineDoms[3]);
                domTools.remove(checkDom);
                domTools.remove(selectDom);
                return;
            }

            var inputDom = idLineDoms[0];
            var itemDoms = itemDomsConfig.doms;

            var orgCheckFunc = checkDom.onchange;
            checkDom.onchange = function() {
                newCheckFunc();
            };
            if (checkDom.checked) {
                newCheckFunc();
            }
            
            function newCheckFunc() {
                var isChecked = checkDom.checked ? true : false;
                for (let i = 0; i < itemDoms.length; i++) {
                    const itemDom = itemDoms[i];
                    if (itemDom.name == "type") {
                        continue;
                    }
                    domTools.switchDisplay(itemDom, !isChecked);
                }
    
                if (isChecked) {
                    var defaultValue;
                    var selectValue = inputDom.value;

                    var options = [];
                    var defaultOptionDom = document.createElement("option");
                    defaultOptionDom.value = "";
                    defaultOptionDom.textContent = "请选择要引用的配置";
                    options.push(defaultOptionDom);
    
                    for (const key in instance.logic) {
                        if (Object.prototype.hasOwnProperty.call(instance.logic, key)) {
                            const logicConfig = instance.logic[key];
                            if (typeValue && logicConfig.type == typeValue && 
                                    logicConfig.id && logicConfig.id.ref != true) {
                                var optionDom = document.createElement("option");
                                var logicConfigId = logicConfig.id.id;
                                optionDom.value = logicConfigId;
                                optionDom.textContent = logicConfigId;
                                options.push(optionDom);

                                if (selectValue == logicConfigId) {
                                    defaultValue = logicConfigId;
                                }
                            }
                        }
                    }

                    domTools.setAll(selectDom, options);
                    if (defaultValue) {
                        selectDom.value = selectValue;
                    }
                }

                orgCheckFunc();
            }
        }
    },
    window: {
        close: function(domId) {
            var dom = document.getElementById(domId);
            if (dom && dom.parentNode) {
                dom.parentNode.removeChild(dom);
            }
        }
    }
}

const domTools = {
    remove: function(dom) {
        if (dom) {
            dom.parentNode.removeChild(dom);
        }
    },
    clearAll: function(parentDom) {
        if (parentDom) {
            parentDom.innerHTML = "";
        }
    },
    setAll: function(parentDom, subDoms) {
        if (parentDom) {
            domTools.clearAll(parentDom);
            domTools.addAll(parentDom, subDoms);
        }
    },
    addAll: function(parentDom, subDoms) {
        if (parentDom && subDoms) {
            for (let i = 0; i < subDoms.length; i++) {
                if (subDoms[i]) {
                    parentDom.appendChild(subDoms[i]);
                }
            }
        }
    },
    switchDisplay: function(dom, isShow) {
        if (isShow == null || isShow == undefined) {
            var switchState = dom.getAttribute("switch-state");
            isShow = switchState && switchState == "hide";
        }

        var noneStyleStr = "display: none;";
        var style = dom.getAttribute("style");
        style = style ? style : "";
        if (isShow) {
            dom.setAttribute("switch-state", "show");
            if (style != "" && style.indexOf(noneStyleStr) !== -1) {
                dom.setAttribute("style", style.replace(noneStyleStr, ""));
            }
        } else {
            dom.setAttribute("switch-state", "hide");
            if (style == "" || style.indexOf(noneStyleStr) === -1) {
                dom.setAttribute("style", noneStyleStr + style);
            }
        }
    },
}

const requestFns = {
    url: function (url, type, fromSubmit, params, headers, successFn, failFn, errorFn) {
        errorFn = errorFn ? errorFn : function () {
            console.log("Request error: ", url, headers, params);
            alert("Request error!");
        };

        url = url.startsWith("http") ? url : (baseUrl + url);
        const xhr = new XMLHttpRequest();
        xhr.open(type.toUpperCase() === 'POST' ? 'POST' : 'GET', url);

        headers = headers ? headers : {};
        const contentType = headers["Content-Type"];
        if (!contentType && !(fromSubmit && fromSubmit === true)) {
            headers["Content-Type"] = "application/json";
        }
        for (const key in headers) {
            if (Object.hasOwnProperty.call(headers, key)) {
                xhr.setRequestHeader(key, headers[key]);
            }
        }

        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                if (successFn) {
                    successFn(xhr.response);
                }
            } else {
                console.log("Request fail:", url, type, params, xhr);
                if (failFn) {
                    failFn({ status: xhr.status, text: xhr.statusText });
                }
            }
        };

        xhr.onerror = function () {
            console.log("Request error: ", url, type, params, xhr);
            if (errorFn) {
                errorFn({ status: xhr.status, text: xhr.statusText });
            } else if (failFn) {
                failFn({ status: xhr.status, text: xhr.statusText });
            }
        };

        let sendData = null;
        if (fromSubmit && fromSubmit === true) {
            sendData = new FormData();
            if (params) {
                for (const key in params) {
                    if (Object.hasOwnProperty.call(params, key)) {
                        let value = params[key];
                        sendData.append(key, value instanceof Object && !(value instanceof File) ? JSON.stringify(value) : value);
                    }
                }
            }
        } else if (params){
            sendData = JSON.stringify(params)
        }

        xhr.send(sendData);
    },
    file: function (path, successFn, failFn) {
        const xhr = new XMLHttpRequest();
        xhr.open("GET", path, false);
        xhr.onload = function () {
            if (xhr.status >= 200 && xhr.status < 300) {
                if (successFn) {
                    successFn(xhr.responseText);
                }
            } else {
                console.log("Request load file fail: ", path, xhr);
                if (failFn) {
                    failFn({ status: xhr.status, text: xhr.statusText });
                }
            }
        };
        xhr.onerror = function () {
            console.log("Request load file error: ", path, xhr);
            if (failFn) {
                failFn({ status: xhr.status, text: xhr.statusText });
            }
        };
        xhr.send();
    }
};