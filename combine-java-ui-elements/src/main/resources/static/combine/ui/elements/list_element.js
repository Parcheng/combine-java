$combineWebUI.element.register("SYSTEM.LIST", (function () {
    const domFns = $combineWebUI.dom;
    const configFns = $combineWebUI.config;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildList(instance, buildData) {
        const body = [];

        if (buildData) {
            data[instance.id] = [];
            buildData = buildData instanceof Array ? buildData : [buildData];
            for (let d = 0; d < buildData.length; d++) {
                body.push(configFns.buildSubElement(instance.content, instance.template.item, buildData[d], data[instance.id]));
            }
        }

        if (body.length == 0 && instance.defaultText) {
            body.push(domFns.build(instance.template.defaultText, instance.defaultText));
        }

        return domFns.build(instance.template.list, body);
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildList(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildList(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());