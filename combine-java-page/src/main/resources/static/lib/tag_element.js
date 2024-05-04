$combineWebUI.element.register("TAG", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildTags(config, buildData) {
        const body = [];
        const settings = config.settings;
        const itemConfig = config[settings.type];
        if (!buildData || !itemConfig) {
            return body;
        }

        data[config.id] = [];
        buildData = buildData instanceof Array ? buildData : [buildData];
        for (let i = 0; i < buildData.length; i++) {
            const currData = buildData[i];
            const text = dataFns.parseVariable(settings.text, currData);
            body.push(domFns.build(itemConfig, text));
            data[config.id].push(text);
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildTags(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTags(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());