// ID重复检查（是否直接使用 id = xxx 的组件？）
// 引用暂时只允许引block的
// flow-component，flow-item 构建 取值 序列化 封装成对象，可new

var groupSettings = {};
var componentSettings = {};

var config = { init: {}, block: {}, before: {}, after: {}, flow: {} };

// document.getElementsById("group");


var buildFns = {

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
            dom.name = "cn-" + data.key;
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