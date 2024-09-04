// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的
// flow-component，flow-item 构建 取值 序列化 封装成对象，可new

var groupMap = {};
var componentMap = {};

var config = { componentInit: {}, componentLogic: {}, before:{}, after:{}, flow:{}};
var idIndex = { before:1, after:1, flow:1, componentLogic:1, componentInit:1 };
var idPrefix = { before: "b_", after: "a_", flow: "f_", componentLogic: "cl_", componentInit: "ci_" }

// document.getElementsById("group");

window.onload = function() {
};

var initFns = {
    loadData() {
        // load data to groupList and componentMap
    },
    loadGroup() {
        // buildFns.groups
        // buildFns.components first
    },
    bindAddItemEvent: function() {
        var beforeAddDom = document.getElementById("before-add");
        dom.ondblclick = function() {
            var flowId = buildFns.beforeFlow();
            optFns.node.openFlowSettingsWindow(flowId);
        }
    
        var afterAddDom = document.getElementById("after-add");
        dom.ondblclick = function() {
            var flowId = buildFns.afterFlow();
            optFns.node.openFlowSettingsWindow(flowId);
        }

        var flowAddDom = document.getElementById("flow-add");
        dom.ondblclick = function() {
            var flowId = buildFns.flow();
            optFns.node.openFlowPathWindow(flowId);
        }
    }
}

var buildFns = {
    groups: function() {
        var groupList = [];
        for (const key in groupMap) {
            if (Object.prototype.hasOwnProperty.call(groupMap, key)) {
                groupList.push(groupMap[key]);
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
            for (const key in componentKeys) {
                if (Object.prototype.hasOwnProperty.call(componentKeys, key)) {
                    const component = componentKeys[groupKey + "." + key];
                    if (component) {
                        componentList.push(component);
                    }
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
        domTools.setAll(parentDom, [beforeDom]);

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
        domTools.setAll(beforeDom, [settingsDom]);

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
        domTools.setAll(beforeDom, [settingsDom]);

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
}

var buildDomFns = {
    tool: {
        groups: function(data) {
            var doms = [];
            for (let index = 0; index < data.length; index++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = "g-" + key;
                itemDom.className = "item";
                itemDom.textContent = itemData.name;
                itemDom.onclick = function() {
                    optFns.tool.checkGroup(key);
                }
                doms.push(itemDom);
            }
    
            return doms;
        },
        components: function(parentKey, data) {
            var doms = [];
            for (let index = 0; index < data.length; index++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = "c-" + parentKey + "." + key;
                itemDom.className = "item";
                itemDom.textContent = itemData.name;
                itemDom.onclick = function() {
                    optFns.tool.openCheckComponentWindow(key);
                }
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
            dom.ondblclick = function() {
                optFns.node.openComponentInitWindow(initId);
            }
            return dom;
        },
        componentLogic: function(logicId, key, type) {
            var dom = document.createElement("div");
            dom.name = logicId;
            dom.className = "component-item";
            dom.innerHTML = type + "<br>" + key;
            dom.ondblclick = function() {
                optFns.node.openComponentLogicWindow(logicId);
            }
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
            dom.ondblclick = function() {
                optFns.node.openFlowSettingsWindow(flowId);
            }
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
            dom.ondblclick = function() {
                optFns.node.openFlowPathWindow(flowId);
            }
            return dom;
        }
    },
    window: {

    }
};

var optFns = {
    tool: {
        checkGroup: function(key) {
        },

        openCheckComponentWindow: function(key) {
        },
        confirmCheckComponent: function(key) {
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

var domTools = {
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
}