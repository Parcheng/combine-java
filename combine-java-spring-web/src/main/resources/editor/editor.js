// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的

var firstGroup = null;
var groupMap = {};
var componentMap = {};

// 组件图像 右键可以编辑/前移/后移/删除/复制

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
    window:1 
};
var idPrefix = { 
    group: "g_", 
    component: "c_", 
    before: "b_", 
    after: "a_", 
    flow: "f_", 
    componentLogic: "cl_", 
    componentInit: "ci_",
    componentConfig: "$",
    window: "w_" 
}

window.onload = function() {
    initFns.loadData();
    initFns.loadGroup();
    initFns.bindAddItemEvent();
    initFns.initCheckBoard();

    // TODO 引用的处理
    config.flow["flow-001"] = {  id: "flow-001", path: "XXX", components: []};
    buildFns.logicComponentWindow("mysql.execute", null, "flow-001");
    buildFns.initComponentWindow("mysql.execute", null);
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
    bindAddItemEvent: function() {
        var beforeAddDom = document.getElementById("before-add");
        beforeAddDom.onclick = function() {
            var flowId = buildFns.beforeFlow();
            optFns.node.openFlowSettingsWindow(flowId);
        }
    
        var afterAddDom = document.getElementById("after-add");
        afterAddDom.onclick = function() {
            var flowId = buildFns.afterFlow();
            optFns.node.openFlowSettingsWindow(flowId);
        }

        var flowAddDom = document.getElementById("flow-add");
        flowAddDom.onclick = function() {
            var flowId = buildFns.flow();
            optFns.node.openFlowPathWindow(flowId);
        }
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
            if (selectValue == null || selectValue == "init" || selectValue == "block") {
                domTools.switchDisplay(flowSelectDom, false);
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

            var flowSelectOptionDomsConfig = buildDomFns.checkSelect.broadSelect(selectOptionData, lastCheckedValue);
            if (flowSelectOptionDomsConfig.checkedValue) {
                flowSelectDom.value = flowSelectOptionDomsConfig.checkedValue;
            }

            domTools.setAll(flowSelectDom, flowSelectOptionDomsConfig.doms);
            domTools.switchDisplay(flowSelectDom, true);
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
                return;
            }

            if (boardSelectValue == "block") {
                buildFns.logicComponentWindow(componentKey, null, null);
                return;
            }

            var flowSelectValue = flowSelectDom.value;
            if (flowSelectValue) {
                buildFns.logicComponentWindow(componentKey, null, flowSelectValue);
                return;
            }
        }
        
        var closeDom = document.getElementById("check-board-window-close");
        closeDom.onclick = function() {
            var boardDom = document.getElementById("check-board-window");
            domTools.switchDisplay(boardDom, false);
        }
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
    beforeFlow: function() {
        var flowId = idPrefix.before + (idIndex.before++);

        var parentDom = document.getElementById("before");
        var beforeDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [beforeDom]);

        config.before[flowId] = { 
            id: flowId,
            components:[] 
        };
        var settingsDom = buildDomFns.node.flowSettingsItem(flowId);
        domTools.addAll(beforeDom, [settingsDom]);

        return flowId;
    },
    afterFlow: function() {
        var flowId = idPrefix.after + (idIndex.after++);

        var parentDom = document.getElementById("after");
        var beforeDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [beforeDom]);

        config.after[flowId] = { 
            id: flowId,
            components:[] 
        };
        var settingsDom = buildDomFns.node.flowSettingsItem(flowId);
        domTools.addAll(beforeDom, [settingsDom]);

        return flowId;
    },
    flow: function(path) {
        var flowId = idPrefix.flow + path;
        if (config.flow[flowId]) {
            alert(path + "已经存在！");
            return;
        }

        var parentDom = document.getElementById("flow");
        var beforeDom = buildDomFns.node.flow(flowId);
        domTools.addAll(parentDom, [beforeDom]);

        config.flow[flowId] = { 
            id: flowId,
            path: path,
            components: []
        };
        var settingsDom = buildDomFns.node.flowPathItem(flowId, path);
        domTools.addAll(beforeDom, [settingsDom]);

        return flowId;
    },
    flowItem: function(flowId, id, componentId, key) {
        var flowDom = document.getElementById(flowId);
        var flowNodeData = config.flow[flowId];
        if (!flowDom || !flowNodeData) {
            console.log("【" + flowId + "】流程不存在")
            alert("流程不存在");
            return;
        }

        flowNodeData.components.push({id: id, key: key, componentId: componentId});
        var flagDom = buildDomFns.node.flowLineItem();
        var componentLogicDom = buildDomFns.node.componentLogic(id, componentId, key);
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
        var componentLogicDom = buildDomFns.node.componentLogic(id, componentId, key);
        domTools.addAll(blockDom, [flagDom, componentLogicDom]);
    },
    checkBoardWindow: function() {
        // 无init配置不能选择init
        // init 时创建
        // init/block/flow/after/before currKey
        // group -> component currId

        var boardDom = document.getElementById("check-board-window");
        var initFlag = boardDom.getAttribute("init");
        if (initFlag != 1) {
            
        }

        domTools.switchDisplay(boardDom, true);
    },
    checkComponentWindow: function() {
        // 无init配置不能选择init
        // init 时创建
        // init/block/flow/after/before currKey
        // group -> component currId
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
        var id = value.$id = value.$id ? value.$id : (idPrefix.componentInit + (idIndex.componentInit++));

        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.key, component.name, initConfig, value,
            function(data) { 
                console.log(data);
                data.$id = id;
                instance.init[id] = data;
                buildFns.initItem(id, data.id, component.key);
            }
        );
        windowsDom.appendChild(fromWindowDom);
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
        var id = value.$id = value.$id ? value.$id : (idPrefix.componentLogic + (idIndex.componentLogic++));

        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.key, component.name, logicConfig, value,
            function(data) {
                console.log(data);
                data.$id = id;
                instance.logic[id] = data;
                if (flowId) {
                    buildFns.flowItem(flowId, id, data.id, component.key);
                } else {
                    buildFns.blockItem(id, data.id, component.key);
                }
            }
        );
        windowsDom.appendChild(fromWindowDom);
    },
    flowSettingsWindow: function(flowId) {
        // continueFrom logicConfig={}
    },
    flowPathWindow: function(flowId) {
        // continueFrom logicConfig={} input
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
                        optFns.tool.openCheckComponentWindow(currKey);
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
            dom.innerHTML = componentId + "<br>" + key;
            dom.ondblclick = (function(initId) {
                var currInitId = initId;
                return function() {
                    optFns.node.openComponentInitWindow(currInitId);
                }
            })(initId);
            return dom;
        },
        componentLogic: function(logicId, componentId, key) {
            var dom = document.createElement("div");
            dom.name = logicId;
            dom.className = "component-item";
            dom.innerHTML = componentId + "<br>" + key;
            dom.ondblclick = (function(logicId) {
                var currLogicId = logicId;
                return function() {
                    optFns.node.openComponentInitWindow(currLogicId);
                }
            })(logicId);
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
            dom.ondblclick = (function(flowId) {
                var currFlowId = flowId;
                return function() {
                    optFns.node.openFlowSettingsWindow(currFlowId);
                }
            })(flowId);
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
            dom.ondblclick = (function(flowId) {
                var currFlowId = flowId;
                return function() {
                    optFns.node.openFlowPathWindow(currFlowId);
                }
            })(flowId);
            return dom;
        }
    },
    window: {
        // flow path window
        // flow settings window
        // id ref c tip
        // TODO 右键菜单，打开/关闭，编辑/左移/右移/删除
        continueFrom: function(key, title, dataList, value, continueFunc) {
            var windowDom = document.createElement("div");
            windowDom.id = idPrefix.window + (idIndex.window++);
            windowDom.className = "from-window";
            windowDom.setAttribute("key", key);

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
            domTools.addAll(bodyDom, itemDomsConfig.doms);

            var itemsGetValueFn = itemDomsConfig.getValueFn;
            var buttonDom = document.createElement("div");
            buttonDom.className = "continue";
            buttonDom.textContent = "确定";
            buttonDom.onclick = function() {
                var valueData = itemsGetValueFn();
                continueFunc(valueData ? valueData : {});
                titleCloseDom.dispatchEvent(new Event('click'));
            };
            bodyDom.appendChild(document.createElement("hr"));
            bodyDom.appendChild(buttonDom);

            return windowDom;
        }
    },
    checkSelect: {
        broadSelect: function() {
            var options = [];

            var defaultOptionDom = document.createElement("option");
            defaultOptionDom.setAttribute("value", null);
            defaultOptionDom.textContent = "请选择面板";
            options.push(defaultOptionDom);

            var initOptionDom = document.createElement("option");
            initOptionDom.setAttribute("value", "init");
            initOptionDom.textContent = "组件全局配置面板";
            options.push(initOptionDom);

            var blockOptionDom = document.createElement("option");
            blockOptionDom.setAttribute("value", "block");
            blockOptionDom.textContent = "公共组件定义面板";
            options.push(blockOptionDom);

            var beforeOptionDom = document.createElement("option");
            beforeOptionDom.setAttribute("value", "before");
            beforeOptionDom.textContent = "前置流程面板";
            options.push(beforeOptionDom);

            var flowOptionDom = document.createElement("option");
            flowOptionDom.setAttribute("value", "flow");
            flowOptionDom.textContent = "执行流程面板";
            options.pushd(flowOptionDom);

            var afterOptionDom = document.createElement("option");
            afterOptionDom.setAttribute("value", "after");
            afterOptionDom.textContent = "后置流程面板";
            options.push(afterOptionDom);

            return options;
        },
        flowSelect: function(selectOptionData, checkedValue) {
            var options = [];

            var defaultFlowOptionDom = document.createElement("option");
            defaultFlowOptionDom.setAttribute("value", null);
            defaultFlowOptionDom.textContent = "请选择流程";
            options.push(defaultFlowOptionDom);

            var defaultCheckValue;
            for (const key in selectOptionData) {
                if (Object.prototype.hasOwnProperty.call(selectOptionData, key)) {
                    const currOptionData = selectOptionData[key];
                    const currOptionDom = document.createElement("option");
                    currOptionDom.setAttribute("value", currOptionData.id);
                    currOptionDom.textContent = currOptionData.path ? currOptionData.path : currOptionData.id;
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
        }
    },
    settings: {
        items : function(dataList, value) {
            var body = [];
            if (!dataList || dataList.length == 0) {
                return body;
            }

            var getValueConfigs = [];
            for (let i = 0; i < dataList.length; i++) {
                var itemKey = dataList[i].key;
                var itemValue = value ? value[itemKey] : null;
                var itemDomConfig = buildDomFns.settings.item(dataList[i], itemValue);

                body.push(itemDomConfig.dom);
                getValueConfigs.push({
                    key: itemKey,
                    fn: itemDomConfig.getValueFn
                });

                if (i < dataList.length - 1) {
                    body.push(document.createElement("hr"));
                }
            }

            return {
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
            titleKeyDom.setAttribute("key", data.key);
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
            if (!data.isArray || (data.isArray && !Array.isArray(value))) {
                value = [value];
            }
            for (let index = 0; index < value.length; index++) {
                var lineConfig = buildDomFns.settings.line(data, value[index], index);
                controlDom.appendChild(lineConfig.dom);
                getLineValueFns.push(lineConfig.getValueFn);
            }

            return {
                dom: itemDom,
                getValueFn: function() {
                    var currFn;
                    if (getLineValueFns.length == 1) {
                        currFn = getLineValueFns[0];
                        return currFn();
                    } else {
                        var data = [];
                        for (let i = 0; i < getLineValueFns.length; i++) {
                            currFn = getLineValueFns[i];
                            data.push(currFn());
                        }
                        return data;
                    }
                }
            };
        },
        line: function(data, value, index) {
            if (index == 0 && (value == null || value == undefined)) {
                value = data.defaultValue;
            }

            var lineDom = document.createElement("div");
            lineDom.className = "line";

            var controlItemConfig;
            switch (data.type) {
                case "ID":
                case "TEXT":
                case "EXPRESSION":
                    controlItemConfig = buildDomFns.settings.control.text(data, value, index);
                    break;
                case "NUMBER":
                    controlItemConfig = buildDomFns.settings.control.number(data, value, index);
                    break;
                case "BOOLEAN":
                    controlItemConfig = buildDomFns.settings.control.boolean(data, value, index);
                    break;
                case "SELECT":
                    controlItemConfig = buildDomFns.settings.control.select(data, value, index);
                    break;
                case "COMPONENT":
                    controlItemConfig = buildDomFns.settings.control.component(data, value, index);
                    break;
                case "MAP":
                    controlItemConfig = buildDomFns.settings.control.map(data, value, index);
                    break;
                case "OBJECT":
                case "CONFIG":
                    controlItemConfig = buildDomFns.settings.control.object(data, value, index);
                    break;
                case "ANY":
                    controlItemConfig = buildDomFns.settings.control.any(data, value, index);
                    break;
                default:
                    controlItemConfig = buildDomFns.settings.control.none(data);
                    break;
            }

            domTools.addAll(lineDom, controlItemConfig.doms);
            return {
                dom: lineDom,
                getValueFn: controlItemConfig.getValueFn
            };
        },
        flag : {
            all : function(data, index) {
                if (!data.isArray) {
                    return null;
                }

                if (index == 0) {
                    if (data.type == "COMPONENT") {
                        return buildDomFns.settings.flag.addComponent(data, index);
                    } else {
                        return buildDomFns.settings.flag.addLine(data, index);
                    }
                } else {
                    if (data.type != "COMPONENT") {
                        return buildDomFns.settings.flag.delLine();
                    }
                }

                return null;
            },
            addLine: function(data, index) {
                var arrayFlagDom = document.createElement("span");
                arrayFlagDom.className = "flag";
                arrayFlagDom.textContent = "+";
                arrayFlagDom.onclick = (function(flagDom, data) {
                    var thisDom = flagDom;
                    var currData = data;
                    return function() {
                        var parentDom = thisDom.parentNode.parentNode;
                        if (parentDom) {
                            var itemLine = buildDomFns.settings.line(currData, null, index+1);
                            parentDom.appendChild(itemLine);
                        }
                    }
                })(arrayFlagDom, data);
    
                return arrayFlagDom;
            },
            addComponent: function(data, index) {
                var arrayFlagDom = document.createElement("span");
                arrayFlagDom.className = "flag";
                arrayFlagDom.textContent = "+";

                // TODO open window
                arrayFlagDom.onclick = (function(flagDom, data, value) {
                    var thisDom = flagDom;
                    var currData = data;
                    var currValue = value;
                    return function() {
                        var lineDom = thisDom.parentNode;
                        if (lineDom) {
                            var itemComponent = buildDomFns.settings.control.component(currData, currValue, index+1);
                            lineDom.appendChild(itemComponent);
                        }
                    }
                })(arrayFlagDom, data, value);

                return arrayFlagDom;
            },
            delLine: function() {
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
                                parentDom.removeChild(lineDom);
                            }
                        }
                    }
                })(arrayFlagDom);
    
                return arrayFlagDom;
            }
        },
        control: {
            // TODO item存储type，获取数据Func,data.id上游设置
            text: function(data, value, index) {
                if (data.type == "ID") {
                    value = idPrefix.componentConfig + (idIndex.componentConfig++);
                }

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

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [dom, arrayFlagDom],
                    getValueFn: function() {
                        return dom.value;
                    }
                };
            },
            number: function(data, value, index) {
                var dom = document.createElement("input");
                dom.className = "input";
                dom.setAttribute("type", "number");
                if (value != null && value != undefined) {
                    dom.value = value;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [dom, arrayFlagDom],
                    getValueFn: function() {
                        return dom.value;
                    }
                };
            },
            boolean: function(data, value, index) {
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

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [trueDom, trueTextDom, falseDom, falseTextDom, arrayFlagDom],
                    getValueFn: function() {
                        return trueDom.checked == true ? true : (falseDom.checked == true ? false : null);
                    }
                }; 
            },
            select: function(data, value, index) {
                var options = data.options;

                var selectDom = document.createElement("select");
                selectDom.className = "select";

                var optionDom = document.createElement("option");
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

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [selectDom, arrayFlagDom],
                    getValueFn: function() {
                        return selectDom.value;
                    }
                };
            },
            component: function(data, value, index) {
                var body = [];
                if (index > 0) {
                    var nextDom = document.createElement("div");
                    nextDom.className = "line-next-flag";
                    nextDom.textContent = "→";
                    body.push(nextDom);
                }
     
                var componenttDom = document.createElement("div");
                // TODO id
                componenttDom.className = "line-component";
                componenttDom.innerHTML = value.key + "<br>" + value.type;
                body.push(componenttDom);

                body.push(buildDomFns.settings.flag.all(data, index));

                // TODO 点击事件，右键菜单
                return {
                    doms: body,
                    getValueFn: function() {
                        // TODO 获取值
                        return null;
                    }
                };
            },
            map: function(data, value, index) {
                var textareaDom = document.createElement("textarea");
                if (value != null && value != undefined) {
                    textareaDom.value = value;
                }
                return {
                    doms: [textareaDom, arrayFlagDom],
                    getValueFn: function() {
                        return textareaDom.value;
                    }
                };
            },
            object: function(data, value, index) {
                var spanDom = document.createElement("span");
                spanDom.className = "fold";
                spanDom.textContent = "配置子属性";
     
                var subItemsDom = document.createElement("div");
                subItemsDom.className = "sub-items";
                domTools.switchDisplay(subItemsDom);

                var subDomsConfig = buildDomFns.settings.items(data.children, value);
                var subDoms = subDomsConfig.doms;
                var subDomsGetValueFn = subDomsConfig.getValueFn;

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

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [spanDom, arrayFlagDom, subItemsDom],
                    getValueFn: function() {
                        var initState = subItemsDom.getAttribute("init-state");
                        if (initState && initState == "1") {
                            return subDomsGetValueFn();
                        }
                        return null;
                    }
                };
            },
            any: function(data, value, index) {
                var selectDom = document.createElement("select");
                selectDom.className = "select";

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
                        
                var brDom = document.createElement("br")
                var textareaDom = document.createElement("textarea");
                if (value != null && value != undefined) {
                    textareaDom.value = value;
                }

                var arrayFlagDom = buildDomFns.settings.flag.all(data, index);
                return {
                    doms: [selectDom, arrayFlagDom, brDom, textareaDom],
                    getValueFn: function() {
                        return {
                            type: selectDom.value,
                            value: textareaDom.value
                        };
                    }
                };
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

        if (isShow) {
            dom.setAttribute("switch-state", "show");
            dom.setAttribute("style", "");
        } else {
            dom.setAttribute("switch-state", "hide");
            dom.setAttribute("style", "display: none;");
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