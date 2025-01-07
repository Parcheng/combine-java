window.onload = function() {
    loadFns.loadData();
    // loadFns.loadOverall();
    initFns.bindNavEvent();
    initFns.loadGroup();
    initFns.loadOverall();
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

            const configInfoDom = document.getElementById("config-info");
            const infoItemDoms = buildFns.info(currComponent);
            domTools.setAll(configInfoDom, infoItemDoms);

            const configInitDom = document.getElementById("config-init-content");
            if (currComponent.initConfig && currComponent.initConfig.length > 0) {
                const initConfigDoms = buildFns.fieldItems(currComponent.initConfig);
                domTools.setAll(configInitDom, initConfigDoms);
            } else {
                const emptyDom = buildFns.emptyItems();
                domTools.setAll(configInitDom, [emptyDom]);
            }
            
            const configLogicDom = document.getElementById("config-logic-content");
            const logicConfigDoms = buildFns.fieldItems(currComponent.logicConfig);
            domTools.setAll(configLogicDom, logicConfigDoms);

            const configCommonDom = document.getElementById("config-common-content");
            const commonObjects = currComponent.commonObjects;
            if (commonObjects && commonObjects.length > 0) {
                for (let coi = 0; coi < commonObjects.length; coi++) {
                    const commonItemDoms = buildFns.commonItems(commonObjects[coi]);
                    if (coi == 0) {
                        domTools.setAll(configCommonDom, commonItemDoms);
                    } else {
                        domTools.addAll(configCommonDom, commonItemDoms);
                    }
                }
            } else {
                const emptyDom = buildFns.emptyItems();
                domTools.setAll(configCommonDom, [emptyDom]);
            }
        }

        componentMenuFns.init.groups();
    },
    loadOverall: function() {
        
    }
}

const buildFns = {
    info: function(config) {
        return buildDomFns.infoItem(config);
    },
    fieldItems: function(configs) {
        if (!configs || configs.length == 0) {
            return buildFns.emptyItems();
        }

        const body = [];
        for (let i = 0; i < configs.length; i++) {
            const config = configs[i];
            const itemDom = buildDomFns.item(config);
            body.push(itemDom);

            if (config.children && config.children.length > 0) {
                const subItemsDom = buildDomFns.subItem();
                const itemDoms = buildFns.fieldItems(config.children);
                domTools.setAll(subItemsDom, itemDoms);
                body.push(subItemsDom);
            }
        }

        return body;
    },
    commonItems: function(config) {
        var body = [];

        var titleDom = document.createElement("div");
        titleDom.className = "item-common-title";
        titleDom.textContent = config.name + " - " + config.key;
        body.push(titleDom);

        if (config.properties && config.properties.length > 0) {
            const propertyDoms = buildFns.fieldItems(config.properties);
            for (let i = 0; i < propertyDoms.length; i++) {
                body.push(propertyDoms[i]);
            }
        }
        
        return body;
    },
    emptyItems: function() {
        var dom = document.createElement("div");
        dom.className = "item-empty";
        dom.textContent = "暂无配置"
        return dom;
    }
}

const buildDomFns = {
    infoItem: function(config) {
        var body = [];

        var titleDom = document.createElement("div");
        titleDom.className = "title";
        titleDom.textContent = config.name + " - " + config.key;
        body.push(titleDom);

        if (config.result) {
            var contentDom = document.createElement("div");
            contentDom.className = "content";
            contentDom.textContent = "返回值 - " + config.result.info 
                + (config.result.isDownload ? "【文件下载】" : "");
            body.push(contentDom);
        }

        return body;
    },
    item: function(config) {
        var dom = document.createElement("div");
        dom.className = "item";

        var nameSpan = document.createElement("span");
        nameSpan.textContent = "【" + config.key + " - " + config.name + "】";
        dom.appendChild(nameSpan);

        var headerSpan = document.createElement("span");
        headerSpan.textContent = "【类型 - " + config.type 
            + "】【" + (config.isArray ? "数组" : "非数组") 
            + "】【" + (config.isRequired ? "必填" : "非必填") + "】";
        dom.appendChild(headerSpan);

        if (config.desc && config.desc.length > 0) {
            for (let di = 0; di < config.desc.length; di++) {
                const descItem = config[di];
                if (descItem && descItem != "") {
                    const descItemSpan = document.createElement("span");
                    descItemSpan.textContent = descItem;
                    dom.appendChild(descItemSpan);
                }
            }
        }
        
        if (config.egs && config.egs.length > 0) {
            if (config.egs.length > 1) {
                const egTitleSpan = document.createElement("span");
                egTitleSpan.textContent = "【示例】";
                dom.appendChild(egTitleSpan);
            }

            var isFirst = true;
            for (let ei = 0; ei < config.egs.length; ei++) {
                var egItem = config.egs[ei];
                var egText = "";
                if (egItem != null && egItem != undefined) {
                    egText = (egItem.desc ? egItem.desc + " -> " : "") + egItem.value;
                }

                if (isFirst && config.egs.length == 1) {
                    egText = "【示例】" + egText;
                    isFirst = false;
                }

                const egItemSpan = document.createElement("span");
                egItemSpan.textContent = egText;
                dom.appendChild(egItemSpan);
            }
        }

        return dom;
    },
    subItem: function() {
        var dom = document.createElement("div");
        dom.className = "sub-items";
        return dom;
    }
}