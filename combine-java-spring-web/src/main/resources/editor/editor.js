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
    componentInit:1 
};
var idPrefix = { 
    group: "g_", 
    component: "c_", 
    before: "b_", 
    after: "a_", 
    flow: "f_", 
    componentLogic: "cl_", 
    componentInit: "ci_" 
}

window.onload = function() {
    initFns.loadData();
    initFns.loadGroup();
    initFns.bindAddItemEvent();
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

    },
    initComponentWindow: function() {

    },
    logicComponentWindow: function() {

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
        from: function() {

        }
    },
    settings: {
        item: function(data) {
            var dom = document.createElement("div");
            dom.className = "item";

            // -------------------- title --------------------------
            var titleDom = document.createElement("div");
            titleDom.className = "label";
            dom.appendChild(titleDom);

            var titleKeyDom = document.createElement("span");
            titleKeyDom.className = "key";
            titleDom.appendChild(titleKeyDom);

            var titleNameDom = document.createElement("span");
            titleNameDom.className = "name";
            titleDom.appendChild(titleNameDom);

            if (data.desc && data.desc.length > 0) {
                var titleDescFlagDom = document.createElement("span");
                titleDescFlagDom.className = "flag";
                titleDescFlagDom.textContent = "[详细信息]";
                titleDescFlagDom.onclick = function() {
                    domTools.switchDisplay(titleDescFlagDom);
                };
                domTools.switchDisplay(titleDescFlagDom);
                titleDom.appendChild(titleDescFlagDom);
            }
            
            if (data.egs && data.egs.length > 0) {
                var titleEgFlagDom = document.createElement("span");
                titleEgFlagDom.className = "flag";
                titleEgFlagDom.textContent = "[示例]";
                titleEgFlagDom.onclick = function() {
                    domTools.switchDisplay(titleEgFlagDom);
                };
                domTools.switchDisplay(titleEgFlagDom);
                titleDom.appendChild(titleEgFlagDom);
            }

            if (data.desc && data.desc.length > 0) {
                var titleDescDom = document.createElement("span");
                titleDescDom.className = "desc";

                var descText = "详细信息：";
                if (data.desc.length > 1) {
                    for (let i = 0; i < data.desc.length; i++) {
                        descText += ("<br>" + data.desc[i]);
                    }
                } else {
                    descText += data.desc[0];
                }
                titleDescDom.textContent =  descText;
                titleDom.appendChild(titleDescDom);
            }
           
            if (data.egs && data.egs.length > 0) {
                var titleEgDom = document.createElement("span");
                titleEgDom.className = "desc";

                var egText = "示例：";
                if (data.egs.length > 1) {
                    for (let i = 0; i < data.egs.length; i++) {
                        egText += ("<br>" + data.egs[i]);
                    }
                } else {
                    egText += data.egs[0];
                }
                titleEgDom.textContent = egText;
                titleDom.appendChild(titleEgDom);
            }
            

            // -------------------- control --------------------------
            var controlDom = document.createElement("div");
            controlDom.className = "control";
            dom.appendChild(controlDom);

            var line = buildDomFns.settings.line(data, true);
            dom.appendChild(line);
        },
        line: function(data, isFirst) {
            var lineDom = document.createElement("div");
            lineDom.className = "line";

            var controls;
            switch (data.type) {
                case "TEXT":
                    controls = buildDomFns.settings.control.text(data);
                    break;
                case "NUMBER":
                    controls = buildDomFns.settings.control.number(data);
                    break;
                case "BOOLEAN":
                    controls = buildDomFns.settings.control.boolean(data);
                    break;
                case "SELECT":
                    controls = buildDomFns.settings.control.select(data);
                    break;
                case "COMPONENT":
                    controls = buildDomFns.settings.control.component(data);
                    break;
                case "MAP":
                    controls = buildDomFns.settings.control.map(data);
                    break;
                case "OBJECT":
                case "CONFIG":
                    controls = buildDomFns.settings.control.object(data);
                    break;
                case "ANY":
                    controls = buildDomFns.settings.control.any(data);
                    break;
                default:
                    controls = buildDomFns.settings.control.none(data);
                    break;
            }
            domTools.addAll(lineDom, controls);

            if (isFirst == true) {
                var lineAddDom = document.createElement("span");
                lineAddDom.className = "flag";
                lineAddDom.textContent = "+";
                lineAddDom.onclick = (function(lineDom) {
                    var currLineDom = lineDom;
                    return function() {
                        var parentDom = currLineDom.parentNode;
                        if (parentDom) {
                            var itemLine = buildDomFns.settings.line(data, false);
                            parentDom.appendChild(itemLine);
                        }
                    }
                })(lineDom);
                lineDom.appendChild(lineAddDom);
            } else {
                var lineSubtractDom = document.createElement("span");
                lineSubtractDom.className = "flag";
                lineSubtractDom.textContent = "-";
                lineSubtractDom.onclick = (function(lineDom) {
                    var currLineDom = lineDom;
                    return function() {
                        var parentDom = currLineDom.parentNode;
                        if (parentDom) {
                            parentDom.removeChild(lineDom);
                        }
                    }
                })(lineDom);
                lineDom.appendChild(lineSubtractDom);
            }
        },
        control: {
            text: function(data) {
                //             <div class="line">
                //                 <input type="text" class="input" /><span class="flag">+</span>
                //             </div>
                //             <div class="line">
                //                 <input type="text" class="input" /><span class="flag">-</span>
                //             </div>
            },
            number: function() {
    
            },
            boolean: function() {
    
            },
            select: function() {
    
            },
            component: function() {
    
            },
            map: function() {
    
            },
            object: function() {
    
            },
            any: function() {
    
            },
            none: function() {

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
                parentDom.appendChild(subDoms[i]);
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