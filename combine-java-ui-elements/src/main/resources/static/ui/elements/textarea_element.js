$combineWebUI.element.register("TEXTAREA", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    function init(config, parentData) {
        return config;
    }

    function buildControls(config, buildData) {
        const settings = config.settings;
        return buildTextarea(config, settings, buildData);
    }

    function buildTextarea(config, item, buildData) {
        const key = dataFns.parseVariableText(item.key, buildData);
        const value = dataFns.parseVariable(item.value, buildData);
        const textDom = domFns.build(config.textarea, value);
        if (key) {
            textDom.name = key;
        }
        return textDom;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            const buttons = buildControls(config, data);
            const externalDom = domFns.build(config.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildControls(config, parentData));
        },
        getData: function getData(id) {
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const valueDom = externalDom.children[0];
                return valueDom.value;
            }
            return null;
        }
    }
})());