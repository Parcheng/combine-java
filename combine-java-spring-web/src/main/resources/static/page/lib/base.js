var baseUrl = "http://127.0.0.1:8888/combine";

var firstGroup = null;
var firstComponentDom = null;
var groupMap = {};
var componentMap = {};
var commonRefMap = {};
var overallProperties = [];

const loadFns = {
    loadData(finishFunc) {
        requestFns.url(baseUrl + "/flow/settings/list", "POST", false, {}, null, 
        // requestFns.file("./test/component-test-data.json", 
            function(data) {
                var groupResultData = JSON.parse(data);
                var groupDataArr = groupResultData.data;

                for (let i = 0; i < groupDataArr.length; i++) {
                    const groupDataItem = groupDataArr[i];
                    var currGroupData = groupMap[groupDataItem.key] = {
                        key: groupDataItem.key,
                        name: groupDataItem.name,
                        commons: groupDataItem.commons,
                        components: []
                    }

                    if (i == 0) {
                        firstGroup = currGroupData;
                    }

                    if (groupDataItem.settings && Array.isArray(groupDataItem.settings)) {
                        for (let j = 0; j < groupDataItem.settings.length; j++) {
                            const componentDataItem = groupDataItem.settings[j];
                            componentDataItem.groupKey = groupDataItem.key;
                            
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
                                    newConfigArr.push(config);
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
                console.log("GroupMap:", groupMap);
                console.log("ComponentMap:", componentMap);
                console.log("CommonRefMap:", commonRefMap);

                if (finishFunc) {
                    finishFunc();
                }
            },
            function(data) {
                alert("加载组件数据失败！")
            }
        );
    },
    loadOverall(finishFunc) {
        requestFns.url(baseUrl + "/flow/settings/overall", "POST", false, {}, null, 
        // requestFns.file("./test/overall-test-data.json", 
            function(data) {
                var overallResultData = JSON.parse(data);
                overallProperties = overallResultData.data;
                console.log("OverallProperties", overallProperties);

                if (finishFunc) {
                    finishFunc();
                }
            },
            function(data) {
                alert("加载全局配置数据失败！")
            }
        );
    }
}

const componentMenuFns = {
    config: {
        checkFirstGroup: true,
        checkFirstComponent: true,
        groupIdPrefix: "g_", 
        componentIdPrefix: "c_", 
        groupDomId: "group",
        componentDomId: "component",
        groupItemClassName: "item",
        componentItemClassName: "item",
        groupCheckedClassName: "item-checked",
        componentCheckedClassName: "item-checked"
    },
    init: {
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
    
            var groupDom = document.getElementById(componentMenuFns.config.groupDomId);
            var doms = componentMenuFns.build.groups(groupList);
            domTools.setAll(groupDom, doms);

            if (firstGroup && componentMenuFns.config.checkFirstGroup == true) {
                componentMenuFns.opt.checkGroup(firstGroup.key);
            }
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
                var componentDom = document.getElementById(componentMenuFns.config.componentDomId);
                var doms =  componentMenuFns.build.components(componentList);
                domTools.setAll(componentDom, doms);

                if (firstComponentDom && componentMenuFns.config.checkFirstComponent == true) {
                    firstComponentDom.dispatchEvent(new Event("click"));
                }
            }
        }
    },
    build: {
        groups: function(data) {
            var doms = [];
            for (let i = 0; i < data.length; i++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = componentMenuFns.config.groupIdPrefix + key;
                itemDom.className = componentMenuFns.config.groupItemClassName;
                itemDom.textContent = itemData.name;
                itemDom.onclick = (function(key) {
                    var currKey = key;
                    return function() {
                        componentMenuFns.opt.checkGroup(currKey);
                    }
                })(key);
                doms.push(itemDom);
            }
    
            return doms;
        },
        components: function(data) {
            var doms = [];
            firstComponentDom = null;
            for (let i = 0; i < data.length; i++) {
                var itemData = data[i];
                var key = itemData.key;
                var itemDom = document.createElement("div");
                itemDom.id = componentMenuFns.config.componentIdPrefix + key;
                itemDom.className = componentMenuFns.config.componentItemClassName;
                itemDom.textContent = itemData.name;
                itemDom.onclick = (function(key, itemDom) {
                    var currKey = key;
                    var currItemDom = itemDom;
                    return function() {
                        const parentDom = currItemDom.parentNode;
                        if (parentDom && parentDom.children && parentDom.children.length > 0) {
                            for (let pc = 0; pc < parentDom.children.length; pc++) {
                                const currDom = parentDom.children[pc];
                                if (currDom) {
                                    currDom.className = componentMenuFns.config.componentItemClassName;
                                }
                            }
                            currItemDom.className = componentMenuFns.config.componentCheckedClassName;
                        }

                        componentMenuFns.opt.checkComponent(currKey);
                    }
                })(key, itemDom);

                if (i == 0) {
                    firstComponentDom = itemDom;
                }

                doms.push(itemDom);
            }

            return doms;
        }
    },
    opt: {
        checkGroup: function(key) {
            var currGroupDom = document.getElementById(componentMenuFns.config.groupIdPrefix + key);
            if (!currGroupDom || currGroupDom.className == componentMenuFns.config.groupCheckedClassName) {
                return;
            }

            var groupDom = document.getElementById(componentMenuFns.config.groupDomId);
            for (var i = 0; i < groupDom.children.length; i++) {
                const groupItemDom = groupDom.children[i];
                groupItemDom.className = componentMenuFns.config.groupItemClassName;
            }
            currGroupDom.className = componentMenuFns.config.groupCheckedClassName;
            componentMenuFns.init.components(key);
        },
        checkComponent: function(key) {
            console.log("Function 'checkComponent' Undefined");
        }
    }
}

const domTools = {
    remove: function(dom) {
        if (dom) {
            if (dom.parentNode) {
                dom.parentNode.removeChild(dom);
            } else {
                dom.remove();
            }
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
    }
}

const logFns = {
    error: function(title1, title2, title3, msg) {
        return "【" + title1 + "】【" + title2 + "】【" + title3 + "】 " + msg;
    }
}

const requestFns = {
    url: function (url, type, fromSubmit, params, headers, successFn, failFn, errorFn) {
        errorFn = errorFn ? errorFn : function () {
            console.log("Request error: ", url, headers, params);
            alert("Request error!");
        };

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