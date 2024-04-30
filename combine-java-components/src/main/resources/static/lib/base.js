$combineWebUI = (function () {
    const triggersDomId = "$combine-web-triggers";
    const groups = {};
    const instances = {};
    const elements = {};
    const temps = {};
    const loads = {};
    const loadCaches = {};
    const loadGlobals = {};

    let baseUrl = "";
    function init(initBaseUrl) {
        baseUrl = initBaseUrl;
    }

    const instanceFns = {
        load: function (groupId, parent, data, isAppend) {
            const group = groups[groupId];
            if (!group) {
                return resultFns.fail("Load fail", "No config group: " + groupId);
            }

            const parentDom = parent instanceof Element ? parent : document.getElementById(parent);
            if (!parent) {
                return resultFns.fail("Load fail", "No parent: " + parent);
            }

            let domData = [];
            let loadIds = [];
            for (let i = 0; i < group.instances.length; i++) {
                const instance = group.instances[i];
                const buildResult = this.build(instance, data);
                domData.push(buildResult.data);
                if (buildResult.loadId) {
                    loadIds.push(buildResult.loadId);
                }
            }

            if (isAppend) {
                domFns.appendBody(parentDom, domData);
            } else {
                domFns.setBody(parentDom, domData);
            }

            loadDataFns.loads(loadIds, data);
            return resultFns.success();
        },
        register: function (config, groupId, data) {
            if (typeof config === 'string') {
                config = JSON.parse(config);
            }

            const id = config.id;
            if (!instances[id]) {
                instances[id] = JSON.stringify(configFns.init(config, data));
            }
            if (groupId) {
                let group = groups[groupId];
                if (!group) {
                    groups[groupId] = {
                        instances: [id]
                    };
                } else {
                    group.instances.push(id);
                }
            }
        },
        registerAndBuild: function (config, data) {
            this.register(config, null, null, data);
            return this.build(config.id, data);
        },
        build: function (id, data) {
            const configResult = this.getConfig(id);
            if (!configResult.success) {
                return resultFns.fail("Build Fail", configResult.showMsg);
            }

            const config = configResult.data;
            const element = elements[config.type];
            if (!element) {
                return resultFns.fail("Build Fail", "No element: " + config.type);
            }

            data = config.data ? config.data : data;
            if (!data) {
                data = config.defaultData;
            }
            const dataField = config.settings.dataField;
            if (dataField) {
                data = data instanceof Object ? data[dataField] : null;
            }

            return resultFns.success(element.build(config, data), config.load ? config.load.id : null);
        },
        refresh: function (id, parentData) {
            const configResult = this.getConfig(id);
            if (!configResult.success) {
                return resultFns.fail("Refresh fail", configResult.showMsg);
            }

            const instance = configResult.data;
            const element = elements[instance.type];
            if (!element) {
                return resultFns.fail("Refresh fail", "No element: " + instance.type);
            }

            if (instance.refresh) {
                element.refresh(id, instance, parentData);
            }
            return resultFns.success();
        },
        getData: function (id) {
            const configResult = this.getConfig(id);
            if (!configResult.success) {
                return resultFns.fail("Get data fail", configResult.showMsg);
            }

            const instance = configResult.data;
            const element = elements[instance.type];
            if (!element) {
                return resultFns.fail("Get data fail", "No instance: " + instance.type);
            }

            return resultFns.success(element.getData(id, instance.settings));
        },
        call: function (id, name, params) {
            const configResult = this.getConfig(id);
            if (!configResult.success) {
                return resultFns.fail("Call fail", configResult.showMsg);
            }

            const instance = configResult.data;
            const element = elements[instance.type];
            if (!element) {
                return resultFns.fail("Call fail", "No instance: " + instance.type);
            }

            if (!name || !element.call || !element.call[name]) {
                return resultFns.fail("Call fail", "No function: " + name);
            }

            const fn = element.call[name];
            return resultFns.success(fn(instance, params));
        },
        getConfig: function (id) {
            const instance = instances[id];
            if (!instance) {
                return resultFns.fail("Get config fail", "No config: " + id);
            }

            return resultFns.success(JSON.parse(instance));
        }
    };

    const elementFns = {
        register: function (type, element) {
            if (!this.verify(type, element)) {
                console.log("Register element [" + type + "] fail, config is wrong!");
                return;
            }
            elements[type] = element;
        },
        verify: function (type, element) {
            if (!type || !element) {
                console.log("Register element [" + type + "] fail, no config!");
                return;
            }

            let msg = "";
            let success = true;
            if (typeof element.build != "function") {
                success = false;
                msg += ", no build function, "
            }
            if (typeof element.refresh != "function") {
                success = false;
                msg += ", no refresh function, "
            }
            if (typeof element.getData != "function") {
                success = false;
                msg += ", no get data function"
            }

            if (!success) {
                console.log("Register element [" + type + "] fail, " + msg + "!");
            }

            return success;
        },
        buildCallFnCode: function (id, name, params) {
            return "$combineWebUI.instance.call('" + id + "','" + name + "'," + JSON.stringify(params) + ")";
        }
    };

    const resultFns = {
        success: function (data, loadId) {
            return {
                success: true,
                data: data,
                loadId: loadId
            };
        },
        fail: function (errorMsg, showMsg) {
            console.log(errorMsg, showMsg);
            return {
                success: false,
                errorMsg: errorMsg,
                showMsg: showMsg
            };
        }
    };

    const triggerFns = {
        successFiledKey: "success",
        failFiledKey: "fail",
        errorFiledKey: "error",
        build: function (trigger, dom, data) {
            if (!trigger || !dom) {
                return;
            }

            const successElement = trigger[this.successFiledKey];
            const failElement = trigger[this.failFiledKey];
            const errorElement = trigger[this.errorFiledKey];
            this.buildAlert(successElement, failElement, errorElement);

            const successFn = this.buildAlertFn(successElement);
            const failFn = this.buildAlertFn(failElement);
            const errorFn = errorElement ? this.buildAlertFn(errorElement) : null;

            const eventKey = trigger.event ? trigger.event : "click";
            switch (trigger.type) {
                case "CALL":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.call.flow(trigger.flow, trigger.params, successFn, failFn, errorFn);
                    });
                    break;
                case "CALL_URL":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.call.url(trigger.url, trigger.mode, trigger.params, trigger.headers, successFn, failFn, errorFn);
                    });
                    break;
                case "CALL_FUNC":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.instance.call(trigger.id, trigger.name, trigger.params);
                    });
                    break;
                case "LOAD":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.instance.load(trigger.groupId, trigger.parentId, trigger.params);
                    });
                    break;
                case "LOAD_DATA":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.loadData.loads(trigger.loadIds);
                    });
                    break;
                case "SKIP":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);
                        combineWebUI.tools.linkTo(trigger.url);
                    });
                    break;
                case "CUSTOM":
                    dom.addEventListener(eventKey, function () {
                        trigger = combineWebUI.trigger.parseVariable(trigger, data);

                        let funcCode = trigger.functionName + "(";
                        if (trigger.functionParams && trigger.functionParams.length > 0) {
                            for (let i = 0; i < trigger.functionParams.length; i++) {
                                funcCode += ("\"" + trigger.functionParams[i] + "\",")
                            }
                        }
                        funcCode += "null)";

                        try {
                            const funcResult = (new Function(funcCode))();
                            if (funcResult && funcResult.success) {
                                if (successFn) successFn(funcResult);
                            } else {
                                if (failFn) failFn(funcResult);
                            }
                        } catch (err) {
                            if (errorFn) errorFn(funcResult);
                        }
                    });
                    break;
                default:
                    console.error("Unknow trigger type!");
                    break;
            }
        },
        trigger: function (trigger, dom) {
            if (!dom || !(dom instanceof Element) || !trigger) {
                return;
            }

            dom.dispatchEvent(new Event(trigger.event));
        },
        buildAlert(successElement, failElement, errorElement) {
            const triggersDom = document.getElementById(triggersDomId);
            if (!triggersDom) {
                return;
            }

            if (successElement) {
                const successDom = document.getElementById(successElement.id);
                if (!successDom) {
                    const successBuildResulr = instanceFns.registerAndBuild(successElement);
                    if (successBuildResulr.success) {
                        domFns.appendBody(triggersDom, successBuildResulr.data);
                    }
                }
            }


            if (failElement) {
                const failDom = document.getElementById(failElement.id);
                if (!failDom) {
                    const failBuildResulr = instanceFns.registerAndBuild(failElement);
                    if (failBuildResulr.success) {
                        domFns.appendBody(triggersDom, failBuildResulr.data);
                    }
                }
            }

            if (errorElement) {
                const errorDom = document.getElementById(errorElement.id);
                if (!errorDom) {
                    const failBuildResulr = instanceFns.registerAndBuild(errorElement);
                    if (failBuildResulr.success) {
                        domFns.appendBody(triggersDom, failBuildResulr.data);
                    }
                }
            }
        },
        buildAlertFn(element) {
            return function (data) {
                if (element) {
                    instanceFns.refresh(element.id, data);
                    domFns.show(element.id);
                }
            }
        },
        parseVariable(trigger, data) {
            if (!data || !trigger) {
                return;
            }
            return dataFns.parseVariable(trigger, data, null, [this.successFiledKey, this.failFiledKey, this.errorFiledKey]);
        }
    };

    const loadDataFns = {
        register: function (id, load) {
            if (!id || !load || !load.id) {
                return;
            }

            let loadConfig = loads[load.id];
            if (!loadConfig) {
                loads[load.id] = { config: load, instances: [id] };
            } else {
                loadConfig.instances.push(id);
                if (load.type !== 'REF') {
                    loadConfig.config = load;
                }
            }
        },
        loads: function (loadIds, data) {
            const loaded = [];
            for (let i = 0; i < loadIds.length; i++) {
                const loadId = loadIds[i];
                if (loadId && loaded.indexOf(loadId) === -1) {
                    this.load(loadId);
                    loaded.push(loadId);
                }
            }
        },
        load: function (loadId, data) {
            const loadConfig = loads[loadId];
            if (!loadConfig) {
                return;
            }

            const config = loadConfig.config;
            if (this.loadCache(loadConfig)) {
                return;
            }

            switch (config.type) {
                case "FLOW":
                    if (config.flow) {
                        const newParams = dataFns.parseVariable(config.params, data);
                        callFns.flow(config.flow, newParams, function (data) {
                            combineWebUI.loadData.loadSuccess(loadConfig, data, true);
                        });
                    }
                    break;
                case "API":
                    if (config.url) {
                        const newParams = dataFns.parseVariable(config.params, data);
                        callFns.url(config.url, config.mode, newParams, function (data) {
                            combineWebUI.loadData.loadSuccess(loadConfig, data, true);
                        });
                    }
                    break;
                case "FILE":
                    if (config.path) {
                        const fileType = config.path.split(".").pop();
                        callFns.file(config.path, true,
                            function (data) {
                                if (fileType.toLowerCase() === 'json') {
                                    data = JSON.parse(data);
                                }
                                combineWebUI.loadData.loadSuccess(loadConfig, data, true);
                            });
                    }
                    break;
                case "REF":
                    console.error("Load no config, ref id: " + loadId);
                    break;
                default:
                    console.error("Unknow load type, id: " + loadId);
                    break;
            }
        },
        loadSuccess: function (loadConfig, data, setCache) {
            if (!data) {
                return;
            }

            if (loadConfig.config.data) {
                data = dataFns.parseVariable(loadConfig.config.data, data);
            }

            const dataMapping = loadConfig.config.dataMapping;
            if (dataMapping && dataMapping.length > 0) {
                for (let i = 0; i < dataMapping.length; i++) {
                    const mappingConfig = dataMapping[i];

                    const mappingSource = mappingConfig.source;
                    if (mappingSource) {
                        dataFns.replaceData(mappingSource, data, function (currData) {
                            const mappingMap = mappingConfig.mappings;
                            if (mappingMap) {
                                for (const key in mappingMap) {
                                    if (Object.hasOwnProperty.call(mappingMap, key)) {
                                        if (currData == key) {
                                            return mappingMap[key];
                                        }
                                    }
                                }
                            }

                            const defaultValue = mappingConfig.defaultValue;
                            return defaultValue != null && defaultValue != undefined ? defaultValue : currData;
                        });
                    }
                }
            }

            if (setCache) {
                this.setCache(loadConfig, data);
            }

            this.setGlobal(loadConfig, data);
            this.notify(loadConfig, data);
        },
        notify: function (loadConfig, data) {
            const instances = loadConfig.instances;
            if (!instances || instances.length === 0) {
                return;
            }

            for (let i = 0; i < instances.length; i++) {
                instanceFns.refresh(instances[i], data);
            }
        },
        loadCache: function (loadConfig) {
            const load = loadConfig.config;
            if (!load || !load.cache) {
                return false;
            }

            const loadId = load.id;
            const loadCache = loadCaches[loadId];
            if (loadCache) {
                const currTime = new Date().getTime();
                if (load.cache < 0 || currTime < (loadCache.time + config.cache)) {
                    this.notify(loadConfig, loadCache.data);
                    return true;
                }
            }

            return false;
        },
        setCache: function (loadConfig, newData) {
            const load = loadConfig.config;
            if (!load || !load.cache) {
                return;
            }

            const loadId = load.id;
            loadCaches[loadId] = {
                time: new Date().getTime(),
                data: newData
            }
        },
        setGlobal: function (loadConfig, newData) {
            const load = loadConfig.config;
            if (!load || !loadConfig.toGlobal) {
                return;
            }

            const loadId = load.id;
            loadGlobals[loadId] = newData;
        }
    };

    const configFns = {
        init: function (logicConfig, data) {
            if (!logicConfig.external) {
                logicConfig.external = {};
            }
            logicConfig.external.id = logicConfig.id;
            logicConfig.settings = logicConfig.settings ? logicConfig.settings : {};

            logicConfig.load = logicConfig.load ? logicConfig.load : {};
            loadDataFns.register(logicConfig.id, logicConfig.load);

            let tempConfig = tempFns.load(logicConfig.tempPath);
            for (const key in tempConfig) {
                if (Object.hasOwnProperty.call(tempConfig, key)) {
                    let elements = logicConfig[key] ? logicConfig[key] : {};
                    const elementTemp = tempConfig[key];

                    if (elements instanceof Array) {
                        const newElements = [];
                        for (let j = 0; j < elements.length; j++) {
                            newElements.push(this.initElement(elements[j], elementTemp, data));
                        }
                        logicConfig[key] = newElements;
                    } else {
                        logicConfig[key] = this.initElement(elements, elementTemp, data);
                    }
                }
            }

            return logicConfig;
        },
        initElement: function (element, elementTemp, data) {
            if (!element) {
                element = {};
            }

            element.id = element.id ? element.id : elementTemp.id;
            element.name = element.name ? element.name : elementTemp.name;
            element.tag = element.tag ? element.tag : elementTemp.tag;
            element.class = elementTemp.class + " " + (element.classes ? element.classes : "");
            element.style = elementTemp.style + (element.style ? element.style : "");
            element.text = element.text ? element.text : elementTemp.text;

            element.properties = element.properties ? element.properties : {};
            if (elementTemp.properties) {
                for (const key in elementTemp.properties) {
                    if (Object.hasOwnProperty.call(elementTemp.properties, key)) {
                        let value = elementTemp.properties[key];
                        if (value) {
                            element.properties[key] = value;
                        }
                    }
                }
            }
            element.properties.id = element.id;
            element.properties.name = element.name;
            element.properties.class = element.class;
            element.properties.style = element.style;

            if (element.events) {
                for (let j = 0; j < element.events.length; j++) {
                    this.addEventProperty(element, element.events[i]);
                }
            }
            if (elementTemp.events) {
                for (let j = 0; j < elementTemp.events.length; j++) {
                    this.addEventProperty(element, elementTemp.events[i]);
                }
            }

            return element;
        },
        addEvent: function (element, type, functionName, functionParams) {
            this.addEventProperty(element, {
                type: type,
                functionName: functionName,
                functionParams: functionParams
            });
        },
        addEventProperty: function (element, event) {
            if (!event) {
                return;
            }

            if (!element.properties) {
                element.properties = {};
            }

            let functionCode = event.functionName + "(";
            if (event.functionParams) {
                for (let i = 0; i < event.functionParams.length; i++) {
                    functionCode += ("'" + event.functionParams[i] + "',");
                }
            }
            functionCode += "null)";
            element.properties[event.type] = functionCode;
        },
        buildSubElement: function (subSettings, domConfig, buildData, elementDatas) {
            let currData = buildData, dom;
            if (subSettings.elementsId) {
                dom = domFns.build(domConfig, []);
                instanceFns.load(subSettings.elementsId, dom, buildData);
            } else if (subSettings.elements) {
                const itemsBody = [];
                for (let i = 0; i < subSettings.elements.length; i++) {
                    const itemConfig = subSettings.elements[i];
                    if (itemConfig) {
                        const buildResult = instanceFns.registerAndBuild(itemConfig, buildData);
                        if (buildResult.success) {
                            itemsBody.push(buildResult.data);
                        }
                    }
                }
                dom = domFns.build(domConfig, itemsBody);
            } else {
                currData = dataFns.parseVariable(subSettings.text, buildData)
                dom = domFns.build(domConfig, currData);
            }

            if (elementDatas && elementDatas instanceof Array) {
                elementDatas.push(currData);
            }
            return dom;
        }
    };

    const tempFns = {
        load: function (tempPath) {
            if (!temps[tempPath]) {
                callFns.file(tempPath, true,
                    function (text) { temps[tempPath] = JSON.parse(text); },
                    function () { temps[tempPath] = {}; });
            }
            return temps[tempPath];
        }
    };

    const domFns = {
        build: function (config, body) {
            let tagName = config.tag;
            let properties = config.properties ? config.properties : {};

            const tag = document.createElement(tagName);
            for (const key in properties) {
                if (Object.hasOwnProperty.call(properties, key)) {
                    let value = properties[key];
                    value = typeof value == "string" ? value.trim() : value;
                    if (value && value !== "") {
                        tag.setAttribute(key, value);
                    }
                }
            }

            this.appendBody(tag, body);
            return tag;
        },
        replace: function (oldDom, newDom) {
            if (!oldDom || !newDom) {
                return;
            }
            oldDom.parentNode.replaceChild(newDom, oldDom);
        },
        appendBody: function (dom, body) {
            if (!dom || !body) {
                return;
            }

            if (body instanceof Array) {
                for (let i = 0; i < body.length; i++) {
                    this.appendBody(dom, body[i]);
                }
            } else if (body instanceof Element) {
                dom.appendChild(body);
            } else {
                dom.append(body);
            }
        },
        setBody: function (dom, body) {
            if (!dom) {
                return;
            }

            while (dom.firstChild) {
                dom.firstChild.remove();
            }
            if (body) {
                this.appendBody(dom, body);
            }
        },
        appendProtity: function (dom, key, value) {
            if (!dom || !key || !value) {
                return;
            }
            dom.setAttribute(key, value);
        },
        show: function (idOrDom) {
            if (!idOrDom) {
                return;
            }
            if (!(idOrDom instanceof Element)) {
                idOrDom = document.getElementById(idOrDom);
            }
            if (idOrDom) {
                idOrDom.style.display = "block"
            }
        },
        hide: function (idOrDom) {
            if (!idOrDom) {
                return;
            }
            if (!(idOrDom instanceof Element)) {
                idOrDom = document.getElementById(idOrDom);
            }
            if (idOrDom) {
                idOrDom.style.display = "none"
            }
        },
        switch: function (idOrDom) {
            if (!idOrDom) {
                return;
            }
            if (!(idOrDom instanceof Element)) {
                idOrDom = document.getElementById(idOrDom);
            }
            if (idOrDom.style.display === "none") {
                idOrDom.style.display = "block";
            } else {
                idOrDom.style.display = "none";
            }
        }
    };

    const dataFns = {
        isVariable: function (variable) {
            return variable.startsWith("#{") && variable.endsWith("}");
        },
        parseVariable: function (variable, data, defaultText, excludeFields) {
            if (!variable) {
                return variable;
            }

            let newVariable;
            if (variable instanceof Array) {
                newVariable = [];
                for (let i = 0; i < variable.length; i++) {
                    newVariable.push(this.parseVariable(variable[i], data));
                }
            } else if (typeof variable === "object") {
                newVariable = {};
                Object.keys(variable).forEach((key) => {
                    if (!excludeFields || excludeFields.indexOf(key) === -1) {
                        newVariable[key] = this.parseVariable(variable[key], data);
                    } else {
                        newVariable[key] = variable[key];
                    }
                });
            } else if (typeof variable == "string") {
                newVariable = this.parseVariableText(variable, data, defaultText);
            } else {
                newVariable = variable;
            }

            return newVariable;
        },
        parseVariableText: function (text, data, defaultText) {
            if (!text) {
                return null;
            }

            const variables = text.match(/#\{(.*?)\}/g);
            if (!variables) {
                return text;
            }

            const isSet = variables.length == 1 && text.length == variables[0].length;
            for (let i = 0; i < variables.length; i++) {
                const variableExpression = variables[i];
                const variable = variableExpression.substring(2, variableExpression.length - 1);

                let currData = data;
                if ("" !== variable && "$this" !== variable) {
                    let variablePath = variable.split(".");
                    currData = this.parseDataAsFlag(variablePath, currData);
                    for (let i = 0; i < variablePath.length; i++) {
                        if (currData) {
                            currData = currData[variablePath[i]];
                        } else {
                            currData = null;
                            break;
                        }
                    }
                }

                if (isSet) {
                    text = currData;
                } else {
                    if (currData == null && defaultText != null && defaultText != undefined) {
                        currData = defaultText;
                    }
                    if (currData instanceof Array) {
                        const newText = [];
                        for (let ci = 0; ci < currData.length; ci++) {
                            newText.push(text.replace(variableExpression, currData[ci]));
                        }
                        text = newText;
                    } else {
                        text = currData == null ? "" : text.replace(variableExpression, currData);
                    }
                }
            }

            return text;
        },
        replaceData: function (path, data, valueOrFn) {
            if (!path || !data || !valueOrFn) {
                return data;
            }

            const pathArr = path.split(".");
            let currData = this.parseDataAsFlag(pathArr, data);
            for (let i = 0; i < pathArr.length; i++) {
                if (!currData) {
                    break;
                }

                const currPath = pathArr[i];
                if (i == pathArr.length - 1) {
                    const isFunc = valueOrFn instanceof Function;
                    if (currData instanceof Array) {
                        for (let j = 0; j < currData.length; j++) {
                            const currDataItem = currData[j];
                            currDataItem[currPath] = isFunc ? valueOrFn(currDataItem[currPath]) : valueOrFn;
                        }
                    } else {
                        currData[currPath] = isFunc ? valueOrFn(currData[currPath]) : valueOrFn;
                    }
                    break;
                }

                if (currData instanceof Array) {
                    const newCurrData = [];
                    for (let j = 0; j < currData.length; j++) {
                        const currDataItem = currData[j];
                        const currDataItemValue = currDataItem[currPath];
                        if (currDataItemValue != null && currDataItemValue != undefined) {
                            newCurrData.push(currDataItemValue);
                        }
                    }
                    currData = newCurrData;
                } else {
                    currData = currData[currPath];
                }

            }

            return data;
        },
        parseDataAsFlag: function (variablePath, defaultData) {
            if (!variablePath || variablePath.length < 2 || !this.hasDataFlag(variablePath[0])) {
                return defaultData;
            }

            const first = variablePath.shift();
            if (first == "$e") {
                const dataResult = instanceFns.getData(variablePath.shift());
                if (dataResult.success) {
                    return dataResult.data;
                } else {
                    console.error(dataResult.showMsg, dataResult.errorMsg);
                    return null;
                }
            } else if (first == "$ld") {
                return loadGlobals;
            }

            console.error("根据标识解析数据失败", "根据标识解析数据-未知的");
            return null;
        },
        hasDataFlag: function (variableText) {
            return variableText == "$e" || variableText == "$ld";
        },
    };

    const callFns = {
        url: function (url, type, params, headers, successFn, failFn, errorFn) {
            const xhr = new XMLHttpRequest();
            xhr.open(type.toUpperCase() === 'POST' ? 'POST' : 'GET', url);

            if (headers) {
                for (const key in headers) {
                    if (Object.hasOwnProperty.call(headers, key)) {
                        xhr.setRequestHeader(key, headers[key]);
                    }

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

            xhr.send(params ? JSON.stringify(params) : null);
        },
        flow: function (flowKey, params, successFn, failFn, errorFn) {
            params = params ? params : {};
            errorFn = errorFn ? errorFn : function () {
                console.log("Request error: ", flowKey, params);
                alert("Request error!");
            };

            const newBaseUrl = baseUrl === "http://127.0.0.1:5500" ? "http://127.0.0.1:8888/combine" : baseUrl;
            this.url(newBaseUrl + "/api/" + flowKey, 'POST', params, { "Content-Type": "application/json" },
                function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        if (successFn) {
                            successFn(data.data);
                        }
                    } else {
                        console.log("Request fail: ", flowKey, params, data);
                        failFn ? failFn(data) : function () {
                            alert("Request fail: " + data.showMsg + "！");
                        };
                    }
                }, errorFn);
        },
        file: function (path, isSync, successFn, failFn) {
            const xhr = new XMLHttpRequest();
            xhr.open("GET", path, !isSync);
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

    const toolFns = {
        linkTo: function (path) {
            location.href = path;
        },
        generateUUID: function () {
            var d = new Date().getTime();
            var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function (c) {
                var r = (d + Math.random() * 16) % 16 | 0;
                d = Math.floor(d / 16);
                return (c == 'x' ? r : (r & 0x3 | 0x8)).toString(16);
            });
            return uuid;
        },
        copy: function (obj) {
            return JSON.parse(JSON.stringify(obj));
        }
    };

    const combineWebUI = {
        init: init,
        call: callFns,
        temp: tempFns,
        instance: instanceFns,
        element: elementFns,
        trigger: triggerFns,
        loadData: loadDataFns,
        config: configFns,
        dom: domFns,
        tools: toolFns,
        data: dataFns
    };

    return combineWebUI;
})();