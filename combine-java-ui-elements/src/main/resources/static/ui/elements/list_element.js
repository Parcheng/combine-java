$combineWebUI.element.register("LIST", (function () {
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
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(instance.template.external, buildList(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildList(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());