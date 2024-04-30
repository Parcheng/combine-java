$combineWebUI.element.register("POP", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;
    const elementFns = $combineWebUI.element;
    const toolFns = $combineWebUI.tools;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildPops(config, buildData) {
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
            const id = toolFns.generateUUID();
            const text = dataFns.parseVariable(settings.text, currData);
            const contentDom = domFns.build(config.content, text);
            const closeDom = null;
            if (settings.hasClose) {
                closeDom = domFns.build(config.close, config.close.text)
                domFns.appendProtity(closeDom, "onclick", elementFns.buildCallFnCode(config.id, "close", id));
            }

            const itemDom = domFns.build(itemConfig, closeDom ? [closeDom, contentDom] : contentDom);
            itemDom.id = id;
            body.push(itemDom);
            data[config.id].push(text);
        }

        return body;
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildPops(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildPops(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        },
        call: {
            close: function (config, closeDomId) {
                const dom = document.getElementById(closeDomId);
                if (dom) {
                    dom.remove();
                }
            }
        }
    }
})());