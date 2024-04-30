$combineWebUI.element.register("FROM", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const configFns = $combineWebUI.config;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        const layout = config.settings.layout ? config.settings.layout.toUpperCase() : null;
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
        buildData = buildData ? buildData : {};
        for (let i = 0; i < settings.items.length; i++) {
            const currItem = settings.items[i];
            const currKey = currItem.fieldKey;
            const hide = currItem.hide === true;

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
                setData(config.id, currKey, text);
                itemBodys.push(domFns.build(config.content, text));
            }

            const groupDom = domFns.build(config.item, itemBodys)
            groupDom.setAttribute("id", dataFns.parseVariableText(currItem.id, buildData));
            if (hide) {
                domFns.hide(groupDom);
            }

            body.push(groupDom);
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
        if (!key) {
            return;
        }

        let currData = data[id];
        if (!currData) {
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
            if (config.from) {
                const fromBody = buildFromBody(config, data);
                fromDom = domFns.build(config.from, fromBody);
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
                if (dataItem.elementId) {
                    const elementDataResult = instanceFns.getData(dataItem.elementId);
                    if (elementDataResult.success) {
                        itemValue = elementDataResult.data;
                    }
                } else if (dataItem.value){
                    itemValue = dataItem.value;
                }

                result[dataItem.key] = itemValue;
            }

            return result;
        }
    }
})());