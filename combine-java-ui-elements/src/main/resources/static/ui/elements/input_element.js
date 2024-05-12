$combineWebUI.element.register("INPUT", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;

    function init(config, parentData) {
        return config;
    }

    function buildControls(config, buildData) {
        let body = [];
        const settings = config.settings;

        if (settings.beforeText) {
            body.push(buildAddon(config, settings.beforeText, buildData));
        }

        const type = settings.type ? settings.type.toLowerCase() : "text";
        body.push(buildInput(config, type, settings, buildData));

        if (settings.afterText) {
            body.push(buildAddon(config, settings.afterText, buildData));
        }

        return body;
    }


    function buildInput(config, type, settings, buildData) {
        const inputDom = domFns.build(config.input, null);
        inputDom.type = type;

        const key = dataFns.parseVariableText(settings.key, buildData);
        if (key) {
            inputDom.name = key;
        }
        if (settings.value) {
            const val = dataFns.parseVariable(settings.value, buildData);
            if (val) {
                inputDom.value = val;
            }
        }
        return inputDom;
    }


    function buildAddon(config, text, buildData) {
        const value = dataFns.parseVariable(text, buildData);
        const addonDom = domFns.build(config.addon, value);
        return addonDom;
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
            if (externalDom && externalDom.children) {
                for (let i = 0; i < externalDom.children.length; i++) {
                    const inputDom = externalDom.children[i];
                    if (inputDom.tagName == 'INPUT') {
                        if (inputDom.getAttribute("type") == "file") {
                            return inputDom.files[0];
                        } else {
                            return inputDom.value;
                        }
                    } else {
                        return inputDom.innerText;
                    }
                }
            }
            return null;
        }
    }
})());