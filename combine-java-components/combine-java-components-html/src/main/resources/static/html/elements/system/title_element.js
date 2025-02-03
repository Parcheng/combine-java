$combine.element.register("SYSTEM.TITLE", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildTitle(instance, buildData) {
        const body = [];
        if (!instance) {
            return body;
        }

        if (instance.top) {
            body.push(domFns.build(instance.template.top, null));
        }

        const itemConfig = instance.template["h" + instance.level];
        if (itemConfig) {
            const text = dataFns.parseVariable(instance.text, buildData);
            body.push(domFns.build(itemConfig, text));
        }

        if (instance.bottom) {
            body.push(domFns.build(instance.template.bottom, null));
        }

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildTitle(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildTitle(instance, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        }
    }
})());