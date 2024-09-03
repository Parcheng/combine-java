// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的
// flow-component，flow-item 构建 取值 序列化 封装成对象，可new

var groupMap = {};
var componentMap = {};

var config = { init: {}, block: {}, before: {}, after: {}, flow: {} };

// document.getElementsById("group");

window.onload = function() {
    // load data to groupList and componentMap
    // buildFns.groups
    // buildFns.components first
};


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
    beforeFlow: function(flowId) {
        var beforeDom = domTools.node.beforeOrAfterFlow(flowId);

        
        // settings
        // before
    },
    afterFlow: function(flowId) {
        // settings
        // after
    },
    flow: function(flowId, path) {
        // path
        // flow
    },
    flowItem: function(flowId, data) {
        // - componentLogic
        // config.before.id ? 
        // 这里不用 data
        var componentLogicDom = domTools.node.componentLogic(null, data);
    },
    initItem: function() {
        // componentInit
        // init
    },
    blockItem: function() {
        // componentLogic
        // block
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
                    callFns.tool.checkGroup(key);
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
                    callFns.tool.openCheckComponentWindow(key);
                }
                doms.push(itemDom);
            }
    
            return doms;
        },
    },
    node: {
        componentInit: function(initId, data) {
            var dom = document.createElement("div");
            dom.id = "cin-" + data.key;
            dom.className = "component-item";
            dom.innerHTML = data.type + "<br>" + data.key;
            dom.ondblclick = function() {
                callFns.node.openComponentInitWindow(initId);
            }
            return dom;
        },
        componentLogic: function(logicId, data) {
            var dom = document.createElement("div");
            dom.name = "cl-" + data.key;
            dom.className = "component-item";
            dom.innerHTML = data.type + "<br>" + data.key;
            dom.ondblclick = function() {
                callFns.node.openComponentLogicWindow(logicId);
            }
            return dom;
        },
        beforeOrAfterFlow: function(flowId) {
            var dom = document.createElement("div");
            dom.id = "boaf-" + flowId;
            dom.className = "flow-item";
            return dom;
        },
        flow: function(flowId) {
            var dom = document.createElement("div");
            dom.id = "f-" + flowId;
            dom.className = "flow-item";
            return dom;
        },
        flowSettingsItem: function(flowId) {
            var dom = document.createElement("div");
            dom.className = "settings-item";
            dom.textContent = "设置";
            dom.ondblclick = function() {
                callFns.node.openFlowSettingsWindow(flowId);
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
                callFns.node.openFlowPathWindow(flowId);
            }
            return dom;
        }
    },
    window: {

    }
};

var callFns = {
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