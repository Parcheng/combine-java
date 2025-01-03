var firstGroup = null;
var copyComponent = {
    init: null,
    logic: null
}

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
    constant: {},
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
    before: "b_", 
    after: "a_", 
    flow: "f_", 
    componentLogic: "cl_", 
    componentInit: "ci_",
    window: "w_",
    subBoard: "sb_"
}

window.onload = function() {
    loadFns.loadData();
    initFns.loadGroup();
    initFns.bindAddFlowEvent();
    initFns.initComponentPop();
    initFns.initCheckBoard();
    initFns.initCheckComponent();
    initFns.initOpts();
};

const initFns = {
    loadGroup() {
        componentMenuFns.opt.checkComponent = function(key) {
            var checkKeyDom = document.getElementById("check-board-source-key");
            checkKeyDom.value = key;
        
            var boardSelectDom = document.getElementById("check-board-select");
            boardSelectDom.dispatchEvent(new Event("change"));
        
            var boardDom = document.getElementById("check-board-window");
            domTools.switchDisplay(boardDom, true);
            window.scrollTo(0, 0);
        }

        componentMenuFns.init.groups();
        if (firstGroup) {
            componentMenuFns.opt.checkGroup(firstGroup.key);
        }
    },
    bindAddFlowEvent: function() {
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

        var constantAddDom = document.getElementById("constant-add");
        constantAddDom.onclick = function() {
            buildFns.constantWindow(null);
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
    },
    initOpts: function() {
        var checkResDom = document.getElementById("check-res-list");
        domTools.switchDisplay(checkResDom, false);

        var checkDom = document.getElementById("opt-tool-check");
        checkDom.onclick = function() {
            var checkResult = optFns.tool.checkConfig(config);
            buildFns.checkResList(checkResult.errors);
        }

        var saveDom = document.getElementById("opt-tool-save");
        saveDom.onclick = function() {
            var checkResult = optFns.tool.checkConfig(config);
            buildFns.checkResList(checkResult.errors);
            if (checkResult.errors > 0) {
                alert("配置检查不通过，无法保存！");
                return;
            }

            requestFns.url(baseUrl + "/register", "POST", false, checkResult.data, 
                function(data) {
                    if (data.success == true) {
                        console.log("保存成功", data);
                        alert("保存成功！");
                    } else {
                        console.log("保存失败", data);
                        alert("保存失败！");
                    }
                }, 
                function(error) {
                    console.log("保存失败", error);
                    alert("保存失败！");
                }
            );
        }

        var exportDom = document.getElementById("opt-tool-export");
        exportDom.onclick = function() {
            var checkResult = optFns.tool.checkConfig(config);
            buildFns.checkResList(checkResult.errors);

            const jsonStr = JSON.stringify(checkResult.data, null, 2);
            const blob = new Blob([jsonStr], { type: 'application/json' });
            const downloadUrl = URL.createObjectURL(blob);

            const link = document.createElement('a');
            link.href = downloadUrl;
            link.download = 'flow-config.json';
            
            document.body.appendChild(link);
            link.click();

            document.body.removeChild(link);
            URL.revokeObjectURL(downloadUrl);
        }

        var importDom = document.getElementById("opt-tool-import");
        var importWindowDom = document.getElementById("import-window");
        importDom.onclick = function() {
            domTools.switchDisplay(importWindowDom, true);
            window.scrollTo(0, 0);
        }

        var importContinueDom = document.getElementById("import-window-continue");
        importContinueDom.onclick = function() {
            var jsonDataDom = document.getElementById("import-json-data");

            var data = null;
            try {
                data = JSON.parse(jsonDataDom.value);
            } catch (error) {
                alert("导入数据非标准SJON格式");
                console.error('JSON Parse Error:', error.message);
                return;
            }

            optFns.tool.loadImportData(data);
            domTools.switchDisplay(importWindowDom, false);
        }

        var importCloseDom = document.getElementById("import-window-close");
        importCloseDom.onclick = function() {
            domTools.switchDisplay(importWindowDom, false);
        }
        domTools.switchDisplay(importWindowDom, false);
    }
}

const buildFns = {
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
    initComponentWindow: function(key, value, isAutoContinue) {
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
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, "init", initConfig, value,
            function(data) { 
                console.log(data);
                data.$id = id;
                instance.init[id] = data;
                buildFns.initItem(id, data.id, component.key);
            }, isAutoContinue);

        if (!isAutoContinue) {
            var windowsDom = document.getElementById("window");
            windowsDom.appendChild(fromWindowDom);
            window.scrollTo(0, 0);
        }
    },
    logicComponentWindow: function(key, value, flowId, isAutoContinue) {
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
        var fromWindowDom = buildDomFns.window.continueFrom(component.name, "logic", logicConfig, value,
            function(data) {
                console.log(data);
                data.$id = id;
                instance.logic[id] = data;
                if (flowId) {
                    buildFns.flowItem(flowId, id, data.id, component.key);
                } else {
                    buildFns.blockItem(id, data.id, component.key);
                }
            }, isAutoContinue);

        if (!isAutoContinue) {
            var windowsDom = document.getElementById("window");
            windowsDom.appendChild(fromWindowDom);
            window.scrollTo(0, 0);
        }
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

        var windowsDom = document.getElementById("window");
        windowsDom.appendChild(fromWindowDom);
        window.scrollTo(0, 0);
    },
    flowSettingsWindow: function(flowId, type, value, isAutoContinue) {
        var isBfore = type == "before";
        var title = isBfore ? "前置流程配置" : "后置流程配置"
        var addFlowConfig = [
            { key: "id", name: "ID", type: "TEXT", isRequired: false, isArray: false },
            { key: "order", name: "执行顺序", type: "NUMBER", isRequired: false, isArray: false, defaultValue: 1 },
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

        var newFlowId, value;
        if (flowId) {
            value = flowConfig[flowId].data;
        } else {
            newFlowId = isBfore ? idPrefix.before + (idIndex.before++) : idPrefix.after + (idIndex.after++);
            if (value) {
                value.id = newFlowId;
            } else {
                value = { id: newFlowId };
            }
        }

        var fromWindowDom = buildDomFns.window.continueFrom(title, null, addFlowConfig, value,
            function(data) { 
                console.log(data);
                if (newFlowId) {
                    createFlowFn(newFlowId);
                    flowConfig[newFlowId].data = data;
                } else {
                    flowConfig[flowId].data = data;
                }
            }, isAutoContinue);

        if (!isAutoContinue) {
            var windowsDom = document.getElementById("window");
            windowsDom.appendChild(fromWindowDom);
            window.scrollTo(0, 0);
        }

        return newFlowId ? newFlowId : flowId;
    },
    flowPathWindow: function(flowId, value, isAutoContinue) {
        var addFlowConfig = [
            { key: "domain", name: "域名称", type: "TEXT", isRequired: true, isArray: false },
            { key: "function", name: "函数名称", type: "TEXT", isRequired: true, isArray: false }
        ];

        var hasFlowId = true;
        if (flowId) {
            value = config.flow[flowId];
        } else {
            hasFlowId = false;
            flowId = idPrefix.flow + (idIndex.flow++);
            if (!value) {
                value = {};
            }
        }

        var fromWindowDom = buildDomFns.window.continueFrom("执行流程配置", null, addFlowConfig, value,
            function(data) { 
                console.log(data);
                if (hasFlowId) {
                    var flowDom = document.getElementById(flowId);
                    if (flowDom && flowDom.children.length > 0) {
                        var pathDom = flowDom.children[0];
                        pathDom.textContent = data.domain + "/" + data.function;

                        var currFlowConfig = config.flow[flowId];
                        currFlowConfig.domain = data.domain;
                        currFlowConfig.function = data.function;
                    }
                } else {
                    buildFns.flow(flowId, data.domain, data.function);
                }
            }, isAutoContinue);

        if (!isAutoContinue) {
            var windowsDom = document.getElementById("window");
            windowsDom.appendChild(fromWindowDom);
            window.scrollTo(0, 0);
        }

        return flowId;
    },
    constantWindow: function(value, isAutoContinue) {
        var constantConfig = [
            { key: "key", name: "常量KEY", type: "TEXT", isRequired: true, isArray: false },
            { key: "value", name: "常量值", type: "ANY", isRequired: true, isArray: false }
        ];

        var constantWindowDom = buildDomFns.window.continueFrom("常量配置", null, constantConfig, value,
            function(data) { 
                console.log(data);
                if (data && data.key) {
                    config.constant[data.key] = data;
                    var constantDom = document.getElementById("constant");
                    var constantItemDom = buildDomFns.node.constantItem(data);
                    constantDom.appendChild(constantItemDom);
                }
            }, isAutoContinue);

        if (!isAutoContinue) {
            var windowsDom = document.getElementById("window");
            windowsDom.appendChild(constantWindowDom);
            window.scrollTo(0, 0);
        }
    },
    checkResList: function(errors) {
        var checkResDom = document.getElementById("check-res-list");

        var titleDom = checkResDom.children[0];
        const now = new Date();
        titleDom.textContent = "检查结果（" 
            + now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate() + " "
            + now.getHours() + ":" + now.getMinutes() + ":" + now.getSeconds() + "）：";

        var itemsDom = checkResDom.children[1];
        domTools.clearAll(itemsDom);
        if (!errors || errors.length == 0) {
            const itemDom = buildDomFns.opt.checkListItem("检测完毕，未发现异常！");
            itemsDom.appendChild(itemDom);
        } else {
            for (let i = 0; i < errors.length; i++) {
                const itemDom = buildDomFns.opt.checkListItem(errors[i]);
                itemsDom.appendChild(itemDom);
            }
        }

        domTools.switchDisplay(checkResDom, true);
    }
}

const buildDomFns = {
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
        },
        constantItem: function(value) {
            var key = value.key;

            var dom = document.createElement("div");
            dom.id = key;
            dom.className = "constant-item";
            
            var keyDom = document.createElement("span");
            keyDom.className = "constant-key";
            keyDom.textContent = key;
            dom.appendChild(keyDom);

            var typeDom = document.createElement("span");
            typeDom.className = "constant-type";
            typeDom.textContent = value.value && value.value.type ? value.value.type : "NONE";
            dom.appendChild(typeDom);

            var valueDom = document.createElement("span");
            valueDom.className = "constant-value";
            if (value.value && value.value.value != null && value.value.value != undefined) {
                valueDom.textContent = typeof value.value.value === "string" ? value.value.value : JSON.stringify(value.value.value);
            }
            dom.appendChild(valueDom);

            var optDom = document.createElement("span");
            optDom.className = "constant-opt";
            dom.appendChild(optDom);

            var optUpdateDom = document.createElement("span");
            optUpdateDom.textContent = "编辑";
            optUpdateDom.onclick = function() {
                buildFns.constantWindow(config.constant[key]);
            }
            optDom.appendChild(optUpdateDom);

            var optDeleteDom = document.createElement("span");
            optDeleteDom.textContent = "删除";
            optDeleteDom.onclick = function() {
                domTools.remove(dom);
            }
            optDom.appendChild(optDeleteDom);

            return dom;
        }
    },
    window: {
        continueFrom: function(title, configType, dataList, value, continueFunc, isAutoContinue) {
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

            // 是否自动触发确认
            if (isAutoContinue && isAutoContinue === true) {
                buttonDom.dispatchEvent(new Event("click"));
            }
            
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
                    data.$settings = dataList;
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
                var hasValue = value != null && value != undefined;
                if (buildDomFns.refCommonKey) {
                    var refCommonKey = buildDomFns.refCommonKey;
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
                    if (hasValue || (initState && initState == "1")) {
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

                if (value == null || value == undefined) {
                    value = {};
                } else if (typeof value != "object") {
                    value = { value: value };
                }

                var currType = value ? value.type : null;
                if (currType == null || currType == undefined) {
                    if (value.value != null || value.value != undefined) {
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
    },
    opt: {
        checkListItem: function(msg) {
            var itemDom = document.createElement("span");
            itemDom.className = "item";
            itemDom.textContent = msg;
            return itemDom;
        }
    }
};

const optFns = {
    tool: {
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
        },
        checkConfig: function(config) {
            var result = {
                data: {},
                errors: []
            }

            var constantData = result.data.constant = {};
            if (config.constant) {
                var constants = config.constant;
                for (const key in constants) {
                    if (!Object.prototype.hasOwnProperty.call(constants, key)) {
                        continue;
                    }

                    const currConstant = constants[key];
                    if (!currConstant.key || currConstant.key == "") {
                        result.errors.push(logFns.error("常量配置", "第" + ci + "项", "NULL", "KEY为空"));
                        continue;
                    }

                    if (currConstant.value == null || currConstant.value == undefined) {
                        result.errors.push(logFns.error("常量配置", "第" + ci + "项", currConstant.key, "值未定义"));
                        continue;
                    }

                    constantData[currConstant.key] = currConstant.value.value;
                }
            }

            var initResult = valueFns.parseComponents(config.inits, instance.init);
            result.data.init = initResult.datas;
            for (let ei = 0; ei < initResult.errors.length; ei++) {
                const errConfig = initResult.errors[ei];
                result.errors.push(logFns.error("组件全局配置", 
                    errConfig.componentType + " | " + errConfig.componentId, 
                    errConfig.valueKey, errConfig.msg));
            }

            var blockResult = valueFns.parseComponents(config.blocks, instance.logic);
            result.data.blocks = blockResult.datas
            for (let ei = 0; ei < blockResult.errors.length; ei++) {
                const errConfig = blockResult.errors[ei];
                result.errors.push(logFns.error("公共组件定义", 
                    errConfig.componentType + " | " + errConfig.componentId, 
                    errConfig.valueKey, errConfig.msg));
            }

            var beforeResult = valueFns.parseSettingFlows(config.before);
            result.data.before = beforeResult.datas;
            for (let ei = 0; ei < beforeResult.errors.length; ei++) {
                const errConfig = beforeResult.errors[ei];
                var currTitle2 = "第 " + errConfig.flowId + "项";
                if (errConfig.componentId || errConfig.componentType) {
                    currTitle2 += " > " + errConfig.componentType + " | " + errConfig.componentId
                }
                result.errors.push(logFns.error("前置流程配置", currTitle2, errConfig.valueKey, errConfig.msg));
            }

            var afterResult = valueFns.parseSettingFlows(config.after);
            result.data.after = afterResult.datas;
            for (let ei = 0; ei < afterResult.errors.length; ei++) {
                const errConfig = afterResult.errors[ei];
                var currTitle2 = "第 " + errConfig.flowId + "项";
                if (errConfig.componentId || errConfig.componentType) {
                    currTitle2 += " > " + errConfig.componentType + " | " + errConfig.componentId
                }
                result.errors.push(logFns.error("后置流程配置", currTitle2, errConfig.valueKey, errConfig.msg));
            }

            var flowResult = valueFns.parsePathFlows(config.flow);
            result.data.flows = flowResult.dataMap;
            for (let ei = 0; ei < flowResult.errors.length; ei++) {
                const errConfig = flowResult.errors[ei];
                var currTitle2 = errConfig.flowId;
                if (errConfig.componentId || errConfig.componentType) {
                    currTitle2 += " > " + errConfig.componentType + " | " + errConfig.componentId
                }
                result.errors.push(logFns.error("执行流程配置", currTitle2, errConfig.valueKey, errConfig.msg));
            }

            return result;
        },
        loadImportData: function(data) {
            if (!data || typeof data != 'object') {
                return;
            }

            var resetId = function(data) {
                if (data.id) {
                    var ref = data.refComponent && refComponent == true;
                    data.id = { ref: ref, id: data.id };
                }
            }

            if (data.init && Array.isArray(data.init) && data.init.length > 0) {
                for (let i = 0; i < data.init.length; i++) {
                    const item = data.init[i];
                    if (!item || !item.type) {
                        continue;
                    }

                    resetId(item);
                    buildFns.initComponentWindow(item.type, item, true);
                }
            }

            if (data.blocks && Array.isArray(data.blocks) && data.blocks.length > 0) {
                for (let i = 0; i < data.blocks.length; i++) {
                    const item = data.blocks[i];
                    if (!item || !item.type) {
                        continue;
                    }

                    resetId(item);
                    buildFns.logicComponentWindow(item.type, item, null, true);  
                }
            }

            if (data.before && Array.isArray(data.before) && data.before.length > 0) {
                for (let i = 0; i < data.before.length; i++) {
                    const item = data.before[i];
                    if (!item) {
                        continue;
                    }

                    const flowId = buildFns.flowSettingsWindow(null, "before", item, true);
                    if (!flowId || !item.flow || !Array.isArray(item.flow) || item.flow.length == 0) {
                        continue;
                    }
                    
                    for (let j = 0; j < item.flow.length; j++) {
                        const flowItem = item.flow[i];
                        if (!flowItem || !flowItem.type) {
                            continue;
                        }

                        resetId(flowItem);
                        buildFns.logicComponentWindow(flowItem.type, flowItem, flowId, true);
                    }
                }
            }

            if (data.flows && typeof data.flows === 'object') {
                var flowData = data.flows;
                for (const key in flowData) {
                    if (!Object.prototype.hasOwnProperty.call(flowData, key)) {
                        continue;
                    }

                    const item = flowData[key];
                    if (!item) {
                        continue;
                    }

                    const keyArr = key.split("/");
                    const value = {
                        domain: keyArr[0],
                        function: keyArr.length > 1 ? keyArr[1] : null
                    }
                    const flowId = buildFns.flowPathWindow(null, value, true);

                    if (!flowId || !Array.isArray(item) || item.length == 0) {
                        continue;
                    }

                    for (let j = 0; j < item.length; j++) {
                        const flowItem = item[j];
                        if (!flowItem || !flowItem.type) {
                            continue;
                        }

                        resetId(flowItem);
                        buildFns.logicComponentWindow(flowItem.type, flowItem, flowId, true);
                    }
                }
            }

            if (data.after && Array.isArray(data.after) && data.after.length > 0) {
                for (let i = 0; i < data.after.length; i++) {
                    const item = data.after[i];
                    if (!item) {
                        continue;
                    }

                    const flowId = buildFns.flowSettingsWindow(null, "after", item, true);
                    if (!flowId || !item.flow || !Array.isArray(item.flow) || item.flow.length == 0) {
                        continue;
                    }
                    
                    for (let j = 0; j < item.flow.length; j++) {
                        const flowItem = item.flow[i];
                        if (!flowItem || !flowItem.type) {
                            continue;
                        }

                        resetId(flowItem);
                        buildFns.logicComponentWindow(flowItem.type, flowItem, flowId, true);
                    }
                }
            }

            if (data.constant) {
                var constants = data.constant;
                for (const key in constants) {
                    if (!Object.prototype.hasOwnProperty.call(constants, key)) {
                        continue;
                    }

                    buildFns.constantWindow({ key: key, value: constants[key]}, true);
                }
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

const valueFns = {
    parsePathFlows: function(flowValueMap) {
        var result = {
            dataMap: {},
            errors: []
        };

        for (const key in flowValueMap) {
            if (Object.prototype.hasOwnProperty.call(flowValueMap, key)) {
                const flowValue = flowValueMap[key];
                const path = flowValue.domain + "/" + flowValue.function;

                if (flowValue.domain == "" || flowValue.function == "") {
                    const errorConfig = {};
                    errorConfig.flowId = path;
                    errorConfig.valueKey = null;
                    errorConfig.msg = "域或函数未配置";
                    result.errors.push(errorConfig);
                } 
                
                var componentsValueResult = valueFns.parseComponents(flowValue.components, instance.logic);
                if (componentsValueResult.datas && componentsValueResult.datas.length > 0) {
                    result.dataMap[path] = componentsValueResult.datas;
                } else {
                    const errorConfig = {};
                    errorConfig.flowId = path;
                    errorConfig.valueKey = null;
                    errorConfig.msg = "未配置流程";
                    result.errors.push(errorConfig);
                }
                
                for (let ei = 0; ei < componentsValueResult.errors.length; ei++) {
                    const errorConfig = componentsValueResult.errors[ei];
                    errorConfig.flowId = path;
                    result.errors.push(errorConfig);
                }
            }
        }

        return result;
    },
    parseSettingFlows: function(flowValueMap) {
        var result = {
            datas: [],
            errors: []
        };

        var i = 0;
        for (const key in flowValueMap) {
            if (Object.prototype.hasOwnProperty.call(flowValueMap, key)) {
                const flowValue = flowValueMap[key];
                var flowValueResult = valueFns.parseValue(flowValue.data);

                for (let fe = 0; fe < flowValueResult.errors.length; fe++) {
                    const errorConfig = flowValueResult.errors[fe];
                    errorConfig.flowId = i + 1;
                    result.errors.push(errorConfig);
                }

                if (flowValueResult.data) {
                    var finalFlowValue = flowValueResult.data;

                    var componentsValueResult = valueFns.parseComponents(flowValue.components, instance.logic);
                    if (componentsValueResult.datas && componentsValueResult.datas.length > 0) {
                        finalFlowValue.flow = componentsValueResult.datas;
                    } else {
                        const errorConfig = {};
                        errorConfig.flowId = i + 1;
                        errorConfig.valueKey = null;
                        errorConfig.msg = "未配置流程";
                        result.errors.push(errorConfig);
                    }

                    for (let ei = 0; ei < componentsValueResult.errors.length; ei++) {
                        const errorConfig = componentsValueResult.errors[ei];
                        errorConfig.flowId = i + 1;
                        result.errors.push(errorConfig);
                    }

                    result.datas.push(finalFlowValue);
                }

                i++;
            }
        }

        return result;
    },
    parseComponents: function(valueConfigs, instanceMap) {
        var result = {
            datas: [],
            errors: []
        };

        for (let i = 0; i < valueConfigs.length; i++) {
            const valueConfig = valueConfigs[i];
            var value = instanceMap[valueConfig.id];
            var valueResult = valueFns.parseValue(value);

            var currId = null;
            var currType = null;
            if (valueResult.data) {
                currId = valueResult.data.id;
                currType = valueResult.data.type;
                result.datas.push(valueResult.data);
            }

            // 检查REF ID
            if (valueResult.refId) {
                var refValue = instanceMap[valueResult.refId];
                if (refValue == null || refValue == undefined) {
                    result.errors.push("引用的 ID：" + valueResult.refId + " 配置不存在");
                }
            }

            for (let ri = 0; ri < valueResult.errors.length; ri++) {
                const errorConfig = valueResult.errors[ri];
                errorConfig.componentId = currId;
                errorConfig.componentType = currType;
                result.errors.push(errorConfig);
            }
        }

        return result;
    },
    parseValue: function(value) {
        var result = {
            data: null,
            refId: null,
            errors: []
        }

        if (value == null || value == undefined) {
            return result;
        }

        const valueIsArray = Array.isArray(value);
        const valueArr = valueIsArray ? value : [value];
        const valueParseData = [];
        for (let i = 0; i < valueArr.length; i++) {
            const itemValue = valueArr[i];
            if (itemValue == null || itemValue == undefined) {
                continue;
            }

            const settings = itemValue.$settings
            if (!settings || settings.length == 0) {
                continue;
            }
            
            var currData = {};
            for (let s = 0; s < settings.length; s++) {
                const setting = settings[s];
                const settingKey = setting.key;
                let currValue = itemValue[settingKey];
                if (currValue == null || currValue == undefined) {
                    if (setting.isRequired == true) {
                        result.errors.push({
                            valueKey: settingKey, 
                            msg: "该选项为必填选项"
                        });
                    }
                }
                
                if (setting.type == "CONFIG" || setting.type == "OBJECT") {
                    const subResult = valueFns.parseValue(currValue);
                    currData[settingKey] = subResult.data;
                    for (let se = 0; se < subResult.errors.length; se++) {
                        const subError = subResult.errors[se];
                        subError.key = settingKey + "." + subError.key;
                        result.errors.push(subError);
                    }
                } else if (setting.type == "ID") {
                    currData[settingKey] = currValue.id;
                    if (currValue.ref == true) {
                        result.refId = refId;
                        currData.refComponent = true;
                    }
                } else if (setting.type == "ANY") {
                    if (Array.isArray(currValue)) {
                        currData[settingKey] = [];
                        for (let cvi = 0; cvi < currValue.length; cvi++) {
                            const currValueItem = currValue[cvi];
                            if (currValueItem && currValueItem.value != null && currValueItem.value != undefined && currValueItem.value != "") {
                                currData[settingKey].push(currValueItem.value);
                            }
                        }
                    } else {
                        if (currValue && currValue.value != null && currValue.value != undefined && currValue.value != "") {
                            currData[settingKey] = currValue.value;
                        }
                    }
                } else {
                    if (currValue != null && currValue != undefined && currValue != "") {
                        currData[settingKey] = currValue;
                    }
                }
            }

            valueParseData.push(currData);
        }
        
        
        result.data = valueIsArray ? valueParseData : (valueParseData.length > 0 ? valueParseData[0] : null);
        return result;
    }
}
