$combineWebUI.element.register("WINDOW", (function () {
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;
    const configFns = $combineWebUI.config;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildHead(config, buildData) {
        let body = [];
        const settings = config.settings;

        const title = dataFns.parseVariable(settings.title, buildData);
        body.push(domFns.build(config.headTitle, title ? title : config.headTitle.text));

        const colseDom = domFns.build(config.headClose, config.headClose.text)
        domFns.appendProtity(colseDom, "onclick", elementFns.buildCallFnCode(config.id, "close", "'" + config.external.id + "'"));
        body.push(colseDom);

        return domFns.build(config.head, body);
    }

    function buildContent(config, buildData) {
        const settings = config.settings;
        return configFns.buildSubElement(settings.body, config.body, buildData);
    }

    return {
        build: function build(config, data) {
            config = init(config, data);

            const headBody = buildHead(config, data);
            const contentBody = buildContent(config, data);
            const windowBody = domFns.build(config.window, [headBody, contentBody]);

            const externalDom = domFns.build(config.external, windowBody);
            externalDom.style.display = "none";
            return externalDom;
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);

            if (data.elements) {
                for (let i = 0; i < config.body.elements.length; i++) {
                    instanceFns.refresh(config.body.elements[i].id, parentData);
                }
            } else if (data.text) {
                let externalDom = document.getElementById(id);
                if (externalDom) {
                    domFns.setBody(externalDom.children[1], data.text);
                }
            }
        },
        getData: function getData(id) {
            return null;
        },
        call: {
            close: function (config, domId) {
                domFns.hide(domId);
            }
        }
    }
})());