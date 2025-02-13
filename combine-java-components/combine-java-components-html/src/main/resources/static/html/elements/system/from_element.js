$combine.element.register("SYSTEM.FROM", (function () {
    const domFns = $combine.dom;
    const instanceFns = $combine.instance;
    const configFns = $combine.config;
    const dataFns = $combine.data;

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
        return instance;
    }

    function buildFromBody(instance, buildData) {
        let body = [];

        buildData = buildData ? buildData : {};
        for (let i = 0; i < instance.items.length; i++) {
            const currItem = instance.items[i];
            const currKey = currItem.fieldKey;
            const fieldName = dataFns.parseVariable(currItem.fieldName, buildData);
            const hide = currItem.hide === true;

            const itemBodies = [];

            const leftBody = [fieldName];
            if (currItem.requiredFlag && currItem.requiredFlag === true) {
                leftBody.push(domFns.build(instance.template.requestFlag, instance.template.requestFlag.text))
            }
            const keyDom = domFns.build(instance.template.left, leftBody);
            itemBodies.push(keyDom);


            const rightBody = [];
            var contentDom = null;
            if (currItem.element) {
                const contentElementDom = buildElement(currItem.element, buildData);
                if (contentElementDom) {
                    setData(instance.id, currKey, null, currItem.element);
                }
                contentDom = domFns.build(instance.template.rightContent, contentElementDom);
            } else {
                const text = dataFns.parseVariable(currItem.text, buildData);
                setData(instance.id, currKey, text);
                contentDom = domFns.build(instance.template.rightContent, text);
            }
            rightBody.push(contentDom);

            if (currItem.desc) {
                const descDom = domFns.build(instance.template.rightDesc, dataFns.parseVariable(currItem.desc, buildData));
                descDom.id = instance.id + "-" + currKey + "-desc";
                if (currItem.showDesc === false) {
                    descDom.style.display = "none";
                }
                rightBody.push(descDom)
            }
            if (currItem.error) {
                const errorDom = domFns.build(instance.template.rightError, dataFns.parseVariable(currItem.error, buildData));
                errorDom.id = instance.id + "-" + currKey + "-error";
                if (currItem.showError !== true) {
                    errorDom.style.display = "none";
                }
                rightBody.push(errorDom)
            }
            itemBodies.push(domFns.build(instance.template.right, rightBody));


            const groupDom = domFns.build(instance.template.item, itemBodies)
            groupDom.setAttribute("id", dataFns.parseVariableText(currItem.id, buildData));
            if (instance.column !== -1) {
                groupDom.style.width =  Math.floor(100 / instance.column) + "%";
            }
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
    
    function switchRightText(id, key, opt, type) {
        const keys = [];
        if (key) {
            keys.push(key);
        } else {
            const currData = data[id];
            if (currData) {
                for (let i = 0; i < currData.length; i++) {
                    keys.push(currData[i].key);
                }
            }
        }

        for (let i = 0; i < keys.length; i++) {
            const currKey = keys[i];
            let domId = type === "desc" ? (id + "-" + currKey + "-desc") : (id + "-" + currKey + "-error");
            const dom = document.getElementById(domId);
            if (dom) {
                if (opt === "show") {
                    dom.style.removeProperty("display");
                } else {
                    dom.style.display = "none";
                }
            }
        }
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
        },
        call: {
            showDesc: function (instance, key) {
                switchRightText(instance.id, key, "show", "desc");
            },
            hideDesc: function (instance, key) {
                switchRightText(instance.id, key, "hide", "desc");
            },
            showError: function (instance, key) {
                switchRightText(instance.id, key, "show", "error");
            },
            hideError: function (instance, key) {
                switchRightText(instance.id, key, "hide", "error");
            }
        }
    }
})());