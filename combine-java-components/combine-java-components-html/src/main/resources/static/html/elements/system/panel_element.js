$combine.element.register("SYSTEM.PANEL", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;
    const configFns = $combine.config;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function build(instance, buildData) {
        let body = [];

        if (instance.title) {
            const title = dataFns.parseVariable(instance.title, buildData);
            body.push(domFns.build(instance.template.title, title));
        }

        const bodyDom = configFns.buildSubElement(instance.body, instance.template.body, buildData);
        body.push(bodyDom);

        return body;
    }

    return {
        build: function (instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, build(instance, data));
        },
        refresh: function (id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, build(instance, parentData));
            }
        },
        getData: function (id) {
            return null;
        }
    }
})());