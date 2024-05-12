$combineWebUI.element.register("WINDOW", (function () {
    const windowsDomId = "$combine-web-windows";
    const domFns = $combineWebUI.dom;
    const instanceFns = $combineWebUI.instance;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;
    const configFns = $combineWebUI.config;
    const triggerFns = $combineWebUI.trigger;

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
        domFns.appendProtity(colseDom, "onclick", elementFns.buildCallFnCode(config.id, "close", null));
        if (settings.closeTriggers) {
            triggerFns.build(settings.closeTriggers, colseDom, buildData);
        }

        body.push(colseDom);

        return domFns.build(config.head, body);
    }

    function buildContent(config, buildData) {
        const settings = config.settings;
        return configFns.buildSubElement(settings.body, config.body, buildData);
    }

    function refreshBody(id, config, data) {
        config = init(config, data);

        if (config.settings?.body?.elements) {
            for (let i = 0; i < config.settings.body.elements?.length; i++) {
                instanceFns.refresh(config.settings.body.elements[i].id, data);``
            }
        } else if (config.settings?.body?.text) {
            let externalDom = document.getElementById(id);
            if (externalDom) {
                const text = dataFns.parseVariableText(config.settings.body.text, data);
                domFns.setBody(externalDom.children[0].children[1], text);
            }
        }
    }

    function initWindowsDom() {
        let windowsDom = document.getElementById(windowsDomId);
        if (!windowsDom) {
            windowsDom = document.createElement("div");
            windowsDom.setAttribute("id", windowsDomId);
            document.body.appendChild(windowsDom);
        }
        return windowsDom;
    }

    return {
        build: function (config, data) {
            config = init(config, data);
            const windowsDom = initWindowsDom();

            const headBody = buildHead(config, data);
            const contentBody = buildContent(config, data);
            const windowBody = domFns.build(config.window, [headBody, contentBody]);

            const externalDom = domFns.build(config.external, windowBody);
            externalDom.style.display = "none";

            windowsDom.appendChild(externalDom);
            return null;
        },
        refresh: function (id, config, parentData) {
            refreshBody(id, config, parentData);
        },
        getData: function (id) {
            return null;
        },
        call: {
            open: function (config, data) {
                if (data) {
                    refreshBody(config.id, config, data);
                }
                domFns.show(config.id);
            },
            close: function (config, data) {
                if (data) {
                    refreshBody(config.id, config, data);
                }
                domFns.hide(config.id);
            }
        }
    }
})());