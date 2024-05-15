$combineWebUI.element.register("SYSTEM.LINE", (function () {
    const domFns = $combineWebUI.dom;
    const dataFns = $combineWebUI.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildLine(instance, buildData) {
        const body = [];

        if (instance.text) {
            body.push(domFns.build(instance.template.line));
            const text = dataFns.parseVariable(instance.text, buildData);
            body.push(domFns.build(instance.template.text, text));
        }
        body.push(domFns.build(instance.template.line));

        return body;
    }

    return {
        build: function build(instance, data) {
            instance = init(instance, data);
            return domFns.build(instance.template.external, buildLine(instance, data));
        },
        refresh: function refresh(id, instance, parentData) {
            instance = init(instance, parentData);
            let externalDom = document.getElementById(id);
            if (externalDom) {
                domFns.setBody(externalDom, buildLine(instance, parentData));
            }
        },
        getData: function getData(id) {
            return null;
        }
    }
})());