$combineWebUI.element.register("LINE", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildLine(config, buildData) {
        const body = [];
        const settings = config.settings;

        if (settings.text) {
            body.push(domFns.build(config.line));
            const text = dataFns.parseVariable(settings.text, buildData);
            body.push(domFns.build(config.text, text));
        }
        body.push(domFns.build(config.line));

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildLine(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildLine(config, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        }
    }
})());