$combineWebUI.element.register("BREADCRUMB", (function () {
    const domFns = $combineWebUI.dom;
    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildBreadcrumb(instance, buildData) {
        const templateConfig = instance.template;
        if (!buildData) {
            data[id] = [];
            return domFns.build(templateConfig.breadcrumb, []);
        }

        const body = [];
        buildData = data[templateConfig.id] = buildData instanceof Array ? buildData : [buildData];
        for (let d = 0; d < buildData.length; d++) {
            body.push(domFns.build(templateConfig.item, buildData[d]));
        }

        return domFns.build(templateConfig.breadcrumb, body);
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildBreadcrumb(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildBreadcrumb(instance, parentData));
            }
        },
        getData: function getData(id) {
            return data[id];
        }
    }
})());