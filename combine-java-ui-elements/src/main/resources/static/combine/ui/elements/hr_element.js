$combine.element.register("SYSTEM.HR", (function () {
    const domFns = $combine.dom;
    const dataFns = $combine.data;

    const data = {};

    function init(instance, parentData) {
        return instance;
    }

    function buildLine(instance, buildData) {
        const body = [];

        const count = instance.count ? instance.count : 1;
        for (let i = 0; i < count; i++) {
            body.push(domFns.build(instance.template.hr));
        }

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