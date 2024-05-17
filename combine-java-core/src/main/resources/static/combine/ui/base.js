$combineWebUI = (function () {
    const triggersDomId = "$combine-web-triggers";
    const groups = {};
    const constant = {};
    const instances = {};
    const instanceRefs = {};
    const instanceTemps = {};
    const elements = {};
    const temps = {};
    const triggers = {};
    const loads = {};
    const loadCaches = {};
    const loadGlobals = {};

    let baseUrl = "";
    function init(initBaseUrl) {
        baseUrl = initBaseUrl;
    }

    const constantFns = {
        register: function (constantConfig) {
            if (constantConfig) {
                constant.data = constantConfig;
            } else {
                constant.data = {};
            }
        },
        get: function () {
            return constant.data;
        }
    }

    const groupFns = {
        load: function (id, parent, data, isAppend) {
            const elementIds = groups[id];
            if (!elementIds) {
                return resultFns.fail("Load fail", "No config group: " + id);
            }

            const parentDom = parent instanceof Element ? parent : document.getElementById(parent);
            if (!parent) {
                return resultFns.fail("Load fail", "No parent: " + parent);
            }

            let domData = [];
            let loadIds = [];
            for (let i = 0; i < elementIds.length; i++) {
                const elementId = elementIds[i];
                const buildResult = instanceFns.build(elementId, data);
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
        register: function (groupId, elementIds) {
            groups[groupId] = elementIds;
        },
        get(groupId) {
            return groups[groupId];
        }
    }

    const instanceFns = {
        register: function (id, instance) {
            configFns.init(instance);
            instances[id] = JSON.stringify(instance);
            return resultFns.success();
        },
        build: function (id, data) {
            const configResult = this.getConfig(id);
            if (!configResult.success) {
                return resultFns.fail("Build Fail", configResult.showMsg);
            }

            const instance = configResult.data;
            const element = elements[instance.type];
            if (!element) {
                return resultFns.fail("Build Fail", "No element: " + config.type);
            }

            data = instance.data ? instance.data : data;
            if (!data) {
                data = instance.defaultData;
            }
            const dataField = instance.dataField;
            if (dataField) {
                data = data instanceof Object ? data[dataField] : null;
            }

            this.initConfig(instance, data);
            const dataLoadId = instance.dataLoadId && instance.dataLoadId !== false ? instance.dataLoadId : null;
            return resultFns.success(element.build(instance, data), dataLoadId);
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

            const dataField = instance.dataField;
            if (dataField) {
                parentData = parentData instanceof Object ? parentData[dataField] : null;
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

            return resultFns.success(element.getData(id, instance));
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
        initConfig: function (instance, data) {
            if (!dataFns.hasVariable(instance.id)) {
                return;
            }

            const sourceConfigId = instance.id;
            const newConfigId = dataFns.parseVariableText(sourceConfigId, data);
            if (!newConfigId) {
                return;
            }

            instance.id = newConfigId;
            instanceRefs[newConfigId] = sourceConfigId;
        },
        getConfig: function (id) {
            let instanceJson = instances[id];
            if (!instanceJson) {
                const instanceRefId = instanceRefs[id];
                if (instanceRefId) {
                    instanceJson = instances[instanceRefId];
                }
            }

            if (!instanceJson) {
                return resultFns.fail("Get config fail", "No config: " + id);
            }

            const instance = JSON.parse(instanceJson);
            instance.id = id;
            return resultFns.success(instance);
        },
        getConfigIds: function (sourceId) {
            if (!dataFns.hasVariable(sourceId)) {
                return [sourceId];
            }

            const ids = [];
            for (const key in instanceRefs) {
                if (Object.hasOwnProperty.call(instanceRefs, key)) {
                    const refId = instanceRefs[key];
                    if (sourceId == refId) {
                        ids.push(refId);
                    }
                }
            }
            return ids;
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
                msg += " no build function, "
            }
            if (typeof element.refresh != "function") {
                success = false;
                msg += " no refresh function, "
            }
            if (typeof element.getData != "function") {
                success = false;
                msg += " no get data function"
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
        register: function (id, trigger) {
            triggers[id] = JSON.stringify(trigger);
        },
        get: function (id) {
            const triggerJson = triggers[id];
            if (triggerJson) {
                const trigger = JSON.parse(triggerJson);
                if (trigger) {
                    return trigger;
                }
            }

            return null;
        },
        build: function (ids, dom, data) {
            if (!ids || !dom) {
                return;
            }

            const triggerIds = ids instanceof Array ? ids : [ids];
            for (let i = 0; i < triggerIds.length; i++) {
                const trigger = this.get(triggerIds[i]);
                if (!trigger) {
                    continue;
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
                    case "CALL_FLOW":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.call.flow(curr.url, curr.mode, curr.fromSubmit, curr.params, curr.headers, successFn, failFn, errorFn, curr.localStorageKey);
                        });
                        break;
                    case "CALL_URL":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.call.url(curr.url, curr.mode, curr.fromSubmit, curr.params, curr.headers, successFn, failFn, errorFn, curr.localStorageKey);
                        });
                        break;
                    case "CALL_FUNC":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.instance.call(curr.id, curr.name, curr.params);
                        });
                        break;
                    case "LOAD":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.group.load(curr.groupId, curr.parentId, curr.params);
                        });
                        break;
                    case "LOAD_DATA":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.loadData.loads(curr.loadIds, data, failFn);
                        });
                        break;
                    case "SKIP":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);
                            combineWebUI.tools.linkTo(curr.url);
                        });
                        break;
                    case "CUSTOM":
                        dom.addEventListener(eventKey, function () {
                            const curr = combineWebUI.trigger.parseVariable(trigger, data);

                            let funcCode = curr.functionName + "(";
                            if (curr.functionParams && curr.functionParams.length > 0) {
                                for (let i = 0; i < curr.functionParams.length; i++) {
                                    funcCode += ("\"" + curr.functionParams[i] + "\",")
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
            }
        },
        trigger: function (ids, dom) {
            if (!dom || !(dom instanceof Element) || !ids) {
                return;
            }

            const triggerIds = ids instanceof Array ? ids : [ids];
            for (let i = 0; i < triggerIds.length; i++) {
                const trigger = this.get(triggerIds[i]);
                if (trigger) {
                    dom.dispatchEvent(new Event(trigger.event));
                }
            }
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
                return trigger;
            }
            return dataFns.parseVariable(trigger, data, null, [this.successFiledKey, this.failFiledKey, this.errorFiledKey]);
        }
    };

    const loadDataFns = {
        register: function (id, load, elementIds) {
            if (!id || !load || !load.id) {
                return;
            }

            loads[id] = { config: load, instances: elementIds };
        },
        loads: function (loadIds, data, failFn) {
            const loaded = [];
            for (let i = 0; i < loadIds.length; i++) {
                const loadId = loadIds[i];
                if (loadId && loaded.indexOf(loadId) === -1) {
                    this.load(loadId, data, failFn);
                    loaded.push(loadId);
                }
            }
        },
        load: function (loadId, data, failFn) {
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
                    if (config.url) {
                        const newHeaders = dataFns.parseVariable(config.headers, data);
                        const newParams = dataFns.parseVariable(config.params, data);
                        callFns.flow(config.url, config.mode, false, newParams, newHeaders, function (data) {
                            combineWebUI.loadData.loadSuccess(loadConfig, data, true);
                        }, failFn, null, config.localStorageKey);
                    }
                    break;
                case "API":
                    if (config.url) {
                        const newHeaders = dataFns.parseVariable(config.headers, data);
                        const newParams = dataFns.parseVariable(config.params, data);
                        callFns.url(config.url, config.mode, false, newParams, newHeaders, function (data) {
                            combineWebUI.loadData.loadSuccess(loadConfig, data, true);
                        }, failFn, null, config.localStorageKey);
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
                const instanceIds = instanceFns.getConfigIds(instances[i]);
                for (let j = 0; j < instanceIds.length; j++) {
                    instanceFns.refresh(instanceIds[j], data);
                }
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
            if (!load || !load.toGlobal) {
                return;
            }

            const loadId = load.id;
            loadGlobals[loadId] = newData;
        }
    };

    const instanceTempFns = {
        register: function (id, instanceTemp) {
            instanceTemps[id] = JSON.stringify(instanceTemp);;
        },
        get: function (id) {
            if (!id || !instanceTemps[id]) {
                return {};
            }
            return JSON.parse(instanceTemps[id]);
        }
    }

    const configFns = {
        init: function (instance) {
            const templateId = instance.templateId;
            const template = instance.template = instanceTempFns.get(templateId);

            if (!template.external) {
                template.external = {};
            }
            template.external.id = instance.id;

            let elementTemplate = tempFns.load(instance.elementTemplatePath);
            for (const key in elementTemplate) {
                if (Object.hasOwnProperty.call(elementTemplate, key)) {
                    let doms = instance.template[key] ? instance.template[key] : {};
                    const tempDom = elementTemplate[key];

                    if (doms instanceof Array) {
                        const newDoms = [];
                        for (let j = 0; j < newDoms.length; j++) {
                            newDoms.push(this.initElement(doms[j], tempDom));
                        }
                        template[key] = newDoms;
                    } else {
                        template[key] = this.initElement(doms, tempDom);
                    }
                }
            }
        },
        initElement: function (element, elementTemp) {
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
            if (subSettings.elements) {
                const itemsBody = [];
                for (let i = 0; i < subSettings.elements.length; i++) {
                    const itemConfig = subSettings.elements[i];
                    if (itemConfig) {
                        const buildResult = instanceFns.build(itemConfig, buildData);
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
        hasVariable: function (variable) {
            return !!variable.match(/#\{(.*?)}/g);
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

            const variables = text.match(/#\{(.*?)}/g);
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
                if (i === pathArr.length - 1) {
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
            if (first === "$c") {
                return constantFns.get();
            } else if (first === "$e") {
                const dataResult = instanceFns.getData(variablePath.shift());
                if (dataResult.success) {
                    return dataResult.data;
                } else {
                    console.error(dataResult.showMsg, dataResult.errorMsg);
                    return null;
                }
            } else if (first === "$ld") {
                return loadGlobals;
            } else if (first === "$ls") {
                const second = variablePath.shift();
                if (second) {
                    const localData = localStorage.getItem(second);
                    if (localData) {
                        return JSON.parse(localData);
                    }
                }
            }

            console.error("根据标识解析数据失败", "根据标识解析数据-未知的");
            return null;
        },
        hasDataFlag: function (variableText) {
            return variableText === "$c" || variableText === "$e"
                || variableText === "$ld" || variableText === "$ls";
        },
    };

    const callFns = {
        url: function (url, type, fromSubmit, params, headers, successFn, failFn, errorFn, localStorageKey) {
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
                        if (localStorageKey) {
                            localStorage.setItem(localStorageKey, JSON.stringify(xhr.response));
                        }
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
        flow: function (url, type, fromSubmit, params, headers, successFn, failFn, errorFn, localStorageKey) {
            params = params ? params : {};
            this.url(url, type, fromSubmit, params, headers,
                function (data) {
                    data = JSON.parse(data);
                    if (data.success) {
                        if (data.dataFlag) {
                            if (data.dataFlag === "redirect") {
                                toolFns.linkTo(data.data);
                                return;
                            }
                        }
                        if (successFn) {
                            successFn(data.data);
                            if (localStorageKey) {
                                localStorage.setItem(localStorageKey, JSON.stringify(data.data));
                            }
                        }
                    } else {
                        console.log("Request fail: ", flowKey, params, data);
                        failFn ? failFn(data) : function () {
                            alert("Request fail: " + data.showMsg + "！");
                        };
                    }
                }, errorFn, null);
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
            window.location.href = path.startsWith("http") ? path : baseUrl + path;
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
        constant: constantFns,
        group: groupFns,
        instance: instanceFns,
        instanceTemp: instanceTempFns,
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