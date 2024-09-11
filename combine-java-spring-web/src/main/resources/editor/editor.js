// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的

var firstGroup = null;
var groupMap = {};
var componentMap = {};

// 组件图像 右键可以编辑/前移/后移/删除/复制

var config = { 
    componentInit: {}, 
    componentLogic: {}, 
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
    buildFns.logicComponentWindow("mysql.execute");
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
    flowItem: function(flowId, key, type) {
        var flowDom = document.getElementById(flowId);
        var flowNodeData = config.flow[flowId];
        if (!flowDom || !flowNodeData) {
            alert("流程不存在");
            return;
        }

        var currIdIndex = idIndex.componentLogic++;
        var logicId = idPrefix.componentLogic + currIdIndex;
        config.componentLogic[logicId] = {
            id: currIdIndex,
            flowId: flowId,
            key: key,
            type: type,
            config: { id: currIdIndex }
        };

        var flagDom = buildDomFns.node.flowLineItem();
        var componentLogicDom = buildDomFns.node.componentLogic(logicId, key, type);
        domTools.addAll(flowDom, [flagDom, componentLogicDom]);
    },
    initItem: function(key, type) {
        var initDom = document.getElementById("init");

        var currIdIndex = idIndex.componentInit++;
        var inidId = idPrefix.componentInit + currIdIndex;
        config.componentInit[inidId] = {
            id: currIdIndex,
            key: key,
            type: type,
            config: { id: currIdIndex }
        };

        var componentInitDom = buildDomFns.node.componentInit(inidId, key, type);
        domTools.addAll(initDom, [componentInitDom]);
    },
    blockItem: function() {
        var blockDom = document.getElementById("block");

        var currIdIndex = idIndex.componentLogic++;
        var logicId = idPrefix.componentLogic + currIdIndex;
        config.componentLogic[logicId] = {
            id: currIdIndex,
            flowId: flowId,
            key: key,
            type: type,
            config: { id: currIdIndex }
        };

        var componentLogicDom = buildDomFns.node.componentLogic(logicId, key, type);
        domTools.addAll(blockDom, [flagDom, componentLogicDom]);
    },
    checkToWindow: function() {
        // 无init配置不能选择init
    },
    initComponentWindow: function(key) {
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

        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(initConfig);
        windowsDom.appendChild(fromWindowDom);
    },
    logicComponentWindow: function(key) {
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

        var windowsDom = document.getElementById("window");
        var fromWindowDom = buildDomFns.window.continueFrom(component.key, component.name, logicConfig, 
            function(data) { console.log(data); });
        windowsDom.appendChild(fromWindowDom);
    },
    flowSettingsWindow: function() {

    },
    flowPathWindow: function() {

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
        componentInit: function(initId, key, type) {
            var dom = document.createElement("div");
            dom.id = initId;
            dom.className = "component-item";
            dom.innerHTML = type + "<br>" + key;
            dom.ondblclick = (function(initId) {
                var currInitId = initId;
                return function() {
                    optFns.node.openComponentInitWindow(currInitId);
                }
            })(initId);
            return dom;
        },
        componentLogic: function(logicId, key, type) {
            var dom = document.createElement("div");
            dom.name = logicId;
            dom.className = "component-item";
            dom.innerHTML = type + "<br>" + key;
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
        checkFlow: function() {

        },
        continueFrom: function(key, title, dataList, continueFunc) {
            var windowId = idPrefix.window + (idIndex.window++);

            var windowDom = document.createElement("div");
            windowDom.id = windowId;
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
                var currWindow = document.getElementById(windowId);
                domTools.remove(currWindow);
            }
            titleDom.appendChild(titleCloseDom);

            var bodyDom = document.createElement("div");
            bodyDom.className = "body";
            windowDom.appendChild(bodyDom);

            var itemDoms = buildDomFns.settings.items(dataList);
            domTools.addAll(bodyDom, itemDoms);
            var buttonDom = buildDomFns.settings.continueButton(windowId, continueFunc);
            bodyDom.appendChild(buttonDom);

            return windowDom;
        }
    },
    settings: {
        items : function(dataList) {
            var body = [];
            if (!dataList || dataList.length == 0) {
                return body;
            }

            for (let i = 0; i < dataList.length; i++) {
                var itemDom = buildDomFns.settings.item(dataList[i]);
                body.push(itemDom);
                body.push(document.createElement("hr"));
            }

            return body;
        },
        continueButton: function(windowId, continueFunc) {
            var dom = document.createElement("div");
            dom.className = "continue";
            dom.textContent = "确定";
            dom.onclick = function() {
                // TODO get from data to func
                var data = {};
                continueFunc(data)
            };

            return dom;
        },
        item: function(data) {
            var dom = document.createElement("div");
            dom.name = data.key;
            dom.className = "item";

            // -------------------- title --------------------------
            var titleDom = document.createElement("div");
            titleDom.className = "label";
            dom.appendChild(titleDom);

            var titleKeyDom = document.createElement("span");
            titleKeyDom.className = "key";
            titleKeyDom.textContent = data.key + (data.isRequired ? "[*]" : "") + "：";
            titleKeyDom.setAttribute("key", data.key);
            titleDom.appendChild(titleKeyDom);

            var titleNameDom = document.createElement("span");
            titleNameDom.className = "name";
            titleNameDom.textContent = data.name;
            titleDom.appendChild(titleNameDom);

            var titleDescDom, titleDescFlagDom;
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
                domTools.switchDisplay(titleDescFlagDom);

                // 详细信息标识
                titleDescFlagDom = document.createElement("span");
                titleDescFlagDom.className = "flag";
                titleDescFlagDom.textContent = "[详细信息]";
                titleDescFlagDom.onclick = function() {
                    domTools.switchDisplay(titleDescDom);
                };
            }
           
            var titleEgDom, titleEgFlagDom;
            if (data.egs && data.egs.length > 0) {
                // 示例DOM
                titleEgDom = document.createElement("span");
                titleEgDom.className = "desc";

                var egText = "示例：";
                if (data.egs.length > 1) {
                    for (let i = 0; i < data.egs.length; i++) {
                        egText += ("<br>" + data.egs[i].value + "<br>" + data.egs[i].desc);
                    }
                } else {
                    egText += data.egs[0];
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
            dom.appendChild(controlDom);

            var line = buildDomFns.settings.line(data, true);
            dom.appendChild(line);

            return dom;
        },
        line: function(data, isFirst) {
            var lineDom = document.createElement("div");
            lineDom.className = "line";

            var arrayFlagDom = buildDomFns.settings.arrayFlag(lineDom, data.isArray, data.type, isFirst);
            var controls;
            switch (data.type) {
                case "ID":
                case "TEXT":
                case "EXPRESSION":
                    controls = buildDomFns.settings.control.text(arrayFlagDom, data, isFirst);
                    break;
                case "NUMBER":
                    controls = buildDomFns.settings.control.number(arrayFlagDom, data, isFirst);
                    break;
                case "BOOLEAN":
                    controls = buildDomFns.settings.control.boolean(arrayFlagDom, data, isFirst);
                    break;
                case "SELECT":
                    controls = buildDomFns.settings.control.select(arrayFlagDom, data, isFirst);
                    break;
                case "COMPONENT":
                    controls = buildDomFns.settings.control.component(arrayFlagDom, data, isFirst);
                    break;
                case "MAP":
                    controls = buildDomFns.settings.control.map(arrayFlagDom, data, isFirst);
                    break;
                case "OBJECT":
                case "CONFIG":
                    controls = buildDomFns.settings.control.object(arrayFlagDom, data);
                    break;
                case "ANY":
                    controls = buildDomFns.settings.control.any(arrayFlagDom, data, isFirst);
                    break;
                default:
                    controls = buildDomFns.settings.control.none(data);
                    break;
            }
            domTools.addAll(lineDom, controls);

            return lineDom;
        },
        arrayFlag: function(lineDom, isArray, dataType, isFirst) {
            if (isArray !== true) {
                return null;
            }

            if (isFirst !== true && dataType === "COMPONENT") {
                return null;
            }

            var arrayFlagDom = document.createElement("span");
            arrayFlagDom.className = "flag";
            arrayFlagDom.textContent = isFirst === true ? "+" : "-";
        
            if (isFirst === true && dataType === "COMPONENT") {
                arrayFlagDom.onclick = (function(lineDom) {
                    var currLineDom = lineDom;
                    return function() {
                        var itemLine = buildDomFns.control.component(data, false);
                        currLineDom.appendChild(itemLine);
                    }
                })(lineDom);
            } else if (isFirst === true) {
                arrayFlagDom.onclick = (function(lineDom) {
                    var currLineDom = lineDom;
                    return function() {
                        var parentDom = currLineDom.parentNode;
                        if (parentDom) {
                            var itemLine = buildDomFns.settings.line(data, false);
                            parentDom.appendChild(itemLine);
                        }
                    }
                })(lineDom);
            } else {
                arrayFlagDom.onclick = (function(lineDom) {
                    var currLineDom = lineDom;
                    return function() {
                        var parentDom = currLineDom.parentNode;
                        if (parentDom) {
                            parentDom.removeChild(lineDom);
                        }
                    }
                })(lineDom);
            }

            return arrayFlagDom;
        },
        control: {
            // TODO item存储type，设置数据，获取数据Func，添加一行可以使用拷贝Dom,data.id上游设置
            text: function(arrayFlagDom, data, isFirst) {
                var defaultValue = data.defaultValue;
                if (data.type == "ID") {
                    defaultValue = idPrefix.componentConfig + (idIndex.componentConfig++);
                }

                var dom = document.createElement("input");
                dom.className = "input";
                dom.setAttribute("type", "text");
                if (isFirst === true && defaultValue) {
                    dom.value = defaultValue;
                }

                return [dom, arrayFlagDom];
            },
            number: function(arrayFlagDom, data, isFirst) {
                var dom = document.createElement("input");
                dom.className = "input";
                dom.setAttribute("type", "number");
                if (isFirst === true && data.defaultValue) {
                    dom.value = data.defaultValue;
                }

                return [dom, arrayFlagDom];
            },
            boolean: function(arrayFlagDom, data, isFirst) {
                var name =  + data.key + "-" + new Date().getTime()

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

                if (isFirst === true && data.defaultValue) {
                    if (data.defaultValue == "true") {
                        trueDom.checked  = true;
                    } else {
                        falseDom.checked  = true;
                    }
                }

                return [trueDom, trueTextDom, falseDom, falseTextDom, arrayFlagDom];
            },
            select: function(arrayFlagDom, data, isFirst) {
                var selectDom = document.createElement("select");
                selectDom.className = "select";

                if (data.options && data.options.length > 0) {
                    for (let i = 0; i < data.options.length; i++) {
                        var optionDom = document.createElement("option");
                        optionDom.setAttribute("value", data.options[i].key);
                        optionDom.textContent = data.options[i].name;
                        selectDom.appendChild(optionDom);
                    }
                }

                if (isFirst === true && data.defaultValue) {
                    selectDom.value = data.defaultValue;
                }

                return [selectDom, arrayFlagDom];
            },
            component: function(arrayFlagDom, data, isFirst) {
                var body = [];
                if (isFirst !== true) {
                    var nextDom = document.createElement("div");
                    nextDom.className = "line-next-flag";
                    nextDom.textContent = "→";
                    body.push(nextDom);
                }
     
                var componenttDom = document.createElement("div");
                componenttDom.className = "line-component";
                componenttDom.innerHTML = data.key + "<br>" + data.id;
                body.push(componenttDom);

                body.push(arrayFlagDom);

                // TODO 点击事件，右键菜单

                return body;
            },
            map: function(arrayFlagDom, data, isFirst) {
                var textareaDom = document.createElement("textarea");
                return [textareaDom, arrayFlagDom];
            },
            object: function(arrayFlagDom, data) {
                var spanDom = document.createElement("span");
                spanDom.className = "object-fold";
                spanDom.textContent = "<配置子属性>";
     
                var subItemsDom = document.createElement("div");
                subItemsDom.className = "sub-items";

                spanDom.onclick = (function(subItemsDom, childrenData) {
                    var currSubItemsDom = subItemsDom;
                    var currChildrenData = childrenData;
                    return function() {
                        var initState = currSubItemsDom.getAttribute("init-state");
                        if (initState && initState == "1") {
                            domTools.switchDisplay(currSubItemsDom);
                            return;
                        }

                        currSubItemsDom.setAttribute("init-state", "1");
                        var subDoms = buildDomFns.settings.item(currChildrenData);
                        domTools.addAll(currSubItemsDom, subDoms);
                    }
                })(subItemsDom, data.children);

                return [spanDom, arrayFlagDom, subItemsDom];
            },
            any: function(arrayFlagDom, data, isFirst) {
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
                        
                var textareaDom = document.createElement("textarea");

                return [selectDom, textareaDom, arrayFlagDom];
            },
            none: function(data) {
                var spanDom = document.createElement("span");
                spanDom.textContent = "未知类型"
                return [span];
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

        openCheckComponentWindow: function(key) {
            // 选择 默认上次的/新建的
        },
        confirmCheckComponent: function(key) {
            // add dom 
            // add data
        }
    },
    node: {
        openComponentInitWindow: function(initId) {
        },
        confirmComponentInit: function() {
        },
        
        openComponentLogicWindow: function(logicId) {
        },
        confirmComponentLogic: function(flowId) {
        },

        openFlowSettingsWindow: function(flowId) {
            // 解析flowId 前缀
        },
        confirmFlowSettings: function() {
        },

        openFlowPathWindow: function(flowId) {
        },
        confirmFlowPath: function(flowId) {
        }
    },
    // TODO 右键菜单，打开/关闭，编辑/左移/右移/删除
    window: {
        close: function(domId) {
            var dom = document.getElementById(domId);
            if (dom && dom.parentNode) {
                dom.parentNode.removeChild(dom);
            }
        }
    }
}

const dataFns = {
    load: function() {

    },
    get: function() {

    },
    init: {
        set: function() {

        },
        add: function() {

        },
        get: function() {

        }
    },
    afterAndBfore: {
        set: function() {

        },
        add: function() {

        },
        get: function() {

        }
    },
    flow: {
        set: function() {

        },
        add: function() {

        },
        get: function() {

        }
    },
    from: {
        set: function() {

        },
        add: function() {

        },
        get: function() {

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
        if (isShow != null && isShow != undefined) {
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