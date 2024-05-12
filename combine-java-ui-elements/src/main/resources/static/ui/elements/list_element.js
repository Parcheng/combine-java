$combineWebUI.element.register("LIST", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const configFns = $combineWebUI.config;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildList(config, buildData) {
        const body = [];
        const settings = config.settings;

        if (buildData) {
            data[config.id] = [];
            buildData = buildData instanceof Array ? buildData : [buildData];
            for (let d = 0; d < buildData.length; d++) {
                body.push(configFns.buildSubElement(settings.content, config.item, buildData[d], data[config.id]));
            }
        }

        if (body.length == 0 && settings.defaultText) {
            body.push(domFns.build(config.defaultText, settings.defaultText));
        }

        return domFns.build(config.list, body);
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildList(config, data));
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