$combineWebUI.element.register("BUTTON", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const configFns = $combineWebUI.config;
    const triggerFns = $combineWebUI.trigger;

    function init(config, parentData) {
        return config;
    }

    function buildButtons(config, buildData) {
        let body = [];
        const settings = config.settings;
        if (!settings.items || settings.items.length === 0) {
            return body;
        }

        for (let i = 0; i < settings.items.length; i++) {
            const item = settings.items[i];

            const itemTemp = config[item.type];
            if (itemTemp) {
                const text = dataFns.parseVariable(item.text, buildData);
                const itemConfig = configFns.initElement(config.button, itemTemp, buildData);
                const buttonDom = domFns.build(itemConfig, text);
                if (item.triggers) {
                    triggerFns.build(item.triggers, buttonDom, buildData);
                }
                body.push(buttonDom);
            }
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            const buttons = buildButtons(config, data);
            const externalDom = domFns.build(config.external, buttons);
            return externalDom
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let dom = document.getElementById(id);
            domFns.setBody(dom, buildButtons(config, parentData));
        },
        getData: function getData(id) {
            return null;
        }
    }
})());