$combineWebUI.element.register("FROM", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const configFns = $combineWebUI.config;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(instance, parentData) {
        const layout = instance.layout ? instance.layout.toUpperCase() : null;
        switch (layout) {
            case "VERTICAL":
                instance.template.from = configFns.initElement(instance.template.from, instance.template.vertical);
                break;
            case "INLINE":
                instance.template.from = configFns.initElement(instance.template.from, instance.template.inline);
                break;
            case "HORIZONTAL":
            default:
                instance.template.from = configFns.initElement(instance.template.from, instance.template.horizontal);
                break;
        }
        return config;
    }

    function buildFromBody(instance, buildData) {
        let body = [];
        buildData = buildData ? buildData : {};
        for (let i = 0; i < instance.items.length; i++) {
            const currItem = instance.items[i];
            const currKey = currItem.fieldKey;
            const hide = currItem.hide === true;

            const itemBodys = [];
            if (currItem.fieldName) {
                itemBodys.push(domFns.build(instance.template.label, currItem.fieldName));
            }
            if (currItem.element) {
                const contentElementDom = buildElement(currItem.element, buildData);
                if (contentElementDom) {
                    itemBodys.push(contentElementDom);
                    setData(instance.id, currKey, null, currItem.element.id);
                }
            } else {
                const text = dataFns.parseVariable(currItem.text, buildData);
                setData(instance.id, currKey, text);
                itemBodys.push(domFns.build(instance.template.content, text));
            }

            const groupDom = domFns.build(instance.template.item, itemBodys)
            groupDom.setAttribute("id", dataFns.parseVariableText(currItem.id, buildData));
            if (hide) {
                domFns.hide(groupDom);
            }

            body.push(groupDom);
        }

        return body;
    }

    function buildElement(elementId, data) {
        const result = instanceFns.build(elementId, data);
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
        build: function (instance, data) {
            instance = init(instance, data);

            let fromDom;
            if (instance.template.from) {
                const fromBody = buildFromBody(instance, data);
                fromDom = domFns.build(instance.template.from, fromBody);
            }

            return domFns.build(instance.template.external, fromDom);
        },
        refresh: function (id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom.children[0], buildFromBody(instance, parentData));
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