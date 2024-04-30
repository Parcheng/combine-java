$combineWebUI.element.register("FROM", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const configFns = $combineWebUI.config;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        const layout = config.layout && config.settings.layout ? config.settings.layout.toUpperCase() : null;
        switch (layout) {
            case "VERTICAL":
                config.from = configFns.initElement(config.from, config.vertical);
                break;
            case "INLINE":
                config.from = configFns.initElement(config.from, config.inline);
                break;
            case "HORIZONTAL":
            default:
                config.from = configFns.initElement(config.from, config.horizontal);
                break;
        }
        return config;
    }

    function buildFromBody(config, buildData) {
        let body = [];
        const settings = config.settings;
        if (!buildData || buildData instanceof Array || !(buildData instanceof Object)) {
            return;
        }

        for (let i = 0; i < settings.items.length; i++) {
            const currItem = settings.items[i];
            const currKey = currItem.fieldKey;

            const itemBodys = [];
            if (currItem.fieldName) {
                itemBodys.push(domFns.build(config.label, currItem.fieldName));
            }
            if (currItem.element) {
                const contentElementDom = buildElement(currItem.element, buildData);
                if (contentElementDom) {
                    itemBodys.push(contentElementDom);
                    setData(config.id, currKey, null, currItem.element.id);
                }
            } else {
                const text = dataFns.parseVariable(currItem.text, buildData);
                itemBodys.push(domFns.build(config.content, text));
                setData(config.id, currKey, text);
            }

            body.push(domFns.build(config.item, itemBodys));
        }

        return body;
    }

    function buildElement(elementConfig, data) {
        const result = instanceFns.registerAndBuild(elementConfig, data);
        if (result.success) {
            return result.data;
        }
        return null;
    }

    function setData(id, key, text, elementId) {
        let currData = data[id];
        if (!currData || !key) {
            currData = data[id] = [];
        }

        const currDataItem = { "key": typeof key === "number" ? ("$" + key) : key };
        if (text) {
            currDataItem.value = text;
        } else if (elementId) {
            currDataItem.elementId = elementId;
        }

        currData.push(currDataItem);
    }

    return {
        build: function (config, data) {
            config = init(config, data);

            let fromDom;
            const formTemp = config[config.settings.layout];
            if (config.from) {
                const fromBody = buildFromBody(config, data);
                const formConfig = configFns.initElement(config.from, formTemp, data);
                fromDom = domFns.build(formConfig, fromBody);
            }

            return domFns.build(config.external, fromDom);
        },
        refresh: function (id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom.children[0], buildFromBody(config, parentData));
            }
        },
        getData: function (id) {
            const result = {};
            const currData = data[id];
            if (!currData) {
                return result;
            }

            for (let i = 0; i < currData.length; i++) {
                let dataItem = currData[i];
                if (!dataItem.key) {
                    continue;
                }

                let itemValue = null;
                if (dataItem.value) {
                    itemValue = dataItem.value;
                } else if (dataItem.elementId) {
                    const elementDataResult = instanceFns.getData(dataItem.elementId);
                    if (elementDataResult.success) {
                        itemValue = elementDataResult.data;
                    }
                }

                result[dataItem.key] = itemValue;
            }

            return result;
        }
    }
})());