


window.onload = function() {
    loadFns.loadData();
    loadFns.loadOverall();
    initFns.bindNavEvent();
    initFns.loadGroup();
};

const initFns = {
    bindNavEvent: function() {
        var homeDom = document.getElementById("home");
        var overallDom = document.getElementById("overall");
        var flowConfigDom = document.getElementById("flow-config");
        var editorDom = document.getElementById("editor");

        var homeNavDom = document.getElementById("home-nav");
        var overallNavDom = document.getElementById("overall-nav");
        var flowConfigNavDom = document.getElementById("flow-config-nav");
        var editorNavDom = document.getElementById("editor-nav");

        homeNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, true);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "active";
            overallNavDom.className = "";
            flowConfigNavDom.className = "";
            editorNavDom.className = "";
        }
        
        overallNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, true);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "";
            overallNavDom.className = "active";
            flowConfigNavDom.className = "";
            editorNavDom.className = "";
        }
        
        flowConfigNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, true);
            domTools.switchDisplay(editorDom, false);

            homeNavDom.className = "";
            overallNavDom.className = "";
            flowConfigNavDom.className = "active";
            editorNavDom.className = "";
        }

        editorNavDom.onclick = function() {
            domTools.switchDisplay(homeDom, false);
            domTools.switchDisplay(overallDom, false);
            domTools.switchDisplay(flowConfigDom, false);
            domTools.switchDisplay(editorDom, true);

            homeNavDom.className = "";
            overallNavDom.className = "";
            flowConfigNavDom.className = "";
            editorNavDom.className = "active";
        }

        homeNavDom.dispatchEvent(new Event("click"));
    },
    loadGroup() {
        componentMenuFns.config.groupDomId = "config-group";
        componentMenuFns.config.componentDomId = "config-component";
        componentMenuFns.opt.checkComponent = function(key) {
            const currComponent = componentMap[key];
            if (!currComponent) {
                return;
            }
            
            const configInitDom = document.getElementById("config-init-content");
            const initConfigDoms = buildFns.fieldItems(currComponent.initConfig);
            domTools.setAll(configInitDom, initConfigDoms);
            
            const configLogicDom = document.getElementById("config-logic-content");
            const logicConfigDoms = buildFns.fieldItems(currComponent.logicConfig);
            domTools.setAll(configLogicDom, logicConfigDoms);
        }

        componentMenuFns.init.groups();
    },
}

const buildFns = {
    fieldItems: function(configs) {
        if (!configs || configs.length == 0) {
            return buildFns.emptyItems();
        }

        const body = [];
        for (let i = 0; i < configs.length; i++) {
            const itemDom = buildDomFns.item(configs[i]);
            body.push(itemDom);
        }

        return body;
    },
    emptyItems: function() {
        var dom = document.createElement("div");
        dom.className = "item";
        dom.textContent = "暂无配置"
        return dom;
    }
}

const buildDomFns = {
    item: function(config) {
        var dom = document.createElement("div");
        dom.className = "item";

        var headerSpan = document.createElement("span");
        headerSpan.textContent = config.key + 
            " | " + config.name + " | " + config.type + 
            " | " + (config.isArray ? "数组 | " : "非数组 | ") + 
            " | " + (config.isRequired ? "必填 | " : "非必填")
        dom.appendChild(headerSpan);

        if (config.desc && config.desc.length > 0) {
            for (let di = 0; di < config.desc.length; di++) {
                const descItemSpan = document.createElement("span");
                descItemSpan.textContent = config[di];
                dom.appendChild(descItemSpan);
            }
        }
        
        if (config.egs && config.egs.length > 0) {
            if (config.egs.length > 1) {
                const egTitleSpan = document.createElement("span");
                egTitleSpan.textContent = "示例: ";
                dom.appendChild(egTitleSpan);
            } else {
                const egFirstSpan = document.createElement("span");
                egFirstSpan.textContent = "示例: " + config[0];
                dom.appendChild(egFirstSpan);

                for (let ei = 1; ei < config.egs.length; ei++) {
                    const egItemSpan = document.createElement("span");
                    egItemSpan.textContent = config[di];
                    dom.appendChild(egItemSpan);
                }
            }
        }

        return dom;
    },
    subItem: function() {

    }
}