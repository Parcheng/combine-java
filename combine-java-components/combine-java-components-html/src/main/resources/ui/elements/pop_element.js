$combine.element.register("SYSTEM.POP", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const elementFns = $combine.element;
    const toolFns = $combine.tools;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildPops(instance, buildData) {
        const body = [];
        if (!buildData) {
            return body;
        }

        data[instance.id] = [];
        buildData = buildData instanceof Array ? buildData : [buildData];
        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const popType = dataFns.parseVariable(instance.popType, currData);
            const itemConfig = instance.template[popType];
            if (!buildData || !itemConfig) {
                return body;
            }

            const id = toolFns.generateUUID();
            const text = dataFns.parseVariable(instance.text, currData);
            const contentDom = domFns.build(instance.template.content, text);
            let closeDom = null;
            if (instance.hasClose) {
                closeDom = domFns.build(instance.template.close, instance.template.close.text)
                domFns.appendProtity(closeDom, "onclick", elementFns.buildCallFnCode(instance.id, "close", id));
            }

            const itemDom = domFns.build(itemConfig, closeDom ? [closeDom, contentDom] : contentDom);
            itemDom.id = id;
            if (instance.size && instance.size > 0) {
                itemDom.style.width = instance.size + "px";
            }

            body.push(itemDom);
            data[instance.id].push(text);
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildPops(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildPops(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            close: function (instance, closeDomId) {
                const dom = document.getElementById(closeDomId);
                if (dom) {
                    dom.remove();
                }
            }
        }
    }
})());