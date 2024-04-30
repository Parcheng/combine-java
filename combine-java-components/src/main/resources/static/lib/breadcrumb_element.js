$combineWebUI.element.register("BREADCRUMB", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(config, parentData) {
        return config;
    }

    function buildBreadcrumb(config, buildData) {
        if (!buildData) {
            data[id] = [];
            return domFns.build(config.breadcrumb, []);
        }

        const body = [];
        buildData = data[config.id] = buildData instanceof Array ? buildData : [buildData];
        for (let d = 0; d < buildData.length; d++) {
            body.push(domFns.build(config.item, buildData[d]));
        }

        return domFns.build(config.breadcrumb, body);
    }

    return {
        build: function build(config, data) {
            config = init(config, data);
            return domFns.build(config.external, buildBreadcrumb(config, data));
        },
        refresh: function refresh(id, config, parentData) {
            config = init(config, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildBreadcrumb(config, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());