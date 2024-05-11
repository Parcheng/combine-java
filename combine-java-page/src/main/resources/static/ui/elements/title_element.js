$combineWebUI.element.register("TITLE", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildTitle(config, buildData) {
        const body = [];
        const settings = config.settings;
        if (!settings) {
            return body;
        }

        if (settings.top) {
            body.push(domFns.build(config.top, null));
        }

        const itemConfig = config["h" + settings.level];
        if (itemConfig) {
            const text = dataFns.parseVariable(settings.text, buildData);
            body.push(domFns.build(itemConfig, text));
        }

        if (settings.bottom) {
            body.push(domFns.build(config.bottom, null));
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildTitle(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTitle(config, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        }
    }
})());